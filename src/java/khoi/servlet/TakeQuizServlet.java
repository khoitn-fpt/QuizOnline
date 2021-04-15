/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoi.dao.QuestionChoiceDAO;
import khoi.dao.QuestionDAO;
import khoi.dto.QuestionChoiceDTO;
import khoi.dto.QuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Nguyen Khoi
 */
public class TakeQuizServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String numOfQuestionString = request.getParameter("numOfQuestion");
        String subject = request.getParameter("subjectID");
        String url = "studentPage";
        try {
            QuestionDAO dao = new QuestionDAO();
            if (!numOfQuestionString.trim().equals("")) {
                int numOfQuestion = Integer.parseInt(numOfQuestionString);
                if (dao.GetNumberOfQuestion(subject) >= numOfQuestion) {
                    dao.GetQuiz(numOfQuestion, subject);
                    List<QuestionDTO> result = dao.getListQuiz();
                    QuestionChoiceDAO cdao = new QuestionChoiceDAO();
                    for (QuestionDTO qdto : result) {
                        cdao.GetQuizChoice(qdto.getQuestionID());
                    }
                    List<QuestionChoiceDTO> ListQuizChoice = cdao.getListQuizChoice();
                    HttpSession session = request.getSession();
                    session.setAttribute("ListQuiz", result);
                    session.setAttribute("ListQuizChoice", ListQuizChoice);
                    url = "takeQuiz";
                } else {
                    request.setAttribute("NotEnoughQuestion", "Not Enough Question");
                    url = "studentPage";
                }
            } else {
                request.setAttribute("error", "Number of question: 10 to 40");
                url = "studentPage";
            }

        } catch (NamingException | SQLException | NumberFormatException e) {
            Logger.getLogger(TakeQuizServlet.class).error("Error at TakeQuizServlet " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

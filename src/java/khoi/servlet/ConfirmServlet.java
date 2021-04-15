/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ConfirmServlet extends HttpServlet {

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
        String url = "updatePage";
        String questionName = request.getParameter("questionName");
        String choice1 = request.getParameter("choice1");
        String choice2 = request.getParameter("choice2");
        String choice3 = request.getParameter("choice3");
        String choice4 = request.getParameter("choice4");
        boolean isError = false;
        if (questionName.trim().isEmpty()) {
            request.setAttribute("name", "Question not empty,");
            isError = true;
        }
        if (choice1.trim().isEmpty()) {
            request.setAttribute("choice1", "Choice 1 not empty, ");
            isError = true;
        }
        if (choice2.trim().isEmpty()) {
            request.setAttribute("choice2", "Choice 2 not empty, ");
            isError = true;
        }
        if (choice3.trim().isEmpty()) {
            request.setAttribute("choice3", "Choice 3 not empty, ");
            isError = true;
        }
        if (choice4.trim().isEmpty()) {
            request.setAttribute("choice4", "Choice 4 not empty, ");
            isError = true;
        }
        String searchValue = request.getParameter("txtSearchValueByQuestionName");
        String subjectID = request.getParameter("subjectIDUpdate");
        String subjectID1 = request.getParameter("subject");
        String statusString = request.getParameter("chkstatus");
        String status1 = request.getParameter("status");
        int choiceID1 = Integer.parseInt(request.getParameter("choiceID1"));
        int choiceID2 = Integer.parseInt(request.getParameter("choiceID2"));
        int choiceID3 = Integer.parseInt(request.getParameter("choiceID3"));
        int choiceID4 = Integer.parseInt(request.getParameter("choiceID4"));
        String indexString = request.getParameter("index");
        String questionIDString = request.getParameter("questionID");
        int questionID = Integer.parseInt(questionIDString);
        int index = Integer.parseInt(indexString);
        boolean status;
        if (statusString == null) {
            status = false;
        } else {
            status = true;
        }
        List<QuestionChoiceDTO> listChoice = null;
        try {
            String btAction = request.getParameter("btAction");
            if ("Save".equals(btAction)) {
                QuestionDTO dto = new QuestionDTO(questionID, questionName, subjectID, status);
                if (listChoice == null) {
                    listChoice = new ArrayList<>();
                }
                QuestionChoiceDTO cdto = new QuestionChoiceDTO(choiceID1, questionID, true, choice1);
                listChoice.add(cdto);
                cdto = new QuestionChoiceDTO(choiceID2, questionID, false, choice2);
                listChoice.add(cdto);
                cdto = new QuestionChoiceDTO(choiceID3, questionID, false, choice3);
                listChoice.add(cdto);
                cdto = new QuestionChoiceDTO(choiceID4, questionID, false, choice4);
                listChoice.add(cdto);

                HttpSession session = request.getSession();
                session.setAttribute("question", dto);
                session.setAttribute("result", listChoice);
                if (isError) {
                    url = "updatePage";
                } else {

                    QuestionChoiceDAO cdao = new QuestionChoiceDAO();
                    QuestionDAO dao = new QuestionDAO();
                    boolean u = dao.UpdateQuestion(questionName, subjectID, status, questionID);
                    if (u) {
                        for (QuestionChoiceDTO qc : listChoice) {
                            cdao.updateQuestionChoice(qc.getChoiceID(), qc.getQuestionID(), qc.isIsRight(), qc.getAnswer());
                        }
                    }
                    url = "search?txtSearchValueByQuestionName=" + searchValue + "&index=" + index + "&subject=" + subjectID1 + "&status=" + status1;
                }
            } else {
                url = "search?txtSearchValueByQuestionName=" + searchValue + "&index=" + index + "&subject=" + subjectID1 + "&status=" + status1;
            }
        } catch (NamingException | SQLException | NumberFormatException e) {
            Logger.getLogger(ConfirmServlet.class).error("Error at ConfirmServlet " + e.toString());
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

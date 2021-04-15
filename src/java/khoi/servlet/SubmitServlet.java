/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoi.dao.HistoryDAO;
import khoi.dao.HistoryDetailDAO;
import khoi.dto.QuestionChoiceDTO;
import khoi.dto.QuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Nguyen Khoi
 */
public class SubmitServlet extends HttpServlet {

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
        int num = Integer.parseInt(request.getParameter("numOfQuestion"));
        String subjectID = request.getParameter("subjectID");
        String url = "studentPage";
        try {
            HttpSession session = request.getSession();
            List<QuestionDTO> result = (List<QuestionDTO>) session.getAttribute("ListQuiz");
            List<QuestionChoiceDTO> listQuestionChoice = (List<QuestionChoiceDTO>) session.getAttribute("ListQuizChoice");

            int count = 0;
            for (QuestionDTO dto : result) {
                for (QuestionChoiceDTO questionChoiceDTO : listQuestionChoice) {
                    if (questionChoiceDTO.getChoiceID() == Integer.parseInt(request.getParameter(String.valueOf(dto.getQuestionID())))) {
                        if (questionChoiceDTO.isIsRight() == true) {
                            count++;
                        }
                    }
                }
            }
            String email = (String) session.getAttribute("EMAIL");
            long time = System.currentTimeMillis();
            Date date = new Date(time);
            float scorce = 10 / num;
            float mark = scorce * count;
            String numOfCorrect = count + "/" + num;
            HistoryDAO dao = new HistoryDAO();
            boolean isSave = dao.CreateHistory(email, subjectID, date, mark, numOfCorrect);
            if (isSave) {
                HistoryDetailDAO hdao = new HistoryDetailDAO();
                int historyNum = dao.getHistoryID();
                String userChoice = "";
                String isRightChoice = "";
                for (QuestionDTO tblQuestionDTO : result) {
                    for (QuestionChoiceDTO questionChoiceDTO1 : listQuestionChoice) {
                        if (tblQuestionDTO.getQuestionID() == questionChoiceDTO1.getQuestionID()) {
                            if (questionChoiceDTO1.getChoiceID() == Integer.parseInt(request.getParameter(String.valueOf(tblQuestionDTO.getQuestionID())))) {
                                userChoice = questionChoiceDTO1.getAnswer();
                            }
                            if (questionChoiceDTO1.isIsRight()) {
                                isRightChoice = questionChoiceDTO1.getAnswer();
                                boolean rs = hdao.CreateHistoryDetail(historyNum, tblQuestionDTO.getQuestionName(), userChoice, isRightChoice);
                                if (rs == false) {
                                    return;
                                }
                            }

                        }
                    }
                }

                request.setAttribute("date", date.toString());
                request.setAttribute("email", email);
                request.setAttribute("mark", mark);
                request.setAttribute("numOfCorrect", numOfCorrect);
                request.setAttribute("subjectID", subjectID);
                url = "viewResult";
            }
        } catch (SQLException | NamingException e) {
            Logger.getLogger(SubmitServlet.class).error("Error at SubmitServlet " + e.toString());
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

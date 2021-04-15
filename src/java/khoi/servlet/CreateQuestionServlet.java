/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoi.dao.QuestionChoiceDAO;
import khoi.dao.QuestionDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author Nguyen Khoi
 */
public class CreateQuestionServlet extends HttpServlet {

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
        String url = "createPage";
        String subject = request.getParameter("subject");
        String questionName = request.getParameter("questionName");
        String choice1 = request.getParameter("choice1");
        String choice2 = request.getParameter("choice2");
        String choice3 = request.getParameter("choice3");
        String choice4 = request.getParameter("choice4");

        //nay de ve trang seach
        String searchValue = request.getParameter("txtSearchValueByQuestionName");
        String subject1 = request.getParameter("subject1");
        String status = request.getParameter("status");
        String index = request.getParameter("index");
        boolean isError = false;
        if (questionName.trim().isEmpty() || questionName.length() > 500) {
            request.setAttribute("name", "Not empty");
            isError = true;
        }
        if (choice1.trim().isEmpty() || choice1.length() > 500) {
            request.setAttribute("choice1", "Not empty");
            isError = true;
        }
        if (choice2.trim().isEmpty() || choice2.length() > 500) {
            request.setAttribute("choice2", "Not empty");
            isError = true;
        }
        if (choice3.trim().isEmpty() || choice3.length() > 500) {
            request.setAttribute("choice3", "Not empty");
            isError = true;
        }
        if (choice4.trim().isEmpty() || choice4.length() > 500) {
            request.setAttribute("choice4", "Not empty");
            isError = true;
        }
        try {
            if (isError == true) {
                url = "createPage?txtSearchValueByQuestionName=" + searchValue + "&index=" + index + "&subject=" + subject1 + "&status=" + status;
            } else {
                QuestionChoiceDAO qdao = new QuestionChoiceDAO();
                QuestionDAO dao = new QuestionDAO();
                long time = System.currentTimeMillis();
                java.sql.Date createDate = new java.sql.Date(time);
                dao.CreateQuestion(questionName, subject, createDate, true);
                int questionID = dao.getQuestionID();
                qdao.createQuestionChoice(questionID, true, choice1);
                qdao.createQuestionChoice(questionID, false, choice2);
                qdao.createQuestionChoice(questionID, false, choice3);
                qdao.createQuestionChoice(questionID, false, choice4);

                if (searchValue.equals("") & subject1.equals("") & status.equals("") & index.equals("")) {
                    url = "adminPage";
                } else {
                    url = "search?txtSearchValueByQuestionName=" + searchValue + "&index=" + index + "&subject=" + subject1 + "&status=" + status;
                }
            }

        } catch (SQLException | NamingException e) {
            Logger.getLogger(CreateQuestionServlet.class).error("Error at CreateQuestionServlet " + e.toString());
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

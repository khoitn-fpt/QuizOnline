/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoi.dao.QuestionChoiceDAO;
import khoi.dao.QuestionDAO;
import khoi.dto.QuestionChoiceDTO;
import khoi.dto.QuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Nguyen Khoi
 */
public class SearchQuestionServlet extends HttpServlet {

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
        String url = "adminPage";
        String searchValue = request.getParameter("txtSearchValueByQuestionName");
        String subjectID = request.getParameter("subject");
        String statusString = request.getParameter("status");
        String indexString = request.getParameter("index");
        if (searchValue.equals("") & subjectID.equals("") & statusString.equals("") & indexString.equals("")) {
            response.sendRedirect("adminPage");
            return;
        }
        int index = Integer.parseInt(indexString);
        boolean status = false;
        if (statusString.equals("true")) {
            status = true;
        }
        try {

            QuestionDAO dao = new QuestionDAO();
            QuestionChoiceDAO cdao = new QuestionChoiceDAO();
            dao.searchQuestion(searchValue, subjectID, status, index);
            List<QuestionDTO> result = dao.getListQuestionn();
            List<QuestionChoiceDTO> listChoice = null;
            if (result != null) {
                for (QuestionDTO questionDTO : result) {
                    cdao.getQuestionChoice(questionDTO.getQuestionID());
                }
                listChoice = cdao.getListChoice();
            } 
            int count = dao.count(searchValue, subjectID, status);
            int endPage = 0;
            endPage = count / 20;
            if (count % 20 != 0) {
                endPage++;
            }
            request.setAttribute("ListSearch", result);
            request.setAttribute("ListChoice", listChoice);
            request.setAttribute("subjectID", subjectID);
            request.setAttribute("Status", status);
            request.setAttribute("end", endPage);

        } catch (SQLException | NamingException e) {
            Logger.getLogger(SearchQuestionServlet.class).error("Error at SearchQuestionServlet " + e.toString());
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

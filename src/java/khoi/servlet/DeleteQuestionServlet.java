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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoi.dao.QuestionDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author Nguyen Khoi
 */
public class DeleteQuestionServlet extends HttpServlet {

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
        String searchValue = request.getParameter("txtSearchValueByQuestionName");
        String questionIDString = request.getParameter("questionID");
        int questionID = Integer.parseInt(questionIDString);
        String subjectID = request.getParameter("subject");
        String statusString = request.getParameter("status");
        String indexString = request.getParameter("index");
        int index = Integer.parseInt(indexString);
        boolean status = false;
        if (statusString.equals("true")) {
            status = true;
        }
        String url = "search?txtSearchValueByQuestionName="+searchValue+"&index="+index+"&subject="+subjectID+"&status="+status;
        try{
            QuestionDAO dao = new QuestionDAO();
            boolean result = dao.DeleteQuestion(questionID);
            if(result){
                request.setAttribute("deleteStatus", "Delete Success");
            }else{
                request.setAttribute("deleteStatus", "Delete fail");
            }
        }catch(SQLException | NamingException | NumberFormatException e){
            Logger.getLogger(DeleteQuestionServlet.class).error("Error at DeleteQuestionServlet " + e.toString());
        }
        finally{
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoi.dao.UserDAO;
import khoi.encryp.SHA_256;
import org.apache.log4j.Logger;

/**
 *
 * @author Nguyen Khoi
 */
public class RegistrationServlet extends HttpServlet {

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
        String url = "registerErrorPage";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        String fullname = request.getParameter("fullname");
        try {
            boolean regisError = false;
            if (email.trim().isEmpty()) {
                request.setAttribute("emailError", "Not Empty");
                regisError = true;
            }else if(!email.matches("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")){
                request.setAttribute("emailError", "Email invalid!");
                regisError = true;
            }
            if (password.trim().isEmpty()) {
                request.setAttribute("passwordError", "Not Empty");
                regisError = true;
            }
            if (!passwordConfirm.trim().equals(password.trim())) {
                request.setAttribute("passwordConfirmError", "Confirm must match password");
                regisError = true;
            }
            if (fullname.trim().isEmpty()) {
                request.setAttribute("fullnameError", "Not Empty");
                regisError = true;
            }
            if (regisError == false) {
                url = "registerErrorPage";
            } else {
                UserDAO dao = new UserDAO();
                SHA_256 encry = new SHA_256();
                String passwordEncry = encry.toHexString(encry.getSHA(password));
                dao.createAccount(email, passwordEncry, fullname, "2", true);
                url = "loginPage";
            }

        } catch (SQLException e) {
            log(e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                request.setAttribute("accountError", "email exits");
            }
        } catch (NamingException | NoSuchAlgorithmException e) {
            Logger.getLogger(RegistrationServlet.class).error("Error at RegistrationServlet " + e.toString());
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

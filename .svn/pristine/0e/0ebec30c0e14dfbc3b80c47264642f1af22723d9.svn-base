/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crmdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author TempRDP2
 */

public class RegisterAgreementServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String answer = FAILURE;
        Validator validator = new Validator();
        HttpSession httpSession = request.getSession();
        Session hibSession = HibernateUtil.getSession();
        hibSession.beginTransaction();
        User user = (User) httpSession.getAttribute("user");
        int client_id = Integer.parseInt(request.getParameter("agreementsClient"));
        List clients = hibSession.createCriteria(Client.class)
                .add(Restrictions.eq("client_id", client_id))
                .add(Restrictions.eq("user", user))
                .list();
        if (clients.size() == 1 && user != null) {
            Client client = (Client) clients.get(0);
            Agreement agreement = validator.validateAgreement(request, user, client);
            if (agreement != null) {
                try {
                    String agr_number = agreement.getAgr_number();
                    List agreements = hibSession.createCriteria(Agreement.class)
                        .add(Restrictions.eq("agr_number", agr_number))
                        .add(Restrictions.eq("user", user))                            
                        .list();
                    if (agreements.size() == 0) {
                        answer = SUCCESS;
                        agreement.setAgr_state(2);
                        hibSession.save(agreement);
                        answer = SUCCESS;
                    }
                } catch (Exception ex) {
                    answer = FAILURE;
                }
            }
        }
        hibSession.beginTransaction().commit();
        hibSession.close();        
        response.getWriter().write(answer);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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

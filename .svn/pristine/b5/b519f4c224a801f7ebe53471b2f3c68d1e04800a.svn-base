/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crmdb;

import java.io.IOException;
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
public class DetachUserPaymentServlet extends HttpServlet {

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
    private final static String FAILURE = "failure";
    private final static String SUCCESS = "success";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String answer = FAILURE;
        HttpSession httpSession = request.getSession();
        int agreement_id = 0;
        try {agreement_id = Integer.parseInt(request.getParameter("param1"));}
        catch (NumberFormatException ex) {agreement_id = 0;}
        User user = (User) httpSession.getAttribute("user");
        System.out.println("agr id: " + agreement_id + " user: " + user + " param1: " + request.getParameter("param1"));
        if (agreement_id != 0 && user != null) {
            Session hibSession = HibernateUtil.getSession();
            hibSession.beginTransaction();
            List<Agreement> agreements = hibSession.createCriteria(Agreement.class)
                .add(Restrictions.eq("agr_id", agreement_id))
                .add(Restrictions.eq("user", user))
                .list();
            System.out.println("Size of agreements: " + agreements.size());
            if (agreements.size() == 1) {
                Agreement agreement = (Agreement) agreements.get(0);
                Payment payment = (Payment) agreement.getPayment();
                agreement.setPayment(null);
                agreement.setAgr_state(2);
                payment.setClient(null);
                payment.setPaym_state(2);
                hibSession.update(agreement);
                hibSession.update(payment);
                hibSession.beginTransaction().commit();
                hibSession.close();
                answer = SUCCESS;
            }
        }
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

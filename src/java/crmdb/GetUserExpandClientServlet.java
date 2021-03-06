/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crmdb;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
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
public class GetUserExpandClientServlet extends HttpServlet {

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
    private static final String FAILURE = "failure";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession httpSession = request.getSession();
        String answer;
        Session hibSession = HibernateUtil.getSession();
        hibSession.beginTransaction();
        int param1 = Integer.parseInt(request.getParameter("param1"));
        List clients = hibSession.createCriteria(Client.class)
                .add(Restrictions.eq("client_id", param1))
                .list();
        Client client = (Client) clients.get(0);
        List agreements = hibSession.createCriteria(Agreement.class)
                .add(Restrictions.eq("client", client))
                .list();
        Collections.sort(agreements, new Comparator<Agreement>() {
            @Override
            public int compare(Agreement lhs, Agreement rhs) {
                return -1 * lhs.getDate_written().compareTo(rhs.getDate_written());
            }
        });
        List payments = hibSession.createCriteria(Payment.class)
                .add(Restrictions.eq("client", client))
                .list();
        Collections.sort(payments, new Comparator<Payment>() {
            @Override
            public int compare(Payment lhs, Payment rhs) {
                return -1 * lhs.getPaym_date().compareTo(rhs.getPaym_date());
            }
        });
        Gson gson = new Gson();
        String jsonClient = gson.toJson(clients.get(0), Client.class);
        String jsonAgreements = gson.toJson(agreements);
        String jsonPayments = gson.toJson(payments);        
        answer = "{ \"client\" : " + jsonClient + ", " + "\"agreements\" : " + jsonAgreements + ", " + "\"payments\" : " + jsonPayments + "}";
        hibSession.beginTransaction().commit();
        hibSession.close();
        if (clients.size() != 1) answer = FAILURE;
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

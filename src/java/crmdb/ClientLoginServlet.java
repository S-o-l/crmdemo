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
public class ClientLoginServlet extends HttpServlet {

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
    static final String SUCCESS = "success";
    static final String FAILURE = "failure";
    static final String WARNING = "warning";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String answer = "";
        HttpSession httpSession = request.getSession();
        String login = request.getParameter("param1");
/*        String agreementsDate_pay = request.getParameter("param111");
        System.out.println((agreementsDate_pay == null) ? "gonull" : "notnull");
        System.out.println((agreementsDate_pay == "") ? "go" : "notnull");*/
        Client tempClient = new Client();
        tempClient.setMd5Password(request.getParameter("param2"), login);
//        System.out.println("-------------------------------------------"+tempClient.getPassword());
        String password = tempClient.getPassword();
        tempClient = null;
// validators to be putted here!!!!
        Session hibSession = HibernateUtil.getSession();
        hibSession.beginTransaction();
        List<Client> clients = hibSession.createCriteria(Client.class)
                .add(Restrictions.eq("login", login))
                .add(Restrictions.eq("password", password))
                .list();
        hibSession.beginTransaction().commit(); 
        hibSession.close();
        if (clients.size() == 0) answer = FAILURE;
        if (clients.size() > 1) answer = WARNING;
        if (clients.size() == 1) {
            answer = SUCCESS;
            httpSession.setAttribute("client", clients.get(0));
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

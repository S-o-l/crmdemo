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
public class UserLoginServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        HttpSession httpSession = request.getSession();
        Session hibSession = HibernateUtil.getSession();
        String login = request.getParameter("param1");
        Accinfo tempAcc = new Accinfo();
        tempAcc.setMd5Password(request.getParameter("param2"), login);
        String password = tempAcc.getPassword();
        System.out.println(password);
        tempAcc = null;
        String answer = "";
        hibSession.beginTransaction();
        List<Accinfo> accinfos = hibSession.createCriteria(Accinfo.class)
                .add(Restrictions.eq("login", login))
                .add(Restrictions.eq("password", password))
                .list();
        if (accinfos.size() == 0) answer = FAILURE;
        if (accinfos.size() == 1) {
            answer = SUCCESS;
            List<User> users = hibSession.createCriteria(User.class)
                    .add(Restrictions.eq("user_id", accinfos.get(0).getUser().getUser_id()))
                    .list();
            User user = (User) accinfos.get(0).getUser();
            httpSession.setAttribute("user", user);
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

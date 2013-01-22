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
public class GetUserAgreementsServlet extends HttpServlet {

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
    private static final String NODATA = "nodata";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String answer = FAILURE;
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            Session hibSession = HibernateUtil.getSession();
            hibSession.beginTransaction();
            List agreements = hibSession.createCriteria(Agreement.class)
                    .add(Restrictions.eq("user", user))
                    .list();
            hibSession.beginTransaction().commit();
            hibSession.close();
            if (agreements.size() == 0) answer = NODATA;
            if (agreements.size() > 0) {
                Collections.sort(agreements, new Comparator<Agreement>() {
                    @Override
                    public int compare(Agreement lhs, Agreement rhs) {
                        return -1 * Double.compare(lhs.getAgr_id(), rhs.getAgr_id());
                    }
                });
                Gson gson = new Gson();
                answer = gson.toJson(agreements);
                System.out.println(answer);
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

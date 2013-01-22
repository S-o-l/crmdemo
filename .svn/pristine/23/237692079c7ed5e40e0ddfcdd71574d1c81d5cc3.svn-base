/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crmdb;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author TempRDP2
 */
public class TestRegistrationServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("-------------------Started");
    response.setContentType("text/html;charset=UTF-8");
    String login = request.getParameter("param1");
    String password = request.getParameter("param2");
    String email = request.getParameter("param3");    
    String surname = request.getParameter("param4");    
    String name = request.getParameter("param5");
    String middlename = request.getParameter("param6");
    System.out.println("-------------------param1 to 6 success");
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date birthday = null;
    try {
    birthday = df.parse(request.getParameter("param7"));
    }
    catch (ParseException ex) {
        System.out.println("-----------exception!!!!!!" + ex);
        // write code to return error to javascript
    }
    System.out.println("-------------------param7 success");
    String sex = request.getParameter("param8").substring(0,1);
    String address = request.getParameter("param9");
    String telephone = request.getParameter("param10");
    System.out.println("-----------all params success, go for db");
    Session session = HibernateUtil.getSession();
    session.beginTransaction();
    Client client = new Client();
    client.setLogin(login);
    client.setMd5Password(password, login);
    client.setEmail(email);
    client.setSurname(surname);    
    client.setName(name);    
    client.setMiddlename(middlename);
    client.setBirthday(birthday);
    client.setSex(sex);    
    client.setAddress(address);    
    client.setTelephone(telephone);    
    session.save(client);
    session.beginTransaction().commit();    
   
    System.out.println("-----------ready to pass answer");
    response.getWriter().write(SUCCESS + "\nParam1: " + login +
        "\nParam2: " + password +
        "\nParam3: " + email +
        "\nParam4: " + surname +    
        "\nParam5: " + name +    
        "\nParam6: " + middlename +    
        "\nParam7: " + birthday +
        "\nParam8: " + sex +    
        "\nParam9: " + address +
        "\nParam10: " + telephone);
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

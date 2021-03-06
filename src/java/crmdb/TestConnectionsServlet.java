/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crmdb;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author TempRDP2
 */
public class TestConnectionsServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");
        String param1 = request.getParameter("param1");
        String param2 = request.getParameter("param2");
        String answer;
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Gson gson = new Gson();
        List<Bank> banks = session.createCriteria(Bank.class)
            .add(Restrictions.eq("name", param1))
            .list();
        Bank bank = null;
        String answerBank = "";
        if (banks.size() > 0) {
            bank = banks.get(0);
            answerBank = bank.getName();
        }
        List<Banks_account> accounts = session.createCriteria(Banks_account.class)
            .add(Restrictions.eq("acc_number", param2))
            .list();
        Banks_account account = null;
        String answerAccount = "";
        if (accounts.size() > 0) {
            account = accounts.get(0);
            answerAccount = account.getAcc_number();
        }
        System.out.println("Bank: -------------" + banks.size() + "-----------" + answerBank);
        System.out.println("Account:-------------" + accounts.size() + "-----------" + answerAccount);
        JsonArray jsonArray = new JsonArray();
        JsonObject accJson;
        JsonParser parser = new JsonParser();
        for (int i = 0; i < accounts.size(); i++) {
            account = accounts.get(i);
            accJson = (JsonObject) parser.parse(gson.toJson(account));
            jsonArray.add(accJson);
        }
        answer = jsonArray.toString();
//        String json = gson.toJson(accounts);
//        answer = json;
//        Bank backBank = gson.fromJson(json, Bank.class);
                session.beginTransaction().commit(); 
                session.close();
        response.getWriter().write(answer);
        //        Banks_account account = (Banks_account) accounts.get(0);
/*        Banks_account newaccount = new Banks_account();
        newaccount.setBank(tempBank);
        newaccount.setAcc_number("2accnumber2");
        newaccount.setCurrency("BBB");
        newaccount.setAcc_state(1);        */
        
       

//        answer = tempBank.getName() + " ";// + newaccount.getAcc_number();
       
        /*        List accs = session.createCriteria(Accinfo.class)
                .add(Restrictions.eq("login", login))
                .list();        */
//        session.save(client);
//        Accinfo currentAcc = new Accinfo();
//        currentAcc.setLogin(login);
//        currentAcc.setMd5Password(password, login);
/*        List users = session.createCriteria(User.class)
                .add(Restrictions.eq("name", "name2"))
                .list();
        User tempUser = (User) users.get(0);*/
//        currentAcc.setUser(tempUser);

        //        session.save(currentAcc);
/*        List accs = session.createCriteria(Accinfo.class)
                .add(Restrictions.eq("user", tempUser))
                .list();*/
        

/*        currentAcc = (Accinfo) accs.get(0);
        String answer = currentAcc.getLogin();*/
        /*        for (int i=0; i<=accs.size()-1;i++) {
            currentAcc = (Accinfo) accs.get(i);
            tempUser = currentAcc.getUser();
            answer += " login:" + currentAcc.getLogin() + " acc_id:" + currentAcc.getAcc_id() + " user id:" + tempUser.getUser_id();
        }*/
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

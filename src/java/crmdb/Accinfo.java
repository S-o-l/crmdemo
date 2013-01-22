/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crmdb;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author TempRDP2
 */

@Entity
@Table(name="accinfo")
public class Accinfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int acc_id;    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;    
    private String login;
    private String password;

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMd5Password(String password, String login) {
            String md5Password = password + login.substring(0,4);
            byte[] bytesOfMessage = null;
            try {
                bytesOfMessage = md5Password.getBytes("UTF-8");
            } catch (UnsupportedEncodingException ex) {
                System.out.println(ex);
            }
            MessageDigest md = null;
                try {
                    md = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException ex) {
                    System.out.println(ex);
                }
            byte[] thedigest = md.digest(bytesOfMessage);
            String hashString = "";
            for(int i = 0; i < thedigest.length; i++) {
                String hexHash = Integer.toHexString(0xFF & thedigest[i]);
                if(hexHash.length() < 2) {
                    hashString = hashString + "0" + hexHash;
                } else
                hashString = hashString + hexHash;
            }
        this.password = hashString;
    }         
    
    
}


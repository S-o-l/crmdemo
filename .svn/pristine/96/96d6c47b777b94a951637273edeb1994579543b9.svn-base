/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crmdb;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author TempRDP2
 */
@Entity
@Table(name="clients")

public class Client implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int client_id;

    private String login;
    private String password;
    private String email;
    private String surname;
    private String name;
    private String middlename;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String sex;
    private String address;
    private String telephone;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
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
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

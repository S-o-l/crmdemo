/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crmdb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author TempRDP2
 */
@Entity
@Table(name="banks_accounts")
public class Banks_account implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int bank_acc_id;

    @ManyToOne
    @JoinColumn(name="bank_id")
    private Bank bank;
    
    private String acc_number;
    private String currency;
    private int acc_state;

    public int getBank_acc_id() {
        return bank_acc_id;
    }

    public void setBank_acc_id(int bank_acc_id) {
        this.bank_acc_id = bank_acc_id;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getAcc_number() {
        return acc_number;
    }

    public void setAcc_number(String acc_number) {
        this.acc_number = acc_number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAcc_state() {
        return acc_state;
    }

    public void setAcc_state(int acc_state) {
        this.acc_state = acc_state;
    }

}

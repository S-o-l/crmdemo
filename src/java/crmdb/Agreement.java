/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crmdb;

import java.util.Date;
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
@Table(name="agreements")

public class Agreement {
    static final long SerialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int agr_id;
    private String agr_number;
    @Temporal(TemporalType.DATE)
    private Date date_written;
    @Temporal(TemporalType.DATE)
    private Date date_begin;    
    @Temporal(TemporalType.DATE)
    private Date date_end;
    @Temporal(TemporalType.DATE)
    private Date date_pay;
    private long sum_pay;
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="payment_id")
    private Payment payment;
    private int agr_state;

    public int getAgr_id() {
        return agr_id;
    }

    public void setAgr_id(int agr_id) {
        this.agr_id = agr_id;
    }



    public String getAgr_number() {
        return agr_number;
    }

    public void setAgr_number(String agr_number) {
        this.agr_number = agr_number;
    }

    public Date getDate_written() {
        return date_written;
    }

    public void setDate_written(Date date_written) {
        this.date_written = date_written;
    }

    public Date getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(Date date_begin) {
        this.date_begin = date_begin;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public Date getDate_pay() {
        return date_pay;
    }

    public void setDate_pay(Date date_pay) {
        this.date_pay = date_pay;
    }

    public long getSum_pay() {
        return sum_pay;
    }

    public void setSum_pay(long sum_pay) {
        this.sum_pay = sum_pay;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getAgr_state() {
        return agr_state;
    }

    public void setAgr_state(int agr_state) {
        this.agr_state = agr_state;
    }
    
    
}

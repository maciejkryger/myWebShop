package pl.javarun.mywebshop.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;


/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 10:59
 * *
 * @className: Order
 * *
 * *
 ******************************************************/
@Entity
public class WebOrder {

    @Id
    private int id;
    private String orderNumber;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @Nullable
    @DateTimeFormat
    private Timestamp creationDate;

    private boolean confirmed;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp confirmedDate;

    private boolean accepted;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp acceptedDate;

    @Nullable
    private boolean paid;
    @Nullable
    private int paidAmount;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp paidDate;
    @Nullable
    @ManyToOne(targetEntity = PaymentMethod.class)
    private PaymentMethod paymentMethod;

    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp shipmentDate;

    @Nullable
    private boolean completed;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp completedDate;

    public WebOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Nullable
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Timestamp getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Timestamp confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public void setCreationDate(@Nullable Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Nullable
    public Timestamp getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(@Nullable Timestamp acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Nullable
    public Timestamp getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(@Nullable Timestamp paidDate) {
        this.paidDate = paidDate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Nullable
    public Timestamp getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(@Nullable Timestamp shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Nullable
    public Timestamp getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(@Nullable Timestamp completedDate) {
        this.completedDate = completedDate;
    }
}

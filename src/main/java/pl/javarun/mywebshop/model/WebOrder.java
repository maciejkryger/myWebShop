package pl.javarun.mywebshop.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
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
//@Table(schema = "order", name = "[order]")
public class WebOrder {

    @Id
//    @Column(name = "order_id")
    private int id;
    private String orderNumber;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @Nullable
    @DateTimeFormat
    private Timestamp creationDate;

    private boolean confirmed;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp confirmDate;

    private boolean accepted;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp acceptDate;
    @ManyToOne(targetEntity = User.class)
    private User acceptUser;

    @Nullable
    private boolean paid;
    @Nullable
    private int paymentAmount;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp paymentDate;
    @Nullable
    @ManyToOne(targetEntity = PaymentMethod.class)
    private PaymentMethod paymentMethod;

    @Nullable
    @ManyToOne(targetEntity = DeliveryOption.class)
    private DeliveryOption deliveryOption;

    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp shipmentDate;
    @Nullable
    private String shipmentNumber;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp shipmentDeliveryDate;


    @Nullable
    private boolean completed;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp completeDate;

    @Nullable
    private boolean canceled;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp cancelDate;
    @ManyToOne(targetEntity = User.class)
    private User cancelUser;

    @Nullable
    private boolean returned;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp returnDate;
    @Nullable
    private boolean returnPaid;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp returnPaymentDate;

    private String comment;

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

    public Timestamp getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Timestamp confirmDate) {
        this.confirmDate = confirmDate;
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
    public Timestamp getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(@Nullable Timestamp acceptDate) {
        this.acceptDate = acceptDate;
    }

    public User getAcceptUser() {
        return acceptUser;
    }

    public void setAcceptUser(User acceptUser) {
        this.acceptUser = acceptUser;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Nullable
    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(@Nullable Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Nullable
    public DeliveryOption getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(@Nullable DeliveryOption deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    @Nullable
    public Timestamp getShipmentDeliveryDate() {
        return shipmentDeliveryDate;
    }

    public void setShipmentDeliveryDate(@Nullable Timestamp shipmentDeliveryDate) {
        this.shipmentDeliveryDate = shipmentDeliveryDate;
    }

    @Nullable
    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(@Nullable String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
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
    public Timestamp getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(@Nullable Timestamp completeDate) {
        this.completeDate = completeDate;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Nullable
    public Timestamp getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(@Nullable Timestamp cancelDate) {
        this.cancelDate = cancelDate;
    }

    public User getCancelUser() {
        return cancelUser;
    }

    public void setCancelUser(User cancelUser) {
        this.cancelUser = cancelUser;
    }


    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Nullable
    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(@Nullable Timestamp returnDate) {
        this.returnDate = returnDate;
    }


    public boolean isReturnPaid() {
        return returnPaid;
    }

    public void setReturnPaid(boolean returnPaid) {
        this.returnPaid = returnPaid;
    }

    @Nullable
    public Timestamp getReturnPaymentDate() {
        return returnPaymentDate;
    }

    public void setReturnPaymentDate(@Nullable Timestamp returnPaymentDate) {
        this.returnPaymentDate = returnPaymentDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    private String id;
    private Order order;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        this.status = PaymentStatus.PENDING.getValue();
    }

    public Payment(String id, Order order, String method, Map<String, String> paymentData, String status) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;

        if (!PaymentStatus.isValid(status)) {
            throw new IllegalArgumentException("Invalid status");
        }
        this.status = status;
    }

    public void setStatus(String status) {
        if (!PaymentStatus.isValid(status)) {
            throw new IllegalArgumentException("Invalid status");
        }
        this.status = status;
    }

    public void setPaymentData(Map<String, String> paymentData) {
        this.paymentData = paymentData;
    }
}
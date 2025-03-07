package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaymentRepository {

    private Map<String, Payment> paymentMap;

    public PaymentRepository() {
        this.paymentMap = new HashMap<>();
    }

    public Payment save(Payment payment) {
        paymentMap.put(payment.getId(), payment);
        return payment;
    }

    public Payment findById(String id) {
        return paymentMap.get(id);
    }

    public List<Payment> findAll() {
        return new ArrayList<>(paymentMap.values());
    }

    public void delete(String id) {
        paymentMap.remove(id);
    }

    public List<Payment> findByOrderId(String orderId) {
        return paymentMap.values().stream()
                .filter(payment -> payment.getOrder().getId().equals(orderId))
                .collect(Collectors.toList());
    }
}
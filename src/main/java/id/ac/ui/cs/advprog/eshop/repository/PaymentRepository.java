package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    public Payment save(Payment payment) {return null;}
    public Payment findById(String id) {return null;}
    public List<Payment> findAll() {return new ArrayList<>();}
    public void delete(String id) {}
    public List<Payment> findByOrderId(String orderId) {return new ArrayList<>();}
}
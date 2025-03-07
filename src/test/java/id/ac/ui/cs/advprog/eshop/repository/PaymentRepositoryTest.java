package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;
    private Payment payment1;
    private Payment payment2;
    private Order order;

    @BeforeEach
    void setUp() {
        // Create the repository
        paymentRepository = new PaymentRepository();

        // Create a test order
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        // Create payment data
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP12345678ABCD");

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("cardNumber", "4111111111111111");
        paymentData2.put("expiryDate", "12/25");
        paymentData2.put("cvv", "123");

        // Create test payments
        payment1 = new Payment("payment-001", order, "VOUCHER", paymentData1);
        payment2 = new Payment("payment-002", order, "CREDIT_CARD", paymentData2, PaymentStatus.SUCCESS.getValue());
    }

    @Test
    void testSavePayment() {
        Payment savedPayment = paymentRepository.save(payment1);

        assertEquals(payment1.getId(), savedPayment.getId());
        assertEquals(payment1.getStatus(), savedPayment.getStatus());
    }

    @Test
    void testFindById() {
        paymentRepository.save(payment1);
        Payment foundPayment = paymentRepository.findById("payment-001");

        assertNotNull(foundPayment);
        assertEquals("payment-001", foundPayment.getId());
        assertEquals("VOUCHER", foundPayment.getMethod());
    }

    @Test
    void testFindByIdNotFound() {
        Payment foundPayment = paymentRepository.findById("non-existent-id");

        assertNull(foundPayment);
    }

    @Test
    void testFindAll() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> allPayments = paymentRepository.findAll();

        assertEquals(2, allPayments.size());
        assertTrue(allPayments.contains(payment1));
        assertTrue(allPayments.contains(payment2));
    }

    @Test
    void testUpdatePayment() {
        paymentRepository.save(payment1);

        // Update the payment status
        payment1.setStatus(PaymentStatus.SUCCESS.getValue());
        Payment updatedPayment = paymentRepository.save(payment1);

        assertEquals(PaymentStatus.SUCCESS.getValue(), updatedPayment.getStatus());

        // Verify the update was persisted
        Payment retrievedPayment = paymentRepository.findById("payment-001");
        assertEquals(PaymentStatus.SUCCESS.getValue(), retrievedPayment.getStatus());
    }

    @Test
    void testDeletePayment() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        paymentRepository.delete("payment-001");

        assertNull(paymentRepository.findById("payment-001"));
        assertNotNull(paymentRepository.findById("payment-002"));
        assertEquals(1, paymentRepository.findAll().size());
    }

    @Test
    void testDeleteNonExistentPayment() {
        paymentRepository.save(payment1);

        // Should not throw an exception
        assertDoesNotThrow(() -> paymentRepository.delete("non-existent-id"));

        // Should still have the original payment
        assertEquals(1, paymentRepository.findAll().size());
    }

    @Test
    void testFindByOrderId() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> orderPayments = paymentRepository.findByOrderId(order.getId());

        assertEquals(2, orderPayments.size());
        assertTrue(orderPayments.contains(payment1));
        assertTrue(orderPayments.contains(payment2));
    }
}
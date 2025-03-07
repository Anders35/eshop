package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order order;
    private Payment payment;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Set up test data
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678");

        payment = new Payment("payment-001", order, "VOUCHER", paymentData);
    }

    @Test
    void testAddPayment() {
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment result = paymentService.addPayment(order, "VOUCHER", paymentData);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(order, result.getOrder());
        assertEquals("VOUCHER", result.getMethod());
        assertEquals(paymentData, result.getPaymentData());
        assertEquals("PENDING", result.getStatus());

        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
    void testSetStatusToSuccess() {
        payment.setStatus("PENDING");
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment result = paymentService.setStatus(payment, "SUCCESS");

        assertNotNull(result);
        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", order.getStatus());

        verify(paymentRepository).save(payment);
    }

    @Test
    void testSetStatusToRejected() {
        payment.setStatus("PENDING");
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment result = paymentService.setStatus(payment, "REJECTED");

        assertNotNull(result);
        assertEquals("REJECTED", result.getStatus());
        assertEquals("FAILED", order.getStatus());

        verify(paymentRepository).save(payment);
    }

    @Test
    void testSetStatusToOther() {
        payment.setStatus("PENDING");
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment result = paymentService.setStatus(payment, "PROCESSING");

        assertNotNull(result);
        assertEquals("PROCESSING", result.getStatus());
        // Order status should not change for other payment statuses
        assertNotEquals("SUCCESS", order.getStatus());
        assertNotEquals("FAILED", order.getStatus());

        verify(paymentRepository).save(payment);
    }

    @Test
    void testGetPayment() {
        when(paymentRepository.findById("payment-001")).thenReturn(payment);

        Payment result = paymentService.getPayment("payment-001");

        assertNotNull(result);
        assertEquals("payment-001", result.getId());
        verify(paymentRepository).findById("payment-001");
    }

    @Test
    void testGetPaymentNotFound() {
        when(paymentRepository.findById("non-existent")).thenReturn(null);

        Payment result = paymentService.getPayment("non-existent");

        assertNull(result);
        verify(paymentRepository).findById("non-existent");
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = List.of(payment);
        when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(1, result.size());
        assertEquals(payment, result.get(0));
        verify(paymentRepository).findAll();
    }
}
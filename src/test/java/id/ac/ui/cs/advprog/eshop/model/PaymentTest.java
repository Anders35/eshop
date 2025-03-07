package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
            products, 1708560000L, "Safira Sudrajat");

        this.paymentData = new HashMap<>();
        this.paymentData.put("voucherCode", "ESHOP12345678ABCD");
    }

    @Test
    void testCreatePaymentWithDefaultStatus() {
        Payment payment = new Payment("payment-123", order, "VOUCHER", paymentData);

        assertEquals("payment-123", payment.getId());
        assertEquals(order, payment.getOrder());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("payment-123", order, "VOUCHER", paymentData, PaymentStatus.SUCCESS.getValue());

        assertEquals("payment-123", payment.getId());
        assertEquals(order, payment.getOrder());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("payment-123", order, "VOUCHER", this.paymentData, "MEOW");
        });
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", order, "VOUCHER", this.paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", order, "VOUCHER", this.paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", order, "VOUCHER", this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

    @Test
    void testSetPaymentDataSuccess() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", order, "VOUCHER", this.paymentData);
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
        payment.setPaymentData(this.paymentData);
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCashOnDeliveryPaymentData() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", order, "VOUCHER", this.paymentData);
        Map<String, String> codData = new HashMap<>();
        codData.put("address", "Jl. Margonda Raya No. 100");
        codData.put("deliveryFee", "15000");

        payment.setPaymentData(codData);

        assertEquals("Jl. Margonda Raya No. 100", payment.getPaymentData().get("address"));
        assertEquals("15000", payment.getPaymentData().get("deliveryFee"));
    }
}
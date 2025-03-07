package enums;

public enum PaymentStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    REJECTED("REJECTED");

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean isValid(String status) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.getValue().equals(status)) {
                return true;
            }
        }
        return false;
    }

    public static PaymentStatus fromString(String status) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.getValue().equals(status)) {
                return paymentStatus;
            }
        }
        throw new IllegalArgumentException("Invalid payment status: " + status);
    }
}
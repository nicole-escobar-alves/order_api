package br.com.postech.techchallenge.order_api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentType {
    PIX("Pix"),
    CREDITCARD("Creditcrd");

    private final String displayName;

    PaymentType(String displayName) {
        this.displayName = displayName;
    }

    @JsonCreator
    public static PaymentType fromDisplayName(String displayName) {
        for (PaymentType category : PaymentType.values()) {
            if (category.getDisplayName().equals(displayName) || category.name().equalsIgnoreCase(displayName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Tipo de pagamento: " + displayName + " não encontrada!");
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}

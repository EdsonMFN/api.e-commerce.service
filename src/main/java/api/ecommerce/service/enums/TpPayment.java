package api.ecommerce.service.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum TpPayment {

    CREDIT_CARD(1,"credit card"),
    DEBIT_CARD(2,"debit card"),
    PIX(3,"pix"),
    TICKET(4,"ticket");

    private final Integer id;
    private final String description;

    TpPayment(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static TpPayment findById(Integer id){
        return Stream.of(TpPayment.values())
                .filter(value -> value.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Type %s not found",id)));
    }
    public static TpPayment findByDescription(String description){
        return Stream.of(TpPayment.values())
                .filter(value -> value.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Type %s not found",description)));
    }
}

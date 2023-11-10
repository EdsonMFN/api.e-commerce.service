package api.ecommerce.service.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum TypeProduct {

    SPORTS(1,"sports"),
    CLOTHES(2,"clothes"),
    SHOE(3,"shoe"),
    ELECTRONICS(4,"electronics");

    private Integer id;
    private String description;

    TypeProduct(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static TypeProduct findById(Integer id){
        return Stream.of(TypeProduct.values())
                .filter(value -> value.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("File %s not found", id)));
    }
    public static TypeProduct findByDescription(String description){
        return Stream.of(TypeProduct.values())
                .filter(value -> value.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("File %s not found", description)));
    }
}

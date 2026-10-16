package net.fatimazahramassane.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import net.fatimazahramassane.billingservice.model.Product;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItem {
    @Id @GeneratedValue
    private Long id;
    private Long productId;
    private int quantity;
    private double price;
    @ManyToOne
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Bill bill;
    @Transient
    private Product product;
}

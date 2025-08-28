package co.com.accenture.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private float stock;
    private Long branchId;
}

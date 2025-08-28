package co.com.accenture.infrastructure.persistence.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table("products")
public class ProductEntity {
    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("stock")
    private Float stock;

    @Column("branch_id")
    private Long branchId;
}

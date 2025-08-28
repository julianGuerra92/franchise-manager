package co.com.accenture.infrastructure.persistence.mysql.repository;

import co.com.accenture.infrastructure.persistence.mysql.entity.ProductEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ProductMysqlRepository extends R2dbcRepository<ProductEntity, Long> {
}

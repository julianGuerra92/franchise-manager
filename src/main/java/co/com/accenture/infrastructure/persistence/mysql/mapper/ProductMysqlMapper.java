package co.com.accenture.infrastructure.persistence.mysql.mapper;

import co.com.accenture.domain.model.Product;
import co.com.accenture.infrastructure.persistence.mysql.entity.ProductEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMysqlMapper {
    Product toDomain(ProductEntity productEntity);
    ProductEntity toEntity(Product product);
}

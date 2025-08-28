package co.com.accenture.presentation.rest.mapper;

import co.com.accenture.domain.model.Product;
import co.com.accenture.presentation.rest.dto.ProductDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductDTOMapper {
    Product toDomain(ProductDTO productDTO);
    ProductDTO toDTO(Product product);
}

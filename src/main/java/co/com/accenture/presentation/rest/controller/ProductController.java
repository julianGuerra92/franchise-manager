package co.com.accenture.presentation.rest.controller;

import co.com.accenture.domain.usecase.ProductUseCases;
import co.com.accenture.presentation.rest.dto.ProductDTO;
import co.com.accenture.presentation.rest.dto.ProductStockDTO;
import co.com.accenture.presentation.rest.mapper.ProductDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCases productUseCases;
    private final ProductDTOMapper productDTOMapper;

    @PostMapping
    Mono<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return productUseCases
                .createProduct(productDTOMapper.toDomain(productDTO)).map(productDTOMapper::toDTO);
    }

    @PutMapping("/{id}")
    Mono<ProductDTO> updateProductStock(@PathVariable Long id, @RequestBody ProductStockDTO productStockDTO) {
        return productUseCases
                .updateProductStock(id, productStockDTO.getStock()).map(productDTOMapper::toDTO);
    }

    @DeleteMapping("/{id}")
    Mono<ProductDTO> deleteProduct(@PathVariable Long id) {
        return productUseCases.deleteProduct(id).map(productDTOMapper::toDTO);
    }
}

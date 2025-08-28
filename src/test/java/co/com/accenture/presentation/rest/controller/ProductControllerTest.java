package co.com.accenture.presentation.rest.controller;

import co.com.accenture.domain.usecase.ProductUseCases;
import co.com.accenture.presentation.rest.dto.ProductDTO;
import co.com.accenture.presentation.rest.dto.ProductStockDTO;
import co.com.accenture.presentation.rest.mapper.ProductDTOMapper;
import co.com.accenture.presentation.rest.mapper.ProductDTOMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    ProductUseCases productUseCases;
    ProductDTOMapper productDTOMapper;
    ProductController productController;

    @BeforeEach
    void setUp() {
        productUseCases = mock(ProductUseCases.class);
        productDTOMapper = new ProductDTOMapperImpl();
        productController = new ProductController(productUseCases, productDTOMapper);
    }

    @Test
    void createProduct_shouldReturnProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        when(productUseCases.createProduct(any())).thenReturn(Mono.just(productDTOMapper.toDomain(productDTO)));

        Mono<ProductDTO> result = productController.createProduct(productDTO);

        StepVerifier.create(result)
                .expectNext(productDTOMapper.toDTO(productDTOMapper.toDomain(productDTO)))
                .verifyComplete();
        verify(productUseCases, times(1)).createProduct(any());
    }

    @Test
    void updateProductStock_shouldReturnProductDTO() {
        ProductStockDTO stockDTO = new ProductStockDTO();
        stockDTO.setStock(10.0F);
        when(productUseCases.updateProductStock(anyLong(), anyFloat()))
                .thenReturn(Mono.just(productDTOMapper.toDomain(new ProductDTO())));

        Mono<ProductDTO> result = productController.updateProductStock(1L, stockDTO);

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();
        verify(productUseCases, times(1)).updateProductStock(anyLong(), anyFloat());
    }

    @Test
    void deleteProduct_shouldReturnProductDTO() {
        when(productUseCases.deleteProduct(anyLong())).thenReturn(Mono.just(productDTOMapper.toDomain(new ProductDTO())));

        Mono<ProductDTO> result = productController.deleteProduct(1L);

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();
        verify(productUseCases, times(1)).deleteProduct(anyLong());
    }
}
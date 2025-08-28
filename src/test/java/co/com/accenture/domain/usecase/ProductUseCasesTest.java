package co.com.accenture.domain.usecase;

import co.com.accenture.domain.model.Product;
import co.com.accenture.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductUseCasesTest {

    BranchUseCases branchUseCases;
    ProductRepository productRepository;
    ProductUseCases productUseCases;

    @BeforeEach
    void setUp() {
        branchUseCases = mock(BranchUseCases.class);
        productRepository = mock(ProductRepository.class);
        productUseCases = new ProductUseCases(branchUseCases, productRepository);
    }

    @Test
    void createProduct_shouldReturnProduct_whenBranchExists() {
        Product product = new Product(1L, "Test Product", 10f, 1L);

        co.com.accenture.domain.model.Branch branch = new co.com.accenture.domain.model.Branch(1L, "Test Branch", 1L);
        when(branchUseCases.getBranchById(1L)).thenReturn(Mono.just(branch));
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(product));

        Mono<Product> result = productUseCases.createProduct(product);

        StepVerifier.create(result)
                .expectNext(product)
                .verifyComplete();

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void updateProductStock_shouldReturnProduct_whenStockIsPositive() {
        Product product = new Product(1L, "Test Product", 10f, 1L);
        when(productRepository.updateStockById(1L, 10f)).thenReturn(Mono.just(product));

        Mono<Product> result = productUseCases.updateProductStock(1L, 10f);

        StepVerifier.create(result)
                .expectNext(product)
                .verifyComplete();

        verify(productRepository, times(1)).updateStockById(1L, 10f);
    }

    @Test
    void deleteProduct_shouldReturnProduct_whenProductExists() {
        Product product = new Product(1L, "Test Product", 10f, 1L);
        when(productRepository.deleteById(1L)).thenReturn(Mono.just(product));

        Mono<Product> result = productUseCases.deleteProduct(1L);

        StepVerifier.create(result)
                .expectNext(product)
                .verifyComplete();

        verify(productRepository, times(1)).deleteById(1L);
    }
}
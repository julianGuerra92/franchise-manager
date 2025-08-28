package co.com.accenture.domain.usecase;

import co.com.accenture.domain.model.Product;
import co.com.accenture.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductUseCases {

    private final BranchUseCases branchUseCases;
    private final ProductRepository productRepository;

    public Mono<Product> createProduct(Product product) {
        return branchUseCases.getBranchById(product.getBranchId())
                .flatMap(branch -> productRepository.save(product))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch not found")));
    }

    public Mono<Product> updateProductStock(Long id, float stock) {
        if (stock <= 0) {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock must be positive"));
        }
        return productRepository.updateStockById(id, stock)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")));
    }

    public Mono<Product> deleteProduct(Long id) {
        return productRepository.deleteById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")));
    }

}

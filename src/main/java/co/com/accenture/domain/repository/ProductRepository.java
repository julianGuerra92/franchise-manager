package co.com.accenture.domain.repository;

import co.com.accenture.domain.model.Product;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> save(Product product);
    Mono<Product> findById(Long id);
    Mono<Product> updateStockById(Long id, float stock);
    Mono<Product> deleteById(Long id);
}

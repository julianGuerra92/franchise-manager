package co.com.accenture.infrastructure.persistence.mysql.adapter;

import co.com.accenture.domain.model.Product;
import co.com.accenture.domain.repository.ProductRepository;
import co.com.accenture.infrastructure.persistence.mysql.entity.ProductEntity;
import co.com.accenture.infrastructure.persistence.mysql.mapper.ProductMysqlMapper;
import co.com.accenture.infrastructure.persistence.mysql.repository.ProductMysqlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductMysqlMapper productMysqlMapper;
    private final ProductMysqlRepository productMysqlRepository;

    @Override
    public Mono<Product> save(Product product) {
        ProductEntity entity = productMysqlMapper.toEntity(product);
        return productMysqlRepository
                .save(entity)
                .map(productMysqlMapper::toDomain);
    }

    @Override
    public Mono<Product> findById(Long id) {
        return productMysqlRepository
                .findById(id)
                .map(productMysqlMapper::toDomain);
    }

    @Override
    public Mono<Product> updateStockById(Long id, float stock) {
        return productMysqlRepository.findById(id)
                .flatMap(entity -> {
                    entity.setStock(stock);
                    return productMysqlRepository.save(entity);
                })
                .map(productMysqlMapper::toDomain);
    }

    @Override
    public Mono<Product> deleteById(Long id) {
        return productMysqlRepository.findById(id)
                .flatMap(entity ->
                        productMysqlRepository.delete(entity)
                                .then(Mono.just(entity))
                )
                .map(productMysqlMapper::toDomain);
    }
}

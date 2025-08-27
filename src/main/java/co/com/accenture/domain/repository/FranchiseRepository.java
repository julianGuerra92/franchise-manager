package co.com.accenture.domain.repository;

import co.com.accenture.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {
    Mono<Franchise> save(Franchise franchise);
    Mono<Franchise> findById(Long id);
    Mono<Franchise> updateById(Long id, Franchise franchise);
}

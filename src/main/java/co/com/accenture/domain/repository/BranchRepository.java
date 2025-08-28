package co.com.accenture.domain.repository;

import co.com.accenture.domain.model.Branch;
import reactor.core.publisher.Mono;

public interface BranchRepository {
    Mono<Branch> save(Branch branch);
    Mono<Branch> findById(Long id);
    Mono<Branch> updateNameById(Long id, String name);
}

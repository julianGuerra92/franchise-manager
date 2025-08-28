package co.com.accenture.domain.usecase;

import co.com.accenture.domain.model.Branch;
import co.com.accenture.domain.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BranchUseCases {

    private final BranchRepository branchRepository;
    private final FranchiseUseCases franchiseUseCases;

    public Mono<Branch> createBranch(Branch branch) {
        return franchiseUseCases.getFranchiseById(branch.getFranchiseId())
                .flatMap(franchise -> branchRepository.save(branch))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Franchise not found")));
    }

    public Mono<Branch> getBranchById(Long id) {
        return branchRepository.findById(id);
    }
}

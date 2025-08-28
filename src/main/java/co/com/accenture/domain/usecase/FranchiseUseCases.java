package co.com.accenture.domain.usecase;

import co.com.accenture.domain.model.Franchise;
import co.com.accenture.domain.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FranchiseUseCases {
    private final FranchiseRepository franchiseRepository;

    public Mono<Franchise> createFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public Mono<Franchise> getFranchiseById(Long id) {
        return franchiseRepository.findById(id);
    }
}

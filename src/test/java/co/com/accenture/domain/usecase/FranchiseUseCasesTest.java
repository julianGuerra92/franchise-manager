package co.com.accenture.domain.usecase;

import co.com.accenture.domain.model.Franchise;
import co.com.accenture.domain.repository.FranchiseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FranchiseUseCasesTest {

    FranchiseRepository franchiseRepository;
    FranchiseUseCases franchiseUseCases;

    @BeforeEach
    void setUp() {
        franchiseRepository = mock(FranchiseRepository.class);
        franchiseUseCases = new FranchiseUseCases(franchiseRepository);
    }

    @Test
    void createFranchise_shouldReturnFranchise() {
        Franchise franchise = new Franchise(1L, "Test Franchise");
        when(franchiseRepository.save(any(Franchise.class))).thenReturn(Mono.just(franchise));

        Mono<Franchise> result = franchiseUseCases.createFranchise(franchise);

        StepVerifier.create(result)
                .expectNext(franchise)
                .verifyComplete();

        verify(franchiseRepository, times(1)).save(franchise);
    }

    @Test
    void getFranchiseById_shouldReturnFranchise() {
        Franchise franchise = new Franchise(1L, "Test Franchise");
        when(franchiseRepository.findById(1L)).thenReturn(Mono.just(franchise));

        Mono<Franchise> result = franchiseUseCases.getFranchiseById(1L);

        StepVerifier.create(result)
                .expectNext(franchise)
                .verifyComplete();

        verify(franchiseRepository, times(1)).findById(1L);
    }
}
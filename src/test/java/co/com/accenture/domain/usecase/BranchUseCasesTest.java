package co.com.accenture.domain.usecase;

import co.com.accenture.domain.model.Branch;
import co.com.accenture.domain.model.Franchise;
import co.com.accenture.domain.repository.BranchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BranchUseCasesTest {

    BranchRepository branchRepository;
    FranchiseUseCases franchiseUseCases;
    BranchUseCases branchUseCases;

    @BeforeEach
    void setUp() {
        branchRepository = mock(BranchRepository.class);
        franchiseUseCases = mock(FranchiseUseCases.class);
        branchUseCases = new BranchUseCases(branchRepository, franchiseUseCases);
    }

    @Test
    void createBranch_shouldReturnBranch_whenFranchiseExists() {
        Branch branch = new Branch(1L, "Test Branch", 1L);
        Franchise franchise = new Franchise(1L, "Test Franchise");

        when(franchiseUseCases.getFranchiseById(1L)).thenReturn(Mono.just(franchise));
        when(branchRepository.save(any(Branch.class))).thenReturn(Mono.just(branch));

        Mono<Branch> result = branchUseCases.createBranch(branch);

        StepVerifier.create(result)
                .expectNext(branch)
                .verifyComplete();

        verify(branchRepository, times(1)).save(branch);
    }

    @Test
    void getBranchById_shouldReturnBranch() {
        Branch branch = new Branch(1L, "Test Branch", 1L);
        when(branchRepository.findById(1L)).thenReturn(Mono.just(branch));

        Mono<Branch> result = branchUseCases.getBranchById(1L);

        StepVerifier.create(result)
                .expectNext(branch)
                .verifyComplete();

        verify(branchRepository, times(1)).findById(1L);
    }
}
package co.com.accenture.presentation.rest.controller;

import co.com.accenture.domain.usecase.BranchUseCases;
import co.com.accenture.presentation.rest.dto.BranchDTO;
import co.com.accenture.presentation.rest.mapper.BranchDTOMapper;
import co.com.accenture.presentation.rest.mapper.BranchDTOMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BranchControllerTest {

    BranchUseCases branchUseCases;
    BranchDTOMapper branchDTOMapper;
    BranchController branchController;

    @BeforeEach
    void setUp() {
        branchUseCases = mock(BranchUseCases.class);
        branchDTOMapper = new BranchDTOMapperImpl();
        branchController = new BranchController(branchUseCases, branchDTOMapper);
    }

    @Test
    void createBranch_shouldReturnBranchDTO() {
        BranchDTO branchDTO = new BranchDTO();
        when(branchUseCases.createBranch(any())).thenReturn(Mono.just(branchDTOMapper.toDomain(branchDTO)));

        Mono<BranchDTO> result = branchController.createBranch(branchDTO);

        StepVerifier.create(result)
                .expectNext(branchDTOMapper.toDTO(branchDTOMapper.toDomain(branchDTO)))
                .verifyComplete();
        verify(branchUseCases, times(1)).createBranch(any());
    }
}
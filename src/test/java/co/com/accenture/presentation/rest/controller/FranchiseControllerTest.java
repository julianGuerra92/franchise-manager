package co.com.accenture.presentation.rest.controller;

import co.com.accenture.domain.usecase.FranchiseUseCases;
import co.com.accenture.presentation.rest.dto.FranchiseDTO;
import co.com.accenture.presentation.rest.mapper.FranchiseDTOMapper;
import co.com.accenture.presentation.rest.mapper.FranchiseDTOMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FranchiseControllerTest {

    FranchiseUseCases franchiseUseCases;
    FranchiseDTOMapper franchiseDTOMapper;
    FranchiseController franchiseController;

    @BeforeEach
    void setUp() {
        franchiseUseCases = mock(FranchiseUseCases.class);
        franchiseDTOMapper = new FranchiseDTOMapperImpl();
        franchiseController = new FranchiseController(franchiseUseCases, franchiseDTOMapper);
    }

    @Test
    void createFranchise_shouldReturnFranchiseDTO() {
        FranchiseDTO franchiseDTO = new FranchiseDTO();
        when(franchiseUseCases.createFranchise(any())).thenReturn(Mono.just(franchiseDTOMapper.toDomain(franchiseDTO)));

        Mono<FranchiseDTO> result = franchiseController.createFranchise(franchiseDTO);

        StepVerifier.create(result)
                .expectNext(franchiseDTOMapper.toDTO(franchiseDTOMapper.toDomain(franchiseDTO)))
                .verifyComplete();
        verify(franchiseUseCases, times(1)).createFranchise(any());
    }
}
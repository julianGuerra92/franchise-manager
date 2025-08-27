package co.com.accenture.presentation.rest.controller;

import co.com.accenture.domain.usecase.CreateFranchiseUseCase;
import co.com.accenture.presentation.rest.dto.FranchiseDTO;
import co.com.accenture.presentation.rest.mapper.FranchiseDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/franchise")
@RequiredArgsConstructor
public class FranchiseController {

    private final CreateFranchiseUseCase createFranchiseUseCase;
    private final FranchiseDTOMapper franchiseDTOMapper;

    @PostMapping
    Mono<FranchiseDTO> createFranchise(@RequestBody FranchiseDTO franchiseDTO) {
        return createFranchiseUseCase
                .createFranchise(franchiseDTOMapper.toDomain(franchiseDTO)).map(franchiseDTOMapper::toDTO);
    }

}

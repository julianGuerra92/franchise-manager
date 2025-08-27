package co.com.accenture.presentation.rest.mapper;

import co.com.accenture.domain.model.Franchise;
import co.com.accenture.presentation.rest.dto.FranchiseDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FranchiseDTOMapper {
    Franchise toDomain(FranchiseDTO franchiseDTO);
    FranchiseDTO toDTO(Franchise franchise);
}

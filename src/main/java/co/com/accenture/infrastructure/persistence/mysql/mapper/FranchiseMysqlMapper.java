package co.com.accenture.infrastructure.persistence.mysql.mapper;

import co.com.accenture.domain.model.Franchise;
import co.com.accenture.infrastructure.persistence.mysql.entity.FranchiseEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FranchiseMysqlMapper {
    Franchise toDomain(FranchiseEntity franchiseEntity);
    FranchiseEntity toEntity(Franchise franchise);
}

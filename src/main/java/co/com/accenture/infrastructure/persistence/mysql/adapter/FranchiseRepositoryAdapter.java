package co.com.accenture.infrastructure.persistence.mysql.adapter;

import co.com.accenture.domain.model.Franchise;
import co.com.accenture.domain.repository.FranchiseRepository;
import co.com.accenture.infrastructure.persistence.mysql.entity.FranchiseEntity;
import co.com.accenture.infrastructure.persistence.mysql.mapper.FranchiseMysqlMapper;
import co.com.accenture.infrastructure.persistence.mysql.repository.FranchiseMysqlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FranchiseRepositoryAdapter implements FranchiseRepository {

    private final FranchiseMysqlMapper franchiseMysqlMapper;
    private final FranchiseMysqlRepository franchiseMysqlRepository;

    @Override
    public Mono<Franchise> save(Franchise franchise) {
        FranchiseEntity entity = franchiseMysqlMapper.toEntity(franchise);
        return franchiseMysqlRepository
                .save(entity)
                .map(franchiseMysqlMapper::toDomain);
    }

    @Override
    public Mono<Franchise> findById(Long id) {
        return null;
    }

    @Override
    public Mono<Franchise> updateById(Long id, Franchise franchise) {
        return null;
    }
}

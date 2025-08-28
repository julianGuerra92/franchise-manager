package co.com.accenture.infrastructure.persistence.mysql.adapter;

import co.com.accenture.domain.model.Branch;
import co.com.accenture.domain.repository.BranchRepository;
import co.com.accenture.infrastructure.persistence.mysql.entity.BranchEntity;
import co.com.accenture.infrastructure.persistence.mysql.mapper.BranchMysqlMapper;
import co.com.accenture.infrastructure.persistence.mysql.repository.BranchMysqlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BranchRepositoryAdapter implements BranchRepository {

    private final BranchMysqlMapper branchMysqlMapper;
    private final BranchMysqlRepository branchMysqlRepository;

    @Override
    public Mono<Branch> save(Branch branch) {
        BranchEntity entity = branchMysqlMapper.toEntity(branch);
        return branchMysqlRepository
                .save(entity)
                .map(branchMysqlMapper::toDomain);
    }

    @Override
    public Mono<Branch> findById(Long id) {
        return branchMysqlRepository
                .findById(id)
                .map(branchMysqlMapper::toDomain);
    }

    @Override
    public Mono<Branch> updateNameById(Long id, String name) {
        return null;
    }
}

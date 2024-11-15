package com.fgiotlead.ds.center.tb.model.repository;

import com.fgiotlead.ds.center.tb.model.entity.TbRelationEntity;
import com.fgiotlead.ds.center.tb.model.entity.pk.TbRelationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TbRelationRepository extends JpaRepository<TbRelationEntity, TbRelationPK> {

    @Query(value = "FROM TbRelationEntity re " +
            "WHERE re.id.fromId = ?1 "
    )
    List<TbRelationEntity> findAllById(UUID fromId);
}

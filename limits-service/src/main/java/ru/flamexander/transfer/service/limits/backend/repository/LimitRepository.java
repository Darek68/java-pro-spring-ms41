package ru.flamexander.transfer.service.limits.backend.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.flamexander.transfer.service.limits.backend.entities.Limit;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LimitRepository extends JpaRepository<Limit,Long> {

    @Transactional
    @Modifying
    @Query("update Limit l set l.balance = :startLimit where l.balance != :startLimit")
    int updateAll(BigDecimal startLimit);

}


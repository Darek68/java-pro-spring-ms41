package ru.flamexander.transfer.service.limits.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.flamexander.transfer.service.limits.backend.entities.Limit;

@Repository
public interface LimitRepository extends JpaRepository<Limit,Long> {
}


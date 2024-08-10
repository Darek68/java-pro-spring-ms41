package ru.flamexander.transfer.service.clients.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.flamexander.transfer.service.clients.backend.entities.Client;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {
}

package ru.flamexander.transfer.service.clients.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.flamexander.transfer.service.clients.backend.entities.Client;
import ru.flamexander.transfer.service.clients.backend.errors.ResourceNotFoundException;
import ru.flamexander.transfer.service.clients.backend.repository.ClientsRepository;

@Service
@RequiredArgsConstructor
public class ClientsService {
    private final ClientsRepository clientsRepository;

    public Client getClientInfoById(Long id) {
        return clientsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Клиент с id = " + id + " не найден"));
    }
}

package vlb.developer.profile.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import vlb.developer.entities.ClientEnty;
import vlb.developer.profile.dtos.ClientDto;
import vlb.developer.profile.repositories.ClientRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClientReadService {

    @Inject
    ClientRepository clientRepository;

    public List<ClientDto> listClients() {
        return clientRepository.listAll()
                .stream()
                .map(ClientDto::from)
                .collect(Collectors.toList());
    }

    public ClientDto getClient(UUID id) {
        ClientEnty client = clientRepository.findById(id);
        if (client == null) {
            throw new NotFoundException("Client not found");
        }
        return ClientDto.from(client);
    }
}

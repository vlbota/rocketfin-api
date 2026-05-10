package vlb.developer.profile.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import vlb.developer.entities.ClientEnty;
import vlb.developer.profile.dtos.ClientDto;
import vlb.developer.profile.forms.ClientCreateForm;
import vlb.developer.profile.forms.ClientUpdateForm;
import vlb.developer.profile.repositories.ClientRepository;

import java.util.UUID;

@ApplicationScoped
public class ClientWriteService {

    @Inject
    ClientRepository clientRepository;

    @Transactional
    public ClientDto addClient(ClientCreateForm form) {
        ClientEnty client = new ClientEnty();
        client.setName(form.name);
        client.setNickname(form.nickname);
        client.setUsername(form.username);
        client.setPassword(BcryptUtil.bcryptHash(form.password));
        client.setEmail(form.email);
        client.setCountryCode(form.countryCode);
        client.setPhoneNumber(form.phoneNumber);
        clientRepository.persist(client);
        return ClientDto.from(client);
    }

    @Transactional
    public ClientDto updateClient(UUID id, ClientUpdateForm form) {
        ClientEnty client = clientRepository.findById(id);
        if (client == null) {
            throw new NotFoundException("Client not found");
        }
        client.setName(form.name);
        client.setNickname(form.nickname);
        client.setEmail(form.email);
        client.setCountryCode(form.countryCode);
        client.setPhoneNumber(form.phoneNumber);
        return ClientDto.from(clientRepository.merge(client));
    }
}

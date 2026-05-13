package vlb.developer.bills.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import vlb.developer.bills.dtos.BillDTO;
import vlb.developer.bills.repositories.BillQueryRepository;
import vlb.developer.entities.BillEnty;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BillReadService {

    @Inject
    BillQueryRepository queryRepository;

    public List<BillDTO> listByClient(UUID clientId) {
        return queryRepository.findAllByClientId(clientId)
                .stream()
                .map(BillDTO::from)
                .toList();
    }

    public BillDTO findByIdAndClient(Long id, UUID clientId) {
        return BillDTO.from(findEntityOrThrow(id, clientId));
    }

    BillEnty findEntityOrThrow(Long id, UUID clientId) {
        return queryRepository.findByIdAndClientId(id, clientId)
                .orElseThrow(() -> new NotFoundException("Bill not found"));
    }
}

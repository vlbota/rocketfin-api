package vlb.developer.bills.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import vlb.developer.entities.BillEnty;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class BillQueryRepository {

    @Inject
    EntityManager em;

    public Optional<BillEnty> findByIdAndClientId(Long id, UUID clientId) {
        return em.createQuery(
                        "SELECT b FROM BillEnty b WHERE b.id = :id AND b.client.id = :clientId", BillEnty.class)
                .setParameter("id", id)
                .setParameter("clientId", clientId)
                .getResultStream()
                .findFirst();
    }

    public List<BillEnty> findAllByClientId(UUID clientId) {
        return em.createQuery(
                        "SELECT b FROM BillEnty b WHERE b.client.id = :clientId ORDER BY b.due DESC", BillEnty.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }
}

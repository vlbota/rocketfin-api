package vlb.developer.bills.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import vlb.developer.entities.BillHistoryEnty;

@ApplicationScoped
public class BillHistoryRepository {

    @Inject
    EntityManager em;

    public void persist(BillHistoryEnty history) {
        em.persist(history);
    }
}

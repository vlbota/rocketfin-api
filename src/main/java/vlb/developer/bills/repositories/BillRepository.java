package vlb.developer.bills.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import vlb.developer.entities.BillEnty;

@ApplicationScoped
public class BillRepository {

    @Inject
    EntityManager em;

    public void persist(BillEnty bill) {
        em.persist(bill);
    }

    public void remove(BillEnty bill) {
        em.remove(em.contains(bill) ? bill : em.merge(bill));
    }
}

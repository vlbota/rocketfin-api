package vlb.developer.bills.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import vlb.developer.entities.BillPaidEnty;

@ApplicationScoped
public class BillPaidRepository {

    @Inject
    EntityManager em;

    public void persist(BillPaidEnty billPaid) {
        em.persist(billPaid);
    }
}

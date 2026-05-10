package vlb.developer.profile.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import vlb.developer.entities.ClientEnty;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ClientRepository {

    @Inject
    EntityManager em;

    public void persist(ClientEnty client) {
        em.persist(client);
    }

    public ClientEnty merge(ClientEnty client) {
        return em.merge(client);
    }

    public ClientEnty findById(UUID id) {
        return em.find(ClientEnty.class, id);
    }

    public Optional<ClientEnty> findByUsername(String username) {
        return em.createQuery(
                        "SELECT c FROM ClientEnty c WHERE c.username = :username", ClientEnty.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    public List<ClientEnty> listAll() {
        return em.createQuery("SELECT c FROM ClientEnty c ORDER BY c.createdAt DESC", ClientEnty.class)
                .getResultList();
    }
}

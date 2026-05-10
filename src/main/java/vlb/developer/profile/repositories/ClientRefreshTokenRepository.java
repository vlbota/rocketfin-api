package vlb.developer.profile.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import vlb.developer.entities.ClientRefreshTokenEnty;

import java.util.Optional;

@ApplicationScoped
public class ClientRefreshTokenRepository {

    @Inject
    EntityManager em;

    public void persist(ClientRefreshTokenEnty token) {
        em.persist(token);
    }

    public Optional<ClientRefreshTokenEnty> findByToken(String token) {
        return em.createQuery(
                        "SELECT t FROM ClientRefreshTokenEnty t WHERE t.refreshToken = :token",
                        ClientRefreshTokenEnty.class)
                .setParameter("token", token)
                .getResultStream()
                .findFirst();
    }

    public void delete(ClientRefreshTokenEnty token) {
        em.remove(em.contains(token) ? token : em.merge(token));
    }
}

package vlb.developer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import vlb.developer.bills.enumerates.HistoryActionEnum;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "account_registry_balance_history")
public class AccountRegistryBalanceHistoryEnty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registry_id", nullable = false)
    private Long registryId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "date_balance", nullable = false)
    private LocalDate dateBalance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HistoryActionEnum action;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    public static AccountRegistryBalanceHistoryEnty from(AccountRegistryBalanceEnty registry, HistoryActionEnum action) {
        var history = new AccountRegistryBalanceHistoryEnty();
        history.setRegistryId(registry.getId());
        history.setAccountId(registry.getAccount().getId());
        history.setDateBalance(registry.getDateBalance());
        history.setAction(action);
        return history;
    }
}

package vlb.developer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import vlb.developer.bills.enumerates.HistoryActionEnum;
import vlb.developer.bills.enumerates.TypeOperationEnum;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "account_transaction_registry_history")
public class AccountTransactionRegistryHistoryEnty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOperationEnum type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HistoryActionEnum action;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    public static AccountTransactionRegistryHistoryEnty from(AccountTransactionRegistryEnty transaction, HistoryActionEnum action) {
        var history = new AccountTransactionRegistryHistoryEnty();
        history.setTransactionId(transaction.getId());
        history.setAccountId(transaction.getAccount().getId());
        history.setType(transaction.getType());
        history.setAction(action);
        return history;
    }
}

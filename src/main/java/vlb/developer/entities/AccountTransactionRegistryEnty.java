package vlb.developer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vlb.developer.bills.enumerates.TypeOperationEnum;

@Entity
@Getter
@Setter
@Table(name = "account_transaction_registry")
public class AccountTransactionRegistryEnty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEnty account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOperationEnum type;
}

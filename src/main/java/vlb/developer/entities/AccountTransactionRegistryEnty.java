package vlb.developer.entities;

import jakarta.persistence.*;
import vlb.developer.bills.enumerates.TypeOperationEnum;

@Entity
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public AccountEnty getAccount() { return account; }
    public void setAccount(AccountEnty account) { this.account = account; }

    public TypeOperationEnum getType() { return type; }
    public void setType(TypeOperationEnum type) { this.type = type; }
}

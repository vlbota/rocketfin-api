package vlb.developer.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "account_registry_balance")
public class AccountRegistryBalanceEnty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEnty account;

    @CreationTimestamp
    @ColumnDefault("current_timestamp")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "date_balance", nullable = false)
    private LocalDate dateBalance;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public AccountEnty getAccount() { return account; }
    public void setAccount(AccountEnty account) { this.account = account; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDate getDateBalance() { return dateBalance; }
    public void setDateBalance(LocalDate dateBalance) { this.dateBalance = dateBalance; }
}

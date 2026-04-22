package vlb.developer.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pay_method")
public class PayMethodEnty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEnty client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEnty account;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public ClientEnty getClient() { return client; }
    public void setClient(ClientEnty client) { this.client = client; }

    public AccountEnty getAccount() { return account; }
    public void setAccount(AccountEnty account) { this.account = account; }
}

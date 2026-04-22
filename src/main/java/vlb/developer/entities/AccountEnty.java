package vlb.developer.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "account")
public class AccountEnty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String description;

    @Column(name = "code_bank")
    private String codeBank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEnty client;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCodeBank() { return codeBank; }
    public void setCodeBank(String codeBank) { this.codeBank = codeBank; }

    public ClientEnty getClient() { return client; }
    public void setClient(ClientEnty client) { this.client = client; }
}

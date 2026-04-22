package vlb.developer.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "category_bill")
public class CategoryBillEnty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    @NotNull
    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEnty client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryBillEnty category;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public ClientEnty getClient() { return client; }
    public void setClient(ClientEnty client) { this.client = client; }

    public CategoryBillEnty getCategory() { return category; }
    public void setCategory(CategoryBillEnty category) { this.category = category; }
}

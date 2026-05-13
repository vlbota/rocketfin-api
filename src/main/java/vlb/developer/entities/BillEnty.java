package vlb.developer.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import vlb.developer.bills.enumerates.TypeOperationEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE bills SET removed = true WHERE id = ?")
@SQLRestriction("removed = false")
@Table(name = "bills")
public class BillEnty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private BigDecimal value;

    @NotNull
    @Column(nullable = false)
    private LocalDate due;

    @NotNull
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEnty client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryBillEnty category;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOperationEnum type;

    @NotNull
    @Column(nullable = false)
    private boolean paid;

    @Column(nullable = false)
    private boolean removed = false;

    public static BillEnty create(String identifier, String description, BigDecimal value, LocalDate due,
                                   TypeOperationEnum type, ClientEnty client, CategoryBillEnty category) {
        var bill = new BillEnty();
        bill.setIdentifier(identifier);
        bill.setDescription(description);
        bill.setValue(value);
        bill.setDue(due);
        bill.setType(type);
        bill.setPaid(false);
        bill.setClient(client);
        bill.setCategory(category);
        return bill;
    }

    public void update(String description, BigDecimal value, LocalDate due, CategoryBillEnty category) {
        setDescription(description);
        setValue(value);
        setDue(due);
        setCategory(category);
    }

    public BillPaidEnty pay(BigDecimal valuePaid, OffsetDateTime paidAt, PayMethodEnty payMethod, AccountEnty account) {
        setPaid(true);
        return BillPaidEnty.create(this, valuePaid, paidAt, payMethod, account);
    }
}

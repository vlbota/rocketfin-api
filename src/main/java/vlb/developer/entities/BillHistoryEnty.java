package vlb.developer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import vlb.developer.bills.enumerates.HistoryActionEnum;
import vlb.developer.bills.enumerates.TypeOperationEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "bill_history")
public class BillHistoryEnty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bill_id", nullable = false)
    private Long billId;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @Column(name = "category_id")
    private Long categoryId;

    private String identifier;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private LocalDate due;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOperationEnum type;

    @Column(nullable = false)
    private boolean paid;

    @Column(nullable = false)
    private boolean removed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HistoryActionEnum action;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    public static BillHistoryEnty from(BillEnty bill, HistoryActionEnum action) {
        var history = new BillHistoryEnty();
        history.setBillId(bill.getId());
        history.setClientId(bill.getClient().getId());
        history.setCategoryId(bill.getCategory() != null ? bill.getCategory().getId() : null);
        history.setIdentifier(bill.getIdentifier());
        history.setDescription(bill.getDescription());
        history.setValue(bill.getValue());
        history.setDue(bill.getDue());
        history.setType(bill.getType());
        history.setPaid(bill.isPaid());
        history.setRemoved(bill.isRemoved());
        history.setAction(action);
        return history;
    }
}

package vlb.developer.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "bill_paid")
public class BillPaidEnty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PayMethodEnty paymentMethod;

    @Column(name = "value_paid")
    private BigDecimal valuePaid;

    @Column(name = "paid_at")
    private OffsetDateTime paidAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    private BillEnty bill;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PayMethodEnty getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PayMethodEnty paymentMethod) { this.paymentMethod = paymentMethod; }

    public BigDecimal getValuePaid() { return valuePaid; }
    public void setValuePaid(BigDecimal valuePaid) { this.valuePaid = valuePaid; }

    public OffsetDateTime getPaidAt() { return paidAt; }
    public void setPaidAt(OffsetDateTime paidAt) { this.paidAt = paidAt; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public BillEnty getBill() { return bill; }
    public void setBill(BillEnty bill) { this.bill = bill; }
}

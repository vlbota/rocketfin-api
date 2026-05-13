package vlb.developer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
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
    @JoinColumn(name = "account")
    private AccountEnty account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    private BillEnty bill;

    public static BillPaidEnty create(BillEnty bill, BigDecimal valuePaid, OffsetDateTime paidAt,
                                       PayMethodEnty payMethod, AccountEnty account) {
        var paid = new BillPaidEnty();
        paid.setBill(bill);
        paid.setValuePaid(valuePaid);
        paid.setPaidAt(paidAt);
        paid.setPaymentMethod(payMethod);
        paid.setAccount(account);
        return paid;
    }
}

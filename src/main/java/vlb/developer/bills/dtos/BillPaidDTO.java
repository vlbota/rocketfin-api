package vlb.developer.bills.dtos;

import lombok.Getter;
import vlb.developer.entities.BillPaidEnty;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
public class BillPaidDTO {

    private final Long id;
    private final Long billId;
    private final BigDecimal valuePaid;
    private final OffsetDateTime paidAt;
    private final OffsetDateTime createdAt;
    private final Long paymentMethodId;
    private final Long accountId;

    public BillPaidDTO(BillPaidEnty billPaid) {
        this.id = billPaid.getId();
        this.billId = billPaid.getBill().getId();
        this.valuePaid = billPaid.getValuePaid();
        this.paidAt = billPaid.getPaidAt();
        this.createdAt = billPaid.getCreatedAt();
        this.paymentMethodId = billPaid.getPaymentMethod() != null ? billPaid.getPaymentMethod().getId() : null;
        this.accountId = billPaid.getAccount() != null ? billPaid.getAccount().getId() : null;
    }

    public static BillPaidDTO from(BillPaidEnty billPaid) {
        return new BillPaidDTO(billPaid);
    }
}

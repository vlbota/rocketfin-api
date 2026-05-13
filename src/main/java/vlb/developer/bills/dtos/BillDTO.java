package vlb.developer.bills.dtos;

import lombok.Getter;
import vlb.developer.bills.enumerates.TypeOperationEnum;
import vlb.developer.entities.BillEnty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
public class BillDTO {

    private final Long id;
    private final String identifier;
    private final String description;
    private final BigDecimal value;
    private final LocalDate due;
    private final TypeOperationEnum type;
    private final boolean paid;
    private final OffsetDateTime createdAt;
    private final Long categoryId;

    public BillDTO(BillEnty bill) {
        this.id = bill.getId();
        this.identifier = bill.getIdentifier();
        this.description = bill.getDescription();
        this.value = bill.getValue();
        this.due = bill.getDue();
        this.type = bill.getType();
        this.paid = bill.isPaid();
        this.createdAt = bill.getCreatedAt();
        this.categoryId = bill.getCategory() != null ? bill.getCategory().getId() : null;
    }

    public static BillDTO from(BillEnty bill) {
        return new BillDTO(bill);
    }
}

package vlb.developer.bills.forms;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record BillPayFORM(
        @NotNull BigDecimal valuePaid,
        @NotNull OffsetDateTime paidAt,
        Long paymentMethodId,
        Long accountId
) {}

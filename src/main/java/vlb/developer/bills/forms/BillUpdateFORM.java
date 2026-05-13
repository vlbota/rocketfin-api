package vlb.developer.bills.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BillUpdateFORM(
        @NotBlank String description,
        @NotNull BigDecimal value,
        @NotNull LocalDate due,
        Long categoryId
) {}

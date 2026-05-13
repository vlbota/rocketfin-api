package vlb.developer.bills.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import vlb.developer.bills.enumerates.TypeOperationEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BillCreateFORM(
        String identifier,
        @NotBlank String description,
        @NotNull BigDecimal value,
        @NotNull LocalDate due,
        @NotNull TypeOperationEnum type,
        Long categoryId
) {}

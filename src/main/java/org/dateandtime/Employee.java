package org.dateandtime;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record Employee( Long employeeId, String firstName, String lastName, LocalDate employmentDate, BigDecimal yearlySalary, PensionPlan pensionPlan) {

}

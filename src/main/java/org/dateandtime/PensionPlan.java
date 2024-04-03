package org.dateandtime;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PensionPlan(String planReferenceNumber, LocalDate enrollmentDate, BigDecimal monthlyContribution) {
}

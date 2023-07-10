import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

public class AccountingUnitTest {

	@Test
    public void testCalculateSalaryForFullTimeWorker() {

        Accounting accounting = new Accounting();

        accounting.hourlyRate = 120;
        LocalDate now = LocalDate.now();

        LocalDate firstDayOfMonth = now.withDayOfMonth(1);
        LocalDate lastDayOfMonth = now.withDayOfMonth(now.lengthOfMonth());
        long workingDays = ChronoUnit.DAYS.between(firstDayOfMonth, lastDayOfMonth) + 1;
        long weekends = workingDays / 7 * 2;
        long weekdays = workingDays - weekends;
        
        long totalWorkingHours = weekdays * 8;
        double expectedSalary = totalWorkingHours * accounting.hourlyRate;
  
        double actualSalary = accounting.calculateSalaryForFullTimeWorker();

        assertEquals(expectedSalary, actualSalary);
    }
}


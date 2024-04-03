package org.dateandtime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.dateandtime.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    List<Employee> employees = new ArrayList<>();

    public Main() {
        employees.add(new Employee(1L, "Daniel", "Agar", LocalDate.parse("2018-01-17"), new BigDecimal("105945.50"), new PensionPlan("EX1089", LocalDate.parse("2023-01-17"), new BigDecimal("100.00"))));
        employees.add(new Employee(2L, "Bernard", "Shaw", LocalDate.parse("2019-04-03"), new BigDecimal("197750.00"), null));
        employees.add(new Employee(3L, "Carly", "Agar", LocalDate.parse("2014-05-16"), new BigDecimal("842000.75"), new PensionPlan("SM2307", LocalDate.parse("2019-11-04"), new BigDecimal("1555.50"))));
        employees.add(new Employee(4L, "Wesley", "Schneider", LocalDate.parse("2019-05-02"), new BigDecimal("74500.00"), null));
    }

    public void printEmployeesInJSON() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        employees.sort(Comparator.comparing(Employee::lastName).thenComparing(Employee::yearlySalary).reversed());
        System.out.println(gson.toJson(employees));
    }

    public void printMonthlyUpcomingEnrolleesReport() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        LocalDate today = LocalDate.now();
        LocalDate nextMonthFirstDay = today.plusMonths(1).withDayOfMonth(1);
        LocalDate nextMonthLastDay = nextMonthFirstDay.plusMonths(1).minusDays(1);

        List<Employee> upcomingEnrollees = employees.stream()
                .filter(employee -> employee.pensionPlan() == null &&
                        employee.employmentDate().plusYears(5).isAfter(nextMonthFirstDay) &&
                        employee.employmentDate().plusYears(5).isBefore(nextMonthLastDay))
                .sorted(Comparator.comparing(Employee::employmentDate))
                .collect(Collectors.toList());

        System.out.println(gson.toJson(upcomingEnrollees));
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.printEmployeesInJSON();
        System.out.println("-------------------------------");
        System.out.println("printMonthlyUpcomingEnrolleesReport");
        app.printMonthlyUpcomingEnrolleesReport();
    }
}

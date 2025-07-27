package com.example.streams;

import com.example.dto.Employee;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class ToMapStreamPractice {

    public static void main(String[] args) {

        //1. Map Employee Id → Employee Name
        //✅ Simple direct mapping.
        //Input:
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", 30, 60000, "Male", "HR", "New York", 2015),
                new Employee(2, "Alice", 28, 80000, "Female", "IT", "New York", 2016)
        );

        out.println("Map Employee Id → Employee Name : " + employees.stream()
                .collect(Collectors.toMap(x -> x.getId(), t -> t.getName())));

        out.println();

        //2. Map Department → List of Employee Names
        //✅ Slightly complex.

        //Input:

        List<Employee> employeesMap = Arrays.asList(
                new Employee(1, "John", 30, 60000, "Male", "HR", "New York", 2015),
                new Employee(2, "Alice", 28, 80000, "Female", "IT", "New York", 2016),
                new Employee(3, "Steve", 32, 85000, "Male", "IT", "San Francisco", 2017)
        );

        out.println(employeesMap.stream()
                .collect(Collectors.groupingBy(x -> x.getDeptName(), Collectors.mapping(e -> e.getName(), Collectors.toList()))));

        out.println();

        //3. Map Gender → Count of Employees
        //✅ Combine with counting.
        //
        //Input:
        //
        List<Employee> employeesMapCount = Arrays.asList(
                new Employee(1, "John", 30, 60000, "Male", "HR", "New York", 2015),
                new Employee(2, "Alice", 28, 80000, "Female", "IT", "New York", 2016),
                new Employee(3, "Eve", 29, 70000, "Female", "Finance", "Chicago", 2014)
        );

        out.println("Map Gender → Count of Employees : " + employeesMapCount.stream()
                .collect(Collectors.groupingBy(x -> x.getGender(), Collectors.counting())));

        //or alternate solution using toMap merge

        out.println(employeesMapCount.stream()
                .collect(Collectors.toMap(x -> x.getGender(), v -> 1, (a, b) -> a + b)));


        //4. Map City → Max Salary Employee
        //✅ Combine with maxBy comparator.

        //Input:

        List<Employee> employees1 = Arrays.asList(
                new Employee(1, "John", 30, 60000, "Male", "HR", "New York", 2015),
                new Employee(2, "Alice", 28, 80000, "Female", "IT", "New York", 2016),
                new Employee(3, "Steve", 32, 85000, "Male", "IT", "San Francisco", 2017)
        );


        out.println(employees1.stream()
                .collect(Collectors.toMap(x -> x.getCity(), e -> e, (a, b) -> a.getSalary() > b.getSalary() ? a : b)));

        // or alternate solution using grouppingby but this one give answer in optional

        out.println(employees1.stream()
                .collect(Collectors.groupingBy(e -> e.getCity(), Collectors.maxBy((a, b) -> Long.compare(a.getSalary(), b.getSalary())))));


        //5. Company → City → List of Employees
        //✅ Nested toMap (level-2)

        //Input:

        Map<String, List<Employee>> companyEmployeeMap = new HashMap<>();
        companyEmployeeMap.put("CompanyA", Arrays.asList(
            new Employee(1, "John", 30, 60000, "Male", "HR", "New York", 2015),
            new Employee(2, "Alice", 28, 80000, "Female", "IT", "Chicago", 2016)
        ));
        companyEmployeeMap.put("CompanyB", Arrays.asList(
            new Employee(3, "Steve", 32, 85000, "Male", "Finance", "San Francisco", 2017)
        ));


        out.println("Company → City → List of Employees : "+companyEmployeeMap.entrySet().stream()
                .collect(Collectors.toMap(x->x.getKey()
                        ,x->x.getValue().stream().collect(Collectors.groupingBy(p->p.getCity(),
                                Collectors.toList())))));

//        companyEmployeeMap.entrySet().stream()
//                .collect(Collectors.groupingBy(x->x.getKey()
//                        ,x->x.getValue().stream()
//                                .collect(Collectors.groupingBy(p->p.getCity(),
//                                Collectors.toList()))));


    }
}

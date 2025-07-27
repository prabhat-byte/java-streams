package com.example.streams;

import com.example.dto.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class PraticeProblemsOnStreams {

    public static void main(String[] args) {

        List<Employee> empList = new ArrayList<>();

        empList.add(new Employee(1, "abc", 28, 123, "F", "HR", "Blore", 2020));

        empList.add(new Employee(2, "xyzvv", 29, 120, "F", "HR", "Hyderabad", 2015));

        empList.add(new Employee(3, "efgkkklo", 30, 115, "M", "HR", "Chennai", 2014));

        empList.add(new Employee(4, "defiiooopmnk", 32, 125, "F", "HR", "Chennai", 2013));

        empList.add(new Employee(5, "ijkm", 22, 150, "F", "IT", "Noida", 2013));

        empList.add(new Employee(6, "mn", 27, 140, "M", "IT", "Chennai", 2017));

        empList.add(new Employee(7, "uvw", 26, 130, "F", "IT", "Pune", 2016));

        empList.add(new Employee(8, "pqr", 23, 145, "M", "IT", "Trivandam", 2015));

        empList.add(new Employee(9, "stv", 25, 160, "M", "IT", "Blore", 2010));

//        List<Employee> newEmpList = new ArrayList<>();
//
//        newEmpList.add(new Employee(101, "Ram", 28, 50000, "M", "HR", "Blore", 2020));
//        newEmpList.add(new Employee(102, "Shyam", 30, 45000, "M", "HR", "Delhi", 2019));
//        newEmpList.add(new Employee(103, "Mohan", 32, 70000, "M", "IT", "Mumbai", 2018));
//        newEmpList.add(new Employee(104, "Sohan", 26, 40000, "M", "IT", "Pune", 2021));
//        newEmpList.add(new Employee(105, "Rohan", 29, 75000, "M", "Sales", "Chennai", 2017));
//        newEmpList.add(new Employee(106, "Gohan", 31, 65000, "M", "Sales", "Hyderabad", 2016));

        // Question 1. Find the count of male and female employees present in the organization.
        out.println(empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())));

        //Question 2 .  Find all employees who lives in ‘Blore’ city, sort them by their name and print the names of employees.
        List<Employee> blore = empList.stream().filter(x -> x.getCity().equals("Blore")).sorted((a, b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());
        blore.stream().forEach(x -> out.println(x.getName()));


        //Question 3 . Find largest 3rd element from the list
        out.println(Arrays.asList(1, 2, 3, 4, 5).stream().distinct().sorted(Comparator.reverseOrder()).skip(2).findFirst().get());

        //Question 4.Group employees by gender and display the count of employees in each gender
        out.println(empList.stream().collect(Collectors.groupingBy(x -> x.getGender(), Collectors.counting())));

        //Question 5.Find the department which has the maximum number of employees
        Map<String, Long> deptWithMaxEmployees = empList.stream().collect(Collectors.groupingBy(x -> x.getDeptName(), Collectors.counting()));
        Optional<Map.Entry<String, Long>> max = deptWithMaxEmployees.entrySet().stream().max((a, b) -> a.getValue().compareTo(b.getValue()));
        out.println(max.get());

        //Question 6 . Find the name of the youngest male employee in the 'HR' department.
        out.println(empList.stream()
                .filter(x -> x.getDeptName().equals("HR") && x.getGender().equals("M")).
                collect(Collectors.minBy((a, b) -> a.getAge() - b.getAge())).get().getName());

        //Question 7. Find the average salary of male and female employees separately.
        out.println(empList.stream().collect(Collectors.groupingBy(x -> x.getGender(), Collectors.averagingLong(x -> x.getSalary()))));

        //Question 8 . Partition employees into two groups: those who are younger than 30 and those who are 30 or older.
        out.println(empList.stream().collect(Collectors.partitioningBy(x -> x.getAge() < 30)));

        //Question 9. Find the second highest salary among employees.
        Optional<Long> first = empList.stream().map(x -> x.getSalary()).collect(Collectors.toList()).
                stream().
                distinct()
                .sorted((a, b) -> b.compareTo(a))
                .skip(1).findFirst();

        // or alternate solution of question 9

        Optional<Long> secondHighest = empList.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();

        System.out.println("Second highest salary: " + secondHighest.get());

        // Question 10 . List names of employees who earn more than the average salary

        Double averageSalary = empList.stream().collect(Collectors.averagingLong(x -> x.getSalary()));
        out.println("Average Salary :" + averageSalary);
        out.println(empList.stream().filter(x -> x.getSalary() > averageSalary).map(x -> x.getName()).collect(Collectors.toList()));


        //Question 11 . Find the employee with the longest name.
        out.println(empList.stream().
                collect(Collectors.groupingBy(x -> x.getName().length())).
                entrySet().stream().
                max((a, b) -> a.getKey() - b.getKey()).get());

        // or alternative solution for question 11

        Employee empWithLongestName = empList.stream()
                .max(Comparator.comparingInt(e -> e.getName().length()))
                .get();
        // this will give only one employee if one than one having the longest name
        // just remember - When question says "Find the employee...", don't group — directly filter, sort, or max/min.


        // Question 12 . Find the sum of salaries for employees living in ‘Chennai’.

        out.println("sum of salaries for employees living in ‘Chennai’ :" + empList.stream().filter(x -> x.getCity().equals("Chennai")).collect(Collectors.summingLong(x -> x.getSalary())));

        // Question 13 . Find the average salary of employees living in each city (group by city).
        out.println("average salary of employees living in each city :" + empList.stream().collect(Collectors.groupingBy(x -> x.getCity(), Collectors.averagingLong(x -> x.getSalary()))));

        // Question 14 . Find the employee with the maximum salary in each department
        out.println("employee with the maximum salary in each department : " + empList.stream().collect(Collectors.groupingBy(x -> x.getDeptName(), Collectors.maxBy((a, b) -> Long.compare(a.getSalary(), b.getSalary())))));

        // or , Alternate solution for question 14 is clearly see difference between both maxBy()
        out.println("employee with the maximum salary in each department : " + empList.stream().collect(Collectors.groupingBy(x -> x.getDeptName(), Collectors.maxBy(Comparator.comparing(Employee::getSalary)))));

        // Question 15 . Find the department which has the maximum number of employees.
        out.println("department which has the maximum number of employees : " + empList.stream().
                collect(Collectors.groupingBy(x -> x.getDeptName(), Collectors.counting()))
                .entrySet().stream()
                .max((a, b) -> a.getValue().compareTo(b.getValue()))
                .get());

        // Question 16 . Find the name of the youngest male employee in the 'HR' department.
        out.println("name of the youngest male employee in the 'HR' department : " + empList.stream().filter(x -> x.getDeptName().equals("HR") && x.getGender().equals("M")).min((a, b) -> Integer.compare(a.getAge(), b.getAge())).get().getName());

        // Question 17 . Sort employees first by department name, then by salary (ascending).
        out.println(empList.stream().
                sorted(Comparator.comparing(Employee::getDeptName)).
                sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.toList()));

        // here wrong thing i did is sorted() are two times soo the last one will override so its wrong
        // insted of use thenComparing() in  this way see below

        out.println(
                empList.stream()
                        .sorted(Comparator.comparing(Employee::getDeptName)
                                .thenComparing(Employee::getSalary))
                        .collect(Collectors.toList()));

        // this one is correct here if department is same then it will sort by salary hope you understand now
        /* First sort by DeptName (A-Z),
        If same department, then sort by Salary (ascending).
         */


        // Question 18 . Find the average age of employees in each city.
        out.println("the average age of employees in each city : " + empList.stream().collect(Collectors.groupingBy(x -> x.getCity(), Collectors.averagingInt(x -> x.getAge()))));

        // Question 19 . Generate a comma-separated string of employee names who work in 'HR' department.
        out.println("comma-separated string of employee names who work in 'HR' department : " + empList.stream().
                filter(x -> x.getDeptName().equals("HR"))
                .map(x -> x.getName())
                .collect(Collectors.joining(", ")));

        // Question 20 . Check if there is any employee from ‘Chennai’ city
        out.println(empList.stream().anyMatch(x -> x.getCity().equals("Chennai")));

        // Question 21 . List the top 3 highest paid employees
        out.println("List the top 3 highest paid employees : " + empList.stream()
                .distinct()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(3).collect(Collectors.toList()));

    }
}

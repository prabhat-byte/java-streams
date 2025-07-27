package com.example.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

import static java.lang.System.out;

public class GroupingMappingQuestions {


    public static class Employee {
        String name;
        String dept;
        int salary;

        public Employee(String name, String dept, int salary) {
            this.name = name;
            this.dept = dept;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDept() {
            return dept;
        }

        public void setDept(String dept) {
            this.dept = dept;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) {

        //🔹 Q1. Map of Starting Letter to Longest Word
        //Input:
        //["ant", "apple", "bat", "ball", "butterfly", "cat"]
        //Output:
        //{a=apple, b=butterfly, c=cat}

        out.println(Arrays.asList("ant", "apple", "bat", "ball", "butterfly", "cat").stream()
                .collect(Collectors.groupingBy(x -> x.charAt(0), Collectors.maxBy(Comparator.comparingInt(String::length))
                )));

        // alternate solution

        out.println(Arrays.asList("ant", "apple", "bat", "ball", "butterfly", "cat").stream()
                .collect(Collectors.groupingBy(
                        x -> x.charAt(0),
                        Collectors.mapping(
                                y -> y,
                                Collectors.maxBy((a, b) -> a.length() - b.length())
                        )
                )));

        // but the both solution above is giving optional but we if we need to wrap it use collectiongAndThen


        out.println(Arrays.asList("ant", "apple", "bat", "ball", "butterfly", "cat").stream()
                .collect(Collectors.groupingBy(
                        x -> x.charAt(0),
                        Collectors.collectingAndThen(
                                Collectors.mapping(
                                        y -> y,
                                        Collectors.maxBy((a, b) -> a.length() - b.length())
                                ),
                                Optional::get

                        )

                )));


        // Q2. Map of Word Length to Smallest Word (lexicographically)
        //Input:
        //["go", "java", "python", "c", "cpp", "ruby", "perl"]
        //Output:
        //{2=go, 4=java, 6=python, 1=c, 3=cpp}

        out.println(Arrays.asList("go", "java", "python", "c", "cpp", "ruby", "perl").stream()
                .collect(Collectors.groupingBy(x -> x.length(), Collectors.collectingAndThen(
                        Collectors.mapping(
                                p -> p,
                                Collectors.minBy((a, b) -> a.compareTo(b))
                        ),
                        Optional::get

                ))));

        //🔹 Q3. Employee with Highest Salary by Department
        //List<Employee> employees = List.of(
        //    new Employee("Amit", "HR", 45000),
        //    new Employee("Ravi", "IT", 90000),
        //    new Employee("Neha", "HR", 50000),
        //    new Employee("Sonal", "IT", 80000),
        //    new Employee("Vikas", "Sales", 70000)
        //);
        //Output:
        //{HR=Neha, IT=Ravi, Sales=Vikas}


        List<Employee> employees = List.of(
                new Employee("Amit", "HR", 45000),
                new Employee("Ravi", "IT", 90000),
                new Employee("Neha", "HR", 50000),
                new Employee("Sonal", "IT", 80000),
                new Employee("Vikas", "Sales", 70000)
        );


        out.println(employees.stream().collect(Collectors.groupingBy(x -> x.dept, Collectors.collectingAndThen(
                Collectors.mapping(
                        p -> p,
                        Collectors.maxBy((a, b) -> a.salary - b.salary)
                ),
                Optional::get
        ))));


        //Q4. First Character → Longest Word
        //Input:
        //["elephant", "egg", "eagle", "tiger", "top", "tree"]
        //Output:
        //{e=elephant, t=tiger}


        out.println(String.valueOf(Arrays.asList("elephant", "egg", "eagle", "tiger", "top", "tree").stream()
                .collect(Collectors.groupingBy(x -> x.charAt(0),
                          LinkedHashMap::new
                        , Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(String::length)),
                                Optional::get
                        )
                ))));


        //🔹 Q5. Department → List of Employee Names (sorted)
        //List<Employee> employees = List.of(
        //    new Employee("Zara", "Finance", 55000),
        //    new Employee("Ayan", "Finance", 48000),
        //    new Employee("Bala", "HR", 60000),
        //    new Employee("Chitra", "HR", 62000)
        //);
        //Output:
        //{Finance=[Ayan, Zara], HR=[Bala, Chitra]}


        List<Employee> employee = List.of(
                new Employee("Zara", "Finance", 55000),
                new Employee("Ayan", "Finance", 48000),
                new Employee("Bala", "HR", 60000),
                new Employee("Chitra", "HR", 62000)
        );

        out.println(employee.stream().sorted((a, b) -> a.name.compareTo(b.getName()))
                .collect(Collectors.groupingBy(x -> x.dept,
                        Collectors.mapping(
                                y -> y.name,
                                Collectors.toList()
                        )
                )));


    }
}

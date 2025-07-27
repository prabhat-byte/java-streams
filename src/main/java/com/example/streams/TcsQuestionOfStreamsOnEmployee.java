package com.example.streams;

import com.example.dto.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class TcsQuestionOfStreamsOnEmployee {

    public static void main(String[] args) {

        List<Employee> empList = new ArrayList<>();

        empList.add(new Employee(1, "abc", 28, 123, "F", "HR", "Blore", 2020));

        empList.add(new Employee(2, "xyz", 29, 120, "F", "HR", "Hyderabad", 2015));

        empList.add(new Employee(3, "efg", 30, 115, "M", "HR", "Chennai", 2014));

        empList.add(new Employee(4, "def", 32, 125, "F", "HR", "Chennai", 2013));

        empList.add(new Employee(5, "ijk", 22, 150, "F", "IT", "Noida", 2013));

        empList.add(new Employee(6, "mno", 27, 140, "M", "IT", "Gurugram", 2017));

        empList.add(new Employee(7, "uvw", 26, 130, "F", "IT", "Pune", 2016));

        empList.add(new Employee(8, "pqr", 23, 145, "M", "IT", "Trivandam", 2015));

        empList.add(new Employee(9, "stv", 25, 160, "M", "IT", "Blore", 2010));

        // Question 1. Find the count of male and female employees present in the organization.
        out.println(empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())));

        //Question 2 .  Find all employees who lives in ‘Blore’ city, sort them by their name and print the names of employees.

        List<Employee> blore = empList.stream().filter(x -> x.getCity().equals("Blore")).sorted((a, b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
        blore.stream().forEach(x -> out.println(x.getName()));


        //Question 3 . Find largest 3rd element from the list

        out.println(Arrays.asList(1, 2, 3, 4, 5).stream().distinct().sorted().skip(2).findFirst().get());


    }

}

package com.example.streams;

import com.example.dto.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class FlatMapWithEmployee {
    public static void main(String[] args) {

        //1. Find the Highest Paid Employee in Each Department, Across All Locations

        Map<String, Map<String, List<Employee>>> companyBranchMap = new HashMap<>();

        companyBranchMap.put("CompanyA", new HashMap<String, List<Employee>>() {{
            put("New York", Arrays.asList(
                    new Employee(1, "John", 30, 60000, "Male", "HR", "New York", 2015),
                    new Employee(2, "Alice", 28, 80000, "Female", "IT", "New York", 2016)
            ));
            put("Chicago", Arrays.asList(
                    new Employee(3, "Bob", 35, 75000, "Male", "Finance", "Chicago", 2013)
            ));
        }});
        companyBranchMap.put("CompanyB", new HashMap<String, List<Employee>>() {{
            put("San Francisco", Arrays.asList(
                    new Employee(4, "Steve", 32, 85000, "Male", "IT", "San Francisco", 2017),
                    new Employee(5, "Tom", 29, 40000, "Male", "HR", "San Francisco", 2018)
            ));
        }});

        out.println(companyBranchMap.values().stream()
                .flatMap(x -> {
                    return x.values().stream().flatMap(k -> k.stream());
                })
                .collect(Collectors.
                        groupingBy(x -> x.getDeptName(),
                                Collectors.maxBy((a, b) -> Long.compare(a.getSalary(), b.getSalary())))));

        out.println();


        //2. Group Employees by Gender and Then City
        //Input:

        List<Map<String, List<Employee>>> branchWiseEmployeeList = new ArrayList<>();

        branchWiseEmployeeList.add(new HashMap<String, List<Employee>>() {{
            put("TeamA", Arrays.asList(
                    new Employee(1, "John", 30, 60000, "Male", "HR", "New York", 2015),
                    new Employee(2, "Alice", 28, 80000, "Female", "IT", "New York", 2016)
            ));
        }});
        branchWiseEmployeeList.add(new HashMap<String, List<Employee>>() {{
            put("TeamB", Arrays.asList(
                    new Employee(3, "Steve", 32, 85000, "Male", "IT", "San Francisco", 2017),
                    new Employee(4, "Eve", 27, 45000, "Female", "Finance", "Chicago", 2014)
            ));
        }});

        out.println(branchWiseEmployeeList.stream()
                .flatMap(x -> {
                    return x.values().stream().flatMap(k -> k.stream());
                }).collect(Collectors.groupingBy(p -> p.getGender(), Collectors.groupingBy(c -> c.getCity()))));


        out.println();

        //3. Find the Average Salary Per Department Across All Divisions
        //Input:

        List<Map<String, Map<String, List<Employee>>>> multipleCompaniesData = new ArrayList<>();

        multipleCompaniesData.add(new HashMap<String, Map<String, List<Employee>>>() {{
            put("CompanyA", new HashMap<String, List<Employee>>() {{
                put("Division1", Arrays.asList(
                        new Employee(1, "John", 30, 60000, "Male", "HR", "New York", 2015),
                        new Employee(2, "Alice", 28, 80000, "Female", "IT", "New York", 2016)
                ));
                put("Division2", Arrays.asList(
                        new Employee(3, "Bob", 35, 70000, "Male", "Finance", "Chicago", 2013)
                ));
            }});
        }});
        multipleCompaniesData.add(new HashMap<String, Map<String, List<Employee>>>() {{
            put("CompanyB", new HashMap<String, List<Employee>>() {{
                put("Division1", Arrays.asList(
                        new Employee(4, "Steve", 32, 90000, "Male", "IT", "San Francisco", 2017),
                        new Employee(5, "Tom", 29, 50000, "Male", "HR", "San Francisco", 2018)
                ));
            }});
        }});

        out.println(multipleCompaniesData.stream().flatMap(x -> {
            return x.values().stream().flatMap(p -> {
                return p.values().stream().flatMap(c -> c.stream());
            });
        }).collect(Collectors.groupingBy(x -> x.getDeptName(), Collectors.averagingLong(x -> x.getSalary()))));


        out.println();

        //4. Find Employees Joined Before 2015 But Have Salary > 55000
        //Input:


        Map<String, List<Map<String, List<Employee>>>> multiLayerData = new HashMap<>();

        multiLayerData.put("CompanyA", Arrays.asList(
                new HashMap<String, List<Employee>>() {{
                    put("Team1", Arrays.asList(
                            new Employee(1, "John", 30, 60000, "Male", "HR", "New York", 2012),
                            new Employee(2, "Alice", 28, 80000, "Female", "IT", "New York", 2016)
                    ));
                }},
                new HashMap<String, List<Employee>>() {{
                    put("Team2", Arrays.asList(
                            new Employee(3, "Bob", 35, 70000, "Male", "Finance", "Chicago", 2011)
                    ));
                }}
        ));
        multiLayerData.put("CompanyB", Arrays.asList(
                new HashMap<String, List<Employee>>() {{
                    put("Team3", Arrays.asList(
                            new Employee(4, "Steve", 32, 85000, "Male", "IT", "San Francisco", 2017),
                            new Employee(5, "Eve", 27, 45000, "Female", "Finance", "Chicago", 2014)
                    ));
                }}
        ));


        out.println(multiLayerData.values().stream()
                .flatMap(x -> {
                    return x.stream().flatMap(p -> {
                        return p.values().stream()
                                .flatMap(t -> t.stream());
                    });
                }).filter(x -> x.getYearOfJoining() < 2015 && x.getSalary() > 55000)
                .collect(Collectors.toList()));


        out.println();

        //5. Get Count of Employees Per Company Per City
        //Input:

        Map<String, Map<String, List<Employee>>> companyCityData = new HashMap<>();

        companyCityData.put("CompanyA", new HashMap<String, List<Employee>>() {{
            put("New York", Arrays.asList(
                    new Employee(1, "John", 30, 60000, "Male", "HR", "New York", 2015),
                    new Employee(2, "Alice", 28, 80000, "Female", "IT", "New York", 2016)
            ));
            put("Chicago", Arrays.asList(
                    new Employee(3, "Bob", 35, 75000, "Male", "Finance", "Chicago", 2013)
            ));
        }});
        companyCityData.put("CompanyB", new HashMap<String, List<Employee>>() {{
            put("San Francisco", Arrays.asList(
                    new Employee(4, "Steve", 32, 85000, "Male", "IT", "San Francisco", 2017),
                    new Employee(5, "Tom", 29, 40000, "Male", "HR", "San Francisco", 2018)
            ));
        }});

        out.println(
                companyCityData.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, // Company Name
                                companyEntry -> companyEntry.getValue() // Map<City, List<Employee>>
                                        .entrySet().stream()
                                        .collect(Collectors.toMap(
                                                Map.Entry::getKey, // City Name
                                                cityEntry -> (long) cityEntry.getValue().size() // Count of Employees in that city
                                        ))
                        ))
        );
        out.println();

        out.println(companyCityData.entrySet().stream()
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()
                        .entrySet().stream()
                        .collect(Collectors.toMap(
                                city -> city.getKey(),
                                city -> city.getValue()
                                        .stream()
                                        .collect(Collectors.counting())
                        ))
                )));


    }


}

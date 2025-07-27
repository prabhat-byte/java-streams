package com.example.streams;

import com.example.dto.EmpDeptAndNameDto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

public class PraticingFlatMapOnMaps {
    public static void main(String[] args) {

        //1. Flatten a Map<String, List<Integer>> to a Single List of Integers
        //Problem: Given a Map<String, List<Integer>>,
        // flatten all the values (lists of integers) into a single list of integers.

        Map<String, List<Integer>> map = new HashMap<>();
        map.put("A", Arrays.asList(1, 2, 3));
        map.put("B", Arrays.asList(4, 5));
        map.put("C", Arrays.asList(6, 7, 8));

        out.println(map.values()
                .stream()
                .flatMap(x -> x.stream())
                .collect(Collectors.toList()));


        //2. Extract All Keys from a Map<String, List<String>>
        //Problem: Given a Map<String, List<String>>,
        // extract all the values (i.e., lists of strings) from the map and flatten them into a single list of strings.

        Map<String, List<String>> departmentMap = new HashMap<>();
        departmentMap.put("HR", Arrays.asList("John", "Alice", "Bob"));
        departmentMap.put("IT", Arrays.asList("Steve", "Tom"));
        departmentMap.put("Finance", Arrays.asList("Rick", "Eve"));

        out.println(departmentMap.keySet().stream()
                .flatMap(x -> Arrays.stream(x.split(" ")))
                .collect(Collectors.toList()));

        // 3. Flatten a Nested Map<String, Map<Integer, List<String>>>
        // Problem: Given a Map<String, Map<Integer, List<String>>>,
        // flatten the entire structure into a single list of strings while preserving the data order.


        Map<String, Map<Integer, List<String>>> nestedMap = new HashMap<>();
        nestedMap.put("HR", new HashMap() {{
            put(1, Arrays.asList("John", "Alice"));
            put(2, Arrays.asList("Bob", "Charlie"));
        }});
        nestedMap.put("IT", new HashMap() {{
            put(1, Arrays.asList("Steve", "Tom"));
            put(2, Arrays.asList("Rick", "Eve"));
        }});

        out.println(nestedMap.entrySet().stream().flatMap(x -> {
            return x.getValue().entrySet().stream().flatMap(k -> {
                return k.getValue().stream();
            });
        }).collect(Collectors.toList()));

        //Extract All Values from a Map<String, List<Integer>> and Filter Odd Numbers
        //Problem: Given a Map<String, List<Integer>>,
        // flatten the lists and filter only the odd numbers.


        Map<String, List<Integer>> mapNumbers = new HashMap<>();
        mapNumbers.put("A", Arrays.asList(1, 2, 3));
        mapNumbers.put("B", Arrays.asList(4, 5, 6));
        mapNumbers.put("C", Arrays.asList(7, 8, 9));

        out.println(mapNumbers.values().stream()
                .flatMap(x -> x.stream())
                .filter(x -> x % 2 != 0)
                .collect(Collectors.toList()));


        //Flatten a Map<String, List<String>> and Remove Duplicates
        //Problem: Given a Map<String, List<String>>,
        // flatten the values (lists of strings) and return a list of unique strings (without duplicates).

        Map<String, List<String>> mapDuplicate = new HashMap<>(); // using HashMap that's why output is [Rick, Alice, John, Bob, Steve, Tom]
        mapDuplicate.put("HR", Arrays.asList("John", "Alice", "Bob"));
        mapDuplicate.put("IT", Arrays.asList("John", "Steve", "Tom"));
        mapDuplicate.put("Finance", Arrays.asList("Rick", "Alice"));

        out.println(mapDuplicate.values().stream().flatMap(x -> x.stream()).distinct().collect(Collectors.toList()));

        // the output should need to be this [John, Alice, Bob, Steve, Tom, Rick] right if we see the order
        // so use LinkedHashMap<> it will guarantee the order


        //Get All Employees' Names from a Map<String, List<Employee>>
        //Problem: Given a Map<String, List<Employee>>,
        // where each department (key) has a list of employees,
        // flatten the employee names into a single list.

        Map<String, List<EmpDeptAndNameDto>> EmpDeptAndNameDtoMap = new HashMap<>();
        EmpDeptAndNameDtoMap.put("HR", Arrays.asList(new EmpDeptAndNameDto("John", "HR"), new EmpDeptAndNameDto("Alice", "HR")));
        EmpDeptAndNameDtoMap.put("IT", Arrays.asList(new EmpDeptAndNameDto("Steve", "IT"), new EmpDeptAndNameDto("Tom", "IT")));

        out.println(EmpDeptAndNameDtoMap.values().stream()
                .flatMap(x -> x.stream())
                .map(x -> x.getName())
                .collect(Collectors.toList()));

        //Flatten a Map<String, List<String>> and Find the Longest Word in Each List
        //Problem: Given a Map<String, List<String>>,
        // find the longest word in each list of strings and flatten the results into a single list of longest words.

        Map<String, List<String>> deptNameMap = new LinkedHashMap<>();
        deptNameMap.put("HR", Arrays.asList("John", "Alice", "Bob"));
        deptNameMap.put("IT", Arrays.asList("Steve", "Tom", "Christopher"));
        deptNameMap.put("Finance", Arrays.asList("Rick", "Eve", "Alexander"));

        out.println(deptNameMap.values().stream().flatMap(x -> {
            return Stream.of(x.stream().max((a, b) -> a.length() - b.length()).get());

        }).collect(Collectors.toList()));


        //Flatten a Map<String, Map<Integer, List<Integer>>> to a Single List of Integers
        //Problem: Given a Map<String, Map<Integer, List<Integer>>>,
        // flatten all the lists of integers inside and return a single list of integers.

        Map<String, Map<Integer, List<Integer>>> mapListOfInteger = new HashMap<>();
        mapListOfInteger.put("A", new HashMap<Integer, List<Integer>>() {{
            put(1, Arrays.asList(1, 2, 3));
            put(2, Arrays.asList(4, 5, 6));
        }});
        mapListOfInteger.put("B", new HashMap<Integer, List<Integer>>() {{
            put(1, Arrays.asList(7, 8, 9));
            put(2, Arrays.asList(10, 11, 12));
        }});

        out.println(mapListOfInteger.values().stream()
                .flatMap(x -> {
                    return x.values().stream().flatMap(k -> k.stream());
                }).collect(Collectors.toList()));


        //Flatten a Map<String, List<List<String>>> into a List of Strings
        //Problem: Given a Map<String, List<List<String>>>,
        // flatten all the lists inside and return a single list of strings.

        Map<String, List<List<String>>> mapString = new HashMap<>();
        mapString.put("HR", Arrays.asList(Arrays.asList("John", "Alice"), Arrays.asList("Bob", "Charlie")));
        mapString.put("IT", Arrays.asList(Arrays.asList("Steve", "Tom"), Arrays.asList("Rick", "Eve")));

        out.println(mapString.values().stream().flatMap(x -> {
            return x.stream().flatMap(k -> {
                return k.stream().flatMap(t -> Arrays.stream(t.split(" ")));
            });
        }).collect(Collectors.toList()));


        // Flatten a Nested Map<String, Map<String, List<Integer>>> and Filter Even Numbers
        //Problem: Given a Map<String, Map<String, List<Integer>>>,
        // flatten the structure and filter out all the even numbers, returning a list of only odd integers.

        Map<String, Map<String, List<Integer>>> mapStringInteger = new HashMap<>();
        mapStringInteger.put("HR", new HashMap<String, List<Integer>>() {{
            put("Group1", Arrays.asList(1, 2, 3));
            put("Group2", Arrays.asList(4, 5, 6));
        }});
        mapStringInteger.put("IT", new HashMap<String, List<Integer>>() {{
            put("Group1", Arrays.asList(7, 8, 9));
            put("Group2", Arrays.asList(10, 11, 12));
        }});

        out.println(mapStringInteger.values().stream()
                .flatMap(x -> {
                    return x.values().stream().flatMap(k -> k.stream());
                }).filter(x -> x % 2 != 0).collect(Collectors.toList()));

    }


}

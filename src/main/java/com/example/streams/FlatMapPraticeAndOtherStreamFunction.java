package com.example.streams;


import com.example.dto.EmployeeSkillDto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.exit;
import static java.lang.System.out;

public class FlatMapPraticeAndOtherStreamFunction {

    public static void main(String[] args) {
        /*
         Always remember when ever you will see one to many mappings you can use flatMap()
          customer -> coustomer.getPhoneNumbers() its list of phoneNumbers soo here if you want them in
          single list you can use flatMap()
         */


        //1. Flatten Lists of Integers

        List<List<Integer>> nums = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8)
        );
        out.println(nums.stream().flatMap(x -> x.stream()).collect(Collectors.toList()));

        //🧠 2. Merge Two Lists of Strings Alphabetically
        //You have two lists:👉 Merge them into one sorted list alphabetically.

        List<String> list1 = Arrays.asList("banana", "apple");
        List<String> list2 = Arrays.asList("cherry", "date");

        out.println(Stream.concat(list1.stream(), list2.stream()).sorted(Comparator.naturalOrder()).collect(Collectors.toList()));

        // using flatmap

        out.println(Stream.of(list1, list2).flatMap(x -> x.stream()).sorted().collect(Collectors.toList()));

        //3. Merge Arrays of Numbers
        //You have two arrays: 👉 Merge both arrays into a single list.

        Integer[] arr1 = {10, 20, 30};
        Integer[] arr2 = {40, 50, 60};

        out.println(Stream.of(arr1, arr2).flatMap(x -> Arrays.stream(x)).collect(Collectors.toList()));

        // without flatmap
        out.println(Stream.concat(Arrays.stream(arr1), Arrays.stream(arr2)).sorted().collect(Collectors.toList()));

        //🧠 4. Split Sentences into Words
        //You have: 👉 Create a list of all individual words.

        //Understand that split creates arrays
        //
        //Convert arrays to streams
        //
        //Flatten multiple small streams into one single stream

        List<String> sentences = Arrays.asList("Java is fun", "Streams are powerful");
        out.println(sentences.stream().flatMap(x -> Arrays.stream(x.split(" "))).collect(Collectors.toList()));

        //x.split(" ") splits each sentence into words (as an array of Strings).
        //
        //Arrays.stream(x.split(" ")) turns the array of words into a stream.
        //
        //flatMap combines these streams of words into a single stream.
        //
        //collect(Collectors.toList()): Collects the words into a List<String>.


        //🧠 5. Merge List of Lists and Remove Duplicates
        //You have: 👉 Flatten it and create a unique list (no duplicate words).

        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("banana", "cherry"),
                Arrays.asList("cherry", "apple")
        );

        out.println(nestedList.stream().flatMap(x -> x.stream()).distinct().collect(Collectors.toList()));


        // Q 6. Flatten a List of Lists into a Single List of Strings
        //Given a list of lists of strings, flatten
        // the entire structure into one single list of strings.

        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana", "cherry"),
                Arrays.asList("dog", "elephant", "fox"),
                Arrays.asList("grape", "honey", "iguana")
        );
        out.println(listOfLists.stream().flatMap(x -> x.stream()).collect(Collectors.toList()));

        // Q 7. Flatten a List of Employees to a List of Skills
        // You have a List<Employee> where each employee has a list of skills.
        // Flatten this list of employees to get a list of all skills.

        List<EmployeeSkillDto> employees = Arrays.asList(
                new EmployeeSkillDto("John", Arrays.asList("Java", "Spring", "AWS")),
                new EmployeeSkillDto("Alice", Arrays.asList("Python", "Django", "Azure")),
                new EmployeeSkillDto("Bob", Arrays.asList("Java", "Kubernetes"))
        );

        out.println(employees.stream().flatMap(x -> x.getSkills().stream()).collect(Collectors.toList()));

        // Q 8. Convert a List of Sentences into a List of All Words
        //You have a list of sentences.
        // Your goal is to get a list of all words from these sentences,
        // where each word is a separate element.
        //

        List<String> sentence = Arrays.asList(
                "Java is powerful",
                "Streams are cool",
                "flatMap is tricky"
        );

        out.println(sentence.stream().
                flatMap(x -> Arrays.stream(x.split(" ")))
                .collect(Collectors.toList()));

        // Q 9. Flatten a List of Integers and Get Only Even Numbers
        //Given a list of lists of integers,
        //flatten them and keep only even numbers in the final result.

        List<List<Integer>> listOfList = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12)
        );

        out.println(listOfList.stream().
                flatMap(x -> x.stream())
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList()));


        // Q 10. Extract All Characters from Multiple Strings
        //Given a list of strings, flatten the strings and get a list of individual characters.

        List<String> strings = Arrays.asList("apple", "banana", "cherry");

        out.println(strings.stream()
                .flatMap(x -> Arrays.stream(x.split("")))
                .collect(Collectors.toList()));

    }

}

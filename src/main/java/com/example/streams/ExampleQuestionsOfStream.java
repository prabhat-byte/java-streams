package com.example.streams;

import com.example.dto.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

public class ExampleQuestionsOfStream {

    public static void main(String[] args) {

        //Example 1 . Collecting Names By Length
        List<String> l1 = Arrays.asList("Anna", "Bob", "Alexander", "Brian", "Alice");
        out.println(l1.stream().collect(Collectors.groupingBy(x -> x.length())));

        //Example 2 . Counting Word Occurrences
        String sentence = "hello world hello java world";
        out.println(Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(x -> x, Collectors.counting())));

        //Example 3 . Counting Even and Odd numbers
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        out.println(list.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0)));

        // Example 4. Summing Values in a map
        Map<String, Integer> items = new HashMap<>();
        items.put("Apple", 10);
        items.put("banana", 10);
        items.put("Oranges", 15);

        out.println(items.values().stream().collect(Collectors.summingInt(x -> x)));
        //or
        out.println(items.values().stream().reduce((a, b) -> a + b).get());


        //Example 5. Create A Map Using Stream Elements
        //name is key and its length is value
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");
        Map<String, Integer> collectToMap = fruits.stream().collect(Collectors.toMap(x -> x, x -> x.length()));
        out.println(collectToMap);

        // Example 6 . for the merge function of toMap
        // basically need to find frequency of each word
        List<String> word2 = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        out.println(word2.stream().collect(Collectors.toMap(k -> k, v -> 1, (a, b) -> a + b)));

        // Example 8. Find the longest string in a list of strings using Java streams.
        List<String> strings = Arrays
                .asList("apple", "banana", "cherry", "date", "grapefruit");

        out.println("longest string in a list of strings is : " + strings.stream().max((a, b) -> a.length() - b.length()).get());

        //Q. Calculate the average age of a list of Person objects using Java streams:
        List<Person> persons = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35)
        );
        out.println(persons.stream().collect(Collectors.averagingInt(Person::getAge)));

        // or Alternate solution

        double averageAge = persons.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);

        //Q.Check if a list of integers contains a prime number using Java streams:
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 11, 12, 13, 14, 15);
        out.println(numbers.stream().anyMatch(x -> {
            for (int i = 2; i <= Math.sqrt(x); i++) {
                if (x % i == 0) {
                    return false;
                }
            }
            return true;
        }));

        // or alternate and efficient solution
        out.println(numbers.stream().anyMatch(x -> x > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(x)).allMatch(i -> x % i != 0)));

        // Q. Merge two sorted lists into a single sorted list using Java streams:

        List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> list2 = Arrays.asList(2, 4, 6, 8, 10);
        //solution 1
        out.println(Stream.of(list1, list2).flatMap(x->x.stream()).sorted().collect(Collectors.toList()));
        //solution 2
        out.println(Stream.concat(list1.stream(), list2.stream()).sorted().collect(Collectors.toList()));


    }
}

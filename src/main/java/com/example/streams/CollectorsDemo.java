package com.example.streams;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class CollectorsDemo {
    public static void main(String[] args) {

        //Collectors is a Utility class
        //Provides a Set of methods to create common collectors

        //1. Collecting to a list
        List<String> names = Arrays.asList("Alice", "Bob", "charlie");

        List<String> res = names.stream().filter(x -> x.startsWith("A")).collect(Collectors.toList());
        out.println(res);

        //2. collecting to a Set
        List<Integer> nums = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        Set<Integer> set = nums.stream().collect(Collectors.toSet());
        out.println(set);

        //3. Collecting to a Specific Collection
        ArrayDeque<String> arrayDeque = names.
                stream()
                .collect(Collectors.toCollection(() -> new ArrayDeque<>()));

        //4. Joining Strings
        // Concatenate Stream elements into a single String
        String name = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));
        out.println(name);

        //5 . Summarizing Data
        // Generates Statistical Summary (count, sum ,min ,average ,max)

        List<Integer> numbers = Arrays.asList(2, 3, 5, 7, 11);

        IntSummaryStatistics stats = numbers.stream().collect(Collectors.summarizingInt(x -> x));
        out.println("Count :" + stats.getCount());
        out.println("Sum :" + stats.getSum());
        out.println("Min :" + stats.getMin());
        out.println("Average :" + stats.getAverage());
        out.println("max :" + stats.getMax());

        // Calculating Average
        Double average = numbers.stream().collect(Collectors.averagingInt(x -> x));
        out.println("Average :" + average);

        // Calculating count
        Long count = numbers.stream().collect(Collectors.counting());
        out.println("Count :" + count);

        // Calculating max
        Optional<Integer> max = numbers.stream().collect(Collectors.maxBy((a, b) -> a - b));
        out.println("Max :" + max.get());

        //Calculating sum
        int sum = numbers.stream().collect(Collectors.summingInt(x -> x));
        out.println("Sum :" + sum);

        // 8. Grouping Elements
        List<String> words = Arrays.asList("hello", "world", "java", "stream", "collectors");
        out.println(words.stream().collect(Collectors.groupingBy(x -> x.length())));
        out.println(words.stream().collect(Collectors.groupingBy(x -> x.length(), Collectors.joining(", "))));
        out.println(words.stream().filter(word -> word.length() > 4 && word.contains("e")).collect(Collectors.groupingBy(String::length)));

        // 9. Partitioning Elements
        // Partitions elements into two groups (true and false) based on a predicate
        out.println("partation" + words.stream().collect(Collectors.partitioningBy(x -> x.length() > 5)));

        //10. Mapping and Collecting
        // this example simply showing that mapping can also be done inside collect
        out.println(words.stream().collect(Collectors.mapping(x->x.toUpperCase(),Collectors.toList())));

    }
}

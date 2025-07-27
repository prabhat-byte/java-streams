package com.example.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class TerminalOperations {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3);

        //Reduce - combine elements to produce a single result

        out.println(list.stream().reduce(Integer::sum).get());

        //anyMatch(),allMatch(),noneMatch()

        boolean allMatch = list.stream().allMatch(x -> x > 0);
        out.println(allMatch);

        boolean anyMatch = list.stream().anyMatch(x -> x % 2 == 0);
        out.println(anyMatch);

        boolean noneMatch = list.stream().noneMatch(x -> x < 0);
        out.println(noneMatch);

        //findFirst() , findAny()

        Optional<Integer> first = list.stream().findFirst();
        out.println(first.get());

        Optional<Integer> any = list.stream().skip(1).findAny();
        out.println(any.get());


        // these all above method are sort circuit operations

        // Some Examples

        List<String> names = Arrays.asList("Bob", "Anna", "Charlie", "David");

        // Find names whose length is greater than 3

        names.stream().filter(x -> x.length() > 3).forEach(x -> out.println(x));


        //Example 2

        List<Integer> integers = Arrays.asList(5, 2, 9, 1, 6);

        // squaring and sorting of numbers
        out.println(integers.stream().map(x -> x * x).sorted().collect(Collectors.toList()));

        //Summing the values
        List<Integer> sum = Arrays.asList(1,2,3,4,5);

        out.println(sum.stream().reduce((x,y)->x+y).get());

        //Example : Counting Occurrence of a Characters
        String sentance = "Hello World";
        out.println(sentance.chars().filter(x->x=='l').count());
        // output = [4,3,2,1]
        List<Integer> ls = Arrays.asList(1,2,3,4,1,3,2);
        out.println(ls.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));


        //stateful and stateless operations


    }
}

package com.example.oops;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;


public class demo {
    public static void main(String[] args) {
        List<String> st = new ArrayList<>();
        st.add("Amit");
        st.add("Rohan");

        out.println(st.stream().collect(Collectors.groupingBy(String::length)));


        // input String str="aaabbcddaab"
        // output should be "a3b2c1d2a2b1"


        String str = "aaabbcddaab";

        StringBuilder stringBuilder = new StringBuilder();

        Character curr = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == curr) {
                count++;
            } else {
                stringBuilder.append(curr).append(count);
                curr = str.charAt(i);
                count = 1;
            }
        }
        stringBuilder.append(curr).append(count);
        out.println(stringBuilder);


        //example [1,2,3,3,4,5,5,4,5]
        //Output: [3,4,5]

        out.println(Stream.of(1, 2, 3, 3, 4, 5, 5, 4, 5)
                .collect(Collectors.groupingBy(x -> x)).entrySet()
                .stream().filter(p -> p.getValue().size() > 1)
                .map(Map.Entry::getKey).collect(Collectors.toList()));

        LinkedHashSet<Integer> set = new LinkedHashSet<>();

        out.println(Stream.of(1, 2, 3, 3, 4, 5, 5, 4, 5).filter(x -> !set.add(x)).collect(Collectors.toSet()));


        HashSet<Integer> mp = new HashSet<>();
        out.println(mp.add(1));
        out.println(mp.add(1));
        out.println(mp.add(2));
        out.println(mp.add(2));
        out.println(mp.add(2));
        out.println(mp.add(4));







    }


}





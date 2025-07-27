package com.example.interviewQestions;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class DiptysQuestions {
    public static void main(String[] args) {
        out.println(List.of(1, 2, 4, 4, 3, 5, 6, 6, 3).stream().collect(Collectors.groupingBy(x -> x
                        , LinkedHashMap::new, Collectors.counting()))

                .entrySet().stream().filter(x -> x.getValue() > 1).map(x -> x.getKey()).sorted().collect(Collectors.toList()));


        //get second highest number from a list java8?

        Integer integer = Arrays.asList(1, 2, 3, 4, 6, 4, 6).stream().distinct().sorted(Comparator.reverseOrder())
                .limit(2).skip(1).findFirst().get();

        out.println(integer);


        // Deolite Coing question - find triplate who's some is equal to target
//        int[] arr = {1, 4, 45, 6, 10, 8};
//        int target = 13;


        int[] arr = {1, 4, 45, 6, 10, 8};
        int target = 14;

        int n = arr.length;

        Arrays.sort(arr);

        out.println(arr[n - 2]);

        List<Integer>  ls = new ArrayList<>();



        for (int i = 0; i < n - 2; i++) {

            int right = n - 1;
            int left = i + 1;

            while(left<right){
                int sum = arr[i]+arr[right]+arr[left];
                if(sum==target){
                    ls.add(arr[i]);
                    ls.add(arr[right]);
                    ls.add(arr[left]);
                    break;
                }
                else if(sum<target){
                    left++;
                }
                else{
                    right--;
                }
            }

        }

        if(ls.isEmpty()){
            out.println("No triplet found with sum " + target);
            return;
        }

        out.println(ls);


    }
}

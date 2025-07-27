package com.example.Strings;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class StreamApiStringPratice {

    public static void main(String[] args) {

        //1. Count the number of vowels in a string
        //Input: "Interview Preparation"

        String s = "Interview Preparation";
        out.println("no of vowels : " + Arrays.stream(s.split("")).filter(x -> "aeiouAeiou".contains(x)).count());


        // 2. Remove all duplicate characters from a string
        //Input: "programming"
        //Expected Output: "progamin" (keep first occurrences)

        String pro = "programming";
        out.println(Arrays.stream(pro.split("")).distinct().collect(Collectors.joining()));

        // 3. Count the frequency of each character in a string
        //Input: "success"
        //
        //Expected Output: {s=3, u=1, c=2, e=1}

        String input = "success";

        out.println(Arrays.stream(input.split("")).collect(Collectors.groupingBy(x -> x, Collectors.counting())));

        //4. Find the first non-repeating character in a string
        //Input: "swiss"
        //
        //Expected Output: "w"

        String nonRepeting = "swiss";
        out.println(Arrays.stream(nonRepeting.split(""))
                .collect(Collectors.groupingBy(x -> x
                        , LinkedHashMap::new ,
                        Collectors.counting()))
                .entrySet().stream().filter(p -> p.getValue() == 1).findFirst().get().getKey());


        //5. Find the first repeating character in a string
        //Input: "streams"
        //
        //Expected Output: "s"

        String repeating = "streams";

        out.println(Arrays.stream(repeating.split(""))
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .entrySet().stream().filter(p -> p.getValue() > 1).findFirst().get().getKey());


        //🔠 Case & Content Filtering
        //6. Count uppercase and lowercase letters in a string
        //Input: "JavaIsFun"
        //
        //Expected Output: {Uppercase=3, Lowercase=6}

        String filtering = "JavaIsFun";

        out.println(Arrays.stream(filtering.split(""))
                .collect(Collectors.partitioningBy(x -> x.matches("[A-Z]"), Collectors.counting())));


        // 7. Check if two strings are anagrams
        //Input: "listen", "silent"
        //
        //Expected Output: true

        String anagram1 = "listen";
        String anagram2 = "silent";

        // First Approch
        Map<String, Long> map1 = Arrays.stream(anagram1.split("")).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        Map<String, Long> map2 = Arrays.stream(anagram2.split("")).collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        System.out.println(map1.equals(map2));


        // Second Approach
        String s1 = Arrays.stream(anagram2.split("")).sorted().collect(Collectors.joining());
        String s2 = Arrays.stream(anagram1.split("")).sorted().collect(Collectors.joining());
        if (Objects.equals(s1, s2)) {
            out.println("Strings are Anagram");
        } else {
            out.println("Strings are not Anagram");
        }


        //8. Find all words in a string that start with a vowel
        //Input: "An elephant is outside under a tree"
        //
        //Expected Output: [An, elephant, is, outside, under]

        String vowelWords = "An elephant is outside under a tree";
        out.println("output is : " + Arrays.stream(vowelWords.split(" "))
                .filter(x -> "AEIOUaeiou".contains(String.valueOf(x.charAt(0))) && x.length() > 1)
                .collect(Collectors.toList()));


        //9. Count words of length greater than 5 in a sentence
        //Input: "Java streams make functional programming easier"
        //
        //Expected Output: 2 (streams, programming)

        String countWords = "Java streams make functional programming easier";

        out.println(Arrays.stream(countWords.split(" "))
                .filter(x -> x.length() > 5)
                .collect(Collectors.toList()));


        //10. Find palindromic words in a sentence
        //Input: "Test madam level noon civic deed refer rotator"
        //
        //Expected Output: [madam, level, noon, civic, deed, refer, rotator]

        String palindrome = "Test madam level noon civic deed refer rotator";

        out.println(Arrays.stream(palindrome.split(" "))
                .filter(x -> {
                    String temp = "";
                    Stack<Character> st = new Stack<>();

                    for (int i = 0; i < x.length(); i++) {
                        st.push(x.charAt(i));
                    }
                    while (!st.isEmpty()) {
                        temp = temp + st.pop();
                    }
                    if (x.equals(temp)) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList()));

        // Alternate Solution

        out.println(Arrays.stream(palindrome.split(" "))
                .filter(x -> x.equals(new StringBuilder(x).reverse().toString()))
                .collect(Collectors.toList()));


        //11. Remove all special characters from a string
        //Input: "@Hello#World!2024*"
        //
        //Expected Output: "HelloWorld2024"

        String specialChars = "@Hello#World!2024*";

        out.println(Arrays.stream(specialChars.split(""))
                .filter(x -> x.matches("[a-zA-Z0-9]"))
                .collect(Collectors.joining()));


        //12. Capitalize the first letter of each word
        //Input: "java stream api is powerful"
        //
        //Expected Output: "Java Stream Api Is Powerful"

        String capitalize = "java stream api is powerful";

        out.println(Arrays.stream(capitalize.split(" "))
                .map(x -> x.substring(0, 1).toUpperCase() + x.substring(1))
                .collect(Collectors.joining(" ")));

        //13. Reverse each word in a string
        //Input: "learn java stream"
        //
        //Expected Output: "nrael avaj maerts"

        String revWords = "learn java stream";
        out.println(Arrays.stream(revWords.split(" "))
                .map(x -> new StringBuilder(x).reverse().toString())
                .collect(Collectors.joining(" ")));


        //14. Remove duplicate words from a sentence
        //Input: "this is is a test test sentence"
        //
        //Expected Output: "this is a test sentence"

        String distinct = "this is is a test test sentence";

        out.println(Arrays.stream(distinct.split(" ")).distinct()
                .collect(Collectors.joining(" ")));


        //15. Sort all words of a sentence alphabetically
        //Input: "stream java api makes coding clean"
        //
        //Expected Output: [api, clean, coding, java, makes, stream]

        String sortWords = "stream java api makes coding clean";

        out.println(Arrays.stream(sortWords.split(" ")).sorted()
                .collect(Collectors.toList()));

        //16. Check if a string contains only digits
        //Input: "123456"
        //
        //Expected Output: true

        String digits = "123456";

        out.println(Arrays.stream(digits.split(""))
                .allMatch(x -> x.matches("[0-9]")));


        //17. Count how many times a substring appears
        //Input: "abababa", Substring: "aba"
        //
        //Expected Output: 3 (overlapping allowed)

        String string = "abababa";
        String subString = "aba";

        out.println(Arrays.stream(string.split(subString)).count()); // this will give output 2 which is not correct

        // solution of this problem is not possible using split because overlapping is there
        // here are few solutions

        int count = 0;
        for (int i = 0; i <= string.length() - subString.length(); i++) {
            if (string.substring(i, i + subString.length()).equals(subString)) {
                count++;
            }
        }
        out.println(count);

        //2nd
        long count2 = IntStream.rangeClosed(0, string.length() - subString.length())
                .filter(i -> string.substring(i, i + subString.length()).equals(subString))
                .count();

        System.out.println(count2);

        //3rd using regex and Pattern Matchers

        Pattern pattern = Pattern.compile("(?=aba)");
        Matcher matcher = pattern.matcher("abababa");

        int count3 = 0;
        while (matcher.find()) {
            count3++;
        }
        System.out.println(count3);


        //18. Find the longest word in a sentence
        //Input: "Java streams simplify complex problems"
        //
        //Expected Output: "simplify"

        String longest = "Java streams simplify complex problems";

        out.println(Arrays.stream(longest.split(" "))
                .sorted((a, b) -> b.length() - a.length()).findFirst().get());


        //19. Group words by their length
        //Input: "java is fun and streams are awesome"
        //
        //Expected Output: {2=[is], 3=[fun, and, are], 4=[java], 6=[stream], 7=[awesome]}

        String groupWords = "java is fun and streams are awesome";

        out.println(Arrays.stream(groupWords.split(" "))
                .collect(Collectors.groupingBy(String::length)));


        //20. Find common characters between two strings
        //Input: "abcdef", "acdfg"
        //
        //Expected Output: [a, c, d, f]

        String commonChars = "abcdef";
        String string2 = "acdfg";

        out.println(Arrays.stream(commonChars.split(""))
                .filter(x -> string2.contains(x)).collect(Collectors.toList()));


        //21. Group characters by frequency
        //Input: "mississippi"
        //
        //Expected Output: {1=[m], 2=[m], 4=[s], 4=[i], 2=[p]} (may vary by grouping)

        String freq = "mississippi";

        out.println(Arrays.stream(freq.split(""))
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .entrySet().stream()
                .collect(Collectors.groupingBy(x -> x.getValue()
                        , Collectors.mapping(p -> p.getKey(), Collectors.toList())
                ))
        );


        //22. Generate all substrings of a string
        //Input: "abc"
        //
        //Expected Output: [a, ab, abc, b, bc, c]

        String genSubString = "abc";

        // Generate all substrings using nested IntStreams and collect them into a list
        List<String> substrings = IntStream.range(0, genSubString.length())  // Outer loop: starting index of substring (0 to length-1)
                .boxed()  // Convert IntStream to Stream<Integer>
                .flatMap(i -> IntStream.rangeClosed(i + 1, genSubString.length())  // Inner loop: ending index of substring (i+1 to length)
                        .mapToObj(j -> genSubString.substring(i, j))  // Extract substring from index i to j-1
                )
                .collect(Collectors.toList());  // Collect all substrings into a list

        System.out.println(substrings);

        //Alternate Solution
        List<String> substring = new ArrayList<>();

        for (int i = 0; i < genSubString.length(); i++) {
            for (int j = i + 1; j <= genSubString.length(); j++) {
                substrings.add(genSubString.substring(i, j));
            }
        }

        System.out.println(substrings);


        //23. Check if a string has all unique characters
        //Input: "stream"
        //
        //Expected Output: true
        //
        //Input: "banana"
        //
        //Expected Output: false

        String unique = "banana";
        out.println(Arrays.stream(unique.split(""))
                .distinct()
                .count() == unique.length()
        );


        //24. Group words by their starting letter
        //Input: "apple ant bat ball cat car"
        //
        //Expected Output: {a=[apple, ant], b=[bat, ball], c=[cat, car]}

        String groupWord = "apple ant bat ball cat car";
        out.println(Arrays.stream(groupWord.split(" "))
                .collect(Collectors.groupingBy(x -> x.charAt(0), Collectors.toList())));


        //25. Find the word(s) with the maximum frequency
        //Input: "apple banana apple orange banana apple"
        //
        //Expected Output: [apple]

        String maxFreq = "apple banana apple orange banana apple";

        out.println(Arrays.stream(maxFreq.split(" "))
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .entrySet().stream().sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .map(x -> x.getKey())
                .limit(1).collect(Collectors.toList()));


        //🧩 26. Group words by length
        //Input: "sun son soon moon tool zoo"
        //
        //Expected Output:
        //{3=[sun, son, zoo], 4=[soon, moon, tool]}

        String wordLength = "sun son soon moon tool zoo";
        out.println(Arrays.stream(wordLength.split(" "))
                .collect(Collectors.groupingBy(x -> x.length(), Collectors.toList())));


        //🧩 27. Count how many words start with each letter
        //Input: "apple ant bat ball apple ant"
        //
        //Expected Output:
        //{a=4, b=2}

        String countWord = "apple ant bat ball apple ant";

        out.println(Arrays.stream(countWord.split(" "))
                .collect(Collectors.groupingBy(x -> x.charAt(0), Collectors.counting())));


        //🧩 28. Group words by starting letter and store them in a Set
        //Input: "dog deer dove duck dog deer"
        //
        //Expected Output:
        //{d=[dog, deer, dove, duck]}

        String setStart = "dog deer dove duck dog deer";

        out.println(Arrays.stream(setStart.split(" "))
                .collect(Collectors.groupingBy(a -> a.charAt(0), Collectors.toCollection(() -> new LinkedHashSet<>()))));

        //🧩 29. Group words by starting letter and sort the words inside each group
        //Input: "apple ant apricot axe art"
        //
        //Expected Output:
        //{a=[ant, apple, apricot, art, axe]}

        String sortStart = "apple ant apricot axe art";

        out.println(Arrays.stream(sortStart.split(" "))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.groupingBy(x -> x.charAt(0))));

        //Alternative way is if no duplicates

        Map<Character, Set<String>> result = Arrays.stream(sortStart.split(" "))
                .collect(Collectors.groupingBy(
                        word -> word.charAt(0),
                        Collectors.toCollection(TreeSet::new)  // ✅ sorts words inside each group
                ));
        out.println(result);

        //🧩 5. Find the longest word for each starting letter
        //Input: "apple ant aeroplane bat ball butterfly cat car"
        //
        //Expected Output:
        //{a=aeroplane, b=butterfly, c=cat}

        String longestStart = "apple ant aeroplane bat ball butterfly cat car";

        Map<Character, List<String>> collect = Arrays.stream(longestStart.split(" "))
                .collect(Collectors.groupingBy(x -> x.charAt(0)));

        out.println(collect.entrySet().stream()
                .collect(Collectors.toMap(a -> a.getKey(), b -> b.getValue()
                        .stream().max(Comparator.comparingInt(x -> x.length()))
                        .get()
                )));


        //a=2,b=2,c1,d1,e=2
        String test = "aabbcdee";
        out.println(Arrays.stream(test.split(""))
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .entrySet()
                .stream().filter(x -> x.getValue() == 1L).findFirst().get().getKey());


        //3. Get Unmodifiable List of Uppercased Words
        //Input: ["apple", "ball", "cat"]
        //✅ Use collectingAndThen + map():
        // OUTPUT : [APPLE, BALL, CAT] (unmodifiable)

        List<String> results = Arrays.asList("apple", "ball", "cat").stream()
                .map(String::toUpperCase)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList
                ));

        out.println(results);


        //1. Create an Unmodifiable Map of Word to Its Length
        //Input: ["apple", "bat", "banana"]
        //Output: {apple=5, bat=3, banana=6}

        Map<String, Integer> collect1 = Arrays.asList("apple", "bat", "banana").stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(x -> x, x -> x.length(),
                                (a, b) -> a,
                                LinkedHashMap::new
                        ),
                        Collections::unmodifiableMap
                ));
        out.println(collect1);


        //2. Count Words by Starting Letter and Make the Map Unmodifiable
        //Input: ["apple", "ant", "banana", "ball", "cat"]
        //Output: {a=2, b=2, c=1}

        Map<Character, Long> collect2 = Arrays.asList("apple", "ant", "banana", "ball", "cat").stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(x -> x.charAt(0), Collectors.counting()),
                        Collections::unmodifiableMap
                ));

        out.println(collect2);


        //3. Group Names by First Letter and Convert List to Set
        //Input: ["adam", "alex", "bob", "bella", "clark"]
        //Output: {a=[adam, alex], b=[bob, bella], c=[clark]}

        out.println(Arrays.asList("adam", "alex", "bob", "bella", "clark").stream()
                .collect(Collectors.groupingBy(x -> x.charAt(0), Collectors.toSet())));

        //In above qustion if ask was to preserv order also this this will th solution
        out.println(String.valueOf(Arrays.asList("adam", "alex", "bob", "bella", "clark").stream()
                .collect(Collectors.groupingBy(x -> x.charAt(0),
                        LinkedHashMap::new,
                        Collectors.toSet()))));


        //4. Get Map of Word → First Letter (Unmodifiable)
        //Input: ["apple", "banana", "carrot"]
        //Output: {apple=a, banana=b, carrot=c}

        Map<String, Character> mp = Arrays.asList("apple", "banana", "carrot", "apple").stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(x -> x, x -> x.charAt(0)
                                , (a, b) -> a,
                                LinkedHashMap::new
                        ),
                        Collections::unmodifiableMap
                ));
        out.println(mp);


        //5. Create a Map of Starting Letter to Longest Word
        //Input: ["ant", "apple", "bat", "ball", "butterfly", "cat"]
        //Output: {a=apple, b=butterfly, c=cat}


        Map<Character, String> collect3 = Arrays.asList("ant", "apple", "bat", "ball", "butterfly", "cat").stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(x -> x.charAt(0), x -> x,
                                (a, b) -> a.length() > b.length() ? a : b,
                                LinkedHashMap::new
                        ),
                        Collections::unmodifiableMap
                ));
        out.println(collect3);


        //6. Create a Sorted Map of Words to Their Length
        //Input: ["dog", "elephant", "cat"]
        //Output: {cat=3, dog=3, elephant=8}


        Map<String, Integer> collect4 = Arrays.asList("dog", "elephant", "cat").stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(x -> x, y -> y.length(),
                                (a, b) -> a,
                                TreeMap::new
                        ),
                        Collections::unmodifiableMap
                ));

        out.println(collect4);

// problem was to sort using length use sort them by key when using TreeMap
        LinkedHashMap<String, Integer> collect5 = Arrays.asList("dog", "elephant", "cat").stream()
                .collect(Collectors.toMap(x -> x, y -> y.length()))
                .entrySet().stream().sorted((a, b) -> a.getValue() - b.getValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
        out.println(collect5);


        //7. Create Map of Word Length to Uppercase Word List
        //Input: ["dog", "apple", "bat", "elephant"]
        //Output: {3=[DOG, BAT], 5=[APPLE], 8=[ELEPHANT]}

        out.println(Arrays.asList("dog", "apple", "bat", "elephant").stream()
                .map(x -> x.toUpperCase())
                .collect(Collectors.groupingBy(x -> x.length())));

        // Alternate approch
        LinkedHashMap<Integer, List<String>> collect6 = Arrays.asList("dog", "apple", "bat", "elephant").stream()
                .collect(Collectors.toMap(
                        x -> x.length(),
                        y -> new ArrayList<>(List.of(y.toUpperCase())),
                        (a, b) -> {
                            a.addAll(b);
                            return a;
                        },
                        LinkedHashMap::new
                ));
        out.println(collect6);


        //8. Create a Map of Character → List of Lowercase Words Starting With It
        //Input: ["Apple", "Ant", "Bat", "Ball", "Cat"]
        //Output: {a=[apple, ant], b=[bat, ball], c=[cat]}

        LinkedHashMap<Character, ArrayList> collect7 = Arrays.asList("Apple", "Ant", "Bat", "Ball", "Cat").stream()
                .collect(Collectors.toMap(
                        x -> x.toLowerCase().charAt(0),
                        y -> new ArrayList(Arrays.asList(y.toLowerCase())),
                        (a, b) -> {
                            a.addAll(b);
                            return a;
                        },
                        LinkedHashMap::new
                ));
        out.println(collect7);

        //alternate way

        out.println(Arrays.asList("Apple", "Ant", "Bat", "Ball", "Cat").stream()
                .map(x -> x.toLowerCase())
                .collect(Collectors.groupingBy(x -> x.charAt(0))));


        //9. Get an Unmodifiable List of Words > 3 Characters
        //Input: ["dog", "apple", "cat", "banana"]
        //Output: [apple, banana] → as unmodifiable

        List<String> collect8 = Arrays.asList("dog", "apple", "cat", "banana").stream()
                .filter(x -> x.length() > 3)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList
                ));

        out.println(collect8);


        //10. Convert List of Words to Unmodifiable Set of Uppercase Words
        //Input: ["apple", "banana", "apple"]
        //Output: [APPLE, BANANA]

        out.println(Arrays.asList("apple", "banana", "apple").stream()
                .map(x -> x.toUpperCase()).collect(Collectors.toUnmodifiableSet()));


        //alternate solution

        Arrays.asList("apple", "banana", "apple").stream()
                .map(String::toUpperCase)
                .collect(Collectors.collectingAndThen(
                        Collectors.toSet(),
                        Collections::unmodifiableSet
                ));


    }
}
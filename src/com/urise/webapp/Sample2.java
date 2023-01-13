package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sample2 {
    static int[] array = {6, 2, 3, 8, 9, 6, 3, 8};
    static ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8, 9));

    public static void main(String[] args) {
        System.out.println(minValue(array));
        System.out.println(oddOrEven(numbers));

//        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8, 9));
//        Optional<Integer> min = numbers.stream().min(Integer::compare);
//        min.ifPresent(v->System.out.println(v)); // 4


//        Stream<Integer> stream = numbers.stream();
//        stream.filter(x-> x % 2 == 0).forEach(System.out::println);
//        Integer sum = numbers.stream().reduce((s1, s2) -> s1 + s2).orElse(0);
//        Integer sumMore2 = numbers.stream().filter(o -> o % 2 == 0).reduce((s1, s2) -> s1 + s2).orElse(0);     // через stream Api
//        long sumOdd = numbers.stream().collect(Collectors.summingInt(((p) -> p % 2 == 1 ? p : 0)));
//        List<Integer> oddOrEven = numbers.stream().filter(o -> o % 2 == 0).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
//        List<Integer> oddOrEven1 = numbers.stream().filter(o -> o % 2 == 0).toList();

//        List<Integer> oddOrEven2 = numbers.stream()
//                .flatMap(x -> {
//                    if (sum % 2 == 0) {
//                        return Stream.of(-x, x);
//                    }
//                    return Stream.empty();
//                })
//                .toList();

//        System.out.println(oddOrEven1);
//        System.out.println(sum);
//        System.out.println(sumMore2);

        //.dropWhile(x -> x >= 3)
//        Stream.of(1, 2, 3, 4, 5, 6)
//                .mapMulti((x, consumer) -> {
//                    if (x % 2 == 0) {
//                        consumer.accept(-x);
//                        consumer.accept(x);
//                    }
//                })
//                .forEach(System.out::println);
// -2, 2, -4, 4, -6, 6

    }

    static int minValue(int[] values) {
        String streamFromArrays = Arrays.stream(values)
                .distinct()
                .sorted()
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
        return Integer.parseInt(streamFromArrays);
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        Integer sum = integers.stream().reduce((s1, s2) -> s1 + s2).orElse(0);
        System.out.println(sum);
        if (sum % 2 == 0)  {
            return integers.stream().filter(o -> o % 2 != 0).toList();
        } else {
            return integers.stream().filter(o -> o % 2 == 0).toList();
        }
    }
}

package day3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitHubCopilot {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);

        for (Integer number : numbers) {
            if (number % 2 == 0) {
                System.out.println(number);

            }
        }
        numbers.forEach(n -> {
            if (n % 2 == 0) {
                System.out.println(n);

            }
        });

        String name = "John";
        String surname = "Doe";
        String fullName = name + " " + surname;
        System.out.println(fullName);
        System.out.println(name.concat(" ").concat(surname));
        System.out.println(name.concat(" " + surname));
        System.out.println(name + " " + surname);


        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.forEach((key, value) -> {
                    System.out.println(key + " " + value);

                }
        );


        int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


        for (int number : numbers2) {
            int max=numbers2[0];
            for (int i = 0; i < numbers2.length; i++) {
                if (numbers2[i]>max) {
                    max=numbers2[i];

                }
            }
        }

    }
    public static  void reverseArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;

        }
    }
}
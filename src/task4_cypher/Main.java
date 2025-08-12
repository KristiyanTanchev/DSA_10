package task4_cypher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line1 = bf.readLine();
        String line2 = bf.readLine();


        Map<String, Character> dictionary = new HashMap<>();
        StringBuilder nextCode = new StringBuilder();
        char nextChar = line2.charAt(0);
        for (int i = 1; i < line2.length(); i++) {
            if (Character.isAlphabetic(line2.charAt(i))){
                dictionary.put(nextCode.toString(), nextChar);
                nextChar = line2.charAt(i);
                nextCode.setLength(0);
            }else{
                nextCode.append(line2.charAt(i));
            }
        }
        dictionary.put(nextCode.toString(), nextChar);

        List<String> result = new ArrayList<>();
        decypher(line1, dictionary, 0, new StringBuilder(), result);

        System.out.println(result.size());
        if (!result.isEmpty()){
            result.sort(String::compareTo);
            for (String msg: result){
                System.out.println(msg);
            }
        }
    }

    private static void decypher(String encrypted,
                                 Map<String, Character> dictionary,
                                 int start,
                                 StringBuilder current, List<String> result) {
        if (start == encrypted.length()){
            result.add(current.toString());
            return;
        }
        for (String code: dictionary.keySet()){
            if (encrypted.startsWith(code, start)){
                current.append(dictionary.get(code));
                decypher(encrypted, dictionary, start + code.length(), current, result);
                current.setLength(current.length() - 1);
            }
        }
    }
}
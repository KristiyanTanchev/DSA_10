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


        Map<Character, String> dictionary = new TreeMap<>();
        StringBuilder nextCode = new StringBuilder();
        char nextChar = line2.charAt(0);
        for (int i = 1; i < line2.length(); i++) {
            if (Character.isAlphabetic(line2.charAt(i))){
                dictionary.put(nextChar, nextCode.toString());
                nextChar = line2.charAt(i);
                nextCode.setLength(0);
            }else{
                nextCode.append(line2.charAt(i));
            }
        }
        dictionary.put(nextChar, nextCode.toString());

        List<String> result = new ArrayList<>();
        decypher(line1, dictionary, 0, new StringBuilder(), result);

        System.out.println(result.size());
        if (!result.isEmpty()){
            for (String msg: result){
                System.out.println(msg);
            }
        }
    }

    private static void decypher(String encrypted,
                                 Map<Character, String> dictionary,
                                 int start,
                                 StringBuilder current, List<String> result) {
        if (start == encrypted.length()){
            result.add(current.toString());
            return;
        }
        for (Map.Entry<Character, String> entry: dictionary.entrySet()){
            String code = entry.getValue();
            if (encrypted.startsWith(code, start)){
                current.append(entry.getKey());
                decypher(encrypted, dictionary, start + code.length(), current, result);
                current.setLength(current.length() - 1);
            }
        }
    }
}
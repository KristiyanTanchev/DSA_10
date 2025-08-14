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
        boolean[] dead = new boolean[line1.length() + 1];

        Map<Character, String> dictionary = new TreeMap<>();
        StringBuilder nextCode = new StringBuilder();
        char nextChar = line2.charAt(0);
        for (int i = 1; i < line2.length(); i++) {
            if (Character.isUpperCase(line2.charAt(i))){
                dictionary.put(nextChar, nextCode.toString());
                nextChar = line2.charAt(i);
                nextCode.setLength(0);
            }else{
                nextCode.append(line2.charAt(i));
            }
        }
        dictionary.put(nextChar, nextCode.toString());
        List<Map.Entry<Character,String>> entries = new ArrayList<>(dictionary.entrySet());

        List<String> result = new ArrayList<>();
        decipher(line1, entries, 0, new StringBuilder(), result, dead);

        System.out.println(result.size());
        for (String msg: result){
            System.out.println(msg);
        }
    }

    private static void decipher(String encrypted,
                                 List<Map.Entry<Character, String>> entries,
                                 int start,
                                 StringBuilder current,
                                 List<String> result,
                                 boolean[] dead) {
        if (dead[start]){
            return;
        }
        final int n = encrypted.length();
        if (start == n){
            result.add(current.toString());
            return;
        }
        final char first = encrypted.charAt(start);

        int resultsSizeBefore = result.size();

        for (Map.Entry<Character, String> entry: entries){
            String code = entry.getValue();
            if (code.isEmpty() || first != code.charAt(0)){
                continue;
            }
            if (start + code.length() > n){
                continue;
            }
            if (encrypted.startsWith(code, start)){
                current.append(entry.getKey());
                decipher(encrypted, entries, start + code.length(), current, result, dead);
                current.setLength(current.length() - 1);
            }
        }
        if (result.size() == resultsSizeBefore){
            dead[start] = true;
        }
    }
}
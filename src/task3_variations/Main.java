package task3_variations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int z = Integer.parseInt(bf.readLine());
        String line2 = bf.readLine();
        char x = line2.charAt(0);
        char y = line2.charAt(2);
        if (x > y){
            char temp = x;
            x = y;
            y = temp;
        }
        StringBuilder current = new StringBuilder();
        generateStrings(current, z, x, y);

    }

    private static void generateStrings(StringBuilder current, int maxLen, char x, char y){
        if (current.length() == maxLen){
            System.out.println(current);
            return;
        }
        generateStrings(current.append(x), maxLen, x, y);
        current.setLength(current.length() - 1);
        generateStrings(current.append(y), maxLen, x, y);
        current.setLength(current.length() - 1);
    }
}

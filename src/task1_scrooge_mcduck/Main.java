package task1_scrooge_mcduck;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int posX = 0;
        int posY = 0;
        int[][] lab = new int[rows][cols];
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[i].length; j++) {
                lab[i][j] = scanner.nextInt();
                if (lab[i][j] == 0){
                    posX = j;
                    posY = i;
                }
            }            
        }
        System.out.println(traverseLab(lab, posX, posY));
    }

    private static int traverseLab(int[][] lab, int posX, int posY){
        int[] nextDirection = getLargestNeighbour(lab, posX, posY);
        if (nextDirection.length == 0){
            return 0;
        }

        lab[nextDirection[0]][nextDirection[1]] --;
        return 1 + traverseLab(lab, nextDirection[1], nextDirection[0]);
    }

    private static int[] getLargestNeighbour(int[][] lab, int posX, int posY){
        int max = 0;
        int maxX = -1;
        int maxY = -1;
        if (posX > 0){
            if (lab[posY][posX - 1] > max){
                max = lab[posY][posX - 1];
                maxX = posX - 1;
                maxY = posY;
            }
        }
        if (posX < lab[0].length - 1){
            if (lab[posY][posX + 1] > max){
                max = lab[posY][posX + 1];
                maxX = posX + 1;
                maxY = posY;
            }
        }
        if (posY > 0){
            if (lab[posY - 1][posX] > max){
                max = lab[posY - 1][posX];
                maxX = posX;
                maxY = posY - 1;
            }
        }
        if (posY < lab.length - 1){
            if (lab[posY + 1][posX] > max){
                max = lab[posY + 1][posX];
                maxX = posX;
                maxY = posY + 1;
            }
        }
        if (max == 0){
            return new int[0];
        }
        return new int[]{maxY, maxX};
    }
}

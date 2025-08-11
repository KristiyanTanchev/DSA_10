package task2_largest_surface;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];
        int[][] results = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int maxArea = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (results[i][j] == 0){
                    int currentCount = getSequenceLength(matrix, j, i, 0, results, visited);
                    if (currentCount > maxArea){
                        maxArea = currentCount;
                    }
                }
            }
        }
        System.out.println(maxArea);
        scanner.close();
    }

    private static int getSequenceLength(int[][] matrix, int posX, int posY,
                                         int counter, int[][] results, boolean[][] visited){
        counter++;
        visited[posY][posX] = true;
        int currentCell = matrix[posY][posX];
        if (posX > 0 && currentCell == matrix[posY][posX - 1] && !visited[posY][posX - 1]){
            counter += getSequenceLength(matrix, posX - 1, posY, 0, results, visited);
        }
        if (posX < matrix[0].length - 1 && currentCell == matrix[posY][posX + 1] && !visited[posY][posX + 1]){
            counter += getSequenceLength(matrix, posX + 1, posY, 0, results, visited);
        }
        if (posY > 0 && currentCell == matrix[posY - 1][posX] && !visited[posY - 1][posX]){
            counter += getSequenceLength(matrix, posX, posY - 1, 0, results, visited);
        }
        if (posY < matrix.length - 1 && currentCell == matrix[posY + 1][posX] && !visited[posY + 1][posX]){
            counter += getSequenceLength(matrix, posX, posY + 1, 0, results, visited);
        }
        results[posY][posX] = counter;
        return counter;
    }
}

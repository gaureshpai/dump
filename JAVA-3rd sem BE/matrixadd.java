public class matrixadd{
    public static void main(String args[]){
        if (N <= 0) {
            System.out.println("Please provide a valid positive integer for the order N.");
            return;
        }

        int[][] matrix1 = new int[N][N];
        int[][] matrix2 = new int[N][N];

        fillMatrix(matrix1, 1);
        fillMatrix(matrix2, 2);

        System.out.println("Matrix 1:");
        printMatrix(matrix1);

        System.out.println("\nMatrix 2:");
        printMatrix(matrix2);

        int[][] resultMatrix = addMatrices(matrix1, matrix2);

        System.out.println("\nResultant Matrix (Matrix1 + Matrix2):");
        printMatrix(resultMatrix);
    }

    private static void fillMatrix(int[][] matrix, int startValue) {
        int value = startValue;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = value++;
            }
        }
    }

    private static int[][] addMatrices(int[][] matrix1, int[][] matrix2) {
        int N = matrix1.length;
        int[][] resultMatrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                resultMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return resultMatrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}

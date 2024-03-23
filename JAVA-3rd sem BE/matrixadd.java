public class matrixadd{
    public static void main(String args[]){
        // Check if N is a positive integer
        if (N <= 0) {
            System.out.println("Please provide a valid positive integer for the order N.");
            return;
        }

        // Create two matrices of order N
        int[][] matrix1 = new int[N][N];
        int[][] matrix2 = new int[N][N];

        // Fill the matrices with some sample values (you can modify this as needed)
        fillMatrix(matrix1, 1);
        fillMatrix(matrix2, 2);

        // Print the matrices
        System.out.println("Matrix 1:");
        printMatrix(matrix1);

        System.out.println("\nMatrix 2:");
        printMatrix(matrix2);

        // Add the matrices
        int[][] resultMatrix = addMatrices(matrix1, matrix2);

        // Print the result matrix
        System.out.println("\nResultant Matrix (Matrix1 + Matrix2):");
        printMatrix(resultMatrix);
    }

    // Helper method to fill a matrix with sequential values
    private static void fillMatrix(int[][] matrix, int startValue) {
        int value = startValue;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = value++;
            }
        }
    }

    // Helper method to add two matrices
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

    // Helper method to print a matrix
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}

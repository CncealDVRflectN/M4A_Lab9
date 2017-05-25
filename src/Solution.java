public class Solution {
    private static double calcF(double x) {
        return 1.5 * Math.pow(Math.E, x) - 0.5 * Math.cos(x);
    }

    private static void printArr(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static double calcFactotial(double num) {
        return (num > 0) ? num * calcFactotial(num - 1) : 1;
    }

    private static double calcInterpolationEndOfTable(double[][] table, double x, int n) {
        double[][] divDif = new double[11][11];
        double result = 0;
        double tmp;
        double t = (x - table[0][10]) / 0.1;
        for (int i = 0; i < 11; i++) {
            divDif[0][i] = table[1][i];
        }
        for (int i = 1; i < 11; i++) {
            for (int j = 0; j < 11 - i; j++) {
                divDif[i][j] = divDif[i - 1][j + 1] - divDif[i - 1][j];
            }
        }
        for (int i = 0; i < n + 1; i++) {
            tmp = divDif[i][10 - i];
            for (int j = 0; j < i; j++) {
                tmp *= t + j;
            }
            tmp /= calcFactotial(i);
            result += tmp;
        }
        return result;
    }

    public static void main(String[] args) {
        double[][] table = new double[2][11];
        int N = 10;
        for (int i = 0; i <= N; i++) {
            table[0][i] = (double)i / N;
            table[1][i] = calcF(table[0][i]);
        }
        System.out.print("X: ");
        printArr(table[0]);
        System.out.print("Y: ");
        printArr(table[1]);
        System.out.println("n = 5");
        System.out.println("r*: " + Math.abs(calcInterpolationEndOfTable(table, 0.1 / 3, 5) - calcF(0.1 / 3)));
        System.out.println("r**: " + Math.abs(calcInterpolationEndOfTable(table, 0.5 + 0.1 / 3, 5) - calcF(0.5 + 0.1 / 3)));
        System.out.println("r***: " + Math.abs(calcInterpolationEndOfTable(table, 1 - 0.1 / 3, 5) - calcF(1 - 0.1 / 3)));
        System.out.println("n = 10");
        System.out.println("r*: " + Math.abs(calcInterpolationEndOfTable(table, 0.1 / 3, 10) - calcF(0.1 / 3)));
        System.out.println("r**: " + Math.abs(calcInterpolationEndOfTable(table, 0.5 + 0.1 / 3, 10) - calcF(0.5 + 0.1 / 3)));
        System.out.println("r***: " + Math.abs(calcInterpolationEndOfTable(table, 1 - 0.1 / 3, 10) - calcF(1 - 0.1 / 3)));
    }
}

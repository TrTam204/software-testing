package lab5;

public class MathFunc {
    private int calls = 0;

    public int getCalls() { return calls; }

    public long factorial(int n) {
        calls++;
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    public int plus(int a, int b) {
        calls++;
        return a + b;
    }
    // BAI 2 - Boundary Value Testing
    public boolean isValidScore(int score) {
        calls++;
        return score >= 0 && score <= 100;
    }
    // ===== BAI 3 - Exception Testing =====
    public int divide(int a, int b) {
        calls++;
        if (b == 0) throw new ArithmeticException("Divide by zero");
        return a / b;
    }
}


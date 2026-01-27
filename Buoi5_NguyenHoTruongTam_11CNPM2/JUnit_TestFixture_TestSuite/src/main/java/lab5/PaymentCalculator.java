package lab5;

public class PaymentCalculator {

    public enum Type { CHILD, FEMALE, MALE }

    public static int calc(int age, Type type) {
        if (type == null) throw new IllegalArgumentException("type is required");
        if (age < 0 || age > 145) throw new IllegalArgumentException("age out of range");

        if (age <= 17) return 50; // 0-17

        if (type == Type.MALE) {
            if (age <= 35) return 100;
            if (age <= 50) return 120;
            return 140; // 51-145
        }

        if (type == Type.FEMALE) {
            if (age <= 35) return 80;
            if (age <= 50) return 110;
            return 140; // 51-145
        }

        throw new IllegalArgumentException("child type only for age 0-17");
    }
}

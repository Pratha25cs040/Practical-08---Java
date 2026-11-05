import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int attempt = 1;
        boolean completed = false;

        while (!completed) {
            try {
                System.out.print("Enter first number: ");
                double a = Double.parseDouble(sc.nextLine());

                System.out.print("Enter operator (+, -, *, /): ");
                String op = sc.nextLine();

                System.out.print("Enter second number: ");
                double b = Double.parseDouble(sc.nextLine());

                double result;

                switch (op) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        if (b == 0) {
                            throw new DivideByZeroException("Cannot divide by zero.");
                        }
                        result = a / b;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator.");
                }

                System.out.println("Result: " + result);
                completed = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numbers.");
            } catch (DivideByZeroException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("Attempt " + attempt + " completed.");
                attempt++;
            }
        }

        sc.close();
    }
}

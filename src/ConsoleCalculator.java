import java.util.Scanner;

public class ConsoleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            int result = calculate(input);
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static int calculate(String expression) {
        String[] tokens = expression.split(" ");
        if (tokens.length != 3 && tokens.length != 5) {
            throw new IllegalArgumentException("Неправильный формат выражения");
        }

        try {
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[2]);
            int c = 0;
            char operator1 = tokens[1].charAt(0);
            char operator2 = 0;

            if (tokens.length == 5) {
                c = Integer.parseInt(tokens[4]);
                operator2 = tokens[3].charAt(0);
            }

            if (a < 1 || a > 10 || b < 1 || b > 10 || (c != 0 && (c < 1 || c > 10))) {
                throw new IllegalArgumentException("Числа должны быть от 1 до 10");
            }

            if (tokens.length == 3) {
                switch (operator1) {
                    case '+':
                        return a + b;
                    case '-':
                        return a - b;
                    case '*':
                        return a * b;
                    case '/':
                        return a / b;
                    default:
                        throw new IllegalArgumentException("Неподдерживаемая операция");
                }
            } else {
                switch (operator1) {
                    case '+':
                        switch (operator2) {
                            case '+':
                                return a + b + c;
                            case '-':
                                return a + b - c;
                            case '*':
                                return a + b * c;
                            case '/':
                                return a + b / c;
                            default:
                                throw new IllegalArgumentException("Неподдерживаемая операция");
                        }
                    case '-':
                        switch (operator2) {
                            case '+':
                                return a - b + c;
                            case '-':
                                return a - b - c;
                            case '*':
                                return a - b * c;
                            case '/':
                                return a - b / c;
                            default:
                                throw new IllegalArgumentException("Неподдерживаемая операция");
                        }
                    case '*':
                        if (operator2 == '-') {
                            return a * b - c;
                        } else {
                            throw new IllegalArgumentException("Неподдерживаемая операция");
                        }
                    case '/':
                        if (operator2 == '*') {
                            return a / b * c;
                        } else {
                            throw new IllegalArgumentException("Неподдерживаемая операция");
                        }
                    default:
                        throw new IllegalArgumentException("Неподдерживаемая операция");
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неправильный формат чисел");
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Ошибка деления на ноль");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Неправильное количество аргументов");
        }
    }
}

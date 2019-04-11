package ru.job4j.solid;

import ru.job4j.calculator.Calculator;

import java.io.InputStream;
import java.util.*;

/**
 * @author Valeriy Gyrievskikh
 * @since 10.04.2019
 */
public class InteractCalc {
    /**
     * Калькулятор, используемый для вычислений.
     */
    private final Calculator calculator;
    /**
     * Устройство ввода данных.
     */
    private final Scanner scanner;
    /**
     * Список доступных операций.
     */
    private List operationsList;
    /**
     * Текущий результат вычислений.
     */
    private double result;

    public static void main(String[] args) {
        InteractCalc calc = new InteractCalc();
        calc.calcStart();
    }

    /**
     * Конструктор, выполняет инициализацию полей.
     *
     * @param calculator Используемый калькулятор.
     * @param input      Используемое устройство ввода.
     */
    private InteractCalc(Calculator calculator, InputStream input) {
        this.calculator = calculator;
        scanner = new Scanner(input);
        operationsList = Arrays.asList(1, 2, 3, 4);
    }

    /**
     * Конструктор, позволяющий задать устройство ввода.
     *
     * @param input Выбранное устройство ввода.
     */
    public InteractCalc(InputStream input) {
        this(new Calculator(), input);
    }

    /**
     * Конструктор, устанавливает в качестве устройства ввода ввод с консоли.
     */
    public InteractCalc() {
        this(new Calculator(), System.in);
    }

    /**
     * Метод выполняет расчет по заданным параметрам.
     */
    private void calculate(int operation, double firstOperand, double secondOperand) {
        switch (operation) {
            case 1:
                calculator.add(firstOperand, secondOperand);
                break;
            case 2:
                calculator.subtract(firstOperand, secondOperand);
                break;
            case 3:
                calculator.div(firstOperand, secondOperand);
                break;
            case 4:
                calculator.multiply(firstOperand, secondOperand);
                break;
            default:
                break;
        }
        result = calculator.getResult();
    }

    /**
     * Метод запускает механизм расчета, и выполняет его,
     * пока пользователь не остановит выполнение.
     */
    public void calcStart() {
        int calc = 0;
        do {
            System.out.println("Make new calc? Exit - 0.");
            if ((calc = scanner.nextInt()) != 0) {
                calculate(selectOperation(), setOperand("first"), setOperand("second"));
                System.out.println("result:" + result);
            }
        } while (calc != 0);
    }

    /**
     * Метод устаналивает выбираемую операцию.
     *
     * @return Выбранная операция.
     */
    private int selectOperation() {
        int operation = 0;
        boolean selected = false;
        do {
            System.out.println("Select operation:");
            System.out.println("1 - add;");
            System.out.println("2 - subtract;");
            System.out.println("3 - division");
            System.out.println("4 - multiply");
            operation = scanner.nextInt();
            if (operationsList.contains(operation)) {
                selected = true;
            }
        } while (!selected);
        return operation;
    }

    /**
     * Метод устанавливает значение операнда.
     *
     * @return Значение операнда.
     */
    private double setOperand(String text) {
        double operand = 0;
        boolean selected = false;
        do {
            System.out.println("Set " + text + " operand value:");
            System.out.println("1 - new;");
            System.out.println("2 - current result.");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Input number:");
                    operand = scanner.nextDouble();
                    selected = true;
                    break;
                case 2:
                    operand = calculator.getResult();
                    selected = true;
                    break;
                default:
                    break;
            }
        } while (!selected);
        return operand;
    }

    /**
     * Метод возвращает текущий результат вычислений.
     *
     * @return Текущий результат.
     */
    public double getResult() {
        return result;
    }
}

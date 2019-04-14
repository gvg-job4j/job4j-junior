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
     * Список доступных инженерных операций.
     */
    private List engOperationsList;
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
        engOperationsList = Arrays.asList(5, 6, 7, 8, 9, 10);
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
    private void calculate(int operation) {
        switch (operation) {
            case 1:
                calculator.add(setOperand(" first operand"), setOperand(" second operand"));
                break;
            case 2:
                calculator.subtract(setOperand(" first operand"), setOperand(" second operand"));
                break;
            case 3:
                calculator.div(setOperand(" first operand"), setOperand(" second operand"));
                break;
            case 4:
                calculator.multiply(setOperand(" first operand"), setOperand(" second operand"));
                break;
            case 5:
                calculator.getSin(setOperand(" angle"));
                break;
            case 6:
                calculator.getCos(setOperand(" angle"));
                break;
            case 7:
                calculator.getTan(setOperand(" angle"));
                break;
            case 8:
                calculator.getCotan(setOperand(" angle"));
                break;
            case 9:
                calculator.getLog10(setOperand(""));
                break;
            case 10:
                calculator.getLogn(setOperand(""));
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
                calculate(selectOperation());
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
            System.out.println("3 - division;");
            System.out.println("4 - multiply;");
            System.out.println("5 - get sin;");
            System.out.println("6 - get cos;");
            System.out.println("7 - get tg;");
            System.out.println("8 - get ctn;");
            System.out.println("9 - get log 10;");
            System.out.println("10 - get log n;");
            operation = scanner.nextInt();
            if (operationsList.contains(operation) || engOperationsList.contains(operation)) {
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
            System.out.println("Set" + text + " value:");
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

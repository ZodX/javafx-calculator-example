package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true, isFloat = false, wasFloat = false;
    private double number1;
    private String operator = "";

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           double number2 = Double.parseDouble(display.getText());
           double result = calculator.calculate(number1, number2, operator);
           if (wasFloat)
               display.setText(String.format("%s", result));
           else
               display.setText(String.format("%.0f", result));
           operator = "";
        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = true;
            isFloat = false;
        }
    }

    @FXML
    public void processClear(ActionEvent event) {
        String clearPressed = ((Button) event.getSource()).getText();
        System.out.println(clearPressed);
        startNumber = true;
        display.setText("0");
        operator = "";
        isFloat = false;
        wasFloat = false;
    }

    @FXML
    public void processFloat (ActionEvent event) {
        String floatPressed = ((Button) event.getSource()).getText();
        System.out.println(floatPressed);
        if (!isFloat) {
            display.setText(display.getText() + ".");
            isFloat = true;
            wasFloat = true;
        } else
            return;
    }

}

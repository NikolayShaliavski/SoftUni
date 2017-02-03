package calculator;

import utils.WebUtils;

import java.net.URLDecoder;
import java.text.DecimalFormat;

public class Calculator {

    //have to add absolute path -> System.getProperties("user.dir") doesn't work in server
    private static final String CALCULATOR_HTML_PATH =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\01.Java_Web_Development_Basics" +
            "\\02.HTTP_Protocol_Exercise\\HomeworkHTTPProtocol\\src\\main\\resources\\static\\html\\calculator.html";

    public static void main(String[] args) {
        printContentType();
        String html = WebUtils.readHTML(CALCULATOR_HTML_PATH);
        System.out.println(html);
        calculateResult();
    }

    private static void calculateResult() {
        String line = System.getProperty("cgi.query_string");
        if (line.equals("")) {
            return;
        }
        String[] params = line.split("&");

        try {
            Double num1 = Double.parseDouble(params[0].substring(params[0].indexOf('=') + 1));
            String signParam = URLDecoder.decode(params[1], "UTF-8");
            Character sign = signParam.charAt(signParam.indexOf('=') + 1);
            Double num2 = Double.parseDouble(params[2].substring(params[2].indexOf('=') + 1));

            Double result = calculate(num1, num2, sign);
            DecimalFormat df = new DecimalFormat("0.####");
            System.out.printf("Result: %s%n", df.format(result));
        } catch (Exception ex) {
            System.out.println("Invalid input");
        }
    }

    private static Double calculate(Double num1, Double num2, Character sign) {
        switch (sign) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default: throw new NumberFormatException();
        }
    }

    private static void printContentType() {
        String contentType = "Content-Type: text/html\n";
        System.out.println(contentType);
    }
}

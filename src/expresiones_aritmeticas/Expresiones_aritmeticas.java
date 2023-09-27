package expresiones_aritmeticas;

import java.util.Scanner;
import java.util.Stack;


public class Expresiones_aritmeticas {

    // Función para verificar si un carácter es un operador válido (+, -, *, /)
    private static boolean esOperador(char caracter) {
        return caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/';
    }

    // Función para obtener la precedencia de un operador
    private static int precedencia(char operador) {
        if (operador == '+' || operador == '-') {
            return 1;
        } else if (operador == '*' || operador == '/') {
            return 2;
        }
        return 0;
    }
    
    // Función para evaluar una expresión aritmética
    public static String evaluarExpresion(String expresion) {
        // Verifica si la expresión comienza con un operador inválido
        if (expresion.length() > 0 && esOperador(expresion.charAt(0))) {
            return "Expresión incorrecta, !!NO PUEDES INICIAR CON EL SIGNO / O *!!";
        }

        Stack<Integer> operandos = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);

            if (Character.isDigit(caracter)) {
                StringBuilder numero = new StringBuilder();
                while (i < expresion.length() && Character.isDigit(expresion.charAt(i))) {
                    numero.append(expresion.charAt(i));
                    i++;
                }
                i--; // Retrocede el índice
                operandos.push(Integer.parseInt(numero.toString()));
            } else if (esOperador(caracter)) {
                // Verifica si hay dos operadores seguidos
                if (i > 0 && esOperador(expresion.charAt(i - 1))) {
                    return "Expresión incorrecta, !!!NO PUEDES JUNTAR DOS SIGNOS!";

                }
                while (!operadores.isEmpty() && precedencia(caracter) <= precedencia(operadores.peek())) {
                    char operador = operadores.pop();
                    int operand2 = operandos.pop();
                    int operand1 = operandos.pop();
                    int resultado = 0;
                    switch (operador) {
                        case '+':
                            resultado = operand1 + operand2;
                            break;
                        case '-':
                            resultado = operand1 - operand2;
                            break;
                        case '*':
                            resultado = operand1 * operand2;
                            break;
                        case '/':
                            resultado = operand1 / operand2;
                            break;
                    }
                    operandos.push(resultado);
                }
                operadores.push(caracter);
            } else {
                return "Expresión incorrecta";
            }
        }

        while (!operadores.isEmpty()) {
            char operador = operadores.pop();
            int operand2 = operandos.pop();
            int operand1 = operandos.pop();
            int resultado = 0;
            switch (operador) {
                case '+':
                    resultado = operand1 + operand2;
                    break;
                case '-':
                    resultado = operand1 - operand2;
                    break;
                case '*':
                    resultado = operand1 * operand2;
                    break;
                case '/':
                    resultado = operand1 / operand2;
                    break;
            }
            operandos.push(resultado);
        }

        if (operandos.size() == 1) {
            return "Resultado: " + operandos.pop();
        } else {
            return "Expresión incorrecta";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Ingrese una expresión (o 'salir' para terminar): ");
            String expresion = scanner.nextLine();

            if (expresion.equalsIgnoreCase("salir")) {
                break;
            }

            String resultado = evaluarExpresion(expresion);
            System.out.println("Resultado: " + resultado);
        }

        scanner.close();
    }
}

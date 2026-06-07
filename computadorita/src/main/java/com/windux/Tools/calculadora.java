package com.windux.Tools;

import java.util.Stack;

public class calculadora {
    public static String convertirApostfix(String infix){
        StringBuilder postfix = new StringBuilder();
        Stack<Character> pila = new Stack<>();
        for (int i = 0; i < infix.length(); i++) {

            char c = infix.charAt(i);

            // Ignorar espacios
            if (c == ' ') {
                continue;
            }

            // Si es número
            if (Character.isDigit(c)) {
                postfix.append(c);
            }

            // Si abre paréntesis
            else if (c == '(') {
                pila.push(c);
            }

            // Si cierra paréntesis
            else if (c == ')') {

                while (!pila.isEmpty() && pila.peek() != '(') {
                    postfix.append(" ");
                    postfix.append(pila.pop());
                }

                pila.pop(); // eliminar '('
            }

            // Operadores
            else {

                postfix.append(" ");

                while (!pila.isEmpty() &&
                        prioridad(pila.peek()) >= prioridad(c)) {

                    postfix.append(pila.pop());
                    postfix.append(" ");
                }

                pila.push(c);
            }
        }

        while (!pila.isEmpty()) {
            postfix.append(" ");
            postfix.append(pila.pop());
        }

        return postfix.toString();
    }
    public static String resolverExpresionPostfix(String postfix){
        Stack<Integer> pila = new Stack<>();

        String[] tokens = postfix.split(" ");

        for (String token : tokens) {

            if (token.isEmpty()) {
                continue;
            }

            // Si es número
            if (Character.isDigit(token.charAt(0))) {
                pila.push(Integer.parseInt(token));
            }

            // Operadores
            else {

                int b = pila.pop();
                int a = pila.pop();

                switch (token) {

                    case "+":
                        pila.push(a + b);
                        break;

                    case "-":
                        pila.push(a - b);
                        break;

                    case "*":
                        pila.push(a * b);
                        break;

                    case "/":
                        pila.push(a / b);
                        break;
                }
            }
        }

        return String.valueOf(pila.pop());
    }

 // Prioridad de operadores
    public static int prioridad(char operador) {

        return switch (operador) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }
}
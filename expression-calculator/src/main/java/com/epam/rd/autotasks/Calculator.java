package com.epam.rd.autotasks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet("/calc")
public class Calculator extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String expression = req.getParameter("expression");
        Map<Character, Integer> variables = parseVariables(req);

        String replacedExpression = replaceVariables(expression, variables);

        try {
            resp.getWriter().println(eval(replacedExpression));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(e.getMessage());
        }
    }

    private Map<Character, Integer> parseVariables(HttpServletRequest req) {
        Map<Character, Integer> variables = new HashMap<>();
        for (String paramName : req.getParameterMap().keySet()) {
            if (!paramName.equals("expression")) {
                try {
                    int value = Integer.parseInt(req.getParameter(paramName));
                    variables.put(paramName.charAt(0), value);
                } catch (NumberFormatException e) {
                    variables.put(paramName.charAt(0),
                            variables.getOrDefault(req.getParameter(paramName).charAt(0), 0));
                }
            }
        }
        return variables;
    }
    private String replaceVariables(String expression, Map<Character, Integer> variables){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++){
            char ch = expression.charAt(i);
            if (isVariable(ch)){
                sb.append(variables.get(ch));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    private boolean isVariable(char ch){
        return ch >= 'a' && ch <= 'z';
    }
    public static Integer eval(final String str) {
        return new Object() {
            int pos = -1, ch;
            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
            int parse() {
                nextChar();
                int x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }
            int parseExpression() {
                int x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            int parseTerm() {
                int x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }
            int parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                int x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Integer.parseInt(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }
                return x;
            }
        }.parse();
    }
}
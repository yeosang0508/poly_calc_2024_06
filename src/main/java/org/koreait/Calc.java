package org.koreait;

import java.util.Arrays;
import java.util.stream.Collectors;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {
    public static int run(String exp) {
        // (20 + 20) + 20
        exp = exp.trim(); // 양 옆의 쓸데없는 공백 제거
        // 괄호 제거
        exp = stripOuterBrackets(exp);

        // 단일항이 들어오면 바로 리턴
        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }

        boolean needToMulti = exp.contains(" * ");
        boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
        boolean needToSplit = exp.contains("(") || exp.contains(")");
        boolean needToCompound = needToMulti && needToPlus;

        if (needToSplit) {
            int firstbracket = 0;
            int lastbracket = 1;

            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == '(') {
                    firstbracket = i;
                    break;
                }
            }
            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == ')') {
                    lastbracket = i;
                }
            }

            String firstExp = "";
            String secondExp = "";
            char operator ;


            if (firstbracket != 0) {
                if(exp.contains("-(")){
                    exp = exp.replaceAll("-\\(", "(-");
                    exp = exp.replaceAll(" \\+ ", " + -");
                    return Calc.run(exp);
                }

                firstExp = exp.substring(0, firstbracket - 3);
                secondExp = exp.substring(firstbracket, lastbracket + 1);
                operator = exp.charAt(firstbracket - 2);
            } else {
                 firstExp = exp.substring(firstbracket, lastbracket + 1);
                 secondExp = exp.substring(lastbracket + 4);
                operator = exp.charAt( lastbracket + 2);
            }


            exp = Calc.run(firstExp) + " " + operator + " " + Calc.run(secondExp);

            return Calc.run(exp);


        } else if (needToCompound) {
            String[] bits = exp.split(" \\+ ");

            String newExp = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));

            return run(newExp);
        }

        if (needToPlus) {
            exp = exp.replaceAll("- ", "+ -");

            String[] bits = exp.split(" \\+ ");


            int sum = 0;

            for (int i = 0; i < bits.length; i++) {
                sum += Integer.parseInt(bits[i]);
            }

            return sum;
        } else if (needToMulti) {
            String[] bits = exp.split(" \\* ");

            int sum = 1;

            for (int i = 0; i < bits.length; i++) {
                sum *= Integer.parseInt(bits[i]);
            }

            return sum;
        }

        throw new RuntimeException("해석 불가 : 올바른 계산식이 아니야");
    }

    private static String stripOuterBrackets(String exp) {
        int outerBracketsCount = 0;

        while (exp.charAt(outerBracketsCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketsCount) == ')') {
            outerBracketsCount++;
        }

        if (outerBracketsCount == 0) return exp;

        return exp.substring(outerBracketsCount, exp.length() - outerBracketsCount);
    }
}

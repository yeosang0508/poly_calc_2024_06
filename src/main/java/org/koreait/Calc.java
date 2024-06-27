package org.koreait;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {

    public static boolean debug = true;
    public static int runCallCount = 0;

    public static int run(String exp) {
        runCallCount++; // 재귀함수 호출될때마다 하나씩 증가

        // 10 + (10 + 5)
        exp = exp.trim(); // 양 옆의 쓸데없는 공백 제거
        // 괄호 제거
        exp = stripOuterBrackets(exp);

        if (debug) {
            System.out.printf("exp(%d) : %s\n", runCallCount, exp);
        }

        // 단일항이 들어오면 바로 리턴
        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }

        boolean needToMulti = exp.contains(" * ");
        boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
        boolean needToSplit = exp.contains("(") || exp.contains(")");
        boolean needToCompound = needToMulti && needToPlus;

        if (needToSplit) {

            if(exp.contains("-(")) {
                exp = exp.replaceAll("-\\(", "(-");
                exp = exp.replaceAll(" \\+ ", " + -");
                exp = exp.replaceAll("\\) \\+ -", ") + ");
                return Calc.run(exp);
            }


            int splitPointIndex = findSplitPointIndex(exp);

            String firstExp = exp.substring(0, splitPointIndex);
            String secondExp = exp.substring(splitPointIndex + 1);

            char operator = exp.charAt(splitPointIndex);

            exp = Calc.run(firstExp) + " " + operator + " " + Calc.run(secondExp);

            return Calc.run(exp);

        } else if (needToCompound) {
            String[] bits = exp.split(" \\+ ");

            String newExp = Arrays.stream(bits).mapToInt(Calc::run).mapToObj(e -> e + "").collect(Collectors.joining(" + "));

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

    private static int findSplitPointIndex(String exp) {

        int index = findSplitPointIndexBy(exp, '*');

        if (index >= 0) return index;

        return findSplitPointIndexBy(exp, '+');
    }

    private static int findSplitPointIndexBy(String exp, char findChar) {
        int brackesCount = 0;

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == '(') {
                brackesCount++;
            } else if (c == ')') {
                brackesCount--;
            } else if (c == findChar) {
                if (brackesCount == 0) return i;
            }
        }
        return -1;
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


// 해결책 1로는 -(8 + 2) * -(7 + 3) + 5 라는 모양을 replaceAll을 사용하여 (-8 + -2) * (-7 + -3) + 5라는 모양으로 바꾸어주고,
// * 에서 나누어서 코딩, ) +에서 나누어서 코딩 해보았지만, +에서 나누었을때는
// -8 + -2) * (-7 + -3 이런 모양으로 깨져서 나왔지만 ) * ( 을 어떻게 해야 해결할 수 있을까 생각
//  *에서 나누었을 때는 잘 실행되는 것처럼 보였지만 (-8 + -2) 먼저 계산 후 남아있고,
// 뒤에 (-7 + -3) + 5 먼저 계산 후 (-8 + -2)과 합쳐지기에 *이 먼저 되야되는 상황이 해결되지 않았다.
//
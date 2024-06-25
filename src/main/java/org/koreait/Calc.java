package org.koreait;

public class Calc {

    public static int run(String exp) {
        boolean needToMulti = exp.contains("*");

        exp = exp.replaceAll("- ", "+ -");

        String[] bits = exp.split(" \\+ ");

        int sum = 0;

        int num = 1;
        if (needToMulti) {
            String[] bits2 = bits[bits.length - 1].split(" \\* ");

            for (int i = 0; i < bits2.length; i++) {
                num *= Integer.parseInt(bits2[i]);
            }
            for (int i = 0; i < bits.length - 1; i++) {
                sum += Integer.parseInt(bits[i]);
            }

            return sum + num;
        }

        for (int i = 0; i < bits.length; i++) {
            sum += Integer.parseInt(bits[i]);
        }

        return sum;

// throw new RuntimeException("해석 불가 : 올바른 계산식이 아니야");
    }
}
package org.koreait;

public class Calc {
    public static int run(String exp) {

        exp = exp.replaceAll("- ", "+ -");

        System.out.println(exp);

        String[] bits = exp.split(" \\+ ");

        int sum = 0;

        for(int i = 0; i < bits.length; i++) {
            sum += Integer.parseInt(bits[i]);
        }

        return sum;

//        throw new RuntimeException("해석 불가 : 올바른 계산식이 아니야");
    }
}
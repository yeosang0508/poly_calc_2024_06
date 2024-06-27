package org.koreait;

public class Calc_my {

    public static int execute(String pur) {
        int sum = 0;

        if (pur.contains("+")) {

            String[] total = pur.split(" \\+ ");

            sum = Integer.parseInt(total[0]) + Integer.parseInt(total[1]);

        }

        return sum;
    }
}

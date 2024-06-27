package org.koreait;

public class Calc_my {

    public static int execute(String pur) {
        int sum = 0;

        if (pur.contains("+")) {

            String[] total = pur.split(" \\+ ");

            for(int i = 0; i < total.length; i++){
                sum += Integer.parseInt(total[i]);
            }
        }

        return sum;
    }
}

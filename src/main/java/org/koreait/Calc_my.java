package org.koreait;

public class Calc_my {

    public static int execute(String pur) {
        int sum = 0;

        boolean mult = pur.contains(" * ");
        boolean plus = pur.contains(" + ");


        if(mult){
            sum = 1;

            String[] total = pur.split(" \\* ");

            for(int i = 0; i < total.length; i++){
                sum *= Integer.parseInt(total[i]);
            }
        }

        if (plus) {
            pur = pur.replaceAll(" - ", " + -");

            String[] total = pur.split(" \\+ ");

            for(int i = 0; i < total.length; i++){
                sum += Integer.parseInt(total[i]);
            }


        }

        return sum;
    }
}

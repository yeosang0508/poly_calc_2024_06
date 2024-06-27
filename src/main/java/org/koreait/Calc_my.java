package org.koreait;

public class Calc_my {

    public static int execute(String pur) {
        int sum = 0;

        if(!pur.contains(" ")){
            return Integer.parseInt(pur);
        }

        boolean mult = pur.contains(" * ");
        boolean plus = pur.contains(" + ");
        boolean multPlus = pur.contains(" * ") && pur.contains(" + ");


        if(multPlus){
            String[] total = pur.split(" \\+ ");

            for(String s : total){
                sum += Calc_my.execute(s);
            }

            return sum;

        }

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

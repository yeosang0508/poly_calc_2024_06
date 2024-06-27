package org.koreait;

public class Calc_my {

    public static int execute(String pur) {
        int sum = 0;

        if (!pur.contains(" ")) {
            return Integer.parseInt(pur);
        }

        boolean bracket = pur.contains("(") && pur.contains(")");
        boolean mult = pur.contains(" * ");
        boolean plus = pur.contains(" + ") || pur.contains(" - ");
        boolean multPlus = pur.contains(" * ") && pur.contains(" + ");

        if (bracket) {

            return bracketIndex(pur);
        }

        if (multPlus) {
            String[] total = pur.split(" \\+ ");

            for (String s : total) {
                sum += Calc_my.execute(s);
            }

            return sum;

        }

        if (mult) {
            sum = 1;

            String[] total = pur.split(" \\* ");

            for (int i = 0; i < total.length; i++) {
                sum *= Integer.parseInt(total[i]);

            }
            return sum;

        }

        if (plus) {
            pur = pur.replaceAll(" - ", " + -");

            String[] total = pur.split(" \\+ ");

            for (int i = 0; i < total.length; i++) {
                sum += Integer.parseInt(total[i]);
            }

            return sum;
        }

        throw new RuntimeException("해석 불가");
    }

    static int bracketIndex(String pur){
        int firstBracket = 0;
        int lastBracket = 0;

        for (int i = 0; i < pur.length(); i++) {
            if(pur.charAt(i) == '(') {
                firstBracket = i;
                break;
            }
        }

        for (int j = 0; j < pur.length(); j++) {
            if(pur.charAt(j) == ')') {
                lastBracket = j;
            }
        }

        if(firstBracket != 0) {
            String str = pur.substring(0, firstBracket) + Calc_my.execute(pur.substring(firstBracket + 1, lastBracket));

            return Calc_my.execute(str);
        }

        String str = Calc_my.execute(pur.substring(firstBracket + 1, lastBracket)) + pur.substring(lastBracket + 1);



        return Calc_my.execute(str);
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package com.example.tandhim.Models;

public class PriceArabicSpell
{
    public static String droitProportionelle(String s) {
        s = Numeric(s);
        if (s == null) {
            return "";
        }
        final double d = Double.parseDouble(s);
        double droit = 0.0;
        if (d < 100000.0) {
            droit = d * 8.0 / 100.0;
        }
        else if (d < 1000000.0) {
            droit = (d - 100000.0) * 6.0 / 100.0 + 8000.0;
        }
        else if (d < 2000000.0) {
            droit = (d - 1000000.0) * 4.0 / 100.0 + 62000.0;
        }
        else if (d < 3000000.0) {
            droit = (d - 2000000.0) * 3.0 / 100.0 + 102000.0;
        }
        else if (d < 10000000) {
            droit = (d - 3000000.0) * 2.0 / 100.0 + 132000.0;
        }
        else if (d >= 10000000) {
            droit = (d - 10000000) * 1.0 / 100.0 + 272000.0;
        }
        return String.valueOf(droit);
    }
    
    public static String Numeric(final String strNum) {
        if (strNum == null || strNum.equals("")) {
            return "0";
        }
        strNum.replaceAll("\u062f\u062c", "");
        strNum.replaceAll(" ", "");
        strNum.replaceAll(".", ",");
        try {
            final double d = Double.parseDouble(strNum);
            return strNum;
        }
        catch (NumberFormatException nfe) {
            return null;
        }
    }
    
    public static String arabicSpell(String number) {
        number = Numeric(number);
        if (number == null) {
            return null;
        }
        final String[] numberPart = number.split(",");
        String spell = "";
        int k = 1;
        char[] sp;
        for (int i = numberPart[0].length() - 1; i >= 0; i = i - 3) {
            if (i - 2 >= 0) {
                char[] s = {numberPart[0].charAt(i), numberPart[0].charAt(i - 1), numberPart[0].charAt(i - 2)};
                sp = s;
            } else if (i - 1 >= 0) {
                char[] s = {numberPart[0].charAt(i), numberPart[0].charAt(i - 1), '0'};
                sp = s;
            } else {
                char[] s = {numberPart[0].charAt(i), '0', '0'};
                sp = s;
            }
            if (k == 1) {
                spell = splitSpell(sp);
            }
            if (k == 2) {
                spell = splitSpell(sp) + " ?????? ?? " + spell;
            }
            if (k == 3) {
                spell = splitSpell(sp) + " ?????????? ?? " + spell;
            }
            if (k == 4) {
                spell = splitSpell(sp) + " ?????????? ??" + spell;
            }

            k++;
        }
        spell += " ?????????? ???????????? ";

        return spell;
    }

    public static String splitSpell(char[] a) {
        String spell = "";
        if (a[2] == '1') {
            spell += "???????? ?? ";
        }
        if (a[2] == '2') {
            spell += "???????????? ?? ";
        }
        if (a[2] == '3') {
            spell += "???????????????? ?? ";
        }
        if (a[2] == '4') {
            spell += "???????????????? ?? ";
        }
        if (a[2] == '5') {
            spell += "?????????????? ?? ";
        }
        if (a[2] == '6') {
            spell += "???????????? ?? ";
        }
        if (a[2] == '7') {
            spell += "?????????????? ?? ";
        }
        if (a[2] == '8') {
            spell += "???????????????? ?? ";
        }
        if (a[2] == '9') {
            spell += "?????????????? ?? ";
        }
        if (a[1] == '1') {
            if (a[0] == '1') {
                spell += "?????? ??????";
            }
            if (a[0] == '2') {
                spell += "??????????????";
            }
            if (a[0] == '3') {
                spell += "?????????? ??????";
            }
            if (a[0] == '4') {
                spell += "?????????? ??????";
            }
            if (a[0] == '5') {
                spell += "???????? ??????";
            }
            if (a[0] == '6') {
                spell += "?????? ??????";
            }
            if (a[0] == '7') {
                spell += "???????? ??????";
            }
            if (a[0] == '8') {
                spell += "???????????? ??????";
            }
            if (a[0] == '9') {
                spell += "???????? ??????";
            }
            return spell;
        }
        if (a[1] == '0') {
            if (a[0] == '1') {
                spell += "????????";
            }
            if (a[0] == '2') {
                spell += "??????????";
            }
            if (a[0] == '3') {
                spell += "??????????";
            }
            if (a[0] == '4') {
                spell += "??????????";
            }
            if (a[0] == '5') {
                spell += "????????";
            }
            if (a[0] == '6') {
                spell += "??????";
            }
            if (a[0] == '7') {
                spell += "????????";
            }
            if (a[0] == '8') {
                spell += "????????????";
            }
            if (a[0] == '9') {
                spell += "????????";
            }
            return spell;
        }
        if (a[0] == '1') {
            spell += "???????? ?? ";
        }
        if (a[0] == '2') {
            spell += "?????????? ?? ";
        }
        if (a[0] == '3') {
            spell += "?????????? ?? ";
        }
        if (a[0] == '4') {
            spell += "?????????? ?? ";
        }
        if (a[0] == '5') {
            spell += "???????? ?? ";
        }
        if (a[0] == '6') {
            spell += "?????? ?? ";
        }
        if (a[0] == '7') {
            spell += "???????? ?? ";
        }
        if (a[0] == '8') {
            spell += "???????????? ?? ";
        }
        if (a[0] == '9') {
            spell += "???????? ?? ";
        }

        if (a[1] == '2') {
            spell += "??????????";
        }
        if (a[1] == '3') {
            spell += "????????????";
        }
        if (a[1] == '4') {
            spell += "????????????";
        }
        if (a[1] == '5') {
            spell += "??????????";
        }
        if (a[1] == '6') {
            spell += "????????";
        }
        if (a[1] == '7') {
            spell += "??????????";
        }
        if (a[1] == '8') {
            spell += "????????????";
        }
        if (a[1] == '9') {
            spell += "??????????";
        }

        return spell;
    }

}

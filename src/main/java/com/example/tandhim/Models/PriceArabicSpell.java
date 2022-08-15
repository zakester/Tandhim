// 
// Decompiled by Procyon v0.5.36
// 

package com.example.tandhim.Models;

import java.util.ArrayList;

public class PriceArabicSpell
{
    public static void main(String [] args){
       System.out.println(SpellCombine("122000000"));
    }
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
    
    /*public static String arabicSpell(String number) {
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
                spell = splitSpell(sp) + " الف و " + spell;
            }
            if (k == 3) {
                spell = splitSpell(sp) + " مليون و " + spell;
            }
            if (k == 4) {
                spell = splitSpell(sp) + " مليار و" + spell;
            }

            k++;
        }
        spell += " دينار جزائري ";

        return spell;
    }*/

    public static String splitSpell(String nbr) {
        char[]a =nbr.toCharArray();
        System.out.println("the actual nbr ="+a.length);
        String spell = "";
        boolean split= false;
        if (a[2] == '1') {
            spell += "مائة";
            split=true;
        }
        if (a[2] == '2') {
            spell += "مائتان";
            split=true;
        }
        if (a[2] == '3') {
            spell += "ثلاثمائة";
            split=true;
        }
        if (a[2] == '4') {
            spell += "أربعمائة";
            split=true;
        }
        if (a[2] == '5') {
            spell += "خمسمائة";
            split=true;
        }
        if (a[2] == '6') {
            spell += "ستمائة";
            split=true;
        }
        if (a[2] == '7') {
            spell += "سبعمائة";
            split=true;
        }
        if (a[2] == '8') {
            spell += "ثمانمائة";
            split=true;
        }
        if (a[2] == '9') {
            spell += "تسعمائة";
            split=true;
        }
        if (split&&(a[1]!='0'||a[0]!='0')) spell+=" و";
        if (a[1] == '1') {
            if (a[0] == '1') {
                spell += "أحد عشر";
            }
            if (a[0] == '2') {
                spell += "إثناعشر";
            }
            if (a[0] == '3') {
                spell += "ثلاثة عشر";
            }
            if (a[0] == '4') {
                spell += "أربعة عشر";
            }
            if (a[0] == '5') {
                spell += "خمسة عشر";
            }
            if (a[0] == '6') {
                spell += "ستة عشر";
            }
            if (a[0] == '7') {
                spell += "سبعة عشر";
            }
            if (a[0] == '8') {
                spell += "ثمانية عشر";
            }
            if (a[0] == '9') {
                spell += "تسعة عشر";
            }
            return spell;
        }
        if (a[1] == '0') {
            if (a[0] == '1') {
                spell += "واحد";
            }
            if (a[0] == '2') {
                spell += "اثنان";
            }
            if (a[0] == '3') {
                spell += "ثلاثة";
            }
            if (a[0] == '4') {
                spell += "أربعة";
            }
            if (a[0] == '5') {
                spell += "خمسة";
            }
            if (a[0] == '6') {
                spell += "ستة";
            }
            if (a[0] == '7') {
                spell += "سبعة";
            }
            if (a[0] == '8') {
                spell += "ثمانية";
            }
            if (a[0] == '9') {
                spell += "تسعة";
            }
            return spell;
        }
        if (a[0] == '1') {
            spell += "واحد";
        }
        if (a[0] == '2') {
            spell += "اثنان";
        }
        if (a[0] == '3') {
            spell += "ثلاثة";
        }
        if (a[0] == '4') {
            spell += "أربعة";
        }
        if (a[0] == '5') {
            spell += "خمسة";
        }
        if (a[0] == '6') {
            spell += "ستة";
        }
        if (a[0] == '7') {
            spell += "سبعة";
        }
        if (a[0] == '8') {
            spell += "ثمانية";
        }
        if (a[0] == '9') {
            spell += "تسعة";
        }
        spell+=" و";
        if (a[1] == '2') {
            spell += "عشرون";
        }
        if (a[1] == '3') {
            spell += "ثلاثون";
        }
        if (a[1] == '4') {
            spell += "اربعون";
        }
        if (a[1] == '5') {
            spell += "خمسون";
        }
        if (a[1] == '6') {
            spell += "ستون";
        }
        if (a[1] == '7') {
            spell += "سبعون";
        }
        if (a[1] == '8') {
            spell += "ثمانون";
        }
        if (a[1] == '9') {
            spell += "تسعون";
        }

        return spell;
    }
public static String SpellCombine(String number) {
    number = Numeric(number);
    String spell = "";
    if (number == null) {
        return null;
    }
    final String[] numberPart = number.split(",");
    ArrayList<String> parts = getNumberPart(numberPart[0]);
    for (int i= parts.size()-1;i>0;i--){
        System.out.println("part "+i+"= "+parts.get(i)+"\n");
        if (i == 3) {
            spell += splitSpell(parts.get(3));
            spell += " مليار";
            if (!splitSpell(parts.get(2)).equals("")) {
                spell += " و";
            }
        }
        if (i == 2) {
            spell += splitSpell(parts.get(2));
            spell += " مليون";
            if (!splitSpell(parts.get(1)).equals("")) {
                spell += " و";
            }
        }
        if (i == 1) {
            spell += splitSpell(parts.get(1));
            spell += " ألف";
            if (!splitSpell(parts.get(0)).equals("")) {
                spell += " و";
            }
        }
    }
    spell+=splitSpell(parts.get(0));
    spell+= " دينار جزائري";
    if (numberPart.length>1){
        ArrayList<String>parts2= getNumberPart(numberPart[1]);
        for (String s:parts2) {
            System.out.println("dfkjlgndlfkj = "+ s);
        }
        for (int i = parts2.size() - 1; i > 0; i--) {
            if (i == 3) {
                spell += splitSpell(parts2.get(3));
                spell += " مليار";
                if (!splitSpell(parts2.get(2)).equals("")) {
                    spell += " و";
                }
            }
            if (i == 2) {
                spell += splitSpell(parts2.get(2));
                spell += " مليون";
                if (!splitSpell(parts2.get(1)).equals("")) {
                    spell += " و";
                }
            }
            if (i == 1) {
                spell += splitSpell(parts2.get(1));
                spell += " ألف";
                if (!splitSpell(parts2.get(0)).equals("")) {
                    spell += " و";
                }
            }
        }
        spell += splitSpell(parts2.get(0));
        spell += " سنتيم";
    }return spell;
}
public static ArrayList<String> getNumberPart(String number){
    ArrayList<String> part =new ArrayList();
    int i;
    int cpt=0;
    String nbr="";
    for (i=number.length()-1;i>=0;i--) {
        nbr+=number.charAt(i);
        System.out.println(number.charAt(i));
        if (cpt == 2) {
            System.out.println(nbr);
            part.add(nbr);
            nbr="";
            cpt = 0;
        }
        cpt++;
    }
        if (cpt<3){
        for (int j =cpt;j<3;j++){
            nbr+='0';
        }
        part.add(nbr);
        }
    return part;
}
}


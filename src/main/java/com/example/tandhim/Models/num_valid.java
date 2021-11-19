/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import org.jdatepicker.JDatePicker;

/**
 *
 * @author DELL
 */
public class num_valid {

    public boolean ver(JComponent input) {

        boolean a = true;

        return a;
    }

    public boolean ver1(JComponent input) {
        String text = ((JTextField) input).getText();
        boolean a = true;
        if (text.equals(null) || text.equals("")) {
            a = false;
        } else {
            a = true;
        }
        char[] c = text.toCharArray();
        for (char i : c) {
            if (!numer(i)) {
                a = false;
                break;
            } else {
                a = true;
            }
        }
        return a;
    }

    public boolean ver2(JComponent input) {
        String text = ((JTextField) input).getText();
        boolean a = true;
        if (text.equals(null) || text.equals("")) {
            a = false;
        } else {
            a = true;
        }
        char[] c = text.toCharArray();
        for (char i : c) {
            if (!numer1(i)) {
                a = false;
                break;
            } else {
                a = true;
            }
        }
        return a;
    }

    public boolean ver5(JComponent input) {

        if (((JComboBox) input).getItemCount() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean ver3(JComponent input) {
        String[] text = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

        boolean b = true;
        int i = 0;
        for (i = 0; i < ((JComboBox) input).getItemCount(); i++) {
            text[i] = ((JComboBox) input).getItemAt(i).toString();
            if ((text[i] == null) || text[i].equals("")) {
                return false;
            } else {
                b = true;
            }
            String[] a = text[i].split(" العنوان: ");
            if (a.length < 2) {
                return false;
            }
        }
        return b;
    }
    
    public boolean isEmptyDatepicke(JComponent input){
        if(((JDatePicker) input).getModel().getValue() == null){
            return true;
        }else{
            return false;
        }
    }

    public boolean ver4(JComponent input) {
        String text = ((JTextField) input).getText();
        boolean a = true;
        if (text.equals(null) || text.equals("")) {
            a = false;
        } else {
            a = true;
        }
        char[] c = text.toCharArray();
        for (char i : c) {
            if (!numer2(i)) {
                a = false;
                break;
            } else {
                a = true;
            }
        }
        return a;
    }
    
    public boolean numer(char c) {
        boolean a = true;
        String cs = Character.toString(c);
        String s = "1234567890/";
        return s.contains(cs);
    }

    public boolean numer1(char c) {
        boolean a = true;
        String cs = Character.toString(c);
        String s = "1234567890";
        return s.contains(cs);
    }

    public boolean numer2(char c) {
        String cs = Character.toString(c);
        String s = "ابتجحخدذرزسشصضطظعغفقكلمنهوي ةىءئؤأآإ";
        return s.contains(cs);
    }

}

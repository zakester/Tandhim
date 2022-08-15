package com.example.tandhim;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            Main.main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

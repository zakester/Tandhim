/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.tandhim.Models;


import java.awt.Component;
import java.util.*;
import java.io.*;
import java.net.*;
import java.lang.Math;
import java.lang.Exception;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class BDConnection {

    static String host = "localhost";
    static String DB_URL
            = "jdbc:mysql://" + host + ":3306/huissier_de_justice?useUnicode=yes&characterEncoding=UTF-8";
    static final String DB_DRV
            = "com.mysql.jdbc.Driver";
    static final String DB_USER = "root";
    static final String DB_PASSWD = "";

    // Converts IP address to the binary form
    private static int[] bina(String[] str) {
        int re[] = new int[32];
        int a, b, c, d, i, rem;
        a = b = c = d = 1;
        Stack<Integer> st = new Stack<Integer>();

        // Separate each number of the IP address
        if (str != null) {
            a = Integer.parseInt(str[0]);
            b = Integer.parseInt(str[1]);
            c = Integer.parseInt(str[2]);
            d = Integer.parseInt(str[3]);
        }

        // convert first number to binary
        for (i = 0; i <= 7; i++) {
            rem = a % 2;
            st.push(rem);
            a = a / 2;
        }

        // Obtain First octet
        for (i = 0; i <= 7; i++) {
            re[i] = st.pop();
        }

        // convert second number to binary
        for (i = 8; i <= 15; i++) {
            rem = b % 2;
            st.push(rem);
            b = b / 2;
        }

        // Obtain Second octet
        for (i = 8; i <= 15; i++) {
            re[i] = st.pop();
        }

        // convert Third number to binary
        for (i = 16; i <= 23; i++) {
            rem = c % 2;
            st.push(rem);
            c = c / 2;
        }

        // Obtain Third octet
        for (i = 16; i <= 23; i++) {
            re[i] = st.pop();
        }

        // convert fourth number to binary
        for (i = 24; i <= 31; i++) {
            rem = d % 2;
            st.push(rem);
            d = d / 2;
        }

        // Obtain Fourth octet
        for (i = 24; i <= 31; i++) {
            re[i] = st.pop();
        }

        return (re);
    }

    // Converts IP address 
    // from binary to decimal form
    public static int[] deci(int[] bi) {

        int[] arr = new int[4];
        int a, b, c, d, i, j;
        a = b = c = d = 0;
        j = 7;

        for (i = 0; i < 8; i++) {

            a = a + (int) (Math.pow(2, j)) * bi[i];
            j--;
        }

        j = 7;
        for (i = 8; i < 16; i++) {

            b = b + bi[i] * (int) (Math.pow(2, j));
            j--;
        }

        j = 7;
        for (i = 16; i < 24; i++) {

            c = c + bi[i] * (int) (Math.pow(2, j));
            j--;
        }

        j = 7;
        for (i = 24; i < 32; i++) {

            d = d + bi[i] * (int) (Math.pow(2, j));
            j--;
        }

        arr[0] = a;
        arr[1] = b;
        arr[2] = c;
        arr[3] = d;
        return arr;
    }

    private static int[] getNetworkAddr(String ipr) {
        int i;
        String[] str = new String[4];
        // Separate IP address and n
        String[] str1 = ipr.split("/");

        // IP address
        String tr = str1[0];

        // Split IP address into 4 subparts x, y, z, t
        str = tr.split("\\.");

        int[] b = new int[32];

        // Convert IP address to binary form
        b = bina(str);

        int n = Integer.parseInt(str1[1]);
        int[] ntwk = new int[32];
        int[] brd = new int[32];
        int t = 32 - n;

        // Obtanining network address
        for (i = 0; i <= (31 - t); i++) {

            ntwk[i] = b[i];
            brd[i] = b[i];
        }

        // Set 32-n bits to 0
        for (i = 31; i > (31 - t); i--) {

            ntwk[i] = 0;
        }

        int[] nt = deci(ntwk);

        return nt;

    }

    public static boolean thereIsMySqlServer(String host) {
        Socket Skt;
        try {
            Skt = new Socket(host, 3306);
            System.out.println("Models.BDConnection.thereIsMySqlServer() " + host);
            return true;
        } catch (IOException ex) {
            return false;
        }

    }

    private static String showIpAddr(int[] nt) {
        return nt[0] + "." + nt[1] + "." + nt[2] + "." + nt[3];
    }

    public static void findMySqlServer() {
        if (thereIsMySqlServer("localhost")) {
            host = "localhost";
        } else {
            InetAddress localhost;
            String ipr = "";
            try {
                localhost = InetAddress.getLocalHost();
                ipr = localhost.getHostAddress() + '/';
                NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localhost);
                ipr += networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
                int[] ip = getNetworkAddr(ipr);
                ip[3]++;
                for (int i = 1; i <= 254; i++) {

                    if (isConnectionpossibele(showIpAddr(ip)) != null) {
                        break;
                    }
                    ip[3]++;
                }
                //TODO ADD CHECK FUNCTION
                if (isConnectionpossibele(showIpAddr(ip)) == null) {

                }
                //System.out.println(showIpAddr(ip));
            } catch (UnknownHostException ex) {
                Logger.getLogger(BDConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(BDConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Models.BDConnection.findMySqlServer()");
        }
    }
    public static int findMySqlServer1() {
        if (thereIsMySqlServer("localhost")) {
            host = "localhost";
        } else {
            InetAddress localhost;
            String ipr = "";
            try {
                localhost = InetAddress.getLocalHost();
                ipr = localhost.getHostAddress() + '/';
                NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localhost);
                ipr += networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
                int[] ip = getNetworkAddr(ipr);
                ip[3]++;
                for (int i = 1; i <= 254; i++) {

                    if (isConnectionpossibele(showIpAddr(ip)) != null) {
                        return i;
                    }
                    ip[3]++;
                }
                //TODO ADD CHECK FUNCTION
                if (isConnectionpossibele(showIpAddr(ip)) == null) {
                    return 0;
                }
                //System.out.println(showIpAddr(ip));
            } catch (UnknownHostException ex) {
                Logger.getLogger(BDConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(BDConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Models.BDConnection.findMySqlServer()");
        }
        return 0;}

    private static Connection isConnectionpossibele(String host) {
        if (thereIsMySqlServer(host)) {
            Connection connection = null;
            try {
                DB_URL
                        = "jdbc:mysql://" + host + ":3306/huissier_de_justice?useUnicode=yes&characterEncoding=UTF-8";
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            } catch (SQLException ex) {
            }
            BDConnection.host = host;
            return connection;
        }
        return null;
    }

    public static Connection getConnection()   {

        Connection c = isConnectionpossibele(host);
        while(c==null)
        {c = isConnectionpossibele(host);
        }
        return c;
    }
}
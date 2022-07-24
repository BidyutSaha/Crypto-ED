package com.example.crypto_ed;

import java.util.ArrayList;

import java.lang.Math;

public class BEETLE_256 {

    public String[][] MixColummSerial(String[][] mat) {
        String[][] M = { { "0000", "0001", "0000", "0000", "0000", "0000", "0000", "0000" },
                { "0000", "0000", "0001", "0000", "0000", "0000", "0000", "0000" },
                { "0000", "0000", "0000", "0001", "0000", "0000", "0000", "0000" },
                { "0000", "0000", "0000", "0000", "0001", "0000", "0000", "0000" },
                { "0000", "0000", "0000", "0000", "0000", "0001", "0000", "0000" },
                { "0000", "0000", "0000", "0000", "0000", "0000", "0001", "0000" },
                { "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0001" },
                { "0010", "0100", "0010", "1011", "0010", "1000", "0101", "0110" } };
        String[][] M8;
        M8 = this.MatrixFieldMult(M, M);
        for (int i = 0; i < 6; i++) {
            M8 = this.MatrixFieldMult(M8, M);
        }

        return this.MatrixFieldMult(M8, mat);
        // return this.xnor(M8, mat);

    }

    public String code_2Y(String w) {
        String I = "0011", H;
        if (w.substring(0, 1).equals("0")) {
            return w.substring(1, w.length()) + "0";
        }
        H = w.substring(1, w.length()) + "0";

        return this.xor(H, I);
    }

    public String field_multiplication(String x, String y) {
        int x_int = Integer.parseInt(x, 2);
        String _2y, _4y, _6y, _8y, _10y, _12y, _14y;
        switch (x_int) {
            case 0:
                return "0000";
            case 1:
                return y;
            case 2:
                return this.code_2Y(y);
            case 3:
                _2y = this.code_2Y(y);
                return this.xor(_2y, y);
            case 4:
                _2y = this.code_2Y(y);
                return this.code_2Y(_2y);
            case 5:
                _2y = this.code_2Y(y);
                _4y = this.code_2Y(_2y);
                return this.xor(_4y, y);
            case 6:
                _2y = this.code_2Y(y);
                _4y = this.code_2Y(_2y);
                return this.xor(_4y, _2y);

            case 7:
                _2y = this.code_2Y(y);
                _4y = this.code_2Y(_2y);
                _6y = this.xor(_4y, _2y);
                return this.xor(_6y, y);
            case 8:
                _2y = this.code_2Y(y);
                _4y = this.code_2Y(_2y);
                return this.code_2Y(_4y);
            case 9:
                _2y = this.code_2Y(y);
                _4y = this.code_2Y(_2y);
                _8y = this.code_2Y(_4y);
                return this.xor(_8y, y);
            case 10:
                _2y = this.code_2Y(y);
                _4y = this.code_2Y(_2y);
                _8y = this.code_2Y(_4y);
                return this.xor(_8y, _2y);

            case 11:
                _2y = this.code_2Y(y);
                _4y = this.code_2Y(_2y);
                _8y = this.code_2Y(_4y);
                _10y = this.xor(_8y, _2y);
                return this.xor(_10y, y);

            case 12:
                _2y = this.code_2Y(y);
                _4y = this.code_2Y(_2y);
                _8y = this.code_2Y(_4y);
                return this.xor(_8y, _4y);
            case 13:
                _2y = this.code_2Y(y);
                _4y = this.code_2Y(_2y);
                _8y = this.code_2Y(_4y);
                _12y = this.xor(_8y, _4y);
                return this.xor(_12y, y);
            case 14:
                _2y = this.code_2Y(y);
                _4y = this.code_2Y(_2y);
                _8y = this.code_2Y(_4y);
                _12y = this.xor(_8y, _4y);
                return this.xor(_12y, _2y);
            case 15:
                _2y = this.code_2Y(y);
                _4y = this.code_2Y(_2y);
                _8y = this.code_2Y(_4y);
                _12y = this.xor(_8y, _4y);
                _14y = this.xor(_12y, _2y);
                return this.xor(_14y, y);

        }
        return "";
    }

    public String[][] MatrixFieldMult(String[][] a, String[][] b) {
        String[][] result = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String placeholder = "0000", tmp;
                for (int k = 0; k < 8; k++) {
                    tmp = this.field_multiplication(a[i][k], b[k][j]);
                    placeholder = this.xor(placeholder, tmp);
                }
                result[i][j] = placeholder;
            }
        }

        return result;
    }

    public String[][] xnor(String[][] a, String[][] b) {
        String[][] result = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                result[i][j] = this.lxnor(a[i][j], b[i][j]);
            }
        }

        return result;
    }

    public String lxor(String a, String b) {
        String rsp = "";
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                rsp = rsp + "0";
            } else {
                rsp = rsp + "1";
            }
        }
        return rsp;
    }

    public String lxnor(String a, String b) {
        String rsp = "";
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                rsp = rsp + "1";
            } else {
                rsp = rsp + "0";
            }
        }
        return rsp;
    }

    public String intToBinaryString(int val, int l) {
        String rsp;
        rsp = Integer.toBinaryString(val);
        int k = l - rsp.length();
        for (int i = 0; i < k; i++) {
            rsp = "0" + rsp;
        }

        return rsp;
    }

    public String lxor(String a, int b) {
        int l = a.length();
        String tmp;
        tmp = this.intToBinaryString(b, l);
        return this.lxor(a, tmp);

    }

    public String xor(String a, String b) {
        String rsp = "";
        int l = a.length();

        int a_int = Integer.parseInt(a, 2);

        int b_int = Integer.parseInt(b, 2);

        int c = a_int ^ b_int;
        rsp = Integer.toBinaryString(c);
        int k = l - rsp.length();
        for (int i = 0; i < k; i++) {
            rsp = "0" + rsp;
        }
        return rsp;
    }

    public String xor(String a, int b) {
        String rsp = "";
        int l = a.length();
        int a_int = Integer.parseInt(a, 2);
        int b_int = b;
        int c = a_int ^ b_int;
        rsp = Integer.toBinaryString(c);
        int k = l - rsp.length();
        for (int i = 0; i < k; i++) {
            rsp = "0" + rsp;
        }
        return rsp;
    }

    public void printArray(String[][] array, int r, int c) {
        // printing the array
        for (int i = 0; i < r; i++) {
            System.out.println("\n");
            for (int j = 0; j < c; j++) {
                System.out.print(array[i][j] + "\t");
            }
        }
        System.out.println("\n");

    }

    public String[][] ShiftRows(String[][] mat) {
        String tmpArray[][] = new String[8][8];
        int h;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                h = (j + i) % 8;
                tmpArray[i][j] = mat[i][h];
            }
        }
        return tmpArray;
    }

    public String[][] SubCells(String[][] mat) {
        String[] sbox = { "1100", "0101", "0110", "1011", "1001", "0000", "1010", "1101", "0011", "1110", "1111",
                "1000", "0100", "0111", "0001", "0010" };
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mat[i][j] = sbox[Integer.parseInt(mat[i][j], 2)];
            }
        }

        return mat;
    }

    public String[][] AddConstant(String[][] mat, int k) {
        int[] RC = new int[] { 1, 3, 7, 14, 13, 11, 6, 12, 9, 2, 5, 10 };
        int[] IC = new int[] { 0, 1, 3, 7, 15, 14, 12, 8 };
        for (int i = 0; i < 8; i++) {
            int xor = 0;
            Integer x = Integer.parseInt(mat[i][0], 2);
            xor = xor ^ x.intValue();
            xor = xor ^ RC[k];
            xor = xor ^ IC[i];
            mat[i][0] = Integer.toBinaryString(xor);
            mat[i][0] = String.format("%4s", mat[i][0]).replace(' ', '0');
        }

        return mat;
    }

    public String Photon256(String pattern) {

        String tmpArray[][] = new String[8][8];
        String tmp_array_[][] = new String[8][8];

        int f = 0;
        int g = 4;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tmpArray[i][j] = pattern.substring(f, g);
                f = f + 4;
                g = g + 4;
            }
        }

        for (int i = 0; i < 12; i++) {
            tmpArray = this.AddConstant(tmpArray, i);
            tmpArray = this.SubCells(tmpArray);
            tmpArray = this.ShiftRows(tmpArray);
            tmpArray = this.MixColummSerial(tmpArray);

        }

        String rsp = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                rsp = rsp + tmpArray[i][j];
            }
        }

        return rsp;

    }

    public String Ozs(String str) {
        int r = 128;
        if (str.length() % r == 0) {
            return str;
        } else {
            str = str + "1";
            return String.format("%-128s", str).replace(' ', '0');
        }
    }

    public String Trunc(String str, int k) {
        return str.substring(0, k);
    }

    public String Tag(String t) {
        int r = 128;
        int k = r / 128;
        String rsp = "";
        for (int i = 0; i < k; i++) {
            rsp = rsp + this.Trunc(this.Photon256(t.substring(i, i + 256)), 128);
        }
        return rsp;

        // return this.Trunc(this.Photon256(t), 128);
    }

    public String Hash(String IV, String D, int c0) {
        int r = 128;
        int d = (int) Math.ceil((float) D.length() / 128);
        ArrayList<String> Ds = new ArrayList<String>();
        int l, p, u;
        String tmp, Y, Z, W;
        for (int i = 0; i < d; i++) {
            l = i * r;
            p = l + r;
            u = (p > D.length()) ? D.length() : p;
            Ds.add(this.Ozs(D.substring(l, u)));
        }

        for (int i = 0; i < d; i++) {
            tmp = this.Photon256(IV);

            Y = tmp.substring(0, 128);
            Z = tmp.substring(128, 256);

            W = this.lxor(Y, Ds.get(i));
            IV = W + Z;
        }

        IV = this.lxor(IV, c0);
        return IV;

    }

    public ArrayList<String> SplitbyBlocks(String str, int k) {

        int r = k;
        int d = (int) Math.ceil((float) str.length() / r);
        ArrayList<String> lst = new ArrayList<String>();
        int l, p, u;
        String tmp_pad = "";
        for (int i = 0; i < d; i++) {
            l = i * r;
            p = l + r;
            u = (p > str.length()) ? str.length() : p;
            tmp_pad = str.substring(l, u);
            tmp_pad = this.Ozs(tmp_pad);
            // TODO : check if padding is required, if padding then also add the trunc
            lst.add(tmp_pad);
        }
        return lst;
    }

    public String Shuffle(String s) {

        int l = s.length();
        int r = l / 2;
        String str1, str2, ls, rs;
        str1 = s.substring(0, r);
        str2 = s.substring(r, l);
        ls = str1.substring(0, str1.length() - 1);
        rs = str1.substring(str1.length() - 1);
        str1 = rs + ls;
        return str2 + str1;
    }

    public ArrayList<String> _row(String S, String U) {
        int U_len;
        String tmp;
        U_len = U.length();
        tmp = this.Trunc(this.Shuffle(S), U_len);
        tmp = this.lxor(tmp, U);
        S = this.lxor(S, this.Ozs(U));
        ArrayList<String> rsp = new ArrayList<String>();
        rsp.add(S);
        rsp.add(tmp);
        return rsp;

    }

    public ArrayList<String> _row_invs(String S, String V) {
        int V_len;
        String tmp;
        V_len = V.length();
        tmp = this.Trunc(this.Shuffle(S), V_len);
        tmp = this.lxor(tmp, V);
        S = this.lxor(S, this.Ozs(tmp));
        ArrayList<String> rsp = new ArrayList<String>();
        rsp.add(S);
        rsp.add(tmp);
        return rsp;

    }

}

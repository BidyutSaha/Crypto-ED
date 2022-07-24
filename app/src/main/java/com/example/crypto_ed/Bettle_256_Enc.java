package com.example.crypto_ed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bettle_256_Enc extends BEETLE_256 {

    public int C0_assignment(String M, String A) {
        String Lambda = "";
        int r = 128;
        if ((M != Lambda) && (A.length() % r == 0)) {
            return 1;
        }
        if (M != Lambda) {
            return 2;
        }
        if (A.length() % r == 0) {
            return 3;
        }
        return 4;
    }

    public int C1_assignment(String A, String M) {
        String Lambda = "";
        int r = 128;
        if ((A != Lambda) && (M.length() % r == 0)) {
            return 1;
        }
        if (A != Lambda) {
            return 2;
        }
        if (M.length() % r == 0) {
            return 5;
        }
        return 6;
    }

    public List<String> Encription(String K, String N, String A, String M) {
        String IV, C, T, Lambda = "", Y = "", Z = "", tmp = "";
        ArrayList<String> Cs;
        Cs = new ArrayList<String>();

        int c0, c1;
        IV = N + K;
        C = Lambda;
        A = A.trim();
        M = M.trim();

        if (A == Lambda && M == Lambda) {
            T = this.lxor(IV, 1);
            T = this.Tag(T);
            return Arrays.asList(Lambda, T);

        }

        c0 = this.C0_assignment(M, A);
        c1 = this.C1_assignment(A, M);

        if (A != Lambda) {

            IV = this.Hash(IV, A, c0);

        }

        if (M != Lambda) {

            ArrayList<String> ms, tmp_row;
            String W, c_i;
            ms = this.SplitbyBlocks(M, 128);

            for (int i = 0; i < ms.size(); i++) {
                tmp = this.Photon256(IV);
                Y = tmp.substring(0, 128);
                Z = tmp.substring(128, 256);
                tmp_row = this._row(Y, ms.get(i));

                W = tmp_row.get(0);
                c_i = tmp_row.get(1);
                Cs.add(c_i);
                IV = W + Z;
            }
            IV = this.lxor(IV, c1);
            C = "";
            for (int i = 0; i < Cs.size(); i++) {
                C = C + Cs.get(i);
            }

            C = this.Trunc(C, M.length());
        }

        T = this.Tag(IV);
        return Arrays.asList(C, T);

    }

}
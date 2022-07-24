package com.example.crypto_ed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bettle_256_Dec extends BEETLE_256 {

    public int C0_assignment(String C, String A) {
        String Lambda = "";
        int r = 128;
        if ((C != Lambda) && (A.length() % r == 0)) {
            return 1;
        }
        if (C != Lambda) {
            return 2;
        }
        if (A.length() % r == 0) {
            return 3;
        }
        return 4;
    }

    public int C1_assignment(String A, String C) {
        String Lambda = "";
        int r = 128;
        if ((A != Lambda) && (C.length() % r == 0)) {
            return 1;
        }
        if (A != Lambda) {
            return 2;
        }
        if (C.length() % r == 0) {
            return 5;
        }
        return 6;
    }

    public List<String> Decription(String K, String N, String A, String C, String T) {
        String IV, M, Lambda = "", Y = "", Z = "", tmp = "", T_, Negative = "-1";
        ArrayList<String> Ms_;
        Ms_ = new ArrayList<String>();

        int c0, c1;
        IV = N + K;
        M = Lambda;

        K = K.trim();
        N = N.trim();
        A = A.trim();
        C = C.trim();
        T = T.trim();

        if (A == Lambda && M == Lambda) {
            T_ = this.lxor(IV, 1);
            T_ = this.Tag(T_);
            if (T == T_) {
                return Arrays.asList(Lambda, Negative);

            }

        }

        c0 = this.C0_assignment(C, A);
        c1 = this.C1_assignment(A, C);

        if (A != Lambda) {
            IV = this.Hash(IV, A, c0);

        }

        if (C != Lambda) {

            ArrayList<String> cs, tmp_row;
            String W, m_i;
            cs = this.SplitbyBlocks(C, 128);

            for (int i = 0; i < cs.size(); i++) {
                tmp = this.Photon256(IV);

                Y = tmp.substring(0, 128);
                Z = tmp.substring(128, 256);
                tmp_row = this._row_invs(Y, cs.get(i));

                W = tmp_row.get(0);
                m_i = tmp_row.get(1);
                Ms_.add(m_i);
                IV = W + Z;

            }

            IV = this.lxor(IV, c1);
            M = "";
            for (int i = 0; i < Ms_.size(); i++) {
                M = M + Ms_.get(i);
            }
        }

        M = this.Trunc(M, C.length());
        System.out.println("\nM after trunktation : " + M);
        System.out.println("\nT : " + T);

        T_ = this.Tag(IV);
        System.out.println("\nT_ : " + T_);

        if (T == T_) {

            return Arrays.asList(M, "1");

        }
        return Arrays.asList(M, Negative);

    }

}
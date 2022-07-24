package com.example.crypto_ed;

import java.util.List;

class App {

        public static void main(String[] args) throws Exception {
                Bettle_256_Enc enc = new Bettle_256_Enc();
                Bettle_256_Dec dec = new Bettle_256_Dec();
                String K, N, A, M, C, T, T1, M_;
                List<String> rsp;

                K = "10110111111101111110000010101000001011111111011111100000101010001000011111110111111000001010100001001111111101111110000010101000";
                N = "10100111111101111110000010101001101011111111011111100000101010001000011111110111111000001010100001001111111101111110000010101000";
                A = "1100011111110111111000001010101001001111111101111110000010101000110001111111011111100000101010000100111111110111111000001010100011000111101101111110011010101000110001111111011111100000101010000100111111101111110000010101000010011111111011111100000101010000";
                M = "1100011111110111111000001010100010011111111011111100000101010001100011111110111111000001010100001001111111101111110000010101000110001111011011111100110101010001100011111110110111000001010100001001111111011111100000101011100100111111110111111000001010100";

                System.out.println("-------Encription-------");
                System.out.printf("\n K : %s", K);
                System.out.printf("\n N : %s", N);
                System.out.printf("\n A : %s", A);
                System.out.printf("\n M : %s", M);
                rsp = enc.Encription(K, N, A, M);
                C = rsp.get(0);
                T = rsp.get(1);
                System.out.printf("\n C : %s", C);
                System.out.printf("\n T : %s", T);

                System.out.println("\n-------Decription-------");
                System.out.printf("\n K : %s", K);
                System.out.printf("\n N : %s", N);
                System.out.printf("\n A : %s", A);
                System.out.printf("\n C : %s", C);
                System.out.printf("\n T : %s", T);
                rsp = dec.Decription(K, N, A, C, T);
                M_ = rsp.get(0);
                System.out.printf("\n M_ : %s\n", M_);
                System.out.println(rsp.get(1));
                System.out.println(M_.equals(M));



        }
}
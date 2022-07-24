package com.example.crypto_ed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    String[] algorithms = {"Beetle", "Locus"};
    String[] operations = {"Encryption", "Decryption"};
    int algorithm_index = 0;
    int operation_index = 0;


    public void visGroup(int id, int val) {
        // id == 0 : encryption
        // id == 1 :  decryption
        if (id == 0) {
            Group group = (Group) findViewById(R.id.group_enc);
            group.setVisibility(View.VISIBLE);
            if (val == 1) {
                group.setVisibility(View.VISIBLE);

            } else {
                group.setVisibility(View.INVISIBLE);

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner algorithms_spinner = findViewById(R.id.algorithms);
        Spinner operations_spinner = findViewById(R.id.operations);


        algorithms_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                algorithm_index = i;
                String msg = algorithms[algorithm_index] + " : " + operations[operation_index];
                Toast.makeText(getApplicationContext(),
                                msg,
                                Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        operations_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                operation_index = i;
                String msg = algorithms[algorithm_index] + " : " + operations[operation_index];
                int id, val;

                if (i == 0) {
                    Group group = (Group) findViewById(R.id.group_enc);
                    group.setVisibility(View.VISIBLE);


                } else {
                    Group group = (Group) findViewById(R.id.group_enc);
                    group.setVisibility(View.INVISIBLE);
                }


                Toast.makeText(getApplicationContext(),
                                msg,
                                Toast.LENGTH_LONG)
                        .show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter adapter_algorithms = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                algorithms);
        adapter_algorithms.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        algorithms_spinner.setAdapter(adapter_algorithms);


        ArrayAdapter adapter_operations
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,


                operations);
        adapter_operations.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        operations_spinner.setAdapter(adapter_operations);


        Button encrypt_btn = (Button) findViewById(R.id.enc_btn_encpt);
        encrypt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText enc_et_k = (EditText) findViewById(R.id.enc_et_k);
                String K = enc_et_k.getText().toString();

                EditText enc_et_n = (EditText) findViewById(R.id.enc_et_n);
                String N = enc_et_n.getText().toString();

                EditText enc_et_a = (EditText) findViewById(R.id.enc_et_a);
                String A = enc_et_a.getText().toString();

                EditText enc_et_m = (EditText) findViewById(R.id.enc_et_m);
                String M = enc_et_m.getText().toString();

                String C, T;
                List<String> rsp;

                if (algorithm_index == 0) {
                    // beetle
                    Bettle_256_Enc enc = new Bettle_256_Enc();
                    rsp = enc.Encription(K, N, A, M);

                } else {
                    // locus
                    Bettle_256_Enc enc1 = new Bettle_256_Enc();
                    rsp = enc1.Encription(K, N, A, M);


                }
                C = rsp.get(0);
                T = rsp.get(1);


//                String msg = K + " : " + N + " : " + A + " : " + M + " : " + algorithms[algorithm_index];
//                Toast.makeText(getApplicationContext(),
//                                msg,
//                                Toast.LENGTH_LONG)
//                        .show();

                EditText enc_et_chipher = (EditText) findViewById(R.id.enc_et_chipher);
                enc_et_chipher.setText(C);
                EditText enc_et_tag = (EditText) findViewById(R.id.enc_et_tag);
                enc_et_tag.setText(T);


            }
        });


    }


}
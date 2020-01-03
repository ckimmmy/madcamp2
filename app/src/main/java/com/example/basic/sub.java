package com.example.basic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;

public class sub extends Activity {
    private Button btn_move,btn;
    private EditText first_name;
    private EditText phone_number;
    private String str1;
    private String str3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        first_name = findViewById(R.id.first_name);
        phone_number = findViewById(R.id.phone_number);

        btn_move = findViewById(R.id.btn_move);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = first_name.getText().toString();
                str3 = phone_number.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("fn", str1);
                intent.putExtra("pn", str3);
                setResult(RESULT_OK,intent);
                finish();

            }

        });
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent dintent =new Intent();
                setResult(RESULT_OK, dintent);
                finish();
            }
        });
    }
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sub,
                container,
                false);

        return view;
    }
    */

}

package com.example.basic;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Else3 extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View waterView = inflater.inflate(R.layout.fragment_else, container, false);
        EditText summoner = (EditText) waterView.findViewById(R.id.editText);



        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                String summoner_string = summoner.getText().toString();

                Intent newIntent = new Intent(getContext(), Elsea3.class);
                newIntent.putExtra("name", "JM");
                //이름 JM으로 임의로 지정함. 여기서 페이스북으로 이름정보 받아오면 될듯
                newIntent.putExtra("summoner", summoner_string);
                startActivity(newIntent);
            }
        };
        Button btnlogin = (Button) waterView.findViewById(R.id.regButton);
        btnlogin.setOnClickListener(listener);
        return waterView;
    }









}

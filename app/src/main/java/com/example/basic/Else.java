package com.example.basic;


import android.app.Activity;
import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Else extends Fragment {
    String testString;
    String summoner_string;

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

        checkAdded();

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                summoner_string = summoner.getText().toString();

                if(summoner_string.length() == 0){
                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getActivity());
                    alert_confirm.setMessage("소환사명을 입력하세요");
                    alert_confirm.setPositiveButton("확인", null);
                    AlertDialog alert = alert_confirm.create();
                    alert.setTitle("알림");
                    alert.show();
                }
                else {
                    addMySummoner(summoner_string);
                }


            }
        };
        Button btnlogin = (Button) waterView.findViewById(R.id.regButton);
        btnlogin.setOnClickListener(listener);
        return waterView;
    }

    public void checkAdded(){
        String url = "http://1ca89363.ngrok.io/checksummoner";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            testjson.put("summoner", "hi");
            String jsonString = testjson.toString(); //완성된 json 포맷

            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());

                        //key값에 따라 value값을 쪼개 받아옵니다.
                        String result = jsonObject.getString("approve");

                        if(result.equals("OK")){
                            //Toast.makeText(getContext(),"summoner name added well",Toast.LENGTH_SHORT).show();
                            Intent newIntent = new Intent(getContext(), Elsea3.class);
                            startActivity(newIntent);
                        }else{
                            //easyToast("insert name");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addMySummoner(String SUMMONER){
        String url = "http://1ca89363.ngrok.io/summoner";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
//            Else.ExampleThread thread = new Else.ExampleThread();
//            thread.start();
//            try{
//                thread.join();
//            }catch(InterruptedException e)
//            {
//            }

            //Integer virtualtier = Getvirtualtier(SUMMONER);

            //testjson.put("tier", virtualtier.toString());
            testjson.put("summoner", SUMMONER);
            String jsonString = testjson.toString(); //완성된 json 포맷

            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());

                        //key값에 따라 value값을 쪼개 받아옵니다.
                        String result = jsonObject.getString("approve");



                        if(result.equals("OK")){
                            //Toast.makeText(getContext(),"summoner name added well",Toast.LENGTH_SHORT).show();
                            Intent newIntent = new Intent(getContext(), Elsea3.class);
                            startActivity(newIntent);
                        }else{
                            //easyToast("실패");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void easyToast(String str){
        Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
    }

    private class ExampleThread extends Thread{

        //String summoner = getIntent().getStringExtra("summoner");
        Get_Tier3 test = new Get_Tier3();

        public void run(){
            try {
                testString = test.AsyncExample(summoner_string);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public int Getvirtualtier(String s){
        String gottier1 = s.split(" ")[0];
        String gottier2 = s.split(" ")[1];
        int gottier1toint = 0;
        int gottier2toint = 0;
        if(gottier1.equals("CHALLENGER")){
            gottier1toint = 1;
        }
        else if(gottier1.equals("GRANDMASTER")){
            gottier1toint = 2;
        }
        else if(gottier1.equals("MASTER")){
            gottier1toint = 3;
        }
        else if(gottier1.equals("DIAMOND")){
            gottier1toint = 4;
        }
        else if(gottier1.equals("PLATINUM")){
            gottier1toint = 5;
        }
        else if(gottier1.equals("GOLD")){
            gottier1toint = 6;
        }
        else if(gottier1.equals("SILVER")){
            gottier1toint = 7;
        }
        else if(gottier1.equals("BRONZE")){
            gottier1toint = 8;
        }
        else{
            gottier1toint = 9;
        }
        if(gottier2.equals("I")){
            gottier2toint = 1;
        }
        else if(gottier2.equals("II")){
            gottier2toint = 2;
        }
        else if(gottier2.equals("IV")){
            gottier2toint = 4;
        }
        else{
            gottier2toint = 3;
        }
        int realtier = 10*gottier1toint + gottier2toint;
        return realtier;

    }
}

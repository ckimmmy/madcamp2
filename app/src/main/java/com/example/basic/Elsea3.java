package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Elsea3 extends AppCompatActivity {

    String testString=null;
    String Tier = null;
    String name;
    //= getIntent().getStringExtra("name");
    String summoner;
    JSONObject jsonObject;
    String friendnickname = null;
    String friendtier = null;
    TextView yourrank;
    Integer myTier;

    ArrayList<String> namearr = new ArrayList<String>();
    ArrayList<Integer> tier1arr = new ArrayList<Integer>();
    ArrayList<String> nicknamearr = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_else2);
        yourrank = (TextView)findViewById(R.id.yourrank);

        bringName();

    }


    public void mOnPopupClick(View v){
        Intent intent = new Intent(this, FriendAdd.class);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode==RESULT_OK){
                String friendname = data.getStringExtra("nameresult");
                friendnickname = data.getStringExtra("nicknameresult");


                Thread2 thread = new Thread2();
                thread.start();
                try{
                    thread.join();
                }catch(InterruptedException e)
                {
                }
                System.out.println(friendtier);
                System.out.println("------------------------------------------------------------------------entered");
                namearr.add(friendname);
                tier1arr.add(Getvirtualtier(friendtier));
                nicknamearr.add(friendnickname);

                Savedata3 [] letsort = new Savedata3[namearr.size()];

                for(int i=0;i<=(namearr.size()-1);i++){
                    letsort[i]=new Savedata3(namearr.get(i), tier1arr.get(i), nicknamearr.get(i));
                }
                Arrays.sort(letsort);

                ListView listview;
                ListViewAdapter3 adapter;
                adapter = new ListViewAdapter3();
                listview = (ListView) findViewById(R.id.listview1);
                listview.setAdapter(adapter);

                //String summoner = getIntent().getStringExtra("summoner");
                for(int i=0; i<=(letsort.length)-1; i++){
                    if(summoner.equals(letsort[i].nicknamec)){
                        //TextView yourrank = (TextView)findViewById(R.id.yourrank);
                        yourrank.setText("당신의 순위는"+" "+ Integer.toString(i+1)+"위 입니다"+"(전체 "+Integer.toString(letsort.length)+"명)");
                    }
                    else;
                }

                for(int i=0; i<=(letsort.length)-1;i++){
                    if((letsort[i].tierc)/10 == 1){
                        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.challenger), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                    }
                    else if((letsort[i].tierc)/10 == 2){
                        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.grandmaster), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                    }
                    else if((letsort[i].tierc)/10 == 3){
                        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.master), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                    }
                    else if((letsort[i].tierc)/10 == 4){
                        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.diamond), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                    }
                    else if((letsort[i].tierc)/10 == 5){
                        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.platinum), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                    }
                    else if((letsort[i].tierc)/10 == 6){
                        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.gold), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                    }
                    else if((letsort[i].tierc)/10 == 7){
                        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.silver), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                    }
                    else if((letsort[i].tierc)/10 == 8){
                        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bronze), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                    }
                    else {
                        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.iron), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                    }
                }
            }
        }
    }

    private class Thread2 extends Thread{
        Get_Tier3 abc = new Get_Tier3();
        public void run(){
            try{
                friendtier = abc.AsyncExample(friendnickname);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    private class ExampleThread extends Thread{

        //String summoner = getIntent().getStringExtra("summoner");
        Get_Tier3 test = new Get_Tier3();

        public void run(){
            try {
                testString = test.AsyncExample(summoner);
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

    public String reversegettier(int s){
        int a;
        int b;
        a=s/10;
        b=s%10;

        String tier1 = null;
        String tier2 = null;

        if(a==1){
            tier1 = "CHALLENGER";
        }
        else if(a==2){
            tier1 = "GRANDMASTER";
        }
        else if(a==3){
            tier1 = "MASTER";
        }
        else if(a==4){
            tier1 = "DIAMOND";
        }
        else if(a==5){
            tier1 = "PLATINUM";
        }
        else if(a==6){
            tier1 = "GOLD";
        }
        else if(a==7){
            tier1 = "SILVER";
        }
        else if(a==8){
            tier1 = "BRONZE";
        }
        else{
            tier1 = "IRON";
        }

        if(b==1){
            tier2 = "I";
        }

        else if(b==2){
            tier2 = "II";
        }
        else if(b==3){
            tier2 = "III";
        }

        else{
            tier2 = "IV";
        }
        return tier1 + " " + tier2;
    }


    void easyToast(String str){
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }

    public void bringName(){
        String url = "http://1ca89363.ngrok.io/bringsummoner";
        //String name;

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            testjson.put("summoner", "hi");
            String jsonString = testjson.toString(); //완성된 json 포맷

            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아
                        jsonObject = new JSONObject(response.toString());


                        //key값에 따라 value값을 쪼개 받아옵니다.
                        JSONObject mydata = response.getJSONObject("user");
                        JSONArray friendsdata = response.getJSONArray("friends");

//                        name = response.getString("name");
//                        summoner = response.getString("summoner");
//                        myTier = response.getInt("tier");
                        name = mydata.getString("name");
                        summoner = mydata.getString("summoner");
                        myTier = mydata.getInt("tier");

                        //ArrayList<JSONObject> friends_list = friendsdata.getJSONArray

                        for(int j=0; j< friendsdata.length() ;j++) {
                            JSONObject oneUser = friendsdata.getJSONObject(j);
                            namearr.add(oneUser.getString("name"));
                            //System.out.println("------------------------------------------------------------------------" + oneUser.getString("name"));
                            tier1arr.add(oneUser.getInt("tier"));
                            //System.out.println("------------------------------------------------------------------------" + oneUser.getInt("tier"));
                            nicknamearr.add(oneUser.getString("summoner"));
                        }


                        //System.out.println("4");
                        Savedata3 [] letsort = new Savedata3[namearr.size()];

                        for(int i=0;i<=(namearr.size()-1);i++){
                            letsort[i]=new Savedata3(namearr.get(i), tier1arr.get(i), nicknamearr.get(i));
                        }
                        Arrays.sort(letsort);
//
                        //System.out.println("5");
                        ListView listview;
                        ListViewAdapter3 adapter;
                        adapter = new ListViewAdapter3();
                        listview = (ListView) findViewById(R.id.listview1);
                        listview.setAdapter(adapter);

                        for(int i=0; i<=(letsort.length)-1; i++){
                            if(summoner.equals(letsort[i].nicknamec)){
                                //yourrank = (TextView)findViewById(R.id.yourrank);
                                yourrank.setText("당신의 순위는"+" "+ Integer.toString(i+1)+"위 입니다"+"(전체 "+Integer.toString(letsort.length)+"명)");
                            }
                        }

                        //System.out.println("6");

                        for(int i=0; i<=(letsort.length)-1;i++){
                            if((letsort[i].tierc)/10 == 1){
                                adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.challenger), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                            }
                            else if((letsort[i].tierc)/10 == 2){
                                adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.grandmaster), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                            }
                            else if((letsort[i].tierc)/10 == 3){
                                adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.master), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                            }
                            else if((letsort[i].tierc)/10 == 4){
                                adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.diamond), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                            }
                            else if((letsort[i].tierc)/10 == 5){
                                adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.platinum), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                            }
                            else if((letsort[i].tierc)/10 == 6){
                                adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gold), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                            }
                            else if((letsort[i].tierc)/10 == 7){
                                adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.silver), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                            }
                            else if((letsort[i].tierc)/10 == 8){
                                adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bronze), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                            }
                            else {
                                adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.iron), Integer.toString(i+1) + "위", letsort[i].namec, reversegettier(letsort[i].tierc), letsort[i].nicknamec);
                            }
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




}




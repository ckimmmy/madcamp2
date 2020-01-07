package com.example.basic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class FriendAdd extends Activity {
    TextView txtText;
    String friendtier = null;
    String friendnickname = null;
    String friendName;
    EditText friendname;
    EditText friendsummoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.friend_add);
        friendname = (EditText)findViewById(R.id.nametxt);
        friendsummoner = (EditText)findViewById(R.id.nicknametxt);


    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        friendName = friendname.getText().toString();
        friendnickname = friendsummoner.getText().toString();

        Thread2 thread = new Thread2();
        thread.start();
        try{
            thread.join();
        }catch(InterruptedException e)
        {
        }

        addFriend(friendName, friendnickname);






        //액티비티(팝업) 닫기

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    public void addFriend(String name, String summoner){
        String url = "http://1ca89363.ngrok.io/addfriend";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.



            Integer virtualtier = Getvirtualtier(friendtier);


            testjson.put("name", name);
            testjson.put("summoner", summoner);
            testjson.put("tier", virtualtier);
            String jsonString = testjson.toString(); //완성된 json 포맷

            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
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
                            Intent intent = new Intent();
                            intent.putExtra("nameresult", name);
                            intent.putExtra("nicknameresult", summoner);
                            setResult(RESULT_OK, intent);
                            finish();
                        }else{
                            //easyToast("로그인 실패");
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

    private class Thread2 extends Thread {
        Get_Tier3 abc = new Get_Tier3();
        public void run(){
            try{
                friendtier = abc.AsyncExample(friendnickname);
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

    void easyToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

}

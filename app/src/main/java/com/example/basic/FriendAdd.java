package com.example.basic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;


public class FriendAdd extends Activity {
    TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.friend_add);

    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기

        EditText friendname = (EditText)findViewById(R.id.nametxt);
        EditText friendnickname = (EditText)findViewById(R.id.nicknametxt);

        Intent intent = new Intent();
        intent.putExtra("nameresult", friendname.getText().toString());
        intent.putExtra("nicknameresult", friendnickname.getText().toString());
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}

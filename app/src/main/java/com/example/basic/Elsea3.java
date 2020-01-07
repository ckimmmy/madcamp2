package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Elsea3 extends AppCompatActivity {

    String testString=null;
    String Tier = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_else2);

        ArrayList<String> namearr = new ArrayList<String>();
        ArrayList<Integer> tier1arr = new ArrayList<Integer>();
        ArrayList<String> nicknamearr = new ArrayList<String>();

        /*


        여기서 서버에서 서버 DB로부터 이름하고 티어 순서대로 채우기


         */

        String[] nametest = new String[] {"송강호", "아이유", "박보영", "이병헌", "공유", "신혜선", "설현"};
        int[] tiertest1 = new int[]{62, 21, 42, 63, 73, 93, 52};
        String[] nicknametest = new String[] {"너만오면고", "매캠화이팅", "공부합시다", "태평소국밥3", "하하하321", "AOA최고", "콘트라베이스"};

        //위에거 예시들은 거

        //CHALLENGER: 1
        //GRANDMASTER: 2
        //MASTER: 3
        //DIAMOND: 4
        //PLATINUM: 5
        //GOLD: 6
        //SILVER: 7
        //BRONZE: 8
        //IRON: 9
        //십의자리가 티어. 일의자리는 티어 내 랭크(1~4)


        for(int i=0; i<=(nametest.length)-1;i++) {
            namearr.add(nametest[i]);
            tier1arr.add(tiertest1[i]);
            nicknamearr.add(nicknametest[i]);
        }

        String name = getIntent().getStringExtra("name");
        String summoner = getIntent().getStringExtra("summoner");


        ExampleThread thread = new ExampleThread();
        thread.start();
        try{
            thread.join();
        }catch(InterruptedException e)
        {
            }


        namearr.add(name);
        tier1arr.add(Getvirtualtier(testString));
        nicknamearr.add(summoner);
        //이게 받은 list들에서 젤 끝에 내 정보 추가한거. 이 arraylist들 서버에 올리면 될듯.


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



    private class ExampleThread extends Thread{

        String summoner = getIntent().getStringExtra("summoner");
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
}





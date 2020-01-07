package com.example.basic;
import android.graphics.drawable.Drawable;



public class ListViewItem3 {
    private Drawable iconDrawable;
    private String rankStr ;
    private String nameStr;
    private String tierStr;
    private String nicknameStr;

    public void setIcon(Drawable icon) {
        iconDrawable = icon;
    }
    public void setrank(String rank){ rankStr = rank; }
    public void setname(String name){
        nameStr = name;
    }
    public void settier(String tier){
        tierStr = tier;
    }
    public void setnickname(String nickname) { nicknameStr = nickname;}

    public Drawable getIcon(){
        return this.iconDrawable;
    }
    public String getrank(){
        return this.rankStr;
    }
    public String getname(){
        return this.nameStr;
    }
    public String gettier(){
        return this.tierStr;
    }
    public String getnickname() { return this.nicknameStr;}

}

package com.example.basic;

public class Savedata3 implements Comparable<Savedata3> {
     public String namec;
     public int tierc;
     public String nicknamec;

     public Savedata3(String namec, int tierc, String nicknamec){
         this.namec = namec;
         this.tierc = tierc;
         this.nicknamec = nicknamec;
     }
     public int compareTo(Savedata3 other){
         return tierc - other.tierc;
        }
}

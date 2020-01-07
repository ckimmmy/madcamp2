package com.example.basic;


import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.league.dto.LeagueEntry;
import net.rithms.riot.api.endpoints.league.dto.LeagueList;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
import net.rithms.riot.api.endpoints.league.methods.GetLeagueById;

public class Get_Id3 {


    //public static void main(String[] args) throws RiotApiException {
    public String getId(String s) throws RiotApiException{
        ApiConfig config = new ApiConfig().setKey("RGAPI-73b2c288-e457-425e-9600-2579688db1ff");
        RiotApi api = new RiotApi(config);

        try {
            Summoner summoner = api.getSummonerByName(Platform.KR, s);
            String MyId= null;
            MyId = summoner.getId();
            return MyId;
        } catch (RiotApiException e){
            System.out.println("------------------------------------------------------------------------summonernukll");
        }


//        LeagueList leagueList= api.getLeagueById(Platform.KR, MyId);
//        System.out.println(leagueList);
        return "null";
    }
//    public String MyId= summoner.getId();

}
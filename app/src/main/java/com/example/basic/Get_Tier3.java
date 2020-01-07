package com.example.basic;

import java.util.Set;
import java.util.logging.Level;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiAsync;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.league.constant.LeagueQueue;
import net.rithms.riot.api.endpoints.league.dto.LeagueEntry;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.api.request.AsyncRequest;
import net.rithms.riot.api.request.RequestAdapter;
import net.rithms.riot.constant.Platform;


public class Get_Tier3 {



    private class ExtendedSummoner {
        public Summoner summoner;
        public LeagueEntry leagueSolo;
        public LeagueEntry leagueFlexSR;
        public LeagueEntry leagueFlexTT;
    }

    public String AsyncExample(String summoner) throws RiotApiException {
        ApiConfig config = new ApiConfig().setKey("RGAPI-73b2c288-e457-425e-9600-2579688db1ff");
        RiotApi api = new RiotApi(config);


        RiotApiAsync apiAsync = api.getAsyncApi();

        // TODO need to rewrite this example to properly work with v4 endpoints
        Get_Id3 get_id=new Get_Id3();

        String summonerId = get_id.getId(summoner); // Encrypted summonerId to request


        Platform platform = Platform.KR; // platform to request
        final ExtendedSummoner eSummoner = new ExtendedSummoner(); // Object where we want to store the data

        // Asynchronously get summoner information

        AsyncRequest requestSummoner = apiAsync.getSummoner(platform, summonerId);

        requestSummoner.addListeners(new RequestAdapter() {
            @Override
            public void onRequestSucceeded(AsyncRequest request) {

                eSummoner.summoner = request.getDto();

            }
        });
//        Log.d("11", eSummoner.summoner.toString());

        // Asynchronously get league information
        AsyncRequest requestLeague = apiAsync.getLeagueEntriesBySummonerId(platform, summonerId);

        requestLeague.addListeners(new RequestAdapter() {
            @Override
            public void onRequestSucceeded(AsyncRequest request) {
                Set<LeagueEntry> leagueEntries = request.getDto();
                if (leagueEntries == null || leagueEntries.isEmpty()) {
                    return;
                }
                for (LeagueEntry leagueEntry : leagueEntries) {
                    if (leagueEntry.getQueueType().equals(LeagueQueue.RANKED_SOLO_5x5.name())) {
                        eSummoner.leagueSolo = leagueEntry;
                    } else if (leagueEntry.getQueueType().equals(LeagueQueue.RANKED_FLEX_SR.name())) {
                        eSummoner.leagueFlexSR = leagueEntry;
                    } else if (leagueEntry.getQueueType().equals(LeagueQueue.RANKED_FLEX_TT.name())) {
                        eSummoner.leagueFlexTT = leagueEntry;
                    }
                }
            }
        });

        try {
            apiAsync.awaitAll();
        } catch (InterruptedException e) {
            RiotApi.log.log(Level.SEVERE, "Waiting Interrupted", e);
        }


//        System.out.print("Solo Rank: ");
//        if (eSummoner.leagueSolo == null) {
//            System.out.println("unranked");
//        } else {
//            System.out.println(eSummoner.leagueSolo.getTier() + " " + eSummoner.leagueSolo.getRank());
//        }

        if (eSummoner.leagueSolo == null) {
            return("unranked");
        } else {
            return(eSummoner.leagueSolo.getTier() + " " + eSummoner.leagueSolo.getRank());
        }

    }
}

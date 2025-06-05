package com.personal.webscrapping.service.impl;

import com.personal.webscrapping.domain.DataBet;
import com.personal.webscrapping.service.WebScrapping;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Getter
@NoArgsConstructor
public class JsoupScrapping implements WebScrapping {
    private final String baseUrl = "https://www.betmonitor.com/content/get_players.php";
    private String url = "https://www.google.com/";
    private final HashMap<String, Document> cacheDocuments = new HashMap<>();
    public List<DataBet> getBets(String url) {
        this.url = url;
        List<DataBet> mockedbets = this.getMockedBet("something");
        List<DataBet> bets = this.getDataBet(Objects.requireNonNull(getDocument(removeId(url))),this.getExcludedCategories());
        bets.sort(Comparator.comparingDouble(r ->  r.bet() - r.highestBet()));
        return bets;
    }

    private List<String> getExcludedCategories() {
        List<String> excludedCategories = new ArrayList<>();
        excludedCategories.add("shoTurnovers per Player");
        excludedCategories.add("shot");
        excludedCategories.add("hattr");
        excludedCategories.add("Player gets Red Card");
        return excludedCategories;
    }

    private List<DataBet> getMockedBet(String url) {
        List<DataBet> bets = new ArrayList<>();

        bets.add(new DataBet(1.2, "SOMETHING "+ removeId(url), 1.1, "pedro", ""));
        bets.add(new DataBet(1.2, "SOMETHING "+this.url, 1.1, "pedro", ""));
        bets.add(new DataBet(1.2, "SOMETHING "+this.url, 1.1, "pedro", ""));
        return bets;
    }

    private String removeId(String url){
        System.out.println(url.substring(url.lastIndexOf("/")+1));
        return url.substring(url.lastIndexOf("/")+1);
    }

    private Document getDocument(String id){
        try{
            if(cacheDocuments.containsKey(id)){
                return cacheDocuments.get(id);
            }
            Document d =  Jsoup.connect("https://www.betmonitor.com/content/get_players.php").data("rid", id)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post();
            cacheDocuments.put(id, d);
            return d;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    private List<DataBet> getDataBet(Document doc, List<String> excludeCategories) {

        List<DataBet> results = new ArrayList<>();

        Elements h2Elements = doc.select("h2");

        for (Element h2 : h2Elements) {
            String category = h2.text().trim();

            if (excludeCategories.contains(category)) {
                continue;
            }

            Element container = h2.nextElementSibling();
            if (container == null || !container.hasClass("outright-cont-col")) {
                continue;
            }

            for (Element league : container.select(".outright-league")) {
                String playerName = "";
                Double decimalOdd = null;
                String highestHouseName = "";
                Double highestHouseOdd = null;

                Element playerNameEl = league.selectFirst(".outright-league-team > span");
                if (playerNameEl != null) {
                    playerName = playerNameEl.text();
                }
                System.out.println("Player: " + playerName);


                Element decimalOddEl = league.selectFirst(".outright-league-team .odd-decimal");
                if (decimalOddEl != null) {
                    try {
                        System.out.println("tEXT: " + decimalOddEl.text());
                        decimalOdd = Double.parseDouble(decimalOddEl.text());
                    } catch (NumberFormatException e) {
                        decimalOdd = null;
                    }
                }

                Element highestHouseNameEl = league.selectFirst(".outright-league-highest > span:nth-of-type(1)");
                if (highestHouseNameEl != null) {
                    highestHouseName = highestHouseNameEl.ownText().trim();
                }
                System.out.println("house name: " + highestHouseName);
                Element highestHouseOddEl = league.selectFirst(".outright-league-highest .odd-decimal");
                if (highestHouseOddEl != null) {
                    try {
                        System.out.println("TEXT2: " + highestHouseOddEl.text());
                        highestHouseOdd = Double.parseDouble(highestHouseOddEl.text());
                    } catch (NumberFormatException e) {
                        highestHouseOdd = null;
                    }
                }
                // Add record to results
                results.add(new DataBet(decimalOdd, highestHouseName, highestHouseOdd, playerName, category));
            }
        }

        return results;
    }


}

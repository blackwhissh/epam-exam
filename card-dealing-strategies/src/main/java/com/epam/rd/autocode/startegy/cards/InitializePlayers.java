package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitializePlayers {
    public static Map<String, List<Card>> init(int players){
        Map<String, List<Card>> stacks = new HashMap<>();
        for(int i = 1; i <= players; i++){
            String player = "Player " + i;
            stacks.put(player, new ArrayList<>());
        }
        return stacks;
    }

    public static void deal(Map<String, List<Card>> stacks, int cards, int players, Deck deck){
        for(int i = 0; i < cards; i++){
            for(int j = 1; j <= players; j++){
                Card card = deck.dealCard();
                String player = "Player " + j;
                stacks.get(player).add(card);
            }
        }
    }
}

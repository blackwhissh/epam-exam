package com.epam.rd.autocode.startegy.cards.games;

import com.epam.rd.autocode.startegy.cards.Card;
import com.epam.rd.autocode.startegy.cards.CardDealingStrategy;
import com.epam.rd.autocode.startegy.cards.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.rd.autocode.startegy.cards.InitializePlayers.*;

public class Texas implements CardDealingStrategy {
    private static final int cards = 2;
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> stacks = init(players);

        deal(stacks,cards,players,deck);

        stacks.put("Community", new ArrayList<>());
        for(int i = 0; i < 5; i++){
            Card card = deck.dealCard();
            stacks.get("Community"). add(card);
        }

        stacks.put("Remaining", deck.restCards());
        return stacks;
    }
}
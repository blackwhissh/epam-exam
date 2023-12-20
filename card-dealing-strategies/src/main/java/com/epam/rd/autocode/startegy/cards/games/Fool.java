package com.epam.rd.autocode.startegy.cards.games;

import com.epam.rd.autocode.startegy.cards.Card;
import com.epam.rd.autocode.startegy.cards.CardDealingStrategy;
import com.epam.rd.autocode.startegy.cards.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.rd.autocode.startegy.cards.InitializePlayers.*;

public class Fool implements CardDealingStrategy {
    private static final int cards = 6;
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> stacks = init(players);
        deal(stacks,cards,players,deck);

        List<Card> trumpCards = new ArrayList<>();
        Card trumpCard = deck.dealCard();
        stacks.put("Trump card", trumpCards);
        trumpCards.add(trumpCard);

        stacks.put("Remaining", deck.restCards());
        if(trumpCards.size() > 1){
            trumpCards.remove(0);
        }

        return stacks;
    }
}

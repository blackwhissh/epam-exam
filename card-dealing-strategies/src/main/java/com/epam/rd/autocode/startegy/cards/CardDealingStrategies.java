package com.epam.rd.autocode.startegy.cards;

import com.epam.rd.autocode.startegy.cards.games.Bridge;
import com.epam.rd.autocode.startegy.cards.games.Classic;
import com.epam.rd.autocode.startegy.cards.games.Fool;
import com.epam.rd.autocode.startegy.cards.games.Texas;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new Texas();
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new Classic();
    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return new Bridge();
    }

    public static CardDealingStrategy foolCardDealingStrategy(){
        return new Fool();
    }

}

package com.epam.rd.autocode.factory.plot.marvel;

import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.EpicCrisis;
import com.epam.rd.autocode.factory.plot.Plot;

public class MarvelPlot implements Plot {
    private final Character[] heroes;
    private final EpicCrisis epicCrisis;
    private final Character villain;

    public MarvelPlot(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.heroes = heroes;
        this.epicCrisis = epicCrisis;
        this.villain = villain;
    }

    public String getHero(Character[] heroes){
        StringBuilder heroNames = new StringBuilder(" the brave ");
        if(heroes.length == 1){
            heroNames.append(heroes[0].name());
            heroNames.append(" is on guard");
        }
        if(heroes.length > 1){
            for (int i = 0; i < heroes.length - 1; i++) {
                if(i == heroes.length - 2){
                    heroNames.append(heroes[i].name());
                    break;
                }else{
                    heroNames.append(heroes[i].name()).append(", ").append("the brave ");
                }
            }
            heroNames.append(" and the brave ");
            heroNames.append(heroes[heroes.length-1].name());
            heroNames.append(" are on guard");
        } else if (heroes.length == 0) {
            return "No Heroes";
        }
        return heroNames.toString();
    }

    @Override
    public String asText() {
        StringBuilder s = new StringBuilder(epicCrisis.name() + " threatens the world. But" + getHero(heroes) + ". So, no way that intrigues of " +
                villain.name());
        if (heroes.length == 1){
            s.append(" will bend the willpower of the inflexible hero.");
        } else {
            s.append(" will bend the willpower of inflexible heroes.");
        }
        return s.toString();
    }
}

package eu.qwan.vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private Map<Choice, Can> choices = new HashMap<>();
    private Map<Choice, Integer> prices = new HashMap<>();
    private int credits = 0;

    public Can deliver(Choice choice) {
        if (!choices.containsKey(choice)) return Can.NO_CAN;
        if (prices.get(choice) > credits) return Can.NO_CAN;
        credits -= prices.get(choice);
        return choices.get(choice);
    }

    public void configureChoice(Choice choice, Can canToDeliver, int priceInCents) {
        choices.put(choice,canToDeliver);
        prices.put(choice, priceInCents);
    }

    public void configureChoice(Choice choice, Can canToDeliver) {
        configureChoice(choice, canToDeliver, 0);
    }

    public void insert(int amount) {
        credits += amount;
    }
}

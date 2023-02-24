package RPG.Items;

import RPG.Item;

public class RingOfFortitude extends Item {

    int strengthToGive = 80; //will be -60 because of the weight of the ring
    int healthToRemove = 20;
    public RingOfFortitude(){
        super(60, "Ring of fortitude", false);
    }
    public int getStrengthToGive(){
        return strengthToGive;
    }

    public int getHealthToRemove() {
        return healthToRemove;
    }
}

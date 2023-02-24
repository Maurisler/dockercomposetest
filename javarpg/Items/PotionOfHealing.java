package RPG.Items;

import RPG.Item;

public class PotionOfHealing extends Item {
    int healthToHeal = 40;
    boolean hasBeenUsed = false;

    public PotionOfHealing(){
        super(40, "Potion of healing", true);
    }

    public int getHealthToHeal(){
        if(!this.hasBeenUsed){
            this.hasBeenUsed = true;
            return this.healthToHeal;
        }
        return 0;
    }

}

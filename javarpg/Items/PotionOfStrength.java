package RPG.Items;

import RPG.Item;

public class PotionOfStrength extends Item {
    boolean hasBeenUsed = false;
    boolean willBeUsed = false;

    public PotionOfStrength(){
        super(50, "Potion of strength", true);
    }

    public int getMultiplier(){
         if(this.willBeUsed && !this.hasBeenUsed){
            this.hasBeenUsed = true;
            return 3;
        }
        return 1;
    }

    public void activateStrength(){
        this.willBeUsed = true;
    }
}

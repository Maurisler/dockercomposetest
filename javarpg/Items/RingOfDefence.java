package RPG.Items;

import RPG.Item;

public class RingOfDefence extends Item {
    boolean hasBeenUsed = false;

    public RingOfDefence(){
        super(10, "Ring of defence", false);
    }

    public boolean getHasBeenUsed(){
        return this.hasBeenUsed;
    }

    public void setHasBeenUsed(boolean hasBeenUsed){
        this.hasBeenUsed = hasBeenUsed;
    }
}

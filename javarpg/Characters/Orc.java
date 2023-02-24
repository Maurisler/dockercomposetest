package RPG.Characters;

import RPG.PlayerCharacter;

public class Orc extends PlayerCharacter {
    int durationOfRage = 0; //if not in rage = 0
    boolean rageHasBeenUsed = true; //rage can only be used once
    public Orc(String name) {
        super(name, 90, 80);
    }

    @Override
    public double getFightValue(){
        if((this.getHealthAtStart() / 100) * this.getHealth() < 25 && !rageHasBeenUsed){
            rageHasBeenUsed = true;
            durationOfRage = 3;
        }
        if(durationOfRage != 0){
            durationOfRage--;
            return super.getFightValue() * 3;
        }
        return super.getFightValue();
    }
}

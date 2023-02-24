package RPG.Characters;

import RPG.PlayerCharacter;
import RPG.Weapons.Bow;

public class Goblin extends PlayerCharacter {
    public Goblin(String name) {
        super(name, 80, 90);
    }

    @Override
    public double getFightValue(){
        if(this.getCurrentWeapon() != null && this.getCurrentWeapon() instanceof Bow){
            return super.getFightValue() * 1.5;
        }
        return super.getFightValue();
    }
}

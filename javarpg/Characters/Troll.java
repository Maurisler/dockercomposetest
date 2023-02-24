package RPG.Characters;

import RPG.PlayerCharacter;
import RPG.Weapons.Cudgel;

public class Troll extends PlayerCharacter {
    public Troll(String name) {
        super(name, 60, 140);
    }

    @Override
    public double getFightValue(){
        if(this.getCurrentWeapon() != null && this.getCurrentWeapon() instanceof Cudgel){
            return super.getFightValue() * 2;
        }
        return super.getFightValue();
    }
}

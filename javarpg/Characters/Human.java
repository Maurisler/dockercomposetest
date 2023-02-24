package RPG.Characters;

import RPG.PlayerCharacter;
import RPG.Weapons.Sword;

public class Human extends PlayerCharacter {
    public Human(String name) {
        super(name, 100, 60);
    }

    @Override
    public double getFightValue(){
        if(this.getCurrentWeapon() != null && this.getCurrentWeapon() instanceof Sword){
            return super.getFightValue() * 1.25;
        }
        return super.getFightValue();
    }
}

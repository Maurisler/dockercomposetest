package RPG.Characters;

import RPG.PlayerCharacter;
import RPG.Weapons.Bow;

import java.util.Random;

public class Elf extends PlayerCharacter {
    double magicValue;
    public Elf(String name) {
        super(name, 120, 40);
        this.magicValue  = new Random().nextInt(4)+1;
    }

    @Override
    public double getFightValue(){
        double returnInt = super.getFightValue() + (magicValue * 0.5);
        if(this.getCurrentWeapon() != null && this.getCurrentWeapon() instanceof Bow){
            return returnInt * 1.5;
        }
        return returnInt;
    }
}

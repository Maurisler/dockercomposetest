package RPG.Weapons;

import RPG.Weapon;

public class Sword extends Weapon {


    public Sword() {
        super( 40, 7, 10);
    }

    @Override
    public double returnAttackValue(){
        return this.getAttack() + (this.getDefense() * 0.5);
    }


}

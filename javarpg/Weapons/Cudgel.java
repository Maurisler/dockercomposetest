package RPG.Weapons;

import RPG.Weapon;

public class Cudgel extends Weapon {

    public Cudgel(){
        super(15, 5, 5);

    }

    @Override
    public double returnAttackValue(){
        return this.getAttack() + (this.getDefense() * 0.5);
    }

}

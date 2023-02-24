package RPG;



public abstract class Weapon {
    int weight;
    int attack;
    int defense;

    public Weapon(int weight, int attack, int defense){
        this.weight = weight;
        this.attack = attack;
        this.defense = defense;
    }

    public double returnAttackValue(){
        return this.attack;
    }

    public int getAttack(){
        return attack;
    }

    public int getWeight(){
        return this.weight;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int newDefense){this.defense = newDefense;}
}




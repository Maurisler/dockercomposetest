package RPG;

import RPG.Items.PotionOfStrength;
import RPG.Items.RingOfDefence;

import java.util.ArrayList;
import java.util.Random;

public abstract class PlayerCharacter {
    String name;
    double health;
    int strength;
    Weapon currentWeapon = null;
    Armour currentArmor = null;
    ArrayList<Item> items = new ArrayList<>();
    double healthAtStart;
    int initiative; //will decide when who starts, higher = better

    public PlayerCharacter(String name, double health, int strength){
        this.name = name;
        this.strength = strength;
        this.initiative = new Random().nextInt(50);
        this.health = health;
        this.healthAtStart = health;
    }

    public int getInitiative(){
        if(this.currentArmor != null){
            return this.initiative - this.currentArmor.getWeight();
        }
        return this.initiative;
    }


    public double getHealth(){
        return this.health;
    }

    public void setHealth(double newHealth){
        this.health = newHealth;
    }

    public String getName(){
        return this.name;
    }

    public Weapon getCurrentWeapon(){
        return this.currentWeapon;
    }

    public void setStrength(int newStrength){
        this.strength = newStrength;
    }

    public void setCurrentArmor(Armour armor){
        this.currentArmor = armor;
    }

    public Armour getCurrentArmor(){
        return this.currentArmor;
    }

    public double getHealthAtStart(){return this.healthAtStart;}

    public double getFightValue(){
        double returnVal;
        double fightMultiplier = 1;
        if(currentWeapon == null){
            for(Item item : items){
                if (item instanceof PotionOfStrength){
                    return ((PotionOfStrength) item).getMultiplier(); //(* 1)
                }
            }
            return 1;
        }else {
            for (Item item : items){
                if(item instanceof RingOfDefence && currentWeapon.getDefense() != 0 && !((RingOfDefence) item).getHasBeenUsed()){
                    currentWeapon.setDefense(currentWeapon.getDefense() + 10);
                    ((RingOfDefence) item).setHasBeenUsed(true);
                }else if(item instanceof PotionOfStrength){
                    fightMultiplier = ((PotionOfStrength) item).getMultiplier();
                }
            }
            returnVal = ( currentWeapon.returnAttackValue() + (((double) new Random().nextInt(3) / 10) + 0.9) ) * fightMultiplier;
            return returnVal;
        }
    }

    public void setCurrentWeapon(Weapon newWeapon){ //for removing weapon give null
        this.currentWeapon = newWeapon;
    }

    public int getStrength(){
        return this.strength;
    }

    public void addAllItem(ArrayList<Item> items){
        this.items = items;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public ArrayList<Item> getItems(){
        return this.items;
    }

    public void replaceItemAtIndex(int i, Item item){
        items.set(i, item);
    }

    public void removeItemAtIndex(int i){ items.remove(i); }

}
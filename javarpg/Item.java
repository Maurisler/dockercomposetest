package RPG;

public abstract class Item {
    int weight;
    String name;
    boolean isVanished = false;
    boolean isUsable;

    public Item(int weight, String name, boolean isUsable){
        this.weight = weight;
        this.name = name;
        this.isUsable = isUsable;
    }

    public int getWeight(){
        return this.weight;
    }

    public boolean getIsUsable(){
        return this.isUsable;
    }

    public void setWeight(int newWeight){
        this.weight = newWeight;
    }

    public String getName(){
        return this.name;
    }

    public void setIsVanished(boolean isVanished){
        this.isVanished = isVanished;
    }

    public boolean getIsVanished(){
        return this.isVanished;
    }
}

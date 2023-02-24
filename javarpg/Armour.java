package RPG;

public abstract class Armour {
        int weight; //the weight also reduces the initiative-value
        int probabilityOfNotBeingHit; //if(new Random().nextInt(probabilityOfBeingHit) != 0), so 2 is 50/50, (between 0 and 1), 4 is 25% (between 0 and 3) and so on...
        String name;


        public Armour(int weight, int probabilityOfNotBeingHit, String name){
            this.weight = weight;
            this.probabilityOfNotBeingHit = probabilityOfNotBeingHit;
            this.name = name;
        }

        public int getProbabilityOfBeingHit(){
            return this.probabilityOfNotBeingHit;
        }

        public int getWeight(){
            return this.weight;
        }

        public String getName(){return this.name;}
    }

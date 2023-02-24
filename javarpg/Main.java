package RPG;
import RPG.Items.RingOfFortitude;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        //If armor dodges hit, potion of strength will still be active

        /*
        COLOR CONVENTIONS OF MyRPG:

        Class:-----------------------------------------------------------------------PURPLE
        Names (of characters):---------------------------------------------------------CYAN
        Damage values:------------------------------------------------------------------RED
        Items and Armor:-------------------------------------------------------------YELLOW
        Weapons:-----------------------------------------------------------------------CYAN
        Selection Option ([1], [2], [3], ...):------------------------------------------RED
        All other stats in numbers (strength, weight, chance of dodging a hit...):---PURPLE

        ALWAYS BOLD AND BRIGHT + ANSI_RESET AT THE END
         */

        PlayerCharacter enemy = Factory.generateRandomCharacter();
        Armour armorToChose;
        final String ANSI_BOLD = "\033[0;1m"; //BOLD
        final String ANSI_RESET = "\u001B[0m"; //RESET
        final String RED_BOLD_BRIGHT = "\033[1;91m";  // RED
        final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
        PlayerCharacter[] savePlayerAndEnemiesFromMethod;


        System.out.println("Welcome to MyRPG! \n");
        System.out.println("Here are the Stats of your opponent:");
        Factory.printStats(enemy);
        System.out.println("We don't know anything about his items yet. Be careful and choose wisely! \n");

        PlayerCharacter myCharacter = Factory.chooseCharacter();
        Weapon weaponToSet;
        boolean isValid = false;

        System.out.println("\nHello " + GREEN_BOLD_BRIGHT + myCharacter.getName() + ANSI_RESET + ". \n");

        myCharacter.addAllItem(Factory.chooseItems(myCharacter.getStrength()));
        for (Item item : myCharacter.getItems()) {
            if (item.getClass().equals(RingOfFortitude.class)) {
                myCharacter.setStrength(myCharacter.getStrength() + ((RingOfFortitude) item).getStrengthToGive());
                myCharacter.setHealth(myCharacter.getHealth() - ((RingOfFortitude) item).getHealthToRemove());
                System.out.println("You lose " + RED_BOLD_BRIGHT + ((RingOfFortitude) item).getHealthToRemove() + ANSI_RESET + " HP due to the power of the ring and gain " + PURPLE_BOLD_BRIGHT + ((RingOfFortitude) item).getStrengthToGive() + ANSI_RESET + " strength. ");
                System.out.println("Those effects are not reversible!\n");

            }
            myCharacter.setStrength(myCharacter.getStrength() - item.weight);
        }

        while (!isValid) {
            weaponToSet = Factory.chooseWeapon(myCharacter.getStrength());
            if (weaponToSet != null) {
                if (weaponToSet.getWeight() > myCharacter.getStrength()) {
                    System.out.println("This Weapon is to heavy! Choose another one or none.\n");
                } else {
                    myCharacter.setCurrentWeapon(weaponToSet);
                    myCharacter.setStrength(myCharacter.getStrength() - myCharacter.getCurrentWeapon().getWeight());
                    isValid = true;
                }
            } else {
                myCharacter.setCurrentWeapon(null);
                isValid = true;
            }
        }

        isValid = false;

        while (!isValid) {
            armorToChose = Factory.chooseArmour(myCharacter);
            if (armorToChose != null) {
                if (armorToChose.getWeight() > myCharacter.getStrength()) {
                    System.out.println("This Armor is to heavy! Choose another one or none. \n");
                } else {
                    myCharacter.setCurrentArmor(armorToChose);
                    myCharacter.setStrength(myCharacter.getStrength() - myCharacter.getCurrentArmor().getWeight());
                    isValid = true;
                }
            } else {
                myCharacter.setCurrentArmor(null);
                isValid = true;
            }
        }

        System.out.println(ANSI_BOLD + "The battle has begun." + ANSI_RESET);
        System.out.println("May the better one win.\n");
        Thread.sleep(2000);
        System.out.println(ANSI_BOLD + "READY?\n" + ANSI_RESET);
        Thread.sleep(1800);
        System.out.println("3\n");
        Thread.sleep(1500);
        System.out.println("2\n");
        Thread.sleep(1500);
        System.out.println("1\n");
        Thread.sleep(1600);
        System.out.println(RED_BOLD_BRIGHT + "FIGHT!\n" + ANSI_RESET);

        for (int roundCount = 0; roundCount < 20; roundCount++) {
            if (enemy.getInitiative() > myCharacter.getInitiative()) {

                System.out.println("════ " + ANSI_BOLD + "ROUND " + PURPLE_BOLD_BRIGHT + (roundCount + 1) + ANSI_RESET + " ════════════════════════════════════════════════════════════════════════════════════════════════════\n");
                System.out.println(GREEN_BOLD_BRIGHT + enemy.getName() + ANSI_RESET + " will start this round!");

                savePlayerAndEnemiesFromMethod = Factory.generateTurn(myCharacter, enemy);
                myCharacter = savePlayerAndEnemiesFromMethod[0];
                enemy = savePlayerAndEnemiesFromMethod[1];

                if (myCharacter.getHealth() <= 0) {
                    Factory.printLose(enemy);
                    System.exit(42069);
                }

                savePlayerAndEnemiesFromMethod = Factory.makeTurn(myCharacter, enemy);
                myCharacter = savePlayerAndEnemiesFromMethod[0];
                enemy = savePlayerAndEnemiesFromMethod[1];

                if (enemy.getHealth() <= 0) {
                        Factory.printWin(enemy);
                        System.exit(42069);
                }

                } else {

                    System.out.println("════ " + ANSI_BOLD + "ROUND " + PURPLE_BOLD_BRIGHT + (roundCount + 1) + ANSI_RESET + " ════════════════════════════════════════════════════════════════════════════════════════════════════\n");
                    System.out.println(GREEN_BOLD_BRIGHT + myCharacter.getName() + ANSI_RESET + " will start this round!");

                    savePlayerAndEnemiesFromMethod = Factory.makeTurn(myCharacter, enemy);
                    myCharacter = savePlayerAndEnemiesFromMethod[0];
                    enemy = savePlayerAndEnemiesFromMethod[1];

                    if (enemy.getHealth() <= 0) {
                        Factory.printWin(enemy);
                        System.exit(42069);
                    }

                    savePlayerAndEnemiesFromMethod = Factory.generateTurn(myCharacter, enemy);
                    myCharacter = savePlayerAndEnemiesFromMethod[0];
                    enemy = savePlayerAndEnemiesFromMethod[1];

                    if (myCharacter.getHealth() <= 0) {
                        Factory.printLose(enemy);
                        System.exit(42069);
                    }

                }
        }

        if (myCharacter.getHealth() > enemy.getHealth()) {
            Factory.printWin(enemy);
        } else if (myCharacter.getHealth() == enemy.getHealth()) {
            Factory.printDraw(enemy);
        } else {
            Factory.printLose(enemy);
        }
    }
}

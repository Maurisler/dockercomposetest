package RPG;

import RPG.Armors.*;
import RPG.Characters.*;
import RPG.Items.*;
import RPG.Weapons.*;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Factory {
    public static PlayerCharacter generateRandomCharacter() {
        PlayerCharacter player = null;
        String[] randomNames = {"Harald", "Björk", "Hanrich", "Ganou", "Anan", "Urus", "Friedgrad", "Gandalf", "Isael", "Mosarus", "Regalas", "Kimer", "Wuleris"};
        String randomName = randomNames[new Random().nextInt(randomNames.length)];
        int generatedWeight = 0;
        switch (new Random().nextInt(6)) {
            case 0:
                player = new Dwarf(randomName);
                break;
            case 1:
                player = new Elf(randomName);
                break;
            case 2:
                player = new Goblin(randomName);
                break;
            case 3:
                player = new Human(randomName);
                break;
            case 4:
                player = new Orc(randomName);
                break;
            case 5:
                player = new Troll(randomName);
                break;
        }

        player.setStrength(player.getStrength() + 20); //always has a bit more strength than usual, makes the fight more challenging

        do{
            switch (new Random().nextInt(5)) { //nextInt bound = amount of possible items + 1
                case 0:
                    player.addItem(new PotionOfHealing());
                    break;
                case 1:
                    player.addItem(new PotionOfStrength());
                    break;
                case 2:
                    player.addItem(new RingOfDefence());
                    break;
                case 3:
                    player.addItem(new RingOfFortitude());
                    player.setStrength(player.getStrength() + new RingOfFortitude().getStrengthToGive()); //applies effect of ring
                    player.setHealth(player.getHealth() - new RingOfFortitude().getHealthToRemove());
                    break;
                case 4:
                    break;
            }
            for (Item item : player.getItems()) {
                generatedWeight += item.getWeight();
            }
        }while (generatedWeight > player.getStrength());

        for (Item item : player.getItems()) {
            player.setStrength(player.getStrength() - item.getWeight());
        }

        do {
            switch (new Random().nextInt(5)) { //nextInt bound = amount of possible weapons + 1
                case 0:
                    player.setCurrentWeapon(null);
                    break;
                case 1:
                    player.setCurrentWeapon(new Bow());
                    break;
                case 2:
                    player.setCurrentWeapon(new Cudgel());
                    break;
                case 3:
                    player.setCurrentWeapon(new Sword());
                    break;
                case 4:
                    player.setCurrentWeapon(new ThrowingKnife());
                    break;
            }

            if (player.getCurrentWeapon() != null) {
                generatedWeight = player.getCurrentWeapon().getWeight();
            }

        } while (generatedWeight > player.getStrength());

        if(player.getCurrentWeapon() != null){
            player.setStrength(player.getStrength() - player.getCurrentWeapon().getWeight());
        }

        if(new HeavyArmour().getWeight() <= player.getStrength() || !(player instanceof Elf || player instanceof Goblin)){
            player.setCurrentArmor(new HeavyArmour());
            player.setStrength(player.getStrength() - player.getCurrentArmor().getWeight());
        }else if(new LightArmour().getWeight() <= player.getStrength()){
            player.setCurrentArmor(new LightArmour());
            player.setStrength(player.getStrength() - player.getCurrentArmor().getWeight());
        }else {
            player.setCurrentArmor(null);
        }

        return player;
    }

    public static PlayerCharacter chooseCharacter() {
        boolean boolFinished = false;
        final String ANSI_RESET = "\u001B[0m"; //RESET
        final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        final String RED_BOLD_BRIGHT = "\033[1;91m";  // RED
        Scanner scanner = new Scanner(System.in);
        PlayerCharacter myCharacter = null;
        String input;
        String secondInput;

        while (!boolFinished) {
            System.out.println("Chose your Character: ");
            System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[1]" + ANSI_RESET + " for a" + PURPLE_BOLD_BRIGHT + " DWARF      " + ANSI_RESET + String.format("%60s", String.format("%-30s", "Health: " + RED_BOLD_BRIGHT + new Dwarf("i").getHealth() + ANSI_RESET)  + String.format("%-30s", " Strength: " + PURPLE_BOLD_BRIGHT + new Dwarf("i").getStrength() + ANSI_RESET)) + " ║");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[2]" + ANSI_RESET + " for a" + PURPLE_BOLD_BRIGHT + " ELF        " + ANSI_RESET + String.format("%60s", String.format("%-30s", "Health: " + RED_BOLD_BRIGHT + new Elf("i").getHealth() + ANSI_RESET)  + String.format("%-30s", " Strength: " + PURPLE_BOLD_BRIGHT + new Elf("i").getStrength() + ANSI_RESET)) + " ║");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[3]" + ANSI_RESET + " for a" + PURPLE_BOLD_BRIGHT + " GOBLIN     " + ANSI_RESET + String.format("%60s", String.format("%-30s", "Health: " + RED_BOLD_BRIGHT + new Goblin("i").getHealth() + ANSI_RESET)  + String.format("%-30s", " Strength: " + PURPLE_BOLD_BRIGHT + new Goblin("i").getStrength() + ANSI_RESET)) + " ║");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[4]" + ANSI_RESET + " for a" + PURPLE_BOLD_BRIGHT + " HUMAN      " + ANSI_RESET + String.format("%60s", String.format("%-30s", "Health: " + RED_BOLD_BRIGHT + new Human("i").getHealth() + ANSI_RESET)  + String.format("%-30s", " Strength: " + PURPLE_BOLD_BRIGHT + new Human("i").getStrength() + ANSI_RESET)) + " ║");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[5]" + ANSI_RESET + " for a" + PURPLE_BOLD_BRIGHT + " ORC        " + ANSI_RESET + String.format("%60s", String.format("%-30s", "Health: " + RED_BOLD_BRIGHT + new Orc("i").getHealth() + ANSI_RESET)  + String.format("%-30s", " Strength: " + PURPLE_BOLD_BRIGHT + new Orc("i").getStrength() + ANSI_RESET)) + " ║");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[6]" + ANSI_RESET + " for a" + PURPLE_BOLD_BRIGHT + " TROLL      " + ANSI_RESET + String.format("%60s", String.format("%-30s", "Health: " + RED_BOLD_BRIGHT + new Troll("i").getHealth() + ANSI_RESET)  + String.format("%-30s", " Strength: " + PURPLE_BOLD_BRIGHT + new Troll("i").getStrength() + ANSI_RESET)) + " ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════╝");

            input = scanner.nextLine();
            if(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6")){
                System.out.println("\nType in the name of your character:");
                secondInput = scanner.nextLine();

                switch (input) {
                    case "1":
                        myCharacter = new Dwarf(secondInput);
                        boolFinished = true;
                        break;
                    case "2":
                        myCharacter = new Elf(secondInput);
                        boolFinished = true;
                        break;
                    case "3":
                        myCharacter = new Goblin(secondInput);
                        boolFinished = true;
                        break;
                    case "4":
                        myCharacter = new Human(secondInput);
                        boolFinished = true;
                        break;
                    case "5":
                        myCharacter = new Orc(secondInput);
                        boolFinished = true;
                        break;
                    case "6":
                        myCharacter = new Troll(secondInput);
                        boolFinished = true;
                        break;
                }
            }else {
                System.out.println("No valid input. Try again.\n");
            }

        }

        return myCharacter;
    }

    public static Weapon chooseWeapon(int remainingStrength) {
        final String ANSI_RESET = "\u001B[0m"; //RESET
        final String RED_BOLD_BRIGHT = "\033[1;91m";  // RED
        final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
        final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("Choose your Weapon:");
            System.out.println("╔════════════════════════════════════════════════════════════╗");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[1]" + ANSI_RESET + " for a" + CYAN_BOLD_BRIGHT + " BOW " + ANSI_RESET + "             Weight:" + PURPLE_BOLD_BRIGHT + String.format("%3d", new Bow().getWeight()) + ANSI_RESET + "   Damage:" + RED_BOLD_BRIGHT + String.format("% 3d", new Bow().getAttack()) + ANSI_RESET + "   ║");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[2]" + ANSI_RESET + " for a" + CYAN_BOLD_BRIGHT + " CUDGEL " + ANSI_RESET + "          Weight:" + PURPLE_BOLD_BRIGHT + String.format("%3d", new Cudgel().getWeight()) + ANSI_RESET + "   Damage:" + RED_BOLD_BRIGHT + String.format("% 3d", new Cudgel().getAttack()) + ANSI_RESET + "   ║");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[3]" + ANSI_RESET + " for a" + CYAN_BOLD_BRIGHT + " SWORD " + ANSI_RESET + "           Weight:" + PURPLE_BOLD_BRIGHT + String.format("%3d", new Sword().getWeight()) + ANSI_RESET + "   Damage:" + RED_BOLD_BRIGHT + String.format("% 3d", new Sword().getAttack()) + ANSI_RESET + "   ║");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[4]" + ANSI_RESET + " for a" + CYAN_BOLD_BRIGHT + " THROWING KNIFE " + ANSI_RESET + "  Weight:" + PURPLE_BOLD_BRIGHT + String.format("%3d", new ThrowingKnife().getWeight()) + ANSI_RESET + "   Damage:" + RED_BOLD_BRIGHT + String.format("% 3d", new ThrowingKnife().getAttack()) + ANSI_RESET + "   ║");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[5]" + ANSI_RESET + " for not choosing any weapon         Damage:" + RED_BOLD_BRIGHT + String.format("% 3d", 1) + ANSI_RESET + "   ║");
            System.out.println("╚═════════ Remaining Strength:" + PURPLE_BOLD_BRIGHT + String.format("%4d", remainingStrength) + ANSI_RESET + " ══════════════════════════╝");

            input = scanner.nextLine();

            switch (input) {
                case "1":
                    return new Bow();
                case "2":
                    return new Cudgel();
                case "3":
                    return new Sword();
                case "4":
                    return new ThrowingKnife();
                case "5":
                    return null;
                default:
                    System.out.println("No valid input. Try again.\n");
            }

        }
    }

    public static Armour chooseArmour(PlayerCharacter character) {
        final String ANSI_RESET = "\u001B[0m"; //RESET
        final String RED_BOLD_BRIGHT = "\033[1;91m";  // RED
        final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("Choose your Armour:");
            System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[1]" + ANSI_RESET + " for a" + YELLOW_BOLD_BRIGHT + " LIGHTWEIGHT ARMOUR " + ANSI_RESET + "             Weight:" + PURPLE_BOLD_BRIGHT + String.format("%3d", new LightArmour().getWeight()) + ANSI_RESET + "   Change of dodging a hit: " + PURPLE_BOLD_BRIGHT + String.format("%3.2f", (double) Math.round(((double) 10 / new LightArmour().getProbabilityOfBeingHit()) * 100) / 10) + " %" + ANSI_RESET + " ║");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[2]" + ANSI_RESET + " for a" + YELLOW_BOLD_BRIGHT + " HEAVY ARMOUR " + ANSI_RESET + "                   Weight:" + PURPLE_BOLD_BRIGHT + String.format("%3d", new HeavyArmour().getWeight()) + ANSI_RESET + "   Change of dodging a hit: " + PURPLE_BOLD_BRIGHT + String.format("%3.2f", (double) Math.round(((double) 10 / new HeavyArmour().getProbabilityOfBeingHit()) * 100) / 10) + " %" + ANSI_RESET + " ║");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[3]" + ANSI_RESET + " for not choosing any armor                                                          ║");
            System.out.println("╚═════════ Remaining Strength:" + PURPLE_BOLD_BRIGHT + String.format("%4d", character.getStrength()) + ANSI_RESET + " ═════════════════════════════════════════════════════════════╝");

            input = scanner.nextLine();

            switch (input) {
                case "1":
                    return new LightArmour();
                case "2":
                    if (!(character instanceof Elf) && !(character instanceof Goblin)) {
                        return new HeavyArmour();//only if suitable character
                    }
                    System.out.println("Your character is too weak for heavy armoury. Choose a light one or none.\n");
                    break;
                case "3":
                    return null;
                default:
                    System.out.println("No valid input. Try again.\n");
            }

        }
    }

    public static ArrayList<Item> chooseItems(int remainingStrength) {
        boolean inputValid;
        boolean repeatAgain = true;
        final String ANSI_RESET = "\u001B[0m"; //RESET
        final String RED_BOLD_BRIGHT = "\033[1;91m";  // RED
        final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Item> returnItemList = new ArrayList<>();
        Item itemToAdd = null;
        boolean notAgain;

        while (repeatAgain) {
            inputValid = false;
            while (!inputValid) {
                notAgain = false;
                System.out.println("Choose an item:");
                System.out.println("╔════════════════════════════════════════════════════════╗");
                System.out.println("║ Press " + RED_BOLD_BRIGHT + "[1]" + ANSI_RESET + " for a" + YELLOW_BOLD_BRIGHT + " Potion of healing " + ANSI_RESET + "         Weight: " + PURPLE_BOLD_BRIGHT + String.format("%3d", new PotionOfHealing().getWeight()) + ANSI_RESET + " ║");
                System.out.println("║ Press " + RED_BOLD_BRIGHT + "[2]" + ANSI_RESET + " for a" + YELLOW_BOLD_BRIGHT + " Potion of strength " + ANSI_RESET + "        Weight: " + PURPLE_BOLD_BRIGHT + String.format("%3d", new PotionOfStrength().getWeight()) + ANSI_RESET + " ║");
                System.out.println("║ Press " + RED_BOLD_BRIGHT + "[3]" + ANSI_RESET + " for a" + YELLOW_BOLD_BRIGHT + " Ring of defence " + ANSI_RESET + "           Weight: " + PURPLE_BOLD_BRIGHT + String.format("%3d", new RingOfDefence().getWeight()) + ANSI_RESET + " ║");
                System.out.println("║ Press " + RED_BOLD_BRIGHT + "[4]" + ANSI_RESET + " for a" + YELLOW_BOLD_BRIGHT + " Ring of fortitude " + ANSI_RESET + "         Weight: " + PURPLE_BOLD_BRIGHT + String.format("%3d", new RingOfFortitude().getWeight()) + ANSI_RESET + " ║");
                System.out.println("║ Press " + RED_BOLD_BRIGHT + "[5]" + ANSI_RESET + " for not choosing any item.                   ║");
                System.out.println("╚═════════ Remaining Strength:" + PURPLE_BOLD_BRIGHT + String.format("%4d", remainingStrength) + ANSI_RESET + " ══════════════════════╝");


                switch (scanner.nextLine()) {
                    case "1":
                        itemToAdd = new PotionOfHealing();
                        inputValid = true;
                        break;
                    case "2":
                        itemToAdd = new PotionOfStrength();
                        inputValid = true;
                        break;
                    case "3":
                        itemToAdd = new RingOfDefence();
                        inputValid = true;
                        break;
                    case "4":
                        itemToAdd = new RingOfFortitude();
                        inputValid = true;
                        break;
                    case "5":
                        itemToAdd = null;
                        inputValid = true;
                        break;
                    default:
                        System.out.println("No valid input. Try again.\n");
                }

                for (Item item : returnItemList) {
                    if (itemToAdd != null && item != null && item.getClass().equals(itemToAdd.getClass())) {
                        System.out.println("You already own this item. Choose another one or none. \n");
                        notAgain = true;
                        inputValid = false;
                    }
                }

                if (itemToAdd != null && (remainingStrength - itemToAdd.getWeight()) < 0) {
                    inputValid = false;
                    System.out.println("This item is to heavy. Choose another one or none.\n");
                } else if (itemToAdd != null && !notAgain) {
                    if (itemToAdd instanceof RingOfFortitude) {
                        remainingStrength += ((RingOfFortitude) itemToAdd).getStrengthToGive();
                    }
                    remainingStrength -= itemToAdd.getWeight();
                    returnItemList.add(itemToAdd);
                }
            }
            inputValid = false;
            while (!inputValid) {
                System.out.println("╔═══════════════════════════════════════════════════╗");
                System.out.println("║ Press " + RED_BOLD_BRIGHT + "[1]" + ANSI_RESET + " to continue with choosing your weapon   ║");
                System.out.println("║ Press " + RED_BOLD_BRIGHT + "[2]" + ANSI_RESET + " for choosing another item               ║");
                System.out.println("╚═══════════════════════════════════════════════════╝\n");

                input = scanner.nextLine();

                if (input.equals("1")) {
                    repeatAgain = false;
                    inputValid = true;
                } else if (input.equals("2")) {
                    inputValid = true;
                } else {
                    System.out.println("Input not valid! Try again.");
                }
            }
        }
        return returnItemList;
    }

    public static void printStats(PlayerCharacter character) {
        final String ANSI_RESET = "\u001B[0m"; //RESET
        final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        final String RED_BOLD_BRIGHT = "\033[1;91m";  // RED
        final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
        final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN

        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║ Name: " + GREEN_BOLD_BRIGHT + String.format("%28s", character.getName()) + ANSI_RESET + " ║");
        System.out.println("║ Class: " + PURPLE_BOLD_BRIGHT + String.format("%27s", character.getClass().getSimpleName().toUpperCase()) + ANSI_RESET + " ║");
        System.out.println("║ Health: " + RED_BOLD_BRIGHT + String.format("%26s", Math.round((character.getHealth()) * 10) / 10) + ANSI_RESET + " ║");
        if (character.getCurrentWeapon() != null) {
            System.out.println("║ Weapon: " + CYAN_BOLD_BRIGHT + String.format("%26s", character.getCurrentWeapon().getClass().getSimpleName().toUpperCase()) + ANSI_RESET + " ║");
            System.out.println("║ Base damage of " + CYAN_BOLD_BRIGHT + String.format("%-20s", character.getCurrentWeapon().getClass().getSimpleName().toUpperCase() + ANSI_RESET + ":") + RED_BOLD_BRIGHT + String.format("%3s", character.getCurrentWeapon().getAttack()) + ANSI_RESET + " ║");
        } else {
            System.out.println("║ Weapon: " + String.format("%37s", GREEN_BOLD_BRIGHT + character.getName() + ANSI_RESET + " has no weapon") + " ║");
        }
        System.out.println("╚════════════════════════════════════╝");
    }

    public static PlayerCharacter[] generateTurn(PlayerCharacter myCharacter, PlayerCharacter enemy) {
        final String ANSI_RESET = "\u001B[0m"; //RESET
        final String RED_BOLD_BRIGHT = "\033[1;91m";  // RED
        final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
        final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        PlayerCharacter[] savePlayerAndEnemiesFromMethod;
        double safeHealthVar;
        Item safeAndChange;

        for(int i = 0; i < enemy.getItems().size(); i++){
            if(enemy.getItems().get(i) instanceof PotionOfStrength && !enemy.getItems().get(i).getIsVanished()){
                safeAndChange = enemy.getItems().get(i);
                ((PotionOfStrength) safeAndChange).activateStrength();
                safeAndChange.setWeight(0);
                safeAndChange.setIsVanished(true);
                enemy.replaceItemAtIndex(i, safeAndChange);
                System.out.println(GREEN_BOLD_BRIGHT + enemy.getName() + ANSI_RESET + " uses " + YELLOW_BOLD_BRIGHT + enemy.getItems().get(i).getName() + ANSI_RESET + " ! \n");
                //maybe only use after random amount of rounds
                return new PlayerCharacter[]{myCharacter, enemy};
            }else if(enemy.getItems().get(i) instanceof PotionOfHealing && ((enemy.getHealthAtStart() / 100) * enemy.getHealth() < 30) && !enemy.getItems().get(i).getIsVanished()){
                safeAndChange = enemy.getItems().get(i);
                safeHealthVar = ((PotionOfHealing) safeAndChange).getHealthToHeal();
                enemy.setHealth(enemy.getHealth() + safeHealthVar);
                safeAndChange.setWeight(0);
                safeAndChange.setIsVanished(true);
                enemy.replaceItemAtIndex(i, safeAndChange);
                System.out.println(GREEN_BOLD_BRIGHT + enemy.getName() + ANSI_RESET + " uses " + YELLOW_BOLD_BRIGHT + enemy.getItems().get(i).getName() + ANSI_RESET + " !");
                System.out.println(GREEN_BOLD_BRIGHT + enemy.getName() + ANSI_RESET + " gains " + RED_BOLD_BRIGHT + safeHealthVar + ANSI_RESET + " HP and has now " + RED_BOLD_BRIGHT + enemy.getHealth() + ANSI_RESET + " HP ! \n");

                return new PlayerCharacter[]{myCharacter, enemy};
            }
        }

        savePlayerAndEnemiesFromMethod = attackAndCheckArmorDodge(enemy, myCharacter);
        enemy = savePlayerAndEnemiesFromMethod[0];
        myCharacter = savePlayerAndEnemiesFromMethod[1];

        return new PlayerCharacter[]{myCharacter, enemy};
    }

    public static PlayerCharacter[] makeTurn(PlayerCharacter myCharacter, PlayerCharacter enemy) {
        final String ANSI_RESET = "\u001B[0m"; //RESET
        final String RED_BOLD_BRIGHT = "\033[1;91m";  // RED
        final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
        final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
        PlayerCharacter[] savePlayerAndEnemiesFromMethod;
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean validInput = false;
        int amountOfNonHiddenItems = 0;

        while (!validInput) {
            System.out.println("╔═══════════════════════════════════════════════════════╗");
            if (myCharacter.getCurrentWeapon() != null) {
                System.out.println("║ Press " + RED_BOLD_BRIGHT + "[1]" + ANSI_RESET + " to attack " + String.format("%-56s", GREEN_BOLD_BRIGHT + enemy.getName() + ANSI_RESET + " with your " + CYAN_BOLD_BRIGHT + myCharacter.getCurrentWeapon().getClass().getSimpleName().toUpperCase() + ANSI_RESET) + "║");
            } else {
                System.out.println("║ Press " + RED_BOLD_BRIGHT + "[1]" + ANSI_RESET + " to attack " + String.format("%-44s", GREEN_BOLD_BRIGHT + enemy.getName() + ANSI_RESET + " with your bare fists") + " ║");
            }
            for(Item i : myCharacter.getItems()){
                if(!i.getIsVanished()){
                    amountOfNonHiddenItems++;
                }
            }
            if (amountOfNonHiddenItems > 0 || myCharacter.getCurrentWeapon() != null || myCharacter.getCurrentArmor() != null) {
                System.out.println("║ Press " + RED_BOLD_BRIGHT + "[2]" + ANSI_RESET + " for opening your inventory                  ║");
            }
            amountOfNonHiddenItems = 0;
            System.out.println("╚═══════════════════════════════════════════════════════╝\n");
            input = scanner.nextLine();

            if (input.equals("1")) {
                validInput = true;
                savePlayerAndEnemiesFromMethod = attackAndCheckArmorDodge(myCharacter, enemy);
                myCharacter = savePlayerAndEnemiesFromMethod[0];
                enemy = savePlayerAndEnemiesFromMethod[1];
            } else if (input.equals("2") && (myCharacter.getItems().size() > 0 || myCharacter.getCurrentWeapon() != null || myCharacter.getCurrentArmor() != null)) {
                validInput = true;
                myCharacter = InteractWithInventory(myCharacter, enemy)[0];
            } else {
                System.out.println("Input not valid! Try again. \n");
            }
        }
        return new PlayerCharacter[]{myCharacter, enemy};
    }

    public static PlayerCharacter[] InteractWithInventory(PlayerCharacter character, PlayerCharacter enemy) {
        final String ANSI_RESET = "\u001B[0m"; //RESET
        final String RED_BOLD_BRIGHT = "\033[1;91m";  // RED
        final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
        final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
        final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        PlayerCharacter[] savePlayerAndEnemiesFromMethod;
        int i;
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean isValid = false;
        Item chosenItem;
        int safeHealthVar;

        while (!isValid) {
            i = 0;
            System.out.println("╔═════════════════════════════════════════════════╗");
            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[0]" + ANSI_RESET + " to return to the menu                 ║");
            while (i < character.getItems().size()) {
                if (!character.getItems().get(i).getIsVanished()) {
                    System.out.println("║ Press " + RED_BOLD_BRIGHT + "[" + (i + 1) + "]" + ANSI_RESET + " for selecting " + String.format("%-35s", YELLOW_BOLD_BRIGHT + character.getItems().get(i).getName() + ANSI_RESET) + "║");
                }
                i++;
            }
            if (character.getCurrentArmor() != null) {
                System.out.println("║ Press " + RED_BOLD_BRIGHT + "[" + (i + 1) + "]" + ANSI_RESET + " for selecting " + String.format("%-35s", YELLOW_BOLD_BRIGHT + character.getCurrentArmor().getName().toUpperCase() + ANSI_RESET) + "║");
                i++;
            }
            if (character.getCurrentWeapon() != null) {
                System.out.println("║ Press " + RED_BOLD_BRIGHT + "[" + (i + 1) + "]" + ANSI_RESET + " for selecting " + String.format("%-35s", CYAN_BOLD_BRIGHT + character.getCurrentWeapon().getClass().getSimpleName().toUpperCase() + ANSI_RESET) + "║");
            }
            System.out.println("╚═════════════════════════════════════════════════╝\n");

            input = scanner.nextLine();
            try {
                Integer.parseInt(input);
                isValid = true;
            } catch (Exception ignored) {
                System.out.println("Input not valid! Try again. \n");
            }
            if (isValid) {
                if (Integer.parseInt(input) == i + 1 && character.getCurrentWeapon() != null) {
                    if (character.getCurrentWeapon() == null) {
                        System.out.println("Input not valid! Try again. \n");
                        isValid = false;
                    } else {
                        isValid = false;
                        while (!isValid) {
                            System.out.println("╔═══════════════════════════════════════════════════════╗");
                            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[1]" + ANSI_RESET + " to attack " + String.format("%-56s", GREEN_BOLD_BRIGHT + enemy.getName() + ANSI_RESET + " with your " + CYAN_BOLD_BRIGHT + character.getCurrentWeapon().getClass().getSimpleName().toUpperCase() + ANSI_RESET) + "║");
                            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[2]" + ANSI_RESET + " to throw your " + CYAN_BOLD_BRIGHT + String.format("%-34s", character.getCurrentWeapon().getClass().getSimpleName().toUpperCase() + ANSI_RESET + " away ") + "║");
                            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[3]" + ANSI_RESET + " to return to the inventory                  ║");
                            System.out.println("╚═══════════════════════════════════════════════════════╝\n");
                            input = scanner.nextLine();

                            switch (input) {
                                case "1":
                                    savePlayerAndEnemiesFromMethod = attackAndCheckArmorDodge(character, enemy);
                                    character = savePlayerAndEnemiesFromMethod[0];
                                    enemy = savePlayerAndEnemiesFromMethod[1];
                                    isValid = true;
                                    break;
                                case "2":
                                    System.out.println("You gained " + PURPLE_BOLD_BRIGHT + character.getCurrentWeapon().getWeight() + ANSI_RESET + " strength back! \n");
                                    character.setStrength(character.getStrength() + character.getCurrentWeapon().getWeight());
                                    character.setCurrentWeapon(null);
                                    isValid = true;
                                    break;
                                case "3":
                                    character = InteractWithInventory(character, enemy)[0];
                                    isValid = true;
                                    break;
                                default:
                                    System.out.println("Input not valid! Try again. \n");
                            }
                        }
                    }
                } else if (Integer.parseInt(input) == i && character.getCurrentArmor() != null) {

                    if (character.getCurrentArmor() == null) {
                        System.out.println("Input not valid! Try again. \n");
                        isValid = false;
                    } else {
                        isValid = false;
                        while (!isValid) {
                            System.out.println("╔═══════════════════════════════════════════════════════╗");
                            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[1]" + ANSI_RESET + " to throw " + YELLOW_BOLD_BRIGHT + String.format("%-39s", character.getCurrentArmor().getName() + ANSI_RESET + " away ") + "║");
                            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[2]" + ANSI_RESET + " to return to the inventory                  ║");
                            System.out.println("╚═══════════════════════════════════════════════════════╝\n");
                            input = scanner.nextLine();

                            switch (input) {
                                case "1":
                                    System.out.println("You gained " + PURPLE_BOLD_BRIGHT + character.getCurrentArmor().getWeight() + ANSI_RESET + " strength back! \n");
                                    character.setStrength(character.getStrength() + character.getCurrentArmor().getWeight());
                                    character.setCurrentArmor(null);
                                    isValid = true;
                                    break;
                                case "2":
                                    character = InteractWithInventory(character, enemy)[0];
                                    isValid = true;
                                    break;
                                default:
                                    System.out.println("Input not valid! Try again. \n");
                            }
                        }
                    }
                } else if(Integer.parseInt(input) == 0){
                    makeTurn(character, enemy);
                } else if (Integer.parseInt(input) < (i + 1) && Integer.parseInt(input) > 0 && character.getItems().size() != 0) {
                    int indexOfChosenItem = Integer.parseInt(input) - 1;
                    chosenItem = character.getItems().get(indexOfChosenItem);
                    if (chosenItem.getIsVanished()) {
                        System.out.println("Input not valid! Try again. \n");
                        isValid = false;
                    } else {
                        isValid = false;
                        while (!isValid) {
                            System.out.println("╔═══════════════════════════════════════════════════════╗");
                            if (chosenItem.getIsUsable()) {
                                System.out.println("║ Press " + RED_BOLD_BRIGHT + "[0]" + ANSI_RESET + " to use " + YELLOW_BOLD_BRIGHT + String.format("%-41s", chosenItem.getName() + ANSI_RESET) + "║");
                            }
                            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[1]" + ANSI_RESET + " to throw " + YELLOW_BOLD_BRIGHT + String.format("%-39s", chosenItem.getName() + ANSI_RESET + " away ") + "║");
                            System.out.println("║ Press " + RED_BOLD_BRIGHT + "[2]" + ANSI_RESET + " to return to the inventory                  ║");
                            System.out.println("╚═══════════════════════════════════════════════════════╝\n");

                            input = scanner.nextLine();

                            if (input.equals("1")) {
                                System.out.println("You gained " + PURPLE_BOLD_BRIGHT + chosenItem.getWeight() + ANSI_RESET + " strength back! \n");
                                character.setStrength(character.getStrength() + chosenItem.getWeight());
                                if (chosenItem instanceof RingOfFortitude) {
                                    System.out.println("You lost " + PURPLE_BOLD_BRIGHT + chosenItem.getWeight() + ANSI_RESET + " strength! You keep the " + PURPLE_BOLD_BRIGHT + (((RingOfFortitude) chosenItem).getStrengthToGive() - chosenItem.getWeight() ) + ANSI_RESET + " Strength you've gained through the ring. \n");
                                    character.setStrength(character.getStrength() - chosenItem.getWeight());
                                }
                                character.removeItemAtIndex(indexOfChosenItem);
                                isValid = true;
                            } else if (input.equals("2")) {
                                character = InteractWithInventory(character, enemy)[0];
                                isValid = true;
                            } else if (input.equals("0") && chosenItem.getIsUsable()) {

                                if (chosenItem instanceof PotionOfHealing) {
                                    safeHealthVar = ((PotionOfHealing) chosenItem).getHealthToHeal();
                                    character.setHealth(character.getHealth() + safeHealthVar);

                                    chosenItem.setWeight(0);
                                    chosenItem.setIsVanished(true);
                                    System.out.println(GREEN_BOLD_BRIGHT + character.getName() + ANSI_RESET + " uses " + YELLOW_BOLD_BRIGHT + chosenItem.getName() + ANSI_RESET + " !");
                                    System.out.println(GREEN_BOLD_BRIGHT + character.getName() + ANSI_RESET + " gains " + RED_BOLD_BRIGHT + safeHealthVar + ANSI_RESET + " HP and has now " + RED_BOLD_BRIGHT + character.getHealth() + ANSI_RESET + " HP ! \n");
                                    character.replaceItemAtIndex(indexOfChosenItem, chosenItem);

                                } else if (chosenItem instanceof PotionOfStrength) {
                                    System.out.println(GREEN_BOLD_BRIGHT + character.getName() + ANSI_RESET + " uses " + YELLOW_BOLD_BRIGHT + chosenItem.getName() + ANSI_RESET + " ! \n");
                                    ((PotionOfStrength) chosenItem).activateStrength();

                                    chosenItem.setWeight(0);
                                    chosenItem.setIsVanished(true);
                                    character.replaceItemAtIndex(indexOfChosenItem, chosenItem);
                                }

                                isValid = true;

                            } else {
                                System.out.println("Input not valid! Try again. \n");
                            }
                        }
                    }
                } else {
                    System.out.println("Input not valid! Try again. \n");
                    isValid = false;
                }
            }
        }
        return new PlayerCharacter[]{character, enemy};
    }

    public static void printLose(PlayerCharacter enemy){
        final String ANSI_RESET = "\u001B[0m"; //RESET
        final String RED_BOLD_BRIGHT = "\033[1;91m";  // RED
        final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN

            System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n");
            System.out.println("╔═════════════════════════════════════════════════════╗");
            System.out.println("║" + RED_BOLD_BRIGHT + " You've lost against " + GREEN_BOLD_BRIGHT + String.format("%-35s", enemy.getName()  + ANSI_RESET + " !") + " ║");
            System.out.println("╚═════════════════════════════════════════════════════╝");

    }

    public static void printWin(PlayerCharacter enemy){
        final String ANSI_RESET = "\u001B[0m"; //RESET
        final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
        final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN

            System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n");
            System.out.println("╔═════════════════════════════════════════════════════╗");
            System.out.println("║" + CYAN_BOLD_BRIGHT + " You've won against " + GREEN_BOLD_BRIGHT + String.format("%-36s", enemy.getName()  + ANSI_RESET + " !") + " ║");
            System.out.println("╚═════════════════════════════════════════════════════╝");
    }

    public static void printDraw(PlayerCharacter enemy){
        final String ANSI_RESET = "\u001B[0m"; //RESET
        final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN

        System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n");
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║" + YELLOW_BOLD_BRIGHT + " You've drawn against " + GREEN_BOLD_BRIGHT + String.format("%-37s", enemy.getName()  + ANSI_RESET + " !") + " ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");
    }

    public static PlayerCharacter[] attackAndCheckArmorDodge(PlayerCharacter attacker, PlayerCharacter defender){
        final String ANSI_RESET = "\u001B[0m"; //RESET
        final String RED_BOLD_BRIGHT = "\033[1;91m";  // RED
        final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
        final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        double damageDealt;

        if(defender.getCurrentArmor() != null && new Random().nextInt((defender.getCurrentArmor().getProbabilityOfBeingHit() - 1 )) == 0){ // -1 because
            System.out.println(GREEN_BOLD_BRIGHT + defender.getName() + ANSI_RESET + " has dodged a hit from " + GREEN_BOLD_BRIGHT + attacker.getName() + ANSI_RESET + " thanks to his " + YELLOW_BOLD_BRIGHT + defender.getCurrentArmor().getName().toUpperCase() + ANSI_RESET + " !\n");
        }else {
            damageDealt = Math.round(attacker.getFightValue() * 100.0) / 100.0;

            if(attacker.getStrength() >= defender.getStrength() && new Random().nextInt(4) == 0){ //Players with a higher strength than the opponent have a higher chance of hitting critical hits.
                damageDealt *= 2;
                System.out.println(GREEN_BOLD_BRIGHT + attacker.getName() + ANSI_RESET + " did a critical hit! ");
            }else if(new Random().nextInt(11) == 0){
                damageDealt *= 2;
                System.out.println(GREEN_BOLD_BRIGHT + attacker.getName() + ANSI_RESET + " did a critical hit! ");
            }

            System.out.println(GREEN_BOLD_BRIGHT + defender.getName() + ANSI_RESET + " lost " + RED_BOLD_BRIGHT + damageDealt + ANSI_RESET + " HP! ");
            defender.setHealth((Math.round(defender.getHealth() * 100.0) / 100.0) - damageDealt);
            System.out.println(GREEN_BOLD_BRIGHT + defender.getName() + ANSI_RESET + " has " + RED_BOLD_BRIGHT + (Math.round(defender.getHealth() * 100.0) / 100.0) + ANSI_RESET + " HP left. \n");
        }

        return new PlayerCharacter[]{attacker, defender};
    }
}

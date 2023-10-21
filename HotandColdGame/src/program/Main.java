package program;
import java.util.*;
public class Main {
    public static void main(String[] args)
    {
        printRules();
        showMenu();
    }// END OF MAIN METHOD
    public static void printRules(){
        System.out.println("Welcome to the Hot/Cold game!");
        System.out.println("*****************************");
        System.out.println("Game rules: a match starts by picking a random number \n" +
                           "between a low and a high value. The player then tries to \n" +
                           "guess the number. The game will respond with adjectives\n" +
                           "such as: cold, warm, hot, very hot! The hotter the\n" +
                           "adjective the closer the guess was to the right answer,\n" +
                           "according to the following table: ");
        System.out.println("*****************************\n");
        System.out.println("extremely hot - 1 away from the target number");
        System.out.println("very hot - 2 away from the target number");
        System.out.println("hot - 3-5 away from the target number");
        System.out.println("warm - 6-10 away from the target number");
        System.out.println("cold - 11-30 away from the target number ");
        System.out.println("ice cold - more than 30 away from the target number");
        System.out.println("*****************************\n");
        System.out.println("Use the menu below to start the game. ");
//        System.out.println("1. Start a new match of Hot/Cold game");
//        System.out.println("2. Print match statistics");
//        System.out.println("3. Exit");

    }//END OF prompt() method
    public static void showMenu(){
        showMenuPrompt();
        getMenuChoice();

    }//END OF showMenu() method
    public static void getMenuChoice(){
        Scanner userInput = new Scanner(System.in);
        int[] totalStat = new int[2];
        int userChoice = 0;
        int totalMatch = 0;
        int totalGuess = 0;

        while(userChoice != 3) {

            System.out.print("Make a choice: ");
            if (userInput.hasNextInt()) {

                userChoice = userInput.nextInt();
                if (userChoice == 1) {
                    totalStat = playMatch();
                    totalMatch += totalStat[0];
                    totalGuess += totalStat[1];

                } else if (userChoice == 2) {
                    totalStat[0] = totalMatch;
                    totalStat[1] = totalGuess;
                    showStatistics(totalStat);
                } else if (userChoice > 3 || userChoice < 1) {
                    System.out.println("Not a valid menu option. Please choose again");
                    userInput.next();
//                  userChoice = userInput.nextInt();
                }
            }else{
                System.out.println("not a valid number");
                userInput.next();
            }
        }

    }//END OF getMenuChoice() method
    public static int[] playMatch(){
        Scanner userInput = new Scanner(System.in);
        int generatedRandom = getGeneratedNum(userInput);
        int totalMatch = 0;
        int totalGuess = 0;
        int guess = 0;

        int [] totalArrays = {totalMatch, totalGuess};

        while(guess != generatedRandom) {
            //System.out.println(generatedRandom);
            System.out.println("Guess?");
            guess = userInput.nextInt();

            if (guess == generatedRandom) {
                System.out.println("YOU WON");
                totalMatch += 1;
                totalGuess += 1;
                showMenuPrompt();
            } else if (guess == generatedRandom - 1 || guess == generatedRandom + 1) {
                totalGuess += 1;
                System.out.println("extremely hot");
            } else if (guess == generatedRandom - 2 || guess == generatedRandom + 2) {
                System.out.println("very hot");
                totalGuess += 1;
            }
            for (int i = 3; i < 6; i++) {
                if (guess == generatedRandom - i || guess == generatedRandom + i) {
                    System.out.println("hot");
                    totalGuess += 1;
                }
            }
            for (int i = 6; i < 11; i++) {
                if (guess == generatedRandom - i || guess == generatedRandom + i) {
                    System.out.println("warm");
                    totalGuess += 1;
                }
            }
            for (int i = 11; i < 31; i++) {
                if (guess == generatedRandom - i || guess == generatedRandom + i) {
                    System.out.println("cold");
                    totalGuess += 1;

                }
            }
            for (int i = 32; i > 31; i++) {
                if (guess == generatedRandom - i || guess == generatedRandom + i) {
                    System.out.println("Ice cold");
                    totalGuess += 1;
                }
            }

        }// END OF WHILE LOOP;
            totalArrays[0] = totalMatch;
            totalArrays[1] = totalGuess;
        return totalArrays;
    }// END OF playMatch() method
    public static int getGeneratedNum(Scanner userInput){
        Random randomNum = new Random();
        System.out.println("Enter a low value for the match");
        int userLow = userInput.nextInt();
        System.out.println("Enter a high value for the match");
        int userHigh = userInput.nextInt();
        int generatedRandom;
        if(userLow > userHigh) {
            generatedRandom = randomNum.nextInt(userHigh,userLow);
            System.out.println("Generating a number between " + userHigh + "-" + userLow);
        }else if(userHigh < userLow)
        {
            generatedRandom = randomNum.nextInt(userLow, userHigh);
            System.out.println("Generating a number between " + userLow + "-" + userHigh);
        } else if (userHigh == userLow) {
            generatedRandom = randomNum.nextInt((userHigh - userLow +1)) + userLow;
            System.out.println("Generating a number between " + userLow + "-" + userHigh);

        } else {
            generatedRandom = randomNum.nextInt(userLow, userHigh);
            System.out.println("Generating a number between " + userLow + "-" + userHigh);
        }
        return generatedRandom;
    }// END OF getGeneratedNum() method
    public static void showStatistics(int[] totalStats){

        if(totalStats[0] == 0){
            System.out.println("you must play before statistics are shown");
        }else{
            System.out.println("*****************************");
            System.out.println("Total matches: " + totalStats[0]);
            System.out.println("Total guesses across matches: " + totalStats[1]);
        }
        // totalStats[0] === number of matches
        // totalStats[1] === number of guess


    }//END OF showStatistics() method
    public static void showMenuPrompt(){

        System.out.println("1. Start a new match of Hot/Cold game");
        System.out.println("2. Print match statistics");
        System.out.println("3. Exit");
    }
}// END OF MAIN CLASS
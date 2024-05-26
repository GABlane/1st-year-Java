import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PAIRS {
    public static String HidenCard = "";
    public static String[] Suites = { "D", "H", "C", "S" };
    public static Random rand = new Random();
    public static Scanner in = new Scanner(System.in);
    public static String quits = "";
    public static ArrayList<String> User;
    public static ArrayList<String> player1;
    public static ArrayList<String> player2;
    public static ArrayList<String> player3;
    public static ArrayList<String> player4;
    public static ArrayList<String> cards;
    public static int attempts = 0;
    public static boolean gameOver = false; // Flag to indicate if the game is over
    public static ArrayList<String> winners; // List to keep track of winners in order

    public static void main(String[] args) {
        System.out.println("NEXT | EXIT");
        quits = in.nextLine();
        while (quits.equalsIgnoreCase("NEXT")) {
            initializeGame();
            play();
            if (gameOver) {
                break; // Exit the loop if the game is over
            }
            System.out.println("NEXT | EXIT");
            quits = in.next();
        }
    }

    public static void initializeGame() {
        cards = new ArrayList<>();
        player1 = new ArrayList<>();
        player2 = new ArrayList<>();
        player3 = new ArrayList<>();
        player4 = new ArrayList<>();
        User = new ArrayList<>();
        winners = new ArrayList<>(); // Initialize the winners list
        gameOver = false; // Reset the game over flag

        Populate();
        Hide();
        while (!cards.isEmpty()) {
            distribute();
        }
        compare(player1);
        compare(player2);
        compare(player3);
        compare(player4);
        compare(User);
        disp("User", User);
        disp("Player 1", player1);
        disp("Player 2", player2);
        disp("Player 3", player3);
        disp("Player 4", player4);
    }

    public static void play() {
        System.out.println("How many attempts?");
        attempts = in.nextInt();
        while (attempts > 0 && quits.equalsIgnoreCase("NEXT") && !gameOver) {
            System.out.println("\n\n\n\n");
            if (!player1.isEmpty()) {
                drawCard(player1, User, "Player 1");
                if (player1.isEmpty() && !winners.contains("Player 1")) {
                    announceWinner("Player 1");
                }
            }

            if (!player2.isEmpty()) {
                drawCard(player2, player1, "Player 2");
                if (player2.isEmpty() && !winners.contains("Player 2")) {
                    announceWinner("Player 2");
                }
            }

            if (!player3.isEmpty()) {
                drawCard(player3, player2, "Player 3");
                if (player3.isEmpty() && !winners.contains("Player 3")) {
                    announceWinner("Player 3");
                }
            }

            if (!player4.isEmpty()) {
                drawCard(player4, player3, "Player 4");
                if (player4.isEmpty() && !winners.contains("Player 4")) {
                    announceWinner("Player 4");
                }
            }

            if (!User.isEmpty()) {
                drawCard(User, player4, "User");
                if (User.isEmpty() && !winners.contains("User")) {
                    announceWinner("User");
                }
            }

            attempts--;
            if (attempts == 0 && !gameOver) {
                System.out.println("NEXT | EXIT");
                quits = in.next();
                if (quits.equalsIgnoreCase("NEXT")) {
                    System.out.println("How many attempts?");
                    attempts = in.nextInt();
                }
            }
        }

        // Display the winners ranking if the game is over
        if (gameOver) {
            System.out.println("Game Over! Final Ranking:");
            for (int i = 0; i < winners.size(); i++) {
                System.out.println((i + 1) + "st: " + winners.get(i));
            }
        }
    }

    public static void drawCard(ArrayList<String> One, ArrayList<String> Two, String three) {
        int index = rand.nextInt(One.size());
        String card = One.get(index);
        Two.add(card);
        One.remove(index);
        compare(Two);
        disp(three + " to " + getPlayerName(Two), Two);
    }

    public static String getPlayerName(ArrayList<String> player) {
        if (player == User)
            return "User";
        if (player == player1)
            return "Player 1";
        if (player == player2)
            return "Player 2";
        if (player == player3)
            return "Player 3";
        if (player == player4)
            return "Player 4";
        return "Unknown";
    }

    public static void announceWinner(String player) {
        System.out.println("\n\n\n\n ===============================================");
        System.out.println(player + " has no more cards to give.");
        winners.add(player); // Add the player to the winners list
        System.out.println("The Hidden card is :" + HidenCard);
        System.out.println("===============================================");

        if (winners.size() == 5) { // All players have finished
            gameOver = true; // Set the game over flag
        }
    }

    public static void compare(ArrayList<String> hand) {
        for (int i = 0; i < hand.size(); i++) {
            char value = Character.toUpperCase(hand.get(i).charAt(0));
            for (int j = i + 1; j < hand.size(); j++) {
                if (Character.toUpperCase(hand.get(j).charAt(0)) == value) {
                    hand.remove(j);
                    j--;
                }
            }
        }
    }

    public static void Hide() {
        int randomIndex = rand.nextInt(cards.size());
        HidenCard = cards.get(randomIndex);
        cards.remove(randomIndex);
    }

    public static void distribute() {
        ArrayList<Integer> indices = new ArrayList<>();
        while (indices.size() < Math.min(5, cards.size())) {
            int index = rand.nextInt(cards.size());
            if (!indices.contains(index)) {
                indices.add(index);
            }
        }

        indices.sort((a, b) -> b - a);
        if (!indices.isEmpty())
            User.add(cards.remove((int) indices.remove(0)));
        if (!indices.isEmpty())
            player1.add(cards.remove((int) indices.remove(0)));
        if (!indices.isEmpty())
            player2.add(cards.remove((int) indices.remove(0)));
        if (!indices.isEmpty())
            player3.add(cards.remove((int) indices.remove(0)));
        if (!indices.isEmpty())
            player4.add(cards.remove((int) indices.remove(0)));
    }

    public static void Populate() {
        for (String suite : Suites) {
            for (int i = 1; i <= 13; i++) {
                cards.add(DispLetter(i) + suite);
            }
        }
    }

    public static void disp(String name, ArrayList<String> hand) {
        StringBuilder display = new StringBuilder(name + ": ");
        for (String card : hand) {
            display.append(card).append(" ");
        }
        System.out.println(display.toString().trim());
    }

    public static String DispLetter(int num) {
        switch (num) {
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            default:
                return String.valueOf(num);
        }
    }
}

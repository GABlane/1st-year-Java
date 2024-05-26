import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ArrayLessons {
    public static Scanner in = new Scanner(System.in);
    public static ArrayList<String> cards;
    public static String[] Suites = { "D", "H", "C", "S" };
    public static Random rand = new Random();
    public static int tmp = 0;
    public static String display = "";

    public static void main(String[] args) {
        String quits = "yes";
        cards = new ArrayList<String>();
        System.out.println("HOW MANY PLAYERS IN THE GAME");
        int Cplayers = in.nextInt();

        switch (Cplayers) {
            case 1:
                while (quits.equalsIgnoreCase("yes")) {
                    if (cards.size() <= 10) {
                        Populate();
                    }
                    user();
                    System.out.println("Continue? YES | NO");
                    quits = in.next().toLowerCase();
                }
                break;
            case 2:
                while (quits.equalsIgnoreCase("yes")) {
                    if (cards.size() <= 10) {
                        Populate();
                    }
                    drawAndDisplayCards("PLAYER 2");
                    user();
                    System.out.println("Continue? YES | NO");
                    quits = in.next().toLowerCase();
                }
                break;
            case 3:
                while (quits.equalsIgnoreCase("yes")) {
                    if (cards.size() <= 10) {
                        Populate();
                    }
                    user();
                    drawAndDisplayCards("PLAYER 2");
                    drawAndDisplayCards("PLAYER 3");
                    System.out.println("Continue? YES | NO");
                    quits = in.next().toLowerCase();
                }
                break;
            case 4:
                while (quits.equalsIgnoreCase("yes")) {
                    if (cards.size() <= 10) {
                        Populate();
                    }
                    user();
                    drawAndDisplayCards("PLAYER 2");
                    drawAndDisplayCards("PLAYER 3");
                    drawAndDisplayCards("PLAYER 4");
                    System.out.println("Continue? YES | NO");
                    quits = in.next().toLowerCase();
                }
                break;
            default:
                System.out.println("This doesn't apply");
                break;
        }
    }

    public static void user() {
        int randomIndex1 = rand.nextInt(cards.size());
        int randomIndex2 = rand.nextInt(cards.size());
        while (randomIndex1 == randomIndex2) {
            randomIndex2 = rand.nextInt(cards.size());
        }

        String Card1 = cards.get(randomIndex1);
        String Card2 = cards.get(randomIndex2);

        System.out.println("USER Selected Card: " + Card1 + " and " + Card2);
        int playerScore = piCards(Card1) + piCards(Card2);
        playerScore = playerScore % 10;
        System.out.println(" USER : " + playerScore);
        System.out.println("Do you wanna add another card? YES | NO");
        String ans = in.next();
        if (ans.equalsIgnoreCase("yes")) {
            int randomIndex3 = rand.nextInt(cards.size());
            while (randomIndex3 == randomIndex2) {
                randomIndex3 = rand.nextInt(cards.size());
            }
            String Card3 = cards.get(randomIndex3);
            System.out.println("USER Selected Card: " + Card1 + " and " + Card2 + " and " + Card3);
            playerScore = piCards(Card1) + piCards(Card2) + piCards(Card3);
            playerScore = playerScore % 10;
            System.out.println("   USER : " + playerScore);
            if (randomIndex1 > randomIndex2 || randomIndex1 > randomIndex3) {
                cards.remove(randomIndex1);
                cards.remove(randomIndex2);
                cards.remove(randomIndex3);
            } else {
                cards.remove(randomIndex3);
                cards.remove(randomIndex2);
                cards.remove(randomIndex1);
            }
        } else {

            if (randomIndex1 > randomIndex2) {
                cards.remove(randomIndex1);
                cards.remove(randomIndex2);
            } else {
                cards.remove(randomIndex2);
                cards.remove(randomIndex1);
            }
        }
    }

    public static void drawAndDisplayCards(String playerName) {
        int randomIndex1 = rand.nextInt(cards.size());
        int randomIndex2 = rand.nextInt(cards.size());

        while (randomIndex1 == randomIndex2) {
            randomIndex2 = rand.nextInt(cards.size());
        }

        String Card1 = cards.get(randomIndex1);
        String Card2 = cards.get(randomIndex2);
        System.out.println(playerName + " Selected Card: " + Card1 + " and " + Card2);
        int playerScore = piCards(Card1) + piCards(Card2);
        playerScore = playerScore % 10;
        System.out.println(playerName + " : " + playerScore);

        if (randomIndex1 > randomIndex2) {
            cards.remove(randomIndex1);
            cards.remove(randomIndex2);
        } else {
            cards.remove(randomIndex2);
            cards.remove(randomIndex1);
        }
    }

    public static int piCards(String card) {
        char value = card.charAt(0);
        switch (value) {
            case '1':
                return 10;
            case 'A':
                return 1;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            default:
                return Character.getNumericValue(value);
        }
    }

    public static void Populate() {
        for (int I = 0; I < 4; I++) {
            for (int i = 1; i <= 13; i++) {
                cards.add(DispLetter(i) + Suites[I]);
            }
        }
    }

    public static void disp() {
        display = "";
        tmp = 0;
        for (int i = 0; i < cards.size(); i++) {
            tmp++;
            display += cards.get(i) + " ";
            if (tmp == 13) {
                tmp = 0;
                display += "\n";
            }
        }
        System.out.println(display);
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

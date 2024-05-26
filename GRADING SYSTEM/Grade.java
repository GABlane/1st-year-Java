import java.util.Scanner;

public class Grade {
    public static int[][] data = new int[100][100];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String quit = "NO";
        int counter = 0;
        while (!quit.equalsIgnoreCase("YES")) {
            System.out.println("**********CLASS CALCULATOR***********");
            System.out.println("HOW MANY CLASS?");
            int quantity = in.nextInt();
            int[][] c_las = new int[quantity + counter][];
            for (int i = counter; i < quantity + counter; i++) {
                System.out.println("HOW MANY STUDENTS ARE THERE IN SECTION " + (i + 1));
                int size = in.nextInt();
                c_las[i] = new int[size];
                System.out.println("Enter " + size + " EXAM SCORES ");
                for (int j = 0; j < size; j++) {
                    System.out.print("GRADE OF STUDENT NUM " + (j + 1) + " :");
                    c_las[i][j] = in.nextInt();
                    data[i][j] = c_las[i][j];
                }
            }
            Ans(c_las, quantity, counter);
            counter += quantity;
            System.out.println("DO YOU WANNA STOP? [YES/NO]");
            quit = in.next();
        }
        System.out.println("DO YOU WANNA RECALL ALL SECTIONS? [y/n] ");
        String confirmation = in.next();
        if (confirmation.equalsIgnoreCase("y")) {
            for (int i = 0; i < counter; i++) {
                System.out.println("-------------------------------------------");
                System.out.println("GRADES ENTEREDS FOR SECTION " + (i + 1) + " :");
                for (int j = 0; j < data[i].length; j++) {
                    if (data[i][j] != 0) {
                        System.out.print(data[i][j] + " | ");
                    } else {
                        break;
                    }
                }
                System.out.print("\n");
            }
        } else {
            System.out.println("ALL RECORDS ARE DELETED THANK YOU!");
        }
        System.out.println("-------------------***** THANK YOU *****------------------------");
        System.out.println("DELETE ALL DATA? y/n");
        confirmation = in.next();
        if (confirmation.equalsIgnoreCase("y")) {
            data = new int[0][0];
            System.out.println("ALL RECORDS ARE DELETED THANK YOU!");
        }
        System.out.println(
                "because we're are not allowed to store this in a file the data will automatically be deleted, thank you for using it.");
        System.out.println("-------------------------------------------");

        in.close();
    }

    public static void Ans(int[][] exam, int quantity, int counter) {
        System.out.println("-------------------------------------------");
        // inputed nums
        for (int i = counter; i < quantity + counter; i++) {
            System.out.println("GRADES ENTERED FOR SECTIONS " + (i + 1) + ":");
            for (int j = 0; j < exam[i].length; j++) {
                System.out.print(exam[i][j] + " | ");
            }
            System.out.print("\n");
        }
        System.out.println("-------------------------------------------");
        // ave of each sections
        for (int i = counter; i < quantity + counter; i++) {
            int Average = 0;
            System.out.print("THE AVERAGE EXAM SCORE FOR EACH CLASS IS " + (i + 1) + " is :");
            for (int j = 0; j < exam[i].length; j++) {
                Average += exam[i][j];
            }
            Average = Average / exam[i].length;
            System.out.print(" " + Average + "\n");
        }
        System.out.println("-------------------------------------------");
        // ave of all
        System.out.print("THE AVERAGE SCORE FOR ALL CLASS IS : ");
        int Average = 0;
        float round = 0;
        for (int i = counter; i < quantity + counter; i++) {
            for (int j = 0; j < exam[i].length; j++) {
                Average += exam[i][j];
            }
            round += exam[i].length;
        }
        round = Average / round;
        System.out.println(round);
        System.out.println("-------------------------------------------");
        // highest & lowest of each sections
        for (int i = counter; i < quantity + counter; i++) {
            int highest = 0, lowest = 10000000;
            System.out.println("THE HIGHEST AND LOWEST IN CLASS " + (i + 1) + " are :");
            for (int j = 0; j < exam[i].length; j++) {
                if (highest < exam[i][j]) {
                    highest = exam[i][j];
                }
                if (lowest > exam[i][j]) {
                    lowest = exam[i][j];
                }
            }
            System.out.println(" Highest : " + highest + " | Lowest : " + lowest);
        }
        System.out.println("-------------------------------------------");
        // highest & lowest of all sections
        System.out.println("THE HIGHEST AND LOWEST AMONG ALL CLASS ARE : ");
        int highest = 0, lowest = 10000000;
        for (int i = counter; i < quantity + counter; i++) {
            for (int j = 0; j < exam[i].length; j++) {
                if (highest < exam[i][j]) {
                    highest = exam[i][j];
                }
                if (lowest > exam[i][j]) {
                    lowest = exam[i][j];
                }
            }
        }
        System.out.println(" Highest : " + highest + " | Lowest : " + lowest);
        System.out.println("-------------------------------------------");
        // mode
        int mode = 0, times = 0;
        for (int i = counter; i < quantity + counter; i++) {
            for (int j = 0; j < exam[i].length; j++) {
                int currentNum = exam[i][j];
                int frequency = 0;
                for (int k = counter; k < quantity + counter; k++) {
                    for (int l = 0; l < exam[k].length; l++) {
                        if (exam[k][l] == currentNum) {
                            frequency++;
                        }
                    }
                }
                if (frequency > times) {
                    mode = currentNum;
                    times = frequency;
                }
            }
        }
        System.out.println("THE MODE OF ALL SECTIONS IS (" + mode + ") WITH A FREQUENCY OF : " + times);
        System.out.println("-------------------------------------------");
        // median
        System.out.print("THE MEDIAN OF ALL SECTIONS IS : ");
        double mid = 0;
        int hi = exam.length - counter;
        double[] median = new double[hi];
        for (int i = counter; i < quantity + counter; i++) {
            int[] section = exam[i];
            int sectionLength = section.length;

            for (int l = 0; l < sectionLength - 1; l++) {
                for (int k = 0; k < sectionLength - 1 - l; k++) {
                    if (section[k] > section[k + 1]) {
                        int temp = section[k];
                        section[k] = section[k + 1];
                        section[k + 1] = temp;
                    }
                }
            }

            if (sectionLength % 2 == 0) {
                median[i - counter] = (section[sectionLength / 2] + section[sectionLength / 2 - 1]) / 2.0;
            } else {
                median[i - counter] = section[sectionLength / 2];
            }
        }

        // Calculate the average of all medians
        for (int x = 0; x < median.length; x++) {
            mid += median[x];
        }
        if ((mid % 2) == 0) {
            mid = mid / median.length;
            mid++;
        } else {
            mid = mid / median.length;
        }
        System.out.println(mid);
        System.out.println("-------------------------------------------");
    }
}

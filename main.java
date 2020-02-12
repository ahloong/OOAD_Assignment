import java.util.*;
import java.util.concurrent.TimeUnit;


public class main {
    static ArrayList<Organism> ants;
    static ArrayList<Organism> bugs;
    public static void main(final String[] args) {
        Organism[][] board = new Organism[20][20]; // array for storing both ants and bugs
        ants = new ArrayList<Organism>(); // array for storing ant
        bugs = new ArrayList<Organism>(); // array for storing bugs
        for (int i = 100; i > 0; i--) { // init how many ants
            Ant a = new Ant(board, ants);
            ants.add(a);
        }
        for (int i = 5; i > 0; i--) { // init how many bugs
            Bug b = new Bug(board, bugs);
            bugs.add(b);
        }
        System.out.println(ants.size());
        while (true) {
            for (int i = 0; i < ants.size(); i++) {
                ants.get(i).update();
                //System.out.println("Ant size: " + ants.size());
            }
            for (int i = 0; i < bugs.size(); i++) {
                bugs.get(i).update();
                //System.out.println("Bug Size: "+ bugs.size());
            }
            printBoard(board);
            System.out.println(" ");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // printBoard(board);
    }

    static void printBoard(final Organism[][] board) {
        for (Organism[] x : board) {
            for (Organism y : x) {
                if (y == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(y + " ");
                }
            }
            System.out.println();

        }
    }

}

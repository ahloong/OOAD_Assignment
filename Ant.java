import java.util.*;

public class Ant extends Organism {
    Organism[][] board;
    ArrayList ants;
    int x, y;
    int counter = 3;
    Random r = new Random();

    public Ant(Organism[][] board, ArrayList ants) {
        do {
            x = r.nextInt((19 - 0) + 1);
            y = r.nextInt((19 - 0) + 1);
        } while (board[y][x] != null);
        this.board = board;
        this.ants = ants;
        board[y][x] = this;
    }

    public Ant(int nx, int ny, Organism[][] board, ArrayList ants) {
        x = nx;
        y = ny;
        this.board = board;
        this.ants = ants;
        board[y][x] = this;
    }

    void move() {
        int choice = 0;
        int nx = x;
        int ny = y;
        if ((x == 0 || board[y][x - 1] != null) && (x == 19 || board[y][x + 1] != null)
                && ((y == 0 || board[y - 1][x] != null) && (y == 19 || board[y + 1][x] != null))) {
            return;
        }
        choice = r.nextInt((3 - 0) + 1);
        // System.out.println("Moving Ant " + choice);
        board[y][x] = null;
        if (choice == 0) {
            ny = y + 1;
        } else if (choice == 1) {
            nx = x + 1;
        } else if (choice == 2) {
            ny = y - 1;
        } else if (choice == 3) {
            nx = x - 1;
        }
        if (nx == -1 || nx == 20 || ny == -1 || ny == 20 || board[ny][nx] != null) {
            move();
            return;
        }
        board[ny][nx] = this;
        y = ny;
        x = nx;
        // try {
        // board[ny][nx] = this;
        // y = ny;
        // x = nx;
        // } catch (Exception e) {
        // //System.out.println("Re-move");
        // this.move();
        // }
    }

    void breed() {

        int choiceB = 0;
        boolean loop = false;
        int nx = 0;
        int ny = 0;
        do {
            nx = x;
            ny = y;
            // System.out.println("RANDING");
            choiceB = r.nextInt((3 - 0) + 1);
            // System.out.println("CHOICE IS " + choiceB);
            // System.out.println("===old x y IS " + x + " " + y);
            // System.out.println("===old nx ny IS " + nx + " " + ny);

            if (choiceB == 0) {
                ny = y + 1;
            } else if (choiceB == 1) {
                nx = x + 1;
            } else if (choiceB == 2) {
                ny = y - 1;
            } else if (choiceB == 3) {
                nx = x - 1;
            }
            // System.out.println("nx " + nx + " ,ny " + ny);

            if (nx > 19 || nx < 0 || ny > 19 || ny < 0 || board[ny][nx] != null) {
                loop = true;
                return;
                // printBoard(board);
                // System.out.println("rebreed nx " + nx + " ,ny " + ny + " x " + x + " ,y " +
                // y);
                // System.out.println("it is a " + board[y][x]);
               /* if ((x == 0 || board[y][x - 1] != null) && (x == 19 || board[y][x + 1] != null)
                        && (y == 0 || board[y - 1][x] != null) && (y == 19 || board[y + 1][x] != null)) {
                    // System.out.println("NO PLACE TO BREED!!!!!!!!!!!!");
                    return;
                } *////////////////
            } else {
                loop = false;
            }
        } while (loop == true);
        Ant a = new Ant(nx, ny, board, ants);
        ants.add(a);
    }

    void update() {
        move();
        // System.out.println("Counter: " + counter);
        counter--;
        // System.out.println("Counter: " + counter);
        if (counter == 0) {
            breed();
            counter = 3;
        }
    }

    public String toString() {
        return "A"; // board will show an "A" for every ant object
    }

    static void printBoard(final Organism[][] board) { // wei han sohai
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
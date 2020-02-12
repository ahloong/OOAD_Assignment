import java.util.*;

public class Bug extends Organism {
    Organism[][] board;
    ArrayList bugs;
    int counter = 8;
    int daiCounter = 3;
    int x, y;
    Random r = new Random();

    public Bug(Organism[][] board, ArrayList bugs) {
        do {
            x = r.nextInt((19 - 0) + 1);
            y = r.nextInt((19 - 0) + 1);
        } while (board[y][x] != null);
        this.board = board;
        this.bugs = bugs;
        board[y][x] = this;
    }

    public Bug(int nx, int ny, Organism[][] board, ArrayList bugs) {
        x = nx;
        y = ny;
        this.board = board;
        this.bugs = bugs;
        board[y][x] = this;
    }

    void move() {
        if (eat()) {
            daiCounter = 3;
            return;
        }
        int choice = 0;
        int nx = x;
        int ny = y;
        if ((x == 0 || board[y][x - 1] != null) && (x == 19 || board[y][x + 1] != null)
                && ((y == 0 || board[y - 1][x] != null) && (y == 19 || board[y + 1][x] != null))) {
            return;
        }
        choice = r.nextInt((3 - 0) + 1);
        // System.out.println("Moving Bug " + choice);
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
                /*if ((x == 0 || board[y][x - 1] != null) && (x == 19 || board[y][x + 1] != null)
                        && (y == 0 || board[y - 1][x] != null) && (y == 19 || board[y + 1][x] != null)) {
                    // System.out.println("NO PLACE TO BREED!!!!!!!!!!!!");
                    return;
                }*/////
            } else {
                loop = false;
            }
        } while (loop == true);
        Bug b = new Bug(nx, ny, board, bugs);
        bugs.add(b);
    }

    boolean eat() {
        // System.out.println("Old ant size: " + main.ants.size());
        if (y != 0 && board[y - 1][x] != null && board[y - 1][x].toString().compareTo("A") == 0) {
            main.ants.remove(board[y - 1][x]);
            board[y][x] = null;
            board[y - 1][x] = this;
            y = y - 1;
            // System.out.println("Nom Nom ant size: " + main.ants.size());
            return true;
        } else if (x != 19 && board[y][x + 1] != null && board[y][x + 1].toString().compareTo("A") == 0) {
            main.ants.remove(board[y][x + 1]);
            board[y][x] = null;
            board[y][x + 1] = this;
            x = x + 1;
            // System.out.println("Nom Nom ant size: " + main.ants.size());
            return true;
        } else if (y != 19 && board[y + 1][x] != null && board[y + 1][x].toString().compareTo("A") == 0) {
            main.ants.remove(board[y + 1][x]);
            board[y][x] = null;
            board[y + 1][x] = this;
            y = y + 1;
            // System.out.println("Nom Nom ant size: " + main.ants.size());
            return true;
        } else if (x != 0 && board[y][x - 1] != null && board[y][x - 1].toString().compareTo("A") == 0) {
            main.ants.remove(board[y][x - 1]);
            board[y][x] = null;
            board[y][x - 1] = this;
            x = x - 1;
            // System.out.println("Nom Nom ant size: " + main.ants.size());
            return true;
        } else {
            // System.out.println("Today Puasa");
            return false;

        }
    }

    void update() {
        move();
        // System.out.println("Counter: " + counter);
        counter--;
        daiCounter--;
        if (daiCounter == 0) {
            main.bugs.remove(this);
            board[y][x] = null;
            // System.out.println("No Food, RIP in pieces");
        }
        // System.out.println("Counter: " + counter);
        if (counter == 0) {
            breed();
            counter = 8;
        }
    }

    public String toString() {
        return "B"; // board will show a "B" for every bug object
    }
}
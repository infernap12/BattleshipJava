package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Player me = new Player();
        me.printDisplayboard();

        System.out.println("Enter the coordinates of the ship:");
        Coord start = null;
        Coord end = null;
        try (Scanner scanner = new Scanner(System.in)) {
            if (scanner.hasNext("[A-Ja-j](10|[1-9])")) {
                start = new Coord(scanner.next());
            } else {
                System.out.println("Error");
                System.exit(0);
            }
            if (scanner.hasNext("[A-Ja-j](10|[1-9])")) {
                end = new Coord(scanner.next());
            } else {
                System.out.println("Error");
                System.exit(0);
            }
        }
        ArrayList<Coord> ship = me.getParts(start, end);
        System.out.println("Length: %d".formatted(ship.toArray().length));
        System.out.print("Parts: ");
        for (Coord loc : ship) {
            System.out.print(loc.name + " ");
        }
        System.out.println();

    }


}

class Player {
    char[][] board;
    ship[] ships;

    void printDisplayboard() {
        char[] rowHeaders = "ABCDEFGHIJ".toCharArray();
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();//new line

        //print board with letter headers
        for (int i = 0; i < board.length; i++) {
            System.out.print(rowHeaders[i] + " ");// print that rows letter
            for (int j = 0; j < board[i].length; j++) {// iterates that row and whacks it down
                System.out.print(board[i][j] + " ");
            }
            System.out.println(); //new line
        }
    }

    public Player() {
        this.board = new char[10][10];

        for (char[] row : board) {
            Arrays.fill(row, '~');
        }
    }

    private void setBoardChar(String input, char ch) {
        Coord loc = new Coord(input);
        board[loc.y][loc.x] = ch;
    }

    private char getBoardChar(String input) {
        Coord loc = new Coord(input);
        return board[loc.y][loc.x];
    }

    public static ArrayList<Coord> getParts(Coord[] coords) {
        return getParts(coords[0], coords[1]);
    }

    public static ArrayList<Coord> getParts(Coord start, Coord end) {
        boolean isVertical = start.x == end.x;
        boolean isHorizontal = start.y == end.y;

        ArrayList<Coord> parts = new ArrayList<>();


        // Check if the movement is along the same row or column
        if (isHorizontal) {
            // Same row, different columns
            for (int i = Math.min(start.x, end.x); i <= Math.max(start.x, end.x); i++) {
                parts.add(new Coord(start.y, i));
            }
        } else if (isVertical) {
            // Same column, different rows
            for (int i = Math.min(start.y, end.y); i <= Math.max(start.y, end.y); i++) {
                parts.add(new Coord(i, start.x));
            }
        } else {
            System.out.println("Error");
            System.exit(01);
        }


        return parts;
    }
}

class ship {
    int length;
    Coord[] parts;
    shipType shipClass;

    public ship(shipType shipClass) {
        this.shipClass = shipClass;
        this.length = this.shipClass.size;
        this.parts = Player.getParts(InputUtils.requestParts());
    }
}

enum shipType {
    AIRCRAFT_CARRIER(5), BATTLESHIP(4), SUBMARINE(3), CRUISER(3), DESTROYER(2);
    final int size;

    shipType(int size) {
        this.size = size;
    }
}

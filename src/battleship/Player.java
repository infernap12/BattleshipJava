package battleship;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    char[][] board;
    Ship[] ships;

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
        ArrayList<Ship> shipArrayList = new ArrayList<>();
        for (Ship.ShipType shipClass : Ship.ShipType.values()) {
            shipArrayList.add(new Ship(shipClass));
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

    public static Coord[] getParts(Coord[] coords) {
        return getParts(coords[0], coords[1]);
    }

    public static Coord[] getParts(Coord start, Coord end) {
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
            System.exit(0);
        }


        return parts.toArray(new Coord[parts.size()]);
    }
}

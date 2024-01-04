package battleship;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    char[][] friendlyBoard;
    char[][] enemyBoard;
    Ship[] ships;
    String name;
    Player opposition;

    Player(String name) {
        this.friendlyBoard = new char[10][10];
        this.enemyBoard = new char[10][10];
        this.name = name;
        for (char[] row : friendlyBoard) {
            Arrays.fill(row, '~');
        }
        for (char[] row : enemyBoard) {
            Arrays.fill(row, '~');
        }
        Player.setupUser(this);

    }

    private static void setupUser(Player player) {
        System.out.printf("%n%s, place your ships on the game field%n%n", player.name);
        player.printBoard(player.friendlyBoard);
        ArrayList<Ship> shipArrayList = new ArrayList<>();

        for (Ship.ShipType shipClass : Ship.ShipType.values()) {
            Coord[] parts;

            System.out.printf("%nEnter the coordinates of the %s (%d cells):%n%n", shipClass.toString(), shipClass.size);

            while (true) {
                parts = InputUtils.requestParts();

                if (parts.length != shipClass.size) {
                    System.out.printf("Error! Wrong length of the %s! Try again:%n%n", shipClass.name());
                } else if (InputUtils.isInCollision(shipArrayList, parts)) {
                    System.out.println("Error! You placed it too close to another one. Try again:\n");
                } else {
                    for (Coord part : parts) {
                        player.setBoardChar(player.friendlyBoard, part, 'O');
                    }
                    break;
                }

            }
            shipArrayList.add(new Ship(shipClass, parts));
            player.ships = shipArrayList.toArray(new Ship[0]);
            player.printBoard(player.friendlyBoard);
        }
    }

    Ship getShipByCoord(Coord coord) {
        for (Ship ship : this.ships) {
            for (Coord part : ship.parts) {
                if (part.name.equals(coord.name)) {
                    return ship;
                }

            }
        }
        return null;
    }

    boolean isLost() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public void Fire(Coord coord) {
        char ch;
        Ship hitShip = opposition.getShipByCoord(coord);
        if (hitShip == null) {
            System.out.println("You missed. Try again:\n");
            ch = 'M';
        } else {
            hitShip.hp = Math.max(hitShip.hp - 1, 0);
            ch = 'x';
            if (hitShip.isSunk()) {
                System.out.println("You sank a ship! Specify a new target:\n");
            } else {
                System.out.println("You hit a ship! Try again:\n");
            }
        }

        for (char[][] board : new char[][][]{opposition.friendlyBoard, enemyBoard}) {
            this.setBoardChar(board, coord, ch);
        }
    }

    void printBoard(char[][] board) {
        char[] rowHeaders = "ABCDEFGHIJ".toCharArray();
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();//new line

        //print friendlyBoard with letter headers
        for (int i = 0; i < board.length; i++) {
            System.out.print(rowHeaders[i] + " ");// print that rows letter
            for (int j = 0; j < board[i].length; j++) {// iterates that row and whacks it down
                System.out.print(board[i][j] + " ");
            }
            System.out.println(); //new line
        }
    }

    private void setBoardChar(char[][] board, Coord loc, char ch) {
        board[loc.y][loc.x] = ch;
    }

    @SuppressWarnings("unused")
    private char getBoardChar(String input) {
        Coord loc = new Coord(input);
        return friendlyBoard[loc.y][loc.x];
    }

    @SuppressWarnings("unused")
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


        return parts.toArray(new Coord[0]);
    }
}

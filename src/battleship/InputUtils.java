package battleship;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class InputUtils {
    static final Scanner scanner = new Scanner(System.in);
    static Coord[] requestParts() {
        Coord start = null;
        Coord end = null;

        while (true) {
                String inputLine = scanner.nextLine().trim();
                if (!inputLine.matches("[A-Ja-j](10|[1-9]) [A-Ja-j](10|[1-9])")) {
                    System.out.println("\nError outside grid\n");
                    scanner.nextLine();
                    continue;
                }
                String[] input = inputLine.split("\\s+");
                start = new Coord(input[0]);
                end = new Coord(input[1]);



            System.out.println();
            boolean validLine = Coord.isNotDiagonal(start, end);

            if (validLine) {
                break;
            }
            System.out.println("Error! Wrong ship location! Try again:\n");
        }

        return Player.getParts(start, end);

    }

    private static Coord requestCoord() {
        try (Scanner scanner = new Scanner(System.in)) {
            if (!scanner.hasNext("[A-Ja-j](10|[1-9])")) {
                System.out.println("Error outside grid");
            }
            return new Coord(scanner.next());
        }
    }

    static boolean isInCollision(ArrayList<Ship> shipArrayList, Coord[] parts) {

        //test if the shilist is null
        for (Ship ship : shipArrayList) {
            for (Coord collision : ship.collision) {
                for (Coord part : parts) {
                    if (Objects.equals(part.name, collision.name)) {
                        return true;
                    }
                }
            }
            //fo each ship, iterate every collisions, then iterate every part, if part == collision return true;Error! You placed it too close to another one. Try again:
        }


        return false;
    }

}

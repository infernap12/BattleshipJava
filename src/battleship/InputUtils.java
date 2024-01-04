package battleship;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class InputUtils {
    static FileHandler fh;

    static {
        try {
            fh = new FileHandler("log.log",true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static final Logger logger = Logger.getLogger(InputUtils.class.getName());
    static final Scanner scanner = new Scanner(System.in);

    static void pause() {
        scanner.nextLine();
    }

    static Coord[] requestParts() {
        Coord start;
        Coord end;

        while (true) {
                String inputLine = scanner.nextLine().trim();

                logger.info(inputLine);
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

    static Coord requestCoord() {
        Coord coord;
        while (true) {
            String inputLine = scanner.nextLine().trim();
            if (!inputLine.matches("[A-Ja-j](10|[1-9])")) {
                System.out.println("\nError outside grid\n");
                scanner.nextLine();
                continue;
            }
            coord = new Coord(inputLine);
            break;

        }
        return coord;
    }

    static boolean isInCollision(ArrayList<Ship> shipArrayList, Coord[] parts) {

        for (Ship ship : shipArrayList) {
            for (Coord collision : ship.collision) {
                for (Coord part : parts) {
                    if (Objects.equals(part.name, collision.name)) {
                        return true;
                    }
                }
            }
        }


        return false;
    }

}

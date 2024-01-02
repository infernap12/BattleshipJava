package battleship;

class Coord {
    int x;
    int y;
    String name;

    Coord(String input) {
        this.name = input.toUpperCase();

        this.x = Integer.parseInt(this.name.substring(1)) - 1;
        this.y = Character.toUpperCase(this.name.charAt(0)) - 65;
    }

    Coord(int y, int x) {
        this.x = x;
        this.y = y;

        name = String.valueOf((char) (this.y + 65)) + (this.x + 1);

    }


    static boolean isValidLine(Coord start, Coord end, Ship.ShipType shipClass) {
        if (start == null || end == null) {
            return false;
        }
        boolean vertical = isVertical(start, end);
        boolean horizontal = isHorizontal(start, end);
        if (!vertical && !horizontal) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
        boolean correctSize = isValidSize(start,end, shipClass.size);
        if (!correctSize) {
            System.out.printf("Error! Wrong length of the %s! Try again:%n%n", shipClass.name());
            return false;
        }

        return true;
    }

    private static boolean isValidSize(Coord start, Coord end, int size) {
        if (isHorizontal(start,end)) {
          return (Math.abs(start.x - end.x) + 1) == size;
        }

        return true;
    }

    private static boolean isNotDiagonal(Coord start, Coord end) {
        return isVertical(start, end) || isHorizontal(start, end);
    }
    private static boolean isHorizontal(Coord start, Coord end) {
        return start.y == end.y;
    }

    private static boolean isVertical(Coord start, Coord end) {
        return start.x == end.x;
    }

}

package battleship;

public class Coord {
    final int x;
    final int y;
    final String name;

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


    static boolean isNotDiagonal(Coord start, Coord end) {
        return isVertical(start, end) || isHorizontal(start, end);
    }

    static boolean isHorizontal(Coord start, Coord end) {
        return start.y == end.y;
    }

    static boolean isVertical(Coord start, Coord end) {
        return start.x == end.x;
    }

}

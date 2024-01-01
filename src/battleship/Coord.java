package battleship;

public class Coord {
    int x;
    int y;
    String name;

    public Coord(String input) {
        this.name = input.toUpperCase();

        this.x = Integer.parseInt(this.name.substring(1)) - 1;
        this.y = Character.toUpperCase(this.name.charAt(0)) - 65;
    }

    public Coord(int y, int x) {
        this.x = x;
        this.y = y;

        name = String.valueOf((char) (this.y + 65)) + (this.x + 1);

    }
}

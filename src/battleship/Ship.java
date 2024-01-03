package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

class Ship {
    final int length;
    int hp;
    Coord[] parts;
    Coord[] collision;
    final ShipType shipClass;

    public Ship(ShipType shipClass, Coord[] parts) {
        this.shipClass = shipClass;
        this.length = this.shipClass.size;
        this.hp = this.length;
        this.parts = parts;
        this.collision = getCollision(parts[0], parts[parts.length - 1]);
    }


    static Coord[] getCollision(Coord start, Coord end) {
        ArrayList<Coord> collisionParts = new ArrayList<>();
        for (int j = Math.max((Math.min(start.y, end.y) - 1), 0); j <= Math.min((Math.max(start.y,end.y) + 1), 9); j++) {
            for (int i = Math.max((Math.min(start.x, end.x)) - 1, 0); i <= Math.min((Math.max(start.x, end.x) + 1), 9); i++) {
                collisionParts.add(new Coord(j, i));
            }
        }
        return collisionParts.toArray(new Coord[0]);
    }

    enum ShipType {
        AIRCRAFT_CARRIER(5), BATTLESHIP(4), SUBMARINE(3), CRUISER(3), DESTROYER(2);
        final int size;

        ShipType(int size) {
            this.size = size;
        }

        static final int MAX_SIZE;
        static final int MIN_SIZE;

        // Static initialization block to determine the largest and smallest sizes
        static {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (ShipType shipType : ShipType.values()) {
                if (shipType.size > max) {
                    max = shipType.size;
                }
                if (shipType.size < min) {
                    min = shipType.size;
                }
            }
            MAX_SIZE = max;
            MIN_SIZE = min;
        }

        @Override
        public String toString() {
            // Replace all underscores with spaces and capitalize the first letter of each word
            return Arrays.stream(this.name().split("_"))
                    .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                    .collect(Collectors.joining(" "));
        }
    }


}
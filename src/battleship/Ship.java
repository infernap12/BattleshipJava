package battleship;

class Ship {
    int length;
    Coord[] parts;
    ShipType shipClass;

    public Ship(ShipType shipClass) {
        this.shipClass = shipClass;
        this.length = this.shipClass.size;
        this.parts = Player.getParts(InputUtils.requestParts(this.shipClass));
        Player.spawnShip(this);
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

}
}
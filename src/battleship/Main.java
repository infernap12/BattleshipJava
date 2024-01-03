package battleship;


public class Main {


    public static void main(String[] args) {
        Player me = new Player();
        me.printDisplayboard();

        Player.setupUser(me);

        System.out.println("The game starts!\n");

        me.printDisplayboard();

        System.out.println("Take a shot!\n");

        me.receiveFire(InputUtils.requestCoord());

        me.printDisplayboard();

    }
}
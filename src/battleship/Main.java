package battleship;


public class Main {


    public static void main(String[] args) {
        Player me = new Player();
        me.printDisplayboard();

        Player.setupUser(me);

    }
}
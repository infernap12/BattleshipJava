package battleship;


public class Main {


    public static void main(String[] args) {
        Player me = new Player();
        me.printBoard(me.friendlyBoard);

        Player.setupUser(me);

        System.out.println("The game starts!\n");

        me.printBoard(me.enemyBoard);

        System.out.println("Take a shot!\n");

        me.receiveFire(InputUtils.requestCoord());

        me.printBoard(me.friendlyBoard);
        me.printBoard(me.enemyBoard);

    }
}
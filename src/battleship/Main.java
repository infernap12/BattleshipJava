package battleship;


public class Main {


    public static void main(String[] args) {
        Player me = new Player();
        me.printBoard(me.friendlyBoard);

        Player.setupUser(me);

        System.out.println("The game starts!\n");

        me.printBoard(me.enemyBoard);


        do {
            playRound(me);
        } while (!me.isLost());
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private static void playRound(Player player) {
        System.out.println("Take a shot!\n");
        player.receiveFire(InputUtils.requestCoord());
        player.printBoard(player.enemyBoard);

    }
}
package battleship;


public class Main {

    static final String P1 = "Player 1";
    static final String P2 = "Player 2";
    static Player player1;
    static Player player2;
    static Player currentPlayer;



    public static void main(String[] args) {



        player1 = new Player(P1);

        System.out.println("\nPress Enter and pass the move to another player");
        InputUtils.pause();
        player2 = new Player(P2);
        player1.opposition = player2;
        player2.opposition = player1;
        currentPlayer = player2;


        do {
            pass(); //players swap
            playRound(currentPlayer);// current player plays a round
        } while (!currentPlayer.opposition.isLost());// test the opposing player, who just got shot at

        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private static void playRound(Player player) {
        player.printBoard(player.enemyBoard);
        System.out.println("---------------------");
        player.printBoard(player.friendlyBoard);
        System.out.printf("%n%s, it's your turn:%n", player.name);
        player.Fire(InputUtils.requestCoord());
        player.printBoard(player.enemyBoard);

    }

    private static void pass() {
        System.out.println("\nPress Enter and pass the move to another player");
        InputUtils.pause();
//        System.out.println("...");
        if (currentPlayer.name.equals(player1.name)) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
    }
}


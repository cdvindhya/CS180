import java.util.Random;
import java.util.Scanner;

public class Uno { //COUNTING PLAYERS WIL START FROM ZERO!!!

    /**
     * nPlayers: Number of players in the game
     * player: Current Player
     * playerHand[][]: List of Players and their hands
     * currentCard: Keeps track of the card on top of the discard pile
     * CARDS_IN_DECK: Total cards in the deck
     * START_HAND: Initial hand size
     * direction: Direction of the play
     * winner: Stores the winner of game
     */
    public static int nPlayers;
    public static int currentPlayer;
    public static UnoCard playerHand[][];
    public static UnoCard currentCard;
    public static final int CARDS_IN_DECK = 112;
    public static final int START_HAND = 7;
    public static boolean direction = true; // true for regular direction, false for reverse direction
    public static int winner;

    /**
     * @param nPlayers
     */
    public Uno(int nPlayers) {
        this.nPlayers = nPlayers;
        playerHand = new UnoCard[nPlayers][CARDS_IN_DECK]; // columns have to be more than start hand
        currentPlayer = 0;
    }

    /**
     * Iterates through the playerHand array and draws a new random card
     * using playerHand[i][j] = new UnoCard until each player’s hand is
     * of size START_HAND.
     * Fill each player’s hand before moving to the next one.
     */
    public static void distributeCards() {
        playerHand = new UnoCard[nPlayers][CARDS_IN_DECK];
        Random rand = new Random();
        for (int i = 0; i < nPlayers; i++) { // playerHand.length
            for (int j = 0; j < START_HAND; j++) {
                playerHand[i][j] = new UnoCard();
            }
        }
    }

    /**
     * Removes the card that was just played from the player’s hand.
     * Accepts the player (player) and card (index) as arguments.
     * <p>
     * If we were to remove player 1’s first card, at playerHand[0][0],
     * we would need to shift playerHand[0][1] into the playerHand[0][0]
     * spot and shift any other cards the player had down by one index as well.
     *
     * @param player
     * @param index
     */
    public static void removeCardFromHand(int player, int index) {
        for (int i = index; i < cardsLeft(player) - 1; i++) {
            playerHand[player][i] = playerHand[player][i + 1];
        }
        playerHand[player][cardsLeft(player) - 1] = null;
    }

    /**
     * Prints the player’s hand in the format:
     * <p>
     * 1: Color Card
     * <p>
     * 2: Color Card
     * <p>
     * 3: Wild
     * <p>
     * 0: Draw a card | Skip turn
     * <p>
     * Note: Add 1 to the currentPlayer value when printing the
     * playerHand [Remember that a user will start counting from 1].
     *
     * @param player
     */
    public static void printHandOfPlayer(int player) {
        /**
         * Failed the Uno printHandOfPlayer test. Print the player's
         * entire hand in the format specified in the handout. - draw card...
         */
        int playerNo = player + 1;
        System.out.println("Player " + playerNo + "'s Hand:");
        System.out.println();
        for (int i = 0; i < playerHand[player].length; i++) {
            if (playerHand[player][i] != null) {
                playerNo = i + 1;
                System.out.println(playerNo + ": " + playerHand[player][i].color + " "
                        + playerHand[player][i].card);
                System.out.println();
            }
            if (playerHand[player][i] == null) {
                break;
            }
        }
        System.out.println("0: Draw a card | Skip turn");
        System.out.println();
    }

    /**
     * Counts the number of cards left in the player’s hand.
     *
     * @param player
     * @return
     */
    public static int cardsLeft(int player) {
        int sum = 0;
        for (int i = 0; i < playerHand[player].length; i++) {
            if (playerHand[player][i] != null) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Handles when a player puts down a Wild card.
     * Must prompt the current player to change the current card’s color.
     */
    public static void handleWild(Scanner s) {
        boolean wild = true;
        while (true) {
            wild = false;
            System.out.println("Played Wild Card");
            System.out.println("Color changes to?");
            System.out.println("1. Red");
            System.out.println("2. Green");
            System.out.println("3. Yellow");
            System.out.println("4. Blue");
            int wildAnswer = s.nextInt();
            if (wildAnswer == 1) {
                currentCard.color = COLOR.RED;
                currentCard.card = CARD.WILD;
                System.out.println("Played RED WILD.");
                break;
            } else if (wildAnswer == 2) {
                currentCard.color = COLOR.GREEN;
                currentCard.card = CARD.WILD;
                System.out.println("Played GREEN WILD.");
                break;
            } else if (wildAnswer == 3) {
                currentCard.color = COLOR.YELLOW;
                currentCard.card = CARD.WILD;
                System.out.println("Played YELLOW WILD.");
                break;
            } else if (wildAnswer == 4) {
                currentCard.color = COLOR.BLUE;
                currentCard.card = CARD.WILD;
                System.out.println("Played BLUE WILD.");
                break;
            } else {
                System.out.println("Invalid choice!");
                wild = true;
            }
        }

    }


    /**
     * Increments currentPlayer. It is set to 0 when currentPlayer = nPlayers-1.
     * You can use the direction variable to iterate in a counter-clockwise
     * direction when reverse is played.
     */
    public static void nextPlayer() {
        if (direction) {
            if (currentPlayer == nPlayers - 1) {
                currentPlayer = 0;
            } else currentPlayer = currentPlayer + 1;
        } else if (!direction) {
            if (currentPlayer == 0) {
                currentPlayer = nPlayers - 1;
            } else {
                currentPlayer = currentPlayer - 1;
            }
        }
    }

    /**
     * Handles when a player puts down a Wild Draw 4 card.
     * Must prompt the current player to change the current card’s color.
     */
    public static void handleWildDraw4(Scanner s) { //Scanner s
        /**
         * Failed the Uno handleWildDraw4 test. Ensure you performing the handleWild
         * functionality, adding 4 cards to the next player's hand, and skipping their turn.
         */
        //Scanner s = new Scanner(System.in);
        boolean wildDrawFour = true;
        while (wildDrawFour) {
            wildDrawFour = false;
            System.out.println("Played Draw 4 Wild Card.");
            System.out.println("Color changes to?");
            System.out.println("1. Red");
            System.out.println("2. Green");
            System.out.println("3. Yellow");
            System.out.println("4. Blue");
            int drawWildFourAns = s.nextInt();
            if (drawWildFourAns == 1) {
                currentCard.color = COLOR.RED;
                currentCard.card = CARD.WILD_DRAW4;
                System.out.println("Played " + currentCard.color + " " + currentCard.card + ".");
                break;
            } else if (drawWildFourAns == 2) {
                currentCard.color = COLOR.GREEN;
                currentCard.card = CARD.WILD_DRAW4;
                System.out.println("Played " + currentCard.color + " " + currentCard.card + ".");
                break;
            } else if (drawWildFourAns == 3) {
                currentCard.color = COLOR.YELLOW;
                currentCard.card = CARD.WILD_DRAW4;
                System.out.println("Played " + currentCard.color + " " + currentCard.card + ".");
                break;
            } else if (drawWildFourAns == 4) {
                currentCard.color = COLOR.BLUE;
                currentCard.card = CARD.WILD_DRAW4;
                System.out.println("Played " + currentCard.color + " " + currentCard.card + ".");
                break;
            } else {
                System.out.println("Invalid choice!");
                wildDrawFour = true;
            }
        }
        nextPlayer();
        for (int i = 0; i < playerHand[currentPlayer].length; i++) {
            if (playerHand[currentPlayer][i] == null) {
                playerHand[currentPlayer][i] = new UnoCard();
                playerHand[currentPlayer][i + 1] = new UnoCard();
                playerHand[currentPlayer][i + 2] = new UnoCard();
                playerHand[currentPlayer][i + 3] = new UnoCard();
                break;
            }
        }
        /**for (int i = 0; i < 4; i++) {
         playerHand[currentPlayer][cardsLeft(currentPlayer) + i] = new UnoCard();
         }**/
        nextPlayer();
    }

    /**
     * Handles when a player puts down a Skip card.
     */
    public static void handleSkip() {
        nextPlayer();
        System.out.println("Player " + currentPlayer + "'s turn had been skipped!");
        nextPlayer();
    }

    /**
     * Handles when a player puts down a Reverse card.
     */
    public static void handleReverse() {
        if (direction) {
            direction = false;
        } else {
            direction = true;
        }
        System.out.println("The game has been reversed!");
    }

    /**
     * Handles when a player puts down a Draw2 card.
     */
    public static void handleDraw2() {
        nextPlayer();
        /**for (int i = 0; i < 2; i++) {
         playerHand[currentPlayer][cardsLeft(currentPlayer) + i] = new UnoCard();
         }**/
        for (int i = 0; i < playerHand[currentPlayer].length; i++) {
            if (playerHand[currentPlayer][i] == null) {
                playerHand[currentPlayer][i] = new UnoCard();
                playerHand[currentPlayer][i + 1] = new UnoCard();
                break;
            }
        }
        nextPlayer();
    }

    /**
     * Refer to documentation on wiki site.
     *
     * @param args
     */
    public static void main(String[] args) {
        // variables
        int winner;
        int cardNo;
        int sum = 0;
        currentPlayer = 0;
        // end of initialized variable
        Scanner scan = new Scanner(System.in);
        System.out.println("How many Players are there?");
        nPlayers = scan.nextInt();
        System.out.println("Shuffling and Adding Cards to Hands...");
        distributeCards();
        //first card
        System.out.println("First Card:");
        UnoCard firstCard = new UnoCard();
        boolean x = true;
        while (x) {
            if ((firstCard.card != CARD.WILD && firstCard.card != CARD.REVERSE
                    && firstCard.card != CARD.SKIP && firstCard.card != CARD.DRAW_2
                    && firstCard.card != CARD.WILD_DRAW4)) {
                System.out.println(firstCard.color + " " + firstCard.card);
                System.out.println();
                currentCard = firstCard;
                x = false;
            } else {
                firstCard = new UnoCard();
            }
        }
        //first card selected
        // user game play - break when cards left is 1
        boolean loop = true;
        boolean isZero = true;
        while (loop) {
            loop = false;
            //for (int p = currentPlayer; p < nPlayers; p++) { // loop, increment current Player
            for (int i = 0; i < nPlayers; i++) {
                sum = 0;
                for (int j = 0; j < playerHand[i].length; j++) {
                    if (playerHand[i][j] != null) {
                        sum = sum + 1;
                    }
                }
                if (sum == 1) {
                    winner = i + 1;
                    System.out.println("Player " + winner + " wins!");
                    //currentPlayer = nPlayers;
                    return;
                }
                //break;
            }
            if (sum == 1) {
                break;
            }
            if (currentPlayer >= nPlayers) {
                break;
            }
            printHandOfPlayer(currentPlayer);
            System.out.println("Your choice?");
            System.out.println();
            //cardNo = 0;
            /**if(scan.hasNextInt()) {
                cardNo = scan.nextInt(); }**/
            cardNo = scan.nextInt();
            //if(scan.hasNext()) { // to handle NoSuchElementException
            if (cardNo == 0) { // draw a card
                if(isZero) {
                    playerHand[currentPlayer][cardsLeft(currentPlayer)] = new UnoCard();
                    isZero = false;
                    loop = true;
                } else {
                    nextPlayer();
                    isZero = true;
                    loop = true;
                }
            } else {
                if (cardNo > (cardsLeft(currentPlayer)) || cardNo < 0) {
                    System.out.println("INVALID CHOICE!");
                    System.out.println("Current Card:");
                    System.out.println(currentCard.color + " " + currentCard.card);
                    System.out.println();
                    loop = true;
                } else if (currentCard.isPlayable(playerHand[currentPlayer][cardNo - 1])) {
                    currentCard = playerHand[currentPlayer][cardNo - 1];
                    if (currentCard.isWild()) {
                        handleWild(scan);
                        removeCardFromHand(currentPlayer, cardNo - 1);
                        nextPlayer();
                    } else if (currentCard.isWildDraw4()) {
                        removeCardFromHand(currentPlayer, cardNo - 1);
                        //handleWildDraw4(scan);
                        handleWildDraw4(scan);
                        //nextPlayer();
                    } else if (currentCard.isDraw2()) {
                        removeCardFromHand(currentPlayer, cardNo - 1);
                        handleDraw2();
                    } else if (currentCard.isReverse()) {
                        removeCardFromHand(currentPlayer, cardNo - 1);
                        handleReverse();
                    } else if (currentCard.isSkip()) {
                        removeCardFromHand(currentPlayer, cardNo - 1);
                        handleSkip();
                    } else {
                        System.out.println("Played " + currentCard.color + " " + currentCard.card);
                        System.out.println();
                        removeCardFromHand(currentPlayer, cardNo - 1);
                        nextPlayer();
                    }
                    loop = true;
                } else {
                    System.out.println("INVALID CHOICE!");
                    System.out.println();
                    System.out.println("Current Card:");
                    System.out.println(currentCard.color + " " + currentCard.card);
                    System.out.println();
                    loop = true;
                }
            }
        }
    }

}
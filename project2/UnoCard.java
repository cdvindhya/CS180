import java.util.Random;

public class UnoCard {

    public COLOR color;
    public CARD card;

    /**
     * Uses Random rand = new Random();
     * Generates a random int between 0 and 14 to select the next card.
     * Generates a random int between 0 and 3 to select the next color.
     * Initializes card and color to the randomly chosen card and color.
     */
    public UnoCard() {
       Random rand  = new Random();
       int randomIntColor = rand.nextInt(4);
       int randomIntCard = rand.nextInt(15);
       this.color = COLOR.values()[randomIntColor];
       this.card = CARD.values()[randomIntCard];
    }

    /**
     * Initializes card and color to the arguments passed in.
     * @param card
     * @param color
     */
    public UnoCard(CARD card, COLOR color) {
        this.card = card;
        this.color = color;
    }

    /**
     * Checks if the current card is Wild.
     * @return boolean
     */
    public boolean isWild() {
        if(this.card == CARD.WILD){
            return true;
        }
        return false;
    }

    /**
     * Checks if the current card is Wild Draw 4.
     * @return boolean
     */
    public boolean isWildDraw4() {
        if(this.card == CARD.WILD_DRAW4){
            return true;
        }
        return false;
    }

    /**
     * Checks if the current card is Draw 2.
     * @return boolean
     */
    public boolean isDraw2() {
        if(this.card == CARD.DRAW_2){
            return true;
        }
        return false;
    }

    /**
     * Checks if the current card is Reverse.
     * @return boolean
     */
    public boolean isReverse() {
        if(this.card == CARD.REVERSE){
            return true;
        }
        return false;
    }

    /**
     * Checks if the current card is Skip.
     * @return boolean
     */
    public boolean isSkip() {
        if(this.card == CARD.SKIP){
            return true;
        }
        return false;
    }

    /**
     * Verifies that the nextCard is playable.
     * Must be one of the following:
     * WILD_DRAW4
     * WILD
     * The same card as the card the method is being called on.
     * The same color as the card the method is being called on.
     * @param nextCard
     * @return boolean
     */
    public boolean isPlayable(UnoCard nextCard) {
        if(nextCard.card == CARD.WILD_DRAW4){
            return true;
        }
        else if(nextCard.card == CARD.WILD){
            return true;
        }
        else if(nextCard.card == this.card){
            return true;
        }
        else if(nextCard.color == this.color){
            return true;
        }
        return false;
    }

}

/*
 * Austin Yuille
 * F 11:00-11:50
 * Lab7 - Card Game
 * 
 * 
 * Object for the Deck.java class
 */
public class Card {
	String suit;
	int rank;

	public Card() {
		suit = "Spade";
		rank = 1;
	}
	
	public Card(String suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}
}

/*
 * Austin Yuille
 * F 11:00-11:50
 * Lab7 - Card Game
 * 
 * Makes a card game with the following rules using a stack of cards
 * 
 * Rules:
 * 1) There are two players.
 * 2) A deck of 52 cards is used. The cards are in 4 suits of identical numbers that range from 1-13.
 * 3) Both players draw two cards and they find the difference between their cards.
 * 4) The players continue to draw cards until the difference of their hand is between 4 and 10(inclusive)
 * 5) Once both players have a score between 4 and 10, they compare scores and the lower score wins.
 * 6) If the players tie, then they repeat steps 3-5.
 */


import java.util.Random;
import java.util.Scanner;

public class Deck {
	// keeps track of the top value
	int top = -1;

	// sets the max stack size
	int max = 52;

	// makes an array of cards
	Card[] cards = new Card[max];

	public static void main(String[] args) {
		Deck deck = new Deck();

		deck.makeDeck();
		deck.printDeck();

		Deck shufDeck = shuffleDeck(deck);
		shufDeck.top = 51;
		shufDeck.printDeck();

		shufDeck.playGame();

	}

	// checks if the deck is empty
	// used when pulling a card
	public boolean isEmpty() {
		return (top < 0);
	}

	// checks if the stack is full
	// used when pushing a value onto the stack
	public boolean isFull() {
		return (top >= max - 1);
	}

	// pushes a card onto the deck
	public void push(Card card) {
		// if there is room in the deck then it will add a card
		if (!isFull()) {

			// keeps track of the top of the deck
			top++;

			// adds the card at the top of the deck
			cards[top] = card;

			// lets the user know if the deck is full
		} else {
			System.out.println("The deck is full so the card can't be added.");
		}
	}

	// retrieves and removes the top card
	public Card pop() {
		// if the deck isn't empty it will return the top card
		// it will also decrease the top counter meaning the card
		// before it is now the new top card
		if (!isEmpty()) {
			return cards[top--];

			// if the deck is empty it returns a blank card
		} else {
			System.out.println("The deck is empty. End of game.");
			Card blank = new Card("None", 0);
			return blank;
		}
	}

	// makes a deck of 52 unique cards
	// 4 suits, 13 ranks
	public void makeDeck() {
		Card c;
		// adds all of the Spades cards ranks 1-13
		for (int i = 1; i <= 13; i++) {
			c = new Card("Spades", i);
			push(c);
		}
		// adds all of the Hearts cards ranks 1-13
		for (int i = 1; i <= 13; i++) {
			c = new Card("Hearts", i);
			push(c);
		}
		// adds all of the Clubs cards ranks 1-13
		for (int i = 1; i <= 13; i++) {
			c = new Card("Clubs", i);
			push(c);
		}
		// adds all of the Diamonds cards ranks 1-13
		for (int i = 1; i <= 13; i++) {
			c = new Card("Diamonds", i);
			push(c);
		}
	}

	// prints the deck from the top down
	public void printDeck() {
		System.out.println("Deck: ");
		int a = top;
		// prints the deck
		while (top >= 0) {
			System.out.println(cards[top].rank + " of " + cards[top].suit + ", ");
			top--;
		}
		top = a;
		System.out.println();
	}

	// finds the user's score
	public int findUserScore() {
		if (!isEmpty()) {
			
			// creates a variable for the user's score
			int scoreU = 0;

			// finds the user's score
			do {
				// pulls two cards for the user
				Card card1 = pop();
				Card card2 = pop();
				
				if(card1.suit == "None" || card2.suit == "None"){
					return -1;
				}
				
				// prints the user's cards
				System.out.println("User's cards: " + card1.rank + " of " + card1.suit + " and " + card2.rank + " of " + card2.suit);
				
				//calculates the scores
				if (card1.rank >= card2.rank) {
					scoreU = card1.rank - card2.rank;
					System.out.println("User's score: " + (scoreU));
				} else if (card2.rank > card1.rank) {
					scoreU = card2.rank - card1.rank;
					System.out.println("User's score: " + (scoreU));
				}

			// continues to draw cards for the user until the score is between 10 and 4
			} while (scoreU > 10 || scoreU < 4);

			// prints and returns the score
			System.out.println("User's final score: " + (scoreU));
			System.out.println();
			return scoreU;
		}
		return -1;
	}

	// finds the computer's score
	public int findCompScore() {
		if (!isEmpty()) {

			// creates a variable for the computer's score
			int scoreC = 0;

			// finds the computer's score
			do {
				// pulls two cards for the computer
				Card card3 = pop();
				Card card4 = pop();
				if(card3.suit == "None" || card4.suit == "None"){
					return -1;
				}
				// prints the computer's cards
				System.out.println("Computer's cards: " + card3.rank + " of " + card3.suit + " and " + card4.rank + " of " + card4.suit);
				
				//calculates the score
				if (card3.rank >= card4.rank) {
					scoreC = card3.rank - card4.rank;
					System.out.println("Computer's score: " + (scoreC));
				} else if (card4.rank > card3.rank) {
					scoreC = card4.rank - card3.rank;
					System.out.println("Computer's score: " + (scoreC));
				}

			// continues to draw cards for the computer until the score is between 10 and 4
			} while (scoreC > 10 || scoreC < 4);

			// prints and returns the score
			System.out.println("Computer's final score: " + (scoreC));
			System.out.println();
			return scoreC;
		}
		return -1;
	}

	// compares the user score and comp score to find a winner
	public void findWinner(int scoreU, int scoreC) {
		if (!isEmpty()) {
			// if scores are equal
			if (scoreU == scoreC) {

				// redraws cards
				System.out.println("The scores are the same. Redrawing cards.");
				System.out.println();
				findWinner(findUserScore(), findCompScore());

				// if the users score is lower, user wins
			} else if (scoreU < scoreC) {
				System.out.println("The user wins!");
				System.out.println();

				// if the computer's score is lower, computer wins
			} else if (scoreC < scoreU) {
				System.out.println("The computer wins!");
				System.out.println();
			}
		}
	}

	// plays the game
	public void playGame() {
		// sets up input from the user
		Scanner sc = new Scanner(System.in);

		// plays the game
		findWinner(findUserScore(), findCompScore());

		// keeps asking the user if they want to play again
		// asks until they say no or the deck is empty
		System.out.println("Would you like to play again? (1 for yes/0 for no)");
		int ans = sc.nextInt();
		while (ans == 1 && findUserScore() != -1 && findCompScore() != -1) {
			System.out.println("yes");
			findWinner(findUserScore(), findCompScore());
			System.out.println("Would you like to play again? (1 for yes/0 for no)");
			ans = sc.nextInt();
		}
		sc.close();
	}

	// shuffles the deck
	public static Deck shuffleDeck(Deck deck) {
		// creates a new deck for the shuffled cards
		Deck shufDeck = new Deck();

		// creates a temporary card
		Card c;

		// creates a random generator
		Random rand = new Random();

		// runs for all 52 cards in the deck
		// pulls the top card and puts in a random spot in the new deck
		for (int i = 0; i < 52; i++) {

			// pulls the top card and temporarily stores it
			c = deck.pop();

			// makes a random int from 0-51
			int r = rand.nextInt(52);
			int j = r;

			// if jth element in the new deck is empty then it inserts that card there
			if (shufDeck.cards[j] == null) {
				shufDeck.cards[j] = c;

				// if it isn't empty then it increases j by one until an empty spot is found
				// then the card is placed there
				// if j reaches 52 it is reset to 0 and keeps increasing
			} else {

				// runs until an empty spot is found
				while (shufDeck.cards[j] != null) {
					j++;

					// resets j to zero
					if (j == 52) {
						j = 0;
					}
				}
				// adds the card when the empty spot is found
				shufDeck.cards[j] = c;
			}
		}
		// returns the shuffled deck
		return shufDeck;
	}

}

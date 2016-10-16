import java.util.List;
import java.io.*;

import org.junit.Test;
import static org.junit.Assert.*;
//import junit.framework.TestCase;
//import junit.framework.Assert;
import org.junit.runner.JUnitCore;

public class testGame {             

	/*
	Unit test for  Bug 1, Balance doesn't increase on winnings
	*/	
	@Test
    public void testBug1() {
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();

        Game game = new Game(d1, d2, d3);
        List<DiceValue> cdv = game.getDiceValues();

   		String name = "Abhishek";
        int balance = 100;
        int limit = 0;
        Player player = new Player(name, balance);
        player.setLimit(limit);
        int bet = 5;

        DiceValue pick = DiceValue.CLUB;

		System.out.printf("\nTEST BUG 1 STARTS\n"); 
		System.out.printf("\nTurn: %s bet %d on %s\n", player.getName(), bet, pick); 
		
		int winnings = game.playRound(player, pick, bet);
				
		System.out.printf("Rolled %s, %s, %s\n",
				cdv.get(0), cdv.get(1), cdv.get(2));
				
		if (winnings > 0) {
			System.out.printf("%s won %d, balance now %d\n\n", player.getName(), winnings, player.getBalance());			
		}
		else {
			System.out.printf("%s lost, balance now %d\n", player.getName(), player.getBalance());
		}
		System.out.printf("TEST BUG 1 ENDS\n\n"); 
		assertEquals(105, player.getBalance());
		
    }
	
	/*
	Unit test for  Bug 2, Player cannot reach betting limit, game ends with $5 balance 
	*/
	@Test
    public void testBug2() {
		String name = "Salman";
        int balance = 50;
        int limit = 0;
		
        Player player = new Player(name, balance);
        player.setLimit(limit);
		Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();
		Game game = new Game(d1, d2, d3);
		List<DiceValue> cdv = game.getDiceValues();
		
        int bet = 5;
		int turn = 0;
		int winCount = 0;
        int loseCount = 0;
		System.out.printf("\nTEST BUG 2 STARTS\n"); 
		while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200)
		{
			turn++;                    
			DiceValue pick = DiceValue.SPADE;
		   
			System.out.printf("Turn %d: %s bet %d on %s\n", turn, player.getName(), bet, pick); 
			
			int winnings = game.playRound(player, pick, bet);
			cdv = game.getDiceValues();
			
			System.out.printf("Rolled %s, %s, %s\n", cdv.get(0), cdv.get(1), cdv.get(2));
			
			if (winnings > 0) {
				System.out.printf("%s won %d, balance now %d\n\n", player.getName(), winnings, player.getBalance());
				winCount++; 
			}
			else {
				System.out.printf("%s lost, balance now %d\n\n", player.getName(), player.getBalance());
				loseCount++;
			}
			
		} //while

		System.out.print(String.format("%d turns later. End Test Game for Bug 2: ", turn));
		System.out.println(String.format("%s now has balance of $%d\n", player.getName(), player.getBalance()));
		System.out.printf("TEST BUG 2 ENDS\n\n"); 
	}
	    
}

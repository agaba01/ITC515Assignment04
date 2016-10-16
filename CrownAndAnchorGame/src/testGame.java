import java.util.List;
import java.io.*;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;

public class testGame {             

	/*
	Unit test for  Bug 1, Balance doesn't increase on winnings
	*/	
	@Test
    public void testBug1() {
        //BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();

        Game game = new Game(d1, d2, d3);
        List<DiceValue> cdv = game.getDiceValues();

   		String name = "Fred";
        int balance = 100;
        int limit = 0;
        Player player = new Player(name, balance);
        player.setLimit(limit);
        int bet = 5;

        DiceValue pick = DiceValue.CLUB;
		   
		System.out.printf("Turn: %s bet %d on %s\n", player.getName(), bet, pick); 
		
		int winnings = game.playRound(player, pick, bet);
				
		System.out.printf("Rolled %s, %s, %s\n",
				cdv.get(0), cdv.get(1), cdv.get(2));
		
		if (winnings > 0) {
			System.out.printf("%s won %d, balance now %d\n\n",
					player.getName(), winnings, player.getBalance());
		}
		else {
			System.out.printf("%s lost, balance now %d\n\n",
					player.getName(), player.getBalance());
		}
		assertEquals(110, player.getBalance());
		
    }
	    
}

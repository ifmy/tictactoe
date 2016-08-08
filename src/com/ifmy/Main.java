package com.ifmy;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ifmy.game.Game;

public class Main {

    public static void main(String[] args) throws Exception {
    	
    	Game game = new Game(3);
        
        while (!game.isOver()) {
            try {
            	render(game);
            } catch (Exception e) {
                printError(e);
            }
        }
        
        if (game.isOver()) {
        	render(game);
        	System.out.println(game.getGameState());
        	System.exit(0);
        }
        
    }

    private static void render(Game game) throws Exception {
   	
        System.out.println("Tic Tac Toe Game");
        System.out.println("====================");
    	
        for (int x = 0; x < game.getSize(); x++) {
        	System.out.print("|");
            for (int y = 0; y < game.getSize(); y++) {
                System.out.printf("%s", game.board[x][y].getCell());
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println(game.getGameState());
        
        inputMove(game);
        
    }

    @SuppressWarnings("resource")
    private static void inputMove(Game game) throws Exception {
    	
        System.out.print("Input coordinates: ");  
		Scanner in = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Column = ");
                int x = in.nextInt();
                System.out.print("Row = ");
                int y = in.nextInt();
                game.move(x - 1, y - 1);
                break;
            } catch (InputMismatchException | NumberFormatException ex) {
                System.out.println(ex.getMessage());
                in.next();
            }
        }
        
    }

    public static void printError(Exception e) {
    	
        System.out.println(e.getMessage());
        System.out.println("Repeat input");
        
    }
    
}

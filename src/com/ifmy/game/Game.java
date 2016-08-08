package com.ifmy.game;

/**
 * Game class.
 * */

public class Game {
	
	public Cell[][] board;
	private int size;
	
	/* Cross start game */
	GameState gameState = GameState.CROSS_MOVE;
	
	public Game(int size) throws Exception {
		setSize(size);
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) throws Exception {
			
		this.size = size;
		board = new Cell[size][size];

		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				board[x][y] = new Cell(CellState.EMPTY);
			}
		}

	}
	
    public void move(int x, int y) throws Exception {
    	
        if (x < 0 || x >= size)
            throw new Exception("Invalid coordinates X");
        if (y < 0 || y >= size)
            throw new Exception("Invalid coordinates Y");
        if (board[x][y].getCell() != CellState.EMPTY)
            throw new Exception("Cell is busy: x = " + x + " y = " + y);
        
        if (gameState == GameState.CROSS_MOVE) {
            board[x][y].setCell(CellState.CROSS);
            gameState = GameState.ZERO_MOVE;
            updateGameState(CellState.CROSS);
        } else if (gameState == GameState.ZERO_MOVE) {
            board[x][y].setCell(CellState.ZERO);
            gameState = GameState.CROSS_MOVE;
            updateGameState(CellState.ZERO);
        }
    }
    
    private void updateGameState(CellState lastMove) {
    	
    	/* Find horizontal */
        for (int y = 0; y < size; y++) {
            boolean line = true;
            for (int x = 0; x < size; x++)
                if (board[x][y].getCell() != lastMove) {
                    line = false;
                }
            if (line) {
            	findWinner(lastMove);
                return;
            }
        }
        
        /* Find vertical */
        for (int x = 0; x < size; x++) {
            boolean line = true;
            for (int y = 0; y < size; y++)
                if (board[x][y].getCell() != lastMove) {
                    line = false;
                }
            if (line) {
            	findWinner(lastMove);
                return;
            }
        }
        
        /* Find on line */
        boolean line = true;
        for (int i = 0; i < size; i++) {
            if (board[i][i].getCell() != lastMove) {
                line = false;
            }
        }
        if (line) {
        	findWinner(lastMove);
            return;
        }
        
        /* Diagonal */
        line = true;
        for (int i = 0; i < size; i++) {
            if (board[i][size - i - 1].getCell() != lastMove) {
                line = false;
            }
        }
        if (line) {
        	findWinner(lastMove);
            return;
        }
        
        /* Standoff inspection */
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                if (board[x][y].getCell() == CellState.EMPTY)
                    return;
        gameState = GameState.STANDOFF;
    }
    
    private void findWinner(CellState lastMove) {
    	
    	if (lastMove == CellState.CROSS) {
    		gameState = GameState.CROSS_WIN;
    	} else if (lastMove == CellState.ZERO) {
    		gameState = GameState.ZERO_WIN;
    	} else if (lastMove == CellState.EMPTY) {
    		throw new RuntimeException("Undefined error!");
    	}
    	
    }
    
    @SuppressWarnings("static-access")
	public boolean isOver() {
        return gameState == gameState.CROSS_WIN ||
        	gameState == gameState.ZERO_WIN ||
        	gameState == gameState.STANDOFF;
    }
    
	public GameState getGameState() {
		return gameState;
	}
}

package com.ifmy.game;

public class Cell {
	
	private CellState cellState = CellState.EMPTY;

    public Cell(CellState cellState) {
        this.cellState = cellState;
    }

	public CellState getCell() {
		return cellState;
	}

	public void setCell(CellState newState) {
        this.cellState = newState;
	}
	
    @Override
    public String toString() {
        return cellState.toString();
    }

}

package com.ifmy.game;

/**
 * State cells on board
 */

public enum CellState {

	EMPTY("*"), ZERO("O"), CROSS("X");

	private String state;

	CellState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return state;
	}

}

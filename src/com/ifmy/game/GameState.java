package com.ifmy.game;

public enum GameState {

	ZERO_MOVE("O move."), ZERO_WIN("O win!"), CROSS_MOVE("X move."), CROSS_WIN("X win!"), STANDOFF("Game standoff!");

	private String state;

	GameState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	@Override
	public String toString() {
		return state.toString();
	}
}

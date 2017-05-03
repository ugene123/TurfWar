package me.armorofglory;

import me.armorofglory.score.ScoreboardManager;

public enum GameState {
	
	LOBBY(true), IN_GAME(false), POST_GAME(false), RESETTING(false);
	
	private boolean canJoin;
	
	private static GameState currentState;
	
	GameState(boolean canJoin) {
		this.canJoin = canJoin;
	}
	
	public boolean canJoin() {
		return canJoin;
	}
	
	public static void setState(GameState state) {
		GameState.currentState = state;
		if(state == LOBBY) {
			ScoreboardManager.setGameState("§aLOBBY");
		} else if(state == IN_GAME) {
			ScoreboardManager.setGameState("§cIN_GAME");
		} else if(state == POST_GAME) {
			ScoreboardManager.setGameState("§cPOST_GAME");
		} else if(state == RESETTING) {
			ScoreboardManager.setGameState("§6RESETTING");
		}
	}
	
	public static boolean isState(GameState state) {
		return GameState.currentState == state;
	}
	
	public static GameState getState(){
		return currentState;
	}
	
}

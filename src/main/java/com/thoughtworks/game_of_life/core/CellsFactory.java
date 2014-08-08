package com.thoughtworks.game_of_life.core;

public class CellsFactory {
	
	public Cell getNewDefaultCell() {
		return new DeadCell();
	}

}

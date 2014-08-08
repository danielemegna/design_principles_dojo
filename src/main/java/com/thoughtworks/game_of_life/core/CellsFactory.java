package com.thoughtworks.game_of_life.core;

import java.util.Map;

public class CellsFactory {

	Class[] allowedCellTypes;

	public CellsFactory(Class[] allowedCellTypes) {
		this.allowedCellTypes = allowedCellTypes;	
		CheckAllowedCellTypes();
	}

	private void CheckAllowedCellTypes()
	{
		for(Class c : allowedCellTypes) {
			if(!Cell.class.isAssignableFrom(c))
				throw new RuntimeException("Wrong class in allowed cell types!");
		}
	}
	
	public Cell getNewDefaultCell() {
		return new DeadCell();
	}

	public Cell advanceCell(Map<Location, Cell> m, Location l)
	{
		Cell c = m.get(l);
		return c.advance(m, l, allowedCellTypes);
	}

}

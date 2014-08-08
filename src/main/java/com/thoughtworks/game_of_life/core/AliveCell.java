package com.thoughtworks.game_of_life.core;

import java.util.Map;

public class AliveCell implements Cell {

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public boolean willBeAlive(int numberOfAliveNeighbours) {
        return numberOfAliveNeighbours == 2 || numberOfAliveNeighbours == 3;
    }

		@Override
		public Cell advance(Map<Location, Cell> m, Location l, Class[] allowedCellTypes)
		{
			int numberOfAliveNeighbours = World.numberOfAliveNeighbours(l, m);
      if(numberOfAliveNeighbours == 2 || numberOfAliveNeighbours == 3)
				return new AliveCell();

			return new DeadCell();	
		}

}

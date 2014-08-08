package com.thoughtworks.game_of_life.core;

import java.util.Map;

public class DeadCell implements Cell {

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public boolean willBeAlive(int numberOfAliveNeighbours) {
        return numberOfAliveNeighbours == 3;
    }

		@Override
		public Cell advance(Map<Location, Cell> m, Location l, Class[] allowedCellTypes)
		{
			int numberOfAliveNeighbours = World.numberOfAliveNeighbours(l, m);
			if(numberOfAliveNeighbours == 3)
				return new AliveCell();

			return new DeadCell();
		}

}

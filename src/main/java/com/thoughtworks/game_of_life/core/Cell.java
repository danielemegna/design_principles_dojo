package com.thoughtworks.game_of_life.core;

import java.util.Map;

public interface Cell {
    boolean isAlive();
    boolean willBeAlive(int numberOfAliveNeighbours);
		Cell advance(Map<Location, Cell> m, Location l, Class[] allowedCellTypes);
}

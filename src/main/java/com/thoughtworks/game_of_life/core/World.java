package com.thoughtworks.game_of_life.core;

import java.util.HashMap;
import java.util.Map;

import static com.thoughtworks.game_of_life.core.Location.allWorldLocations;

public class World {

    public static final int DEFAULT_WIDTH = 10;
    public static final int DEFAULT_HEIGHT = 10;

		CellsFactory cellsFactory;
    Map<Location, Cell> cells;

    public World()  {
				cellsFactory = new CellsFactory();
        cells = initCells();
    }

		private Cell generateNewCellFromCurrent(Cell cell, Location location)
		{
			if(cell.willBeAlive(numberOfAliveNeighbours(location)))
				return new AliveCell();

			return new DeadCell();
		}

    public void advance() {
        Map<Location, Cell> newCells = new HashMap<>();

        for (Location l : allWorldLocations(DEFAULT_WIDTH, DEFAULT_HEIGHT)) {
						Cell c = cells.get(l);
            newCells.put(l, this.generateNewCellFromCurrent(c, l));
        }
        cells = newCells;
    }

    public boolean isEmpty() {
        for (Cell cell: cells.values()) {
            if (cell.isAlive()){
                return false;
            }
        }
        return true;
    }

    public void setLiving(Location location) {
        cells.put(location, new AliveCell());
    }

    public boolean isAlive(Location location) {
        return cells.get(location).isAlive();
    }

    private Map<Location,Cell> initCells() {
        Map<Location, Cell> cells = new HashMap<>();
        for (Location location : allWorldLocations(DEFAULT_WIDTH, DEFAULT_HEIGHT)) {
            cells.put(location, cellsFactory.getNewDefaultCell());
        }
        return cells;
    }

    public int numberOfAliveNeighbours(Location l) {
        int aliveNeighbours = 0;

        for (Location location : l.allNeighbours(DEFAULT_WIDTH, DEFAULT_HEIGHT)){
            if (cells.get(location).isAlive()){
                aliveNeighbours++;
            }
        }
        return aliveNeighbours;
    }

}

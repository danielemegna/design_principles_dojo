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
				cellsFactory = new CellsFactory(
					new Class[]{ 
						AliveCell.class,
						DeadCell.class
					}
				);

        cells = initCells();
    }

    public void advance() {
        Map<Location, Cell> newCells = new HashMap<>();
        for (Location l : allWorldLocations(DEFAULT_WIDTH, DEFAULT_HEIGHT)) {
						Cell newCell = cellsFactory.advanceCell(cells, l);
						newCells.put(l, newCell);
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
        return cells.get(location).isAlive(); } private Map<Location,Cell> initCells() { Map<Location, Cell> cells = new HashMap<>();
        for (Location location : allWorldLocations(DEFAULT_WIDTH, DEFAULT_HEIGHT)) {
            cells.put(location, cellsFactory.getNewDefaultCell());
        }
        return cells;
    }

    public static int numberOfAliveNeighbours(Location l, Map<Location, Cell> cells) {
        int aliveNeighbours = 0;

        for (Location location : l.allNeighbours(DEFAULT_WIDTH, DEFAULT_HEIGHT)){
            if (cells.get(location).isAlive()){
                aliveNeighbours++;
            }
        }
        return aliveNeighbours;
    }

		public static Object instantiateClassFromName(String name) {
			try {
				Class c = Class.forName("com.thoughtworks.game_of_life.core." + name);
				return c.newInstance();
			} catch (Exception ex) {
				throw new RuntimeException("Error during instantiate " + name + " class --> " + ex.getMessage());
			}
		}

}

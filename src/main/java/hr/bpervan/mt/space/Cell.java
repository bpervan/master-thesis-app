package hr.bpervan.mt.space;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Branimir on 15.6.2015..
 */
public class Cell {
    public String cellName;
    public Map<Cell, Direction> neighbours;

    public Cell(){
        this.neighbours = new HashMap<>();
    }

    public Cell(String name){
        this.cellName = name;
        this.neighbours = new HashMap<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;

        Cell cell = (Cell) o;

        if (cellName != null ? !cellName.equals(cell.cellName) : cell.cellName != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = cellName != null ? cellName.hashCode() : 0;
        result = 31 * result + (neighbours != null ? neighbours.hashCode() : 0);
        return result;
    }
}

package hr.bpervan.mt.space;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Branimir on 15.6.2015..
 */
public class Cell {
    public Map<Cell, Direction> neighbours;

    public Cell(){
        this.neighbours = new HashMap<>();
    }
}

package hr.bpervan.mt.space;

/**
 * Created by Branimir on 15.6.2015..
 */
public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    UPLEFT,
    UPRIGHT,
    DOWNLEFT,
    DOWNRIGHT;

    public Direction getOpposite(Direction in){
        if(in == Direction.UP){
            return DOWN;
        }
        if(in == Direction.DOWN){
            return UP;
        }
        if(in == Direction.LEFT){
            return RIGHT;
        }
        if(in == Direction.RIGHT){
            return LEFT;
        }
        if(in == Direction.UPLEFT){
            return DOWNRIGHT;
        }
        if(in == Direction.UPRIGHT){
            return DOWNLEFT;
        }
        if(in == Direction.DOWNLEFT){
            return UPRIGHT;
        }
        return Direction.UPLEFT;
    }
}

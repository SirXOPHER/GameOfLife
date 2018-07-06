import java.util.Arrays;
import java.util.List;

class Game {

    private final List<Cell> cells;

    Game(Cell... cell){
        this.cells = Arrays.asList(cell);
    }

    boolean isEmpty() {
        return cells.isEmpty();
    }

    Game evolve() {
        return new Game();
    }

}

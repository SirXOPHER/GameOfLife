
class Game {

    private Cell cell = null;

    boolean isEmpty() {
        return cell == null;
    }

    Game evolve() {
        return this;
    }

    void populate(Cell cell) {
        this.cell = cell;
    }
}

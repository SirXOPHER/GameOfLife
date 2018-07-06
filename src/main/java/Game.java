
class Game {

    private final Cell cell;

    Game(){
        this(null);
    }

    Game(Cell cell){
        this.cell = cell;
    }

    boolean isEmpty() {
        return cell == null;
    }

    Game evolve() {
        return new Game();
    }

}

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Game {

    private final Set<Cell> cells;

    Game(Cell... cell) {
        this.cells = Stream.of(cell).collect(Collectors.toSet());
    }

    private Game(Set<Cell> cells) {
        this.cells = cells;
    }

    Game evolve() {
        Set<Cell> survivingCells = cells.stream()
                .filter(this::survive)
                .collect(Collectors.toSet());
        return new Game(survivingCells);
    }

    boolean isEmpty() {
        return cells.isEmpty();
    }

    private boolean survive(Cell cell) {
        long numberOfNeighbours = cells.stream()
                .filter(c -> !c.equals(cell))
                .filter(cell::isNeighbor)
                .count();

        return numberOfNeighbours >= 2 && numberOfNeighbours < 4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        return cells != null ? cells.equals(game.cells) : game.cells == null;
    }

    @Override
    public int hashCode() {
        return cells != null ? cells.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Game{" +
                "cells=" + cells +
                '}';
    }
}

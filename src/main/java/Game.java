import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Game {

    private static final int MINIMUM_NUMBER_OF_NEIGHBOURS_TO_SURVIVE = 2;
    private static final int MAXIMUM_NUMBER_OF_NEIGHBOURS_TO_SURVIVE = 4;
    private static final int EXACT_NUMBER_OF_NEIGHBOURS_TO_REPRODUCE = 3;
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

        Set<Cell> generatedCells = cells.stream()
                .flatMap(c -> c.produceNeighbours())
                .distinct()
                .filter( c -> !cells.contains(c))
                .filter(this::surviveReproduction)
                .collect(Collectors.toSet());

        survivingCells.addAll(generatedCells);
        return new Game(survivingCells);
    }

    private boolean surviveReproduction(Cell cell) {
        long numberOfNeighbours =  cells.stream()
                .filter(cell::isNeighbour)
                .count();
        return numberOfNeighbours == EXACT_NUMBER_OF_NEIGHBOURS_TO_REPRODUCE;
    }

    private boolean survive(Cell cell) {
        long numberOfNeighbours = cells.stream()
                .filter(c -> !c.equals(cell))
                .filter(cell::isNeighbour)
                .count();

        return numberOfNeighbours >= MINIMUM_NUMBER_OF_NEIGHBOURS_TO_SURVIVE
                && numberOfNeighbours < MAXIMUM_NUMBER_OF_NEIGHBOURS_TO_SURVIVE;
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

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Cell {

    private final int x;
    private final int y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean isNeighbour(Cell cell) {
        return Math.abs(cell.x - x) <= 1
                && Math.abs(cell.y - y) <= 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    Set<Cell> produceNeighbours() {
        return Stream.of(
                new Cell(x-1, y),
                new Cell(x-1, y+1),
                new Cell( x, y+1),
                new Cell(x+1, y+1),
                new Cell(x+1, y),
                new Cell( x+1, y-1),
                new Cell(x, y-1),
                new Cell(x-1, y-1)
                ).collect(Collectors.toSet());
    }
}

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GameOfLifeShould {

    @Test
    public void haveEmptyUniverseWithNoSeed() {
        assertThat(new Game().isEmpty(), is(true));
    }

    @Test
    public void ensureEmptyUniverseIsStillEmptyAfterFirstGeneration() {
        assertThat(new Game().evolve().isEmpty(), is(true));
    }

    @Test
    public void detectSeed() {
        Game game = new Game(new Cell(0, 0));

        assertThat(game.isEmpty(), is(false));
    }

    @Test
    public void killACellWithNoNeighbour() {
        Game game = new Game(new Cell(0, 0));

        Game nextGameGeneration = game.evolve();

        assertThat(nextGameGeneration.isEmpty(), is(true));
    }

    @Test
    public void killACellWithOneNeighbour() {
        Game game = new Game(new Cell(0, 0), new Cell(0, 1));

        Game nextGameGeneration = game.evolve();

        assertThat(nextGameGeneration.isEmpty(), is(true));
    }

    @Test
    public void preserveACellWithTwoNeighbor() {
        Game game = new Game(new Cell(0,0), new Cell(0,1), new Cell(1,0));
        Game expectedGame = new Game(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0));

        Game nextGameGeneration = game.evolve();

        assertThat(nextGameGeneration.evolve(), is(equalTo(expectedGame)));
    }
}

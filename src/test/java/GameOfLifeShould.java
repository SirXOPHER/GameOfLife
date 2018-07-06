import org.junit.Test;

import static org.hamcrest.core.Is.is;
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
        Game game = new Game(new Cell());
        assertThat(game.isEmpty(), is(false));
    }
}

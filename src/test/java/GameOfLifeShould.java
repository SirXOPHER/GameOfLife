import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class GameOfLifeShould {

    @Test
    public void haveEmptyUniverseWithNoSeed() {
        assertThat(new Game().isEmpty(), is(true));
    }


    @Test
    public void detectSeed() {
        Game game = new Game(new Cell(0, 0));

        assertThat(game.isEmpty(), is(false));
    }

    @Test
    @Parameters(method = "getGameBeforeAndAfterEvolution")
    public void evolveGame(Game gameBeforeEvolution, Game expectedGameAfterEvolution) {

        Game afterEvolutionGame = gameBeforeEvolution.evolve();

        assertThat(afterEvolutionGame, is(equalTo(expectedGameAfterEvolution)));
    }

    public Object[][] getGameBeforeAndAfterEvolution(){
        return new Object[][]{
                // Dead cell with no neighbour stay dead
                new Object[]{
                        new Game(), new Game()
                },
                // A cell with no neighbour dies
                new Object[]{
                        new Game(new Cell(0,0)), new Game()
                },
                // A cell with two neighbours survive
                new Object[]{
                        new Game(new Cell(0,0), new Cell(0,1), new Cell(1,0)),
                        new Game(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0))
                },
                // A cell with three neighbours survive
                new Object[]{
                        new Game(new Cell(0,0), new Cell(0,1), new Cell(1,0), new Cell(1, 1)),
                        new Game(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0), new Cell(1, 1))
                },
                // A cell with four neighbours dies
                new Object[]{
                        new Game(new Cell(0,0), new Cell(0,1), new Cell(1,0), new Cell (1,1), new Cell (-1, 0)),
                        new Game(new Cell(1,0), new Cell(1,1), new Cell(-1,0))
                }
        };
    }
}

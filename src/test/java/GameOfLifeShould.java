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
    @Parameters(method = "getGameBeforeAndAfterEvolution")
    public void evolveGame(Game gameBeforeEvolution, Game expectedGameAfterEvolution) {

        Game afterEvolutionGame = gameBeforeEvolution.evolve();

        assertThat(afterEvolutionGame, is(equalTo(expectedGameAfterEvolution)));
    }

    public Object[][] getGameBeforeAndAfterEvolution() {
        return new Object[][]{
                // Dead cell with no neighbour stay dead
                new Object[]{
                        new Game(), new Game()
                },
                // A cell with no neighbour dies
                new Object[]{
                        new Game(new Cell(0, 0)), new Game()
                },
                // A cell with two neighbours survive (and generate a new one)
                new Object[]{
                        new Game(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0)),
                        new Game(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0), new Cell(1,1))
                },
                // A cell with three neighbours survive
                new Object[]{
                        new Game(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0), new Cell(1, 1)),
                        new Game(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0), new Cell(1, 1))
                },
                // A cell with four neighbours dies (and generate 1 new cell)
                new Object[]{
                        new Game(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0), new Cell(1, 1), new Cell(-1, 0)),
                        new Game(new Cell(-1, 0), new Cell(-1, 1), new Cell(1, 0), new Cell(1, 1), new Cell(0, -1) )
                },
                // A dead cell with three neighbours become alive
                new Object[]{
                        new Game(new Cell(-1, 0), new Cell(0, 1), new Cell(1, 0)),
                        new Game(new Cell(0, 1), new Cell(0, 0))
                }
        };
    }
}

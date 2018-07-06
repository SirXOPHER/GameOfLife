import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameOfLifeShould {

    @Test
    public void haveEmptyUniverseWithNoSeed() {
        assertThat(new Game().isEmpty(), is(true));
    }
}

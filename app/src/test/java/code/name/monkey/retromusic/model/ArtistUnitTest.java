
package code.name.monkey.retromusic.model;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


public class ArtistUnitTest {

    Artist testEmptyArtist = Artist.getEmpty();
    Artist testArtist = Artist.getEmpty();

    @Test
    public void getNameUnknown() {
        System.out.println("expected:      " + "Unknown Artist");
        System.out.println("actual result: " + testEmptyArtist.getName());
        assertEquals("Unknown Artist", testEmptyArtist.getName());
    }
}

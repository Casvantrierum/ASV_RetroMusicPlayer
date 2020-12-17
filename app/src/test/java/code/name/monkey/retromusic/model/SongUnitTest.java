package code.name.monkey.retromusic.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SongUnitTest {
    Song testSong = new Song(1, "TestSong", 1, 2020, 125, "data", 123456789, 1, "testAlbum", 1, "testArtist", null, null);

    @Test
    public void songHashCode() throws Exception {
        int hashCode = testSong.hashCode();
        System.out.println("hashcode: " + hashCode);
        assertEquals(4, 2 + 2);
    }

    @Test
    public void equals() throws Exception {
        Song testSong2 = new Song(1, "TestSong", 1, 2020, 125, "data", 123456789, 1, "testAlbum", 1, "testArtist", null, null);
        System.out.println("?" + testSong.equals(testSong2));
        assertEquals(true, testSong.equals(testSong2));
    }
}
package code.name.monkey.retromusic.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SongUnitTest {
    Song testSong = Song.getEmptySong();

    @Test
    public void songHashCode() throws Exception {


        int result = Long.hashCode( (int) testSong.getId());
        result = 31 * result + testSong.getTitle().hashCode();
        result = 31 * result + testSong.getTrackNumber();
        result = 31 * result + testSong.getYear();
        result = 31 * result + Long.hashCode( (int) testSong.getDuration());
        result = 31 * result + testSong.getData().hashCode();
        result = 31 * result + Long.hashCode( (int) testSong.getDateModified());
        result = 31 * result + Long.hashCode( (int) testSong.getAlbumId());
        result = 31 * result + testSong.getAlbumName().hashCode();
        result = 31 * result + Long.hashCode( (int) testSong.getArtistId());
        result = 31 * result + testSong.getArtistName().hashCode();

        result = 31 * result;
        if(testSong.getComposer() != null){
            result += testSong.getComposer().hashCode();
        }

        result = 31 * result;
        if(testSong.getAlbumArtist() != null){
            result += testSong.getAlbumArtist().hashCode();
        }

        assertEquals(result, testSong.hashCode());
    }

    @Test
    public void songEquals(){
        Song testSongEqual = testSong;
        assertTrue(testSong.equals(testSongEqual));
        
        Song testSongNotEqual = new Song(1, "TestSong", 1, 2020, 125, "data", 1234567891, 1, "testAlbum", 1, "testArtist", null, null);
        assertFalse(testSong.equals(testSongNotEqual));
    }
}
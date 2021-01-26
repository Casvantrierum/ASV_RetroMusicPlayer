package code.name.monkey.retromusic.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SongUnitTest {
    Song testSongEmpty = Song.getEmptySong();
    Song testSong = new Song(1, "TestSong", 1, 2020, 125, "data", 1234567891, 1, "testAlbum", 1, "testArtist", null, null);


    @Test
    public void hash_code(){
        int result = Long.hashCode( (int) testSongEmpty.getId());
        result = 31 * result + testSongEmpty.getTitle().hashCode();
        result = 31 * result + testSongEmpty.getTrackNumber();
        result = 31 * result + testSongEmpty.getYear();
        result = 31 * result + Long.hashCode( (int) testSongEmpty.getDuration());
        result = 31 * result + testSongEmpty.getData().hashCode();
        result = 31 * result + Long.hashCode( (int) testSongEmpty.getDateModified());
        result = 31 * result + Long.hashCode( (int) testSongEmpty.getAlbumId());
        result = 31 * result + testSongEmpty.getAlbumName().hashCode();
        result = 31 * result + Long.hashCode( (int) testSongEmpty.getArtistId());
        result = 31 * result + testSongEmpty.getArtistName().hashCode();

        result = 31 * result;
        if(testSongEmpty.getComposer() != null){
            result += testSongEmpty.getComposer().hashCode();
        }

        result = 31 * result;
        if(testSongEmpty.getAlbumArtist() != null){
            result += testSongEmpty.getAlbumArtist().hashCode();
        }

        assertEquals(result, testSongEmpty.hashCode());
    }

    @Test
    public void equals(){
        Song testSongEqual = testSongEmpty;
        assertTrue(testSongEmpty.equals(testSongEqual));

        assertFalse(testSongEmpty.equals(testSong));
    }

    @Test
    public void companion_object(){
        Song emptySong =  new Song(
                -1, "", -1, -1, -1, "", -1,-1,"",-1,"", "", ""
        );
        assertEquals(emptySong, Song.getEmptySong());
    }
}
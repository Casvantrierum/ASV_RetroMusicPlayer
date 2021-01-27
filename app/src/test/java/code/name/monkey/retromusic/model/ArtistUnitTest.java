package code.name.monkey.retromusic.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


@RunWith(PowerMockRunner.class)     //  The test should be run using PowerMock runner
public class ArtistUnitTest {
    Song testSongEmpty;

    Album testAlbum1;
    Album testAlbum2;
    Album testAlbumEmpty;

    Artist testArtist;
    Artist artistEmptyTest;

    List<Song> allSongs = new ArrayList<>();

    @Before
    public void initialize() {
        List<Song> songsAlbum1 = new ArrayList<>();
        List<Song> songsAlbum2 = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            int albumId;
            int trackNr;
            if (i < 3){
                trackNr = i + 1;
                albumId = 1;
            }
            else {
                trackNr = i - 2;
                albumId = 2;
            }
            Song testSong = new Song(i, "TestSong" + i, trackNr, 2020, 125, "data", 1234567891, albumId, "testAlbum"+ albumId, 1, "testArtist", null, null);
            if (i < 3){
                songsAlbum1.add(testSong);
            }
            else songsAlbum2.add(testSong);
            allSongs.add(testSong);
        }

        testSongEmpty = Song.getEmptySong();

        testAlbum1 = new Album(1, songsAlbum1);
        testAlbum2 = new Album(2, songsAlbum2);

        testAlbumEmpty =  Album.getEmpty();

        List<Album> albums = new ArrayList<>();
        albums.add(testAlbum1);
        albums.add(testAlbum2);

        testArtist = new Artist(1, albums);

        artistEmptyTest = Artist.getEmpty();

    }

    @Test
    public void songsTest() {
        List<String> emptyList = Collections.emptyList();
        assertEquals(emptyList, artistEmptyTest.getSongs());

        assertEquals(allSongs, testArtist.getSongs());
        assertArrayEquals(allSongs.toArray(), testArtist.getSongs().toArray());
    }

    @Test
    public void albumCountTest() {
        assertEquals(0, artistEmptyTest.getAlbumCount());
        assertEquals(2, testArtist.getAlbumCount());
    }

    @Test
    public void songCountTest() {
        assertEquals(0, artistEmptyTest.getSongCount());
        assertEquals(4, testArtist.getSongCount());
    }

    @Test
    public void companionObjectValuesTest(){
        assertEquals("Unknown Artist", Artist.UNKNOWN_ARTIST_DISPLAY_NAME);
        assertEquals("Various Artists", Artist.VARIOUS_ARTISTS_DISPLAY_NAME);
        assertEquals(-2, Artist.VARIOUS_ARTISTS_ID);

        Artist emptyArtist = new Artist(-1, Collections.emptyList());
        assertEquals(emptyArtist, Artist.getEmpty());
    }
}

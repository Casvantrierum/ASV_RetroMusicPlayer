package code.name.monkey.retromusic.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

public class AlbumUnitTest {
    Song testSongEmpty;

    Album testAlbum1;
    Album testAlbum2;
    Album testAlbumEmpty;

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
        }testSongEmpty = Song.getEmptySong();

        testAlbum1 = new Album(1, songsAlbum1);
        testAlbum2 = new Album(2, songsAlbum2);

        testAlbumEmpty =  Album.getEmpty();
    }

    @Test
    public void getFirstSongTest() {
        assertEquals(allSongs.get(0), testAlbum1.safeGetFirstSong());
        assertEquals(allSongs.get(3), testAlbum2.safeGetFirstSong());
        assertNotEquals(allSongs.get(0), testAlbum2.safeGetFirstSong());

        assertNotNull(testAlbumEmpty.safeGetFirstSong());
        assertEquals(testSongEmpty, testAlbumEmpty.safeGetFirstSong());
    }

    @Test
    public void getTitleTest() {
        Album albumSpy = spy(Album.getEmpty());
        when(albumSpy.safeGetFirstSong()).thenReturn(allSongs.get(0));
        assertEquals(allSongs.get(0).getAlbumName(), albumSpy.getTitle());

        when(albumSpy.safeGetFirstSong()).thenReturn(testSongEmpty);
        assertEquals(testSongEmpty.getAlbumName(), albumSpy.getTitle());
    }

    @Test
    public void companionObjectValuesTest(){
        Album emptyAlbum = new Album(-1, Collections.emptyList());
        assertEquals(emptyAlbum, Album.getEmpty());
    }
}

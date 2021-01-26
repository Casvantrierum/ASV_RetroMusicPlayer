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
    Song testSong1;
    Song testSong2;
    Song testSong3;
    Song testSong4;
    Song testSongEmpty;

    Album testAlbum1;
    Album testAlbum2;
    Album testAlbumEmpty;

    @Before
    public void initialize() {
        testSong1 = new Song(1, "TestSong1", 1, 2020, 125, "data", 1234567891, 1, "testAlbum1", 1, "testArtist", null, null);
        testSong2 = new Song(2, "TestSong2", 2, 2020, 125, "data", 1234567891, 1, "testAlbum1", 1, "testArtist", null, null);
        testSong3 = new Song(3, "TestSong3", 3, 2020, 125, "data", 1234567891, 1, "testAlbum1", 1, "testArtist", null, null);
        testSong4 = new Song(4, "TestSong4", 1, 2019, 125, "data", 1234567891, 2, "testAlbum2", 1, "testArtist", null, null);
        testSongEmpty = Song.getEmptySong();

        List<Song> songsAlbum1 = new ArrayList<>();
        songsAlbum1.add(testSong1);
        songsAlbum1.add(testSong2);
        songsAlbum1.add(testSong3);
        testAlbum1 = new Album(1, songsAlbum1);
        List<Song> songsAlbum2 = new ArrayList<>();
        songsAlbum2.add(testSong4);
        testAlbum2 = new Album(2, songsAlbum2);

        testAlbumEmpty =  Album.getEmpty();
    }

    @Test
    public void get_first_song() {
        assertEquals(testSong1, testAlbum1.safeGetFirstSong());
        assertEquals(testSong4, testAlbum2.safeGetFirstSong());
        assertNotEquals(testSong1, testAlbum2.safeGetFirstSong());

        assertNotNull(testAlbumEmpty.safeGetFirstSong());
        assertEquals(testSongEmpty, testAlbumEmpty.safeGetFirstSong());
    }

    @Test
    public void get_title() {
        Album albumSpy = spy(Album.getEmpty());
        when(albumSpy.safeGetFirstSong()).thenReturn(testSong1);
        assertEquals(testSong1.getAlbumName(), albumSpy.getTitle());

        when(albumSpy.safeGetFirstSong()).thenReturn(testSongEmpty);
        assertEquals(testSongEmpty.getAlbumName(), albumSpy.getTitle());
    }

    @Test
    public void companion_object_values(){
        Album emptyAlbum = new Album(-1, Collections.emptyList());
        assertEquals(emptyAlbum, Album.getEmpty());
    }
}

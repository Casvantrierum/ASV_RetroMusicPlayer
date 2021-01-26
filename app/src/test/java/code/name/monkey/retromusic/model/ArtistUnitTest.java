package code.name.monkey.retromusic.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import code.name.monkey.retromusic.util.MusicUtil;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)     //  The test should be run using PowerMock runner
//@PrepareForTest({MusicUtil.class})    //  We should specify the list of classes that contain static methods that we want to mock
public class ArtistUnitTest {
    Song testSong1;
    Song testSong2;
    Song testSong3;
    Song testSong4;
    Song testSongEmpty;

    Album testAlbum1;
    Album testAlbum2;
    Album testAlbumEmpty;

    Artist testArtist;
    Artist artistEmptyTest;

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

        List<Album> albums = new ArrayList<>();
        albums.add(testAlbum1);
        albums.add(testAlbum2);

        testArtist = new Artist(1, albums);

        artistEmptyTest = Artist.getEmpty();

    }

    @Test
    public void songs() {
        List<String> emptyList = Collections.emptyList();
        assertEquals(emptyList, artistEmptyTest.getSongs());

        List<Song> allSongs = new ArrayList<>();
        allSongs.add(testSong1);
        allSongs.add(testSong2);
        allSongs.add(testSong3);
        allSongs.add(testSong4);
        assertEquals(allSongs, testArtist.getSongs());

    }

    @Test
    public void album_count() {
        assertEquals(0, artistEmptyTest.getAlbumCount());
        assertEquals(2, testArtist.getAlbumCount());
    }

    @Test
    public void song_count() {
        assertEquals(0, artistEmptyTest.getSongCount());
        assertEquals(4, testArtist.getSongCount());
    }

    @Test
    public void companion_object_values(){
        assertEquals("Unknown Artist", Artist.UNKNOWN_ARTIST_DISPLAY_NAME);
        assertEquals("Various Artists", Artist.VARIOUS_ARTISTS_DISPLAY_NAME);
        assertEquals(-2, Artist.VARIOUS_ARTISTS_ID);

        Artist emptyArtist = new Artist(-1, Collections.emptyList());
        assertEquals(emptyArtist, Artist.getEmpty());
    }
}

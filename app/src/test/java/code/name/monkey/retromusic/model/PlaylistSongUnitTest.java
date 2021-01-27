package code.name.monkey.retromusic.model;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class PlaylistSongUnitTest {
    Song testSongEmpty = Song.getEmptySong();
    PlaylistSong testPlaylistSong = new PlaylistSong(
            testSongEmpty.getId(),
            testSongEmpty.getTitle(),
            testSongEmpty.getTrackNumber(),
            testSongEmpty.getYear(),
            testSongEmpty.getDuration(),
            testSongEmpty.getData(),
            testSongEmpty.getDateModified(),
            testSongEmpty.getAlbumId(),
            testSongEmpty.getAlbumName(),
            testSongEmpty.getArtistId(),
            testSongEmpty.getArtistName(),
            0,
            0,
            testSongEmpty.getComposer(),
            testSongEmpty.getAlbumArtist()
    );

    @Test
    public void equalsTest() {
        //assert false because it has extra properties.
        assertFalse(testPlaylistSong.equals(testSongEmpty));

        PlaylistSong playlistSongEqual = testPlaylistSong;
        assertTrue(testPlaylistSong.equals(playlistSongEqual));
    }

    @Test
    public void hashCodeTest() {

        int result = testSongEmpty.hashCode();
        result = 31 * result + Long.hashCode( (int) testPlaylistSong.getId());
        result = 31 * result + testPlaylistSong.getTitle().hashCode();
        result = 31 * result + testPlaylistSong.getTrackNumber();
        result = 31 * result + testPlaylistSong.getYear();
        result = 31 * result + Long.hashCode( (int) testPlaylistSong.getDuration());
        result = 31 * result + testPlaylistSong.getData().hashCode();
        result = 31 * result + Long.hashCode( (int) testPlaylistSong.getDateModified());
        result = 31 * result + Long.hashCode( (int) testPlaylistSong.getAlbumId());
        result = 31 * result + testPlaylistSong.getAlbumName().hashCode();
        result = 31 * result + Long.hashCode( (int) testPlaylistSong.getArtistId());
        result = 31 * result + testPlaylistSong.getArtistName().hashCode();
        result = 31 * result + Long.hashCode( (int) testPlaylistSong.getPlaylistId());
        result = 31 * result + Long.hashCode( (int) testPlaylistSong.getIdInPlayList());
        result = 31 * result;
        if(testSongEmpty.getComposer() != null){
            result += testSongEmpty.getComposer().hashCode();
        }

        result = 31 * result;
        if(testSongEmpty.getAlbumArtist() != null){
            result += testSongEmpty.getAlbumArtist().hashCode();
        }

        assertEquals(result, testPlaylistSong.hashCode());

        //assert false because it has extra properties.
        assertNotSame(testPlaylistSong.hashCode(), testSongEmpty.hashCode());
    }
}

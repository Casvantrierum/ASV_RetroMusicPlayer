package code.name.monkey.retromusic.model;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotSame;

public class PlaylistSongUnitTest {
    Song testSong = Song.getEmptySong();
    PlaylistSong testPlaylistSong = new PlaylistSong(
            testSong.getId(),
            testSong.getTitle(),
            testSong.getTrackNumber(),
            testSong.getYear(),
            testSong.getDuration(),
            testSong.getData(),
            testSong.getDateModified(),
            testSong.getAlbumId(),
            testSong.getAlbumName(),
            testSong.getArtistId(),
            testSong.getArtistName(),
            0,
            0,
            testSong.getComposer(),
            testSong.getAlbumArtist()
    );

    @Test
    public void equalsNormalSong() {
        //assert false because it has extra properties.
        assertFalse(testPlaylistSong.equals(testSong));
    }

    @Test
    public void hashCodeNotEqualsNormalSong() {
        //assert false because it has extra properties.
        assertNotSame(testPlaylistSong.hashCode(), testSong.hashCode());
    }
}

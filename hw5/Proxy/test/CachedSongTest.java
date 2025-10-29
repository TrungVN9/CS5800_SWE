import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CachedSongTest {
    private SongServiceDB realService;
    private CachedSongService cachedService;
    private List<Song> testSongs;

    @BeforeEach
    void setUp() {
        testSongs = new ArrayList<>();
        testSongs.add(new Song(1, "Shape of You", "Ed Sheeran", "Divide", 30));
        testSongs.add(new Song(2, "Perfect", "Ed Sheeran", "Divide", 50));
        testSongs.add(new Song(3, "Paul", "Eminem", "The Slim Shady", 50));
        testSongs.add(new Song(4, "My Name is", "Eminem", "The Slim Shady", 30));

        realService = new SongServiceDB(testSongs);
        cachedService = new CachedSongService(realService);
    }

    @Test
    void testSearchByID_FirstAccess() {
        // First access should hit the database
        Song song = cachedService.searchByID(1);
        assertNotNull(song);
        assertEquals("Shape of You", song.getTitle());
        assertEquals("Ed Sheeran", song.getArtist());
    }

    @Test
    void testSearchByID_CachedAccess() {
        // First access to populate cache
        cachedService.searchByID(1);

        // Second access should be from cache
        long startTime = System.currentTimeMillis();
        Song song = cachedService.searchByID(1);
        long endTime = System.currentTimeMillis();

        assertNotNull(song);
        assertEquals("Shape of You", song.getTitle());

        assertTrue((endTime - startTime) < 100, "Cached access should be fast");
    }

    @Test
    void testSearchByTitle_FirstAccess() {
        List<Song> songs = cachedService.searchByTitle("Perfect");
        assertFalse(songs.isEmpty());
        assertEquals(1, songs.size());
        assertEquals("Perfect", songs.get(0).getTitle());
    }

    @Test
    void testSearchByTitle_CachedAccess() {
        // First access to populate cache
        cachedService.searchByTitle("Perfect");

        // Second access should be from cache
        long startTime = System.currentTimeMillis();
        List<Song> songs = cachedService.searchByTitle("Perfect");
        long endTime = System.currentTimeMillis();

        assertFalse(songs.isEmpty());
        assertEquals(1, songs.size());
        assertEquals("Perfect", songs.get(0).getTitle());

        assertTrue((endTime - startTime) < 100, "Cached access should be fast");
    }

    @Test
    void testSearchByAlbum_FirstAccess() {
        List<Song> songs = cachedService.searchByAlbum("Divide");
        assertFalse(songs.isEmpty());
        assertEquals(2, songs.size());
        assertTrue(songs.stream().allMatch(song -> song.getAlbum().equals("Divide")));
    }

    @Test
    void testSearchByAlbum_CachedAccess() {
        // First access to populate cache
        cachedService.searchByAlbum("Divide");

        // Second access should be from cache
        long startTime = System.currentTimeMillis();
        List<Song> songs = cachedService.searchByAlbum("Divide");
        long endTime = System.currentTimeMillis();

        assertFalse(songs.isEmpty());
        assertEquals(2, songs.size());
        assertTrue(songs.stream().allMatch(song -> song.getAlbum().equals("Divide")));

        assertTrue((endTime - startTime) < 100, "Cached access should be fast");
    }

    @Test
    void testSearchByID_NonExistent() {
        Song song = cachedService.searchByID(999);
        assertNull(song);
    }

    @Test
    void testSearchByTitle_NonExistent() {
        List<Song> songs = cachedService.searchByTitle("Nonexistent Song");
        assertTrue(songs.isEmpty());
    }

    @Test
    void testSearchByAlbum_NonExistent() {
        List<Song> songs = cachedService.searchByAlbum("Nonexistent Album");
        assertTrue(songs.isEmpty());
    }

    @Test
    void testCachePersistence() {
        Song firstAccess = cachedService.searchByID(1);

        Song secondAccess = cachedService.searchByID(1);

        assertSame(firstAccess, secondAccess, "Cache should return the same object instance");
    }
}

package Proxy;
import java.util.*;

public class CachedSongService implements SongService {
    private final SongServiceDB realService;
    private final Map<Integer, Song> cacheById = new HashMap<>();
    private final Map<String, List<Song>> cacheByTitle = new HashMap<>();
    private final Map<String, List<Song>> cacheByAlbum = new HashMap<>();

    public CachedSongService(SongServiceDB realService) {
        this.realService = realService;
    }

    @Override
    public Song searchByID(Integer songID) {
        if (!cacheById.containsKey(songID)) {
            Song song = realService.searchByID(songID);
            if (song != null) cacheById.put(songID, song);
        } else {
            System.out.println("Retrieved from cache (by ID).");
        }
        return cacheById.get(songID);
    }

    @Override
    public List<Song> searchByTitle(String title) {
        if (!cacheByTitle.containsKey(title)) {
            List<Song> results = realService.searchByTitle(title);
            cacheByTitle.put(title, results);
        } else {
            System.out.println("Retrieved from cache (by Title).");
        }
        return cacheByTitle.get(title);
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        if (!cacheByAlbum.containsKey(album)) {
            List<Song> results = realService.searchByAlbum(album);
            cacheByAlbum.put(album, results);
        } else {
            System.out.println("Retrieved from cache (by Album).");
        }
        return cacheByAlbum.get(album);
    }
}

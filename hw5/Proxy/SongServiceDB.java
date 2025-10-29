package Proxy;

import java.util.ArrayList;
import java.util.List;

public class SongServiceDB implements SongService{
    private final List<Song> songDB;

    public SongServiceDB(List<Song> newSongDB) {
        this.songDB = newSongDB;
    }

    @Override
    public Song searchByID(Integer songID) {
        simulateDelay();
        System.out.println("Searching in the actual database...");
        return songDB.stream()
                .filter(song -> song.getID().equals(songID))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<Song> searchByTitle(String title) {
        simulateDelay();
        System.out.println("Searching in the actual database...");
        List<Song> results = new ArrayList<>();
        for (Song s : songDB) {
            if (s.getTitle().equalsIgnoreCase(title)) {
                results.add(s);
            }
        }
        return results;
    }

     @Override
     public List<Song> searchByAlbum(String album) {
        simulateDelay();
        System.out.println("Searching in the actual database...");
        List<Song> results = new ArrayList<>();
        for (Song s : songDB) {
            if (s.getAlbum().equalsIgnoreCase(album)) {
                results.add(s);
            }
        }
        return results;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {};
    }

}

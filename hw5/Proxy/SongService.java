package Proxy;
import java.util.*;
public interface SongService {
    Song searchByID(Integer songID);

    List<Song> searchByTitle(String title);

    List<Song> searchByAlbum(String album);
}

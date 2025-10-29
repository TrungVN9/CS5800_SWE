package Proxy;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        List<Song> songsDB = new ArrayList<>();

        songsDB.add(new Song(1, "Shape of You", "Ed Sheeran", "Divide", 30));
        songsDB.add(new Song(2, "Perfect", "Ed Sheeran", "Divide", 50));
        songsDB.add(new Song(3, "Paul ", "Eminem", "The Slim Shady", 50));
        songsDB.add(new Song(4, "My Name is ", "Eminem", "The Slim Shady", 30));
        songsDB.add(new Song(5, "Lose Yourself", "Eminem", "8 Mile", 45));
        songsDB.add(new Song(6, "Numb", "Linkin Park", "Meteora", 40));
        songsDB.add(new Song(7, "In the End", "Linkin Park", "Hybrid Theory", 35));

        SongServiceDB realServiceDB = new SongServiceDB(songsDB);
        CachedSongService cachedSongs = new CachedSongService(realServiceDB);

        System.out.println("\n=== First search by ID ===");
        System.out.println(cachedSongs.searchByID(1));

        System.out.println("\n=== Second search by ID (cached) ===");
        System.out.println(cachedSongs.searchByID(1));

        System.out.println("\n=== Search by Title ===");
        printSongs(cachedSongs.searchByTitle("Lose Yourself"));

        System.out.println("\n=== Search by Album ===");
        printSongs(cachedSongs.searchByAlbum("Divide"));

        System.out.println("\n=== Cached Album Search ===");
        printSongs(cachedSongs.searchByAlbum("Divide"));
    }
    
// Helper function for printing song lists
    private static void printSongs(List<Song> songs) {
        if (songs.isEmpty()) {
            System.out.println("No songs found.");
        } else {
            for (Song song : songs) {
                System.out.println("Song ID: " + song.getID() + ", Title: " + song.getTitle() +
                                   ", Artist: " + song.getArtist() + ", Album: " + song.getAlbum() +
                                   ", Duration: " + song.getDuration() + "s");
            }
        }
    }
}


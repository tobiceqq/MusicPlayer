package utils;

import java.util.Comparator;
import java.util.List;


/**
 * Utility class for sorting songs based on different criteria.
 */
public class SongSorter {

    /**
     * Sorts the songs alphabetically by their title.
     *
     * @param songs the list of songs to sort
     */
    public static void sortByTitle(List<Song> songs) {
        songs.sort(Comparator.comparing(Song::getTitle, String.CASE_INSENSITIVE_ORDER));
    }

    /**
     * Sorts the songs by their duration in ascending order.
     *
     * @param songs the list of songs to sort
     */
    public static void sortByDuration(List<Song> songs) {
        songs.sort(Comparator.comparingInt(Song::getDurationInSeconds));
    }

    /**
     * Sorts the song by the number of times they have been played - descending.
     *
     * @param songs the list of songs to sort
     */
    public static void sortByPlayCount(List<Song> songs) {
        songs.sort(Comparator.comparingInt(Song::getPlayCount).reversed());
    }
}

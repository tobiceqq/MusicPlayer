package model;

/**
 * Represents a song in the music player.
 */
public class Song {
    private String title;
    private String artist;
    private int durationInSeconds;
    private int playCount;
    private boolean favorite;

    public Song(String title, String artist, int durationInSeconds) {
        this.title = title;
        this.artist = artist;
        this.durationInSeconds = durationInSeconds;
        this.playCount = 0;
        this.favorite = false;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public int getPlayCount() {
        return playCount;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    /**
     * Increments the play count by one.
     */
    public void incrementPlayCount() {
        playCount++;
    }

    private String formatDuration() {
        int minutes = durationInSeconds / 60;
        int seconds = durationInSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    @Override
    public String toString() {
        return title + " (" + formatDuration() + ")" + (favorite ? " ❤\uFE0F" : "");
    }
}

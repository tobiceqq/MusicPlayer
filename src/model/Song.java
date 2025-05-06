package model;

public class Song {

    private final String title;
    private final String artist;
    private final int durationInSeconds;
    private int playCount;
    private boolean favorite;
    private final String filePath;

    public Song(String title, String artist, int durationInSeconds, String filePath) {
        this.title = title;
        this.artist = artist;
        this.durationInSeconds = durationInSeconds;
        this.filePath = filePath;
        this.playCount = 0;
        this.favorite = false;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void incrementPlayCount() {
        playCount++;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return String.format("%s – %s [%s]%s", artist, title, formatDuration(), favorite ? " ❤\uFE0F" : "");
    }

    /**
     * Formats the duration in MM:SS format.
     *
     * @return a formatted string of the song duration
     */
    private String formatDuration() {
        int minutes = durationInSeconds / 60;
        int seconds = durationInSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
}

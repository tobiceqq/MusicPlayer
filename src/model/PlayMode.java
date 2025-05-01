package model;

/**
 * Represents different play modes for the music player.
 * Defines how songs in the playlist will be played.
 */
public enum PlayMode {

    NORMAL("Normal play order"),
    SHUFFLE("Normal play order"),
    LOOP_ONE("Repeat the current song"),
    LOOP_ALL("Repeat entire playlist"),
    FAVORITES("Play only favorite songs");

    private final String description;

    PlayMode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

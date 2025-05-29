package command;

import model.Song;
import playlist.PlaylistManager;
import utils.ConsoleStyle;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Stats implements Command {

    private final PlaylistManager playlistManager;

    public Stats(PlaylistManager playlistManager) {
        this.playlistManager = playlistManager;
    }

    /**
     * Displays statistics for the selected playlist.
     *
     * @return formated stats or error
     */
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return ConsoleStyle.color("❌ No playlist selected." , ConsoleStyle.RED);
        }

        List<Song> songs = playlistManager.getCurrentPlaylist().getSongs();
        if (songs.isEmpty()) {
            return ConsoleStyle.color("\uD83D\uDCED Playlist is empty.", ConsoleStyle.BLUE);
        }

        int totalSongs = songs.size();
        int totalDuration = songs.stream().mapToInt(Song::getDurationInSeconds).sum();
        int favoriteCount = (int) songs.stream().filter(Song::isFavorite).count();
        Song mostPlayed = songs.stream().max(Comparator.comparingInt(Song::getPlayCount)).orElse(null);

        List<Song> top3 = songs.stream().sorted(Comparator.comparingInt(Song::getPlayCount).reversed()).limit(3).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append(ConsoleStyle.bold("\uD83D\uDCCA Playlist stats\n"));
        sb.append("____________________________________\n");
        sb.append("Total songs      : ").append(totalSongs).append("\n");
        sb.append("Total duration      : ").append(totalDuration / 60).append(":").append(String.format("%02d" , totalDuration % 60)).append("\n");
        sb.append("Favorites      : ").append(favoriteCount).append(" ❤\uFE0F\n");

        if (mostPlayed != null) {
            sb.append(ConsoleStyle.bold("Most played      : ")).append(mostPlayed.getTitle()).append(" - ").append(mostPlayed.getArtist()).append(" (").append(mostPlayed.getPlayCount()).append("x)\n");

        }

        sb.append(ConsoleStyle.underline(ConsoleStyle.bold("\nTop 3 most played:\n")));
        for (int i = 0; i < top3.size(); i++) {
            Song s = top3.get(i);
            sb.append(String.format("%d. %s – %s (%dx)\n" , i + 1 , s.getTitle(), s.getArtist() , s.getPlayCount()));
        }

        sb.append(ConsoleStyle.underline(ConsoleStyle.bold("\nAll songs in playlist:\n")));
        List<Song> allSongs = playlistManager.getCurrentPlaylist().getSongs();
        for (int i = 0; i < allSongs.size(); i++) {
            Song s = allSongs.get(i);
            sb.append(i + 1).append(". ").append(s.getTitle()).append(" - ").append(s.getArtist()).append(" [")
               .append(String.format("%d:%02d", s.getDurationInSeconds() / 60, s.getDurationInSeconds() % 60)).append("]\n");
        }

        return sb.toString().strip();
    }

    /**
     * Indicates whether this command should cause the program to exit.
     *
     * @return false, as this commands does not terminate the program
     */
    @Override
    public boolean exit() {
        return false;
    }
}

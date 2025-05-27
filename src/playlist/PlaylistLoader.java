package playlist;

import model.Playlist;
import model.Song;

/**
 * Creates playlists (albums) with songs.
 */
public class PlaylistLoader {

    public static void loadPlaylists(PlaylistManager playlistManager) {
        playlistManager.createPlaylist("LONG.LIVE.A$AP" , "A$AP Rocky");
        Playlist longLiveAsap = playlistManager.getPlaylist("LONG.LIVE.A$AP");

        if (longLiveAsap != null) {
            longLiveAsap.addSong(new Song("Long Live A$AP" , "A$AP Rocky" , 289 , "songs/Long Live A$AP.wav"));
            longLiveAsap.addSong(new Song("LVL" , "A$AP Rocky" , 220 , "songs/LVL.wav"));
            longLiveAsap.addSong(new Song("Fashion Killa" , "A$AP Rocky" , 239 , "songs/Fashion Killa.wav"));
            longLiveAsap.addSong(new Song("Ghetto Symphony" , "A$AP Rocky" , 240 , "songs/Ghetto Symphony.wav"));
        }


        playlistManager.createPlaylist("LYFESTYLE" , "Yeat");
        Playlist lyfestyle = playlistManager.getPlaylist("LYFESTYLE");

        if (lyfestyle != null) {
            lyfestyle.addSong(new Song("GEEK TIMË" , "Yeat" , 170 , "songs/GEEK TIMË.wav"));
            lyfestyle.addSong(new Song("STFU" , "Yeat" , 104 , "songs/STFU.wav"));
            lyfestyle.addSong(new Song("GONE 4 A MIN" , "Yeat" , 135 , "songs/GONE 4 A MIN.wav"));
            lyfestyle.addSong(new Song("FOREVER AGAIN" , "Yeat" , 199 , "songs/FOREVER AGAIN.wav"));
            lyfestyle.addSong(new Song("FATË (BONUS)" , "Yeat" , 199 , "songs/FATË (BONUS).wav"));
        }





    }





}

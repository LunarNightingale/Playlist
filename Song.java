/**
 * @author Siu Hin Nicholas Cheng
 * SBU ID: 116564445
 * email: Siuhin.Cheng@stonybrook.edu
 * Assignment #: HW2
 * Course: CSE 214
 * Recitation: R03 (Kailash Anand & Kevin Zheng)(Monday 5:00 P.M. - 5:55 P.M.)
 */
public class Song{
    private String name, artist, album;
    private int length;

    /**
     * Default constructor for Song.
     */
    public Song() {
        name = "";
        artist = "";
        album = "";
        length = 0;

    }

    /**
     * Parameter constructor for Song.
     * @param named
     * The name of the song. Since this is what the name of the wav file is based on, you may assume that this is unique and that only one song with a given name will exist within the playlist.
     * @param artistName
     * The performer of the song.
     * @param albumName
     * The album the song was released on.
     * @param len
     * Length of the song in seconds.
     */
    public Song(String named, String artistName, String albumName, int len) {
        name = named;
        artist = artistName;
        album = albumName;
        length = len;
    }

    /**
     * Getter for name.
     * @return
     * Song name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name.
     * @param newName
     * New name of the song.
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Getter for artist.
     * @return
     * Artist name.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Setter for artist.
     * @param newArtist
     * Updated artist name.
     */
    public void setArtist(String newArtist) {
        artist = newArtist;
    }

    /**
     * Getter for album.
     * @return
     * Album name.
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Setter for album.
     * @param newAlbum
     * Updated album name.
     */
    public void setAlbum(String newAlbum) {
        album = newAlbum;
    }

    /**
     * Getter for length.
     * @return
     * Song length in seconds.
     */
    public int getLength() {
        return length;
    }

    /**
     * Setter for length.
     *
     * @param len Updated song length in seconds.
     */
    public void setLength(int len) {
        length = len;
    }

    /**
     * toString method for Song.
     * @return
     * Returns a string that holds a formatted version of Song.
     */
    public String toString() {
        String format = "%-30s %-30s %-30s %5s";
        return String.format(format, name, artist, album, length);
    }
}
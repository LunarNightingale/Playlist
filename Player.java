/**
 * @author Siu Hin Nicholas Cheng
 * SBU ID: 116564445
 * email: Siuhin.Cheng@stonybrook.edu
 * Assignment #: HW2
 * Course: CSE 214
 * Recitation: R03 (Kailash Anand & Kevin Zheng)(Monday 5:00 P.M. - 5:55 P.M.)
 */
import java.util.Scanner;
public class Player{
    /**
     * Player for a song playlist.
     * @param args
     * String variable to be passed via console (unused).
     */
    public static void main(String[] args) throws IllegalArgumentException{
        SongLinkedList playlist = new SongLinkedList();
        Scanner scan = new Scanner(System.in);
        String command = "";
        System.out.println("(A) Add Song to Playlist\n(F) Go to Next Song\n(B) Go to Previous Song\n(R) Remove Song from Playlist\n"
            + "(L) Play a Song\n(C) Clear the Playlist\n(S) Shuffle Playlist\n(Z) Random Song\n(P) Print Playlist\n"
            + "(T) Get the total amount of songs in the playlist\n(Q) Exit the playlist");
        do{
            System.out.println("Enter an option: ");
            command = scan.next().toUpperCase();
            if(command.equals("A")){
                String songName, artist, album;
                int len;
                scan.nextLine();
                System.out.println("Enter song title: ");
                songName = scan.nextLine();
                System.out.println("Enter the artist(s) of the song: ");
                artist = scan.nextLine();
                System.out.println("Enter album: ");
                album = scan.nextLine();
                System.out.println("Enter length (in seconds): ");
                len = scan.nextInt();
                Song song = new Song(songName, artist, album, len);
                playlist.insertAfterCursor(song);
                System.out.println("'" + songName + "' by " + artist + " is added to your playlist.");
            } else if(command.equals("F")){
                playlist.cursorForwards();
            } else if(command.equals("B")){
                playlist.cursorBackwards();
            } else if(command.equals("R")){
                if(playlist.getCursor() != null){
                    System.out.println("'" + playlist.getCursor().getData().getName() + "' by " + playlist.getCursor().getData().getArtist() + " was removed from the playlist.");
                    playlist.removeCursor();
                } else{
                    System.out.println("Your playlist is empty.");
                }
            } else if(command.equals("L")){
                scan.nextLine();
                System.out.println("Enter name of song to play: ");
                playlist.play(scan.nextLine());
            } else if(command.equals("C")){
                playlist.deleteAll();
                System.out.println("Playlist cleared.");
            } else if(command.equals("S")){
                playlist.shuffle();
                System.out.println("Playlist shuffled.");
                System.out.println("");
            } else if(command.equals("Z")){
                System.out.println("Playing a random song. . .");
                if(playlist.getSize() != 0){
                    playlist.random();
                } else{
                    System.out.println("Playlist is empty.");
                }
            } else if(command.equals("P")){
                playlist.printPlaylist();
            } else if(command.equals("T")){
                if(playlist.getSize() == 0){
                    System.out.println("Your playlist is empty.");
                } else if(playlist.getSize() == 1){
                    System.out.println("Your playlist contains 1 song.");
                } else{
                    System.out.println("Your playlist contains " + playlist.getSize() + " songs.");
                }
            } else if(command.equals("Q")){
                System.out.println("Program terminated.");
            } else{
                System.out.println("Invalid Option.");
                scan.nextLine();
            }
        } while(!command.equals("Q"));
    }
}

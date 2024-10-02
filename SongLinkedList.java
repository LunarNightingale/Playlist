/**
 * @author Siu Hin Nicholas Cheng
 * SBU ID: 116564445
 * email: Siuhin.Cheng@stonybrook.edu
 * Assignment #: HW2
 * Course: CSE 214
 * Recitation: R03 (Kailash Anand & Kevin Zheng)(Monday 5:00 P.M. - 5:55 P.M.)
 */
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.lang.IllegalArgumentException;
import java.util.Random;
public class SongLinkedList{
    private SongNode head, tail, cursor;
    private int size;

    /**
     * Default constructor for SongLinkedList.
     * @custom.postcondition
     * Head, tail, and cursor are initially set to null.
     */
    public SongLinkedList(){
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    /**
     * Getter for head.
     * @return
     * Head of linked list.
     */
    public SongNode getHead(){
        return head;
    }

    /**
     * Setter for head.
     * @param newHead
     * Updated head node.
     */
    public void setHead(SongNode newHead){
        if(head == null){
            cursor = newHead;
            tail = newHead;
            size++;
        } else{
            newHead.setNext(head.getNext());
        }
        head = newHead;
    }

    /**
     * Getter for tail.
     * @return
     * Tail of linked list.
     */
    public SongNode getTail(){
        return tail;
    }

    /**
     * Setter for tail.
     * @param newTail
     * Updated tail node.
     */
    public void setTail(SongNode newTail){
        if(tail == null){
            head = newTail;
            cursor = newTail;
            size++;
        } else{
            newTail.setPrev(tail.getPrev());
        }
        tail = newTail;
    }

    /**
     * Getter for cursor.
     * @return
     * Current cursor node.
     */
    public SongNode getCursor(){
        return cursor;
    }

    /**
     * Setter for cursor.
     * @param newCursor
     * Updated cursor node.
     */
    public void setCursor(SongNode newCursor){
        if(cursor == null){
            head = newCursor;
            tail = newCursor;
            size++;
        }
        cursor = newCursor;
    }

    /**
     * Plays the song indicated by name.
     * @param name
     * Name of the song to be played.
     * @custom.precondition
     * Name of the song must be in the playlist and there must also be a file associated with it.
     * @custom.postcondition
     * Song is now playing.
     * @throws IllegalArgumentException
     * Either song was not found in linked list or the file was not located.
     */
    public void play(String name) throws IllegalArgumentException{
        try{
            AudioInputStream AIS = AudioSystem.getAudioInputStream(new File(name + ".wav"));
            Clip c = AudioSystem.getClip();
            c.open(AIS);
            c.start();
            System.out.println("'" + name + "' by " + findNode(name).getData().getArtist() + " is now playing.");
        } catch(Exception e){
            System.out.println("'" + name + "' not found.");
        }
    }

    /**
     * Moves the cursor to point at the next SongNode.
     * @custom.precondition
     * List is not empty.
     * @custom.postcondition
     * Cursor has been advanced to the next SongNode, or has remained at the tail of the list.
     */
    public void cursorForwards(){
        if(cursor == null){
            System.out.println("Cannot move forwards, playlist is empty.");
        }
        if(cursor.getNext() != null){
            cursor = cursor.getNext();
            System.out.println("Cursor moved to the next song.");
        } else{
            System.out.println("Already at the end of the playlist.");
        }
    }
    /**
     * Moves the cursor to point at the previous SongNode.
     * @custom.precondition
     * List is not empty.
     * @custom.postcondition
     * Cursor has been advanced to the previous SongNode, or has remained at the head of the list.
     */
    public void cursorBackwards(){
        if(cursor == null){
            System.out.println("Cannot move backwards, playlist is empty.");
        }
        if(cursor.getPrev() != null){
            cursor = cursor.getPrev();
            System.out.println("Cursor moved to the previous song.");
        } else{
            System.out.println("Already at the beginning of the playlist.");
        }
    }
    /**
     * Inserts a song into the playlist after the cursor position.
     * @param newSong
     * Song that will be inserted into list.
     * @custom.precondition
     * newSong has been initialized.
     * @custom.postcondition.
     * Song has been inserted after the position of cursor, rest of playlist is preserved, and cursor now points at the inserted node.
     */
    public void insertAfterCursor(Song newSong){
        SongNode newNode = new SongNode(newSong);
        if(cursor == null){
            head = newNode;
            tail = newNode;
            cursor = newNode;
        } else{
            newNode.setNext(cursor.getNext());
            cursor.setNext(newNode);
            newNode.setPrev(cursor);
            if(cursor == tail){
                tail = newNode;
            } else{
                newNode.getNext().setPrev(newNode);
            }
            cursorForwards();
        }
        size++;
    }

    /**
     * Removes the SongNode referenced by the cursor and returns the Song retained within the node.
     * @custom.precondition
     * The cursor is not null.
     * @custom.postcondition
     * The SongNode referenced by the cursor has been removed from the playlist.
     * The cursor now references the next node, or the previous node if no next node exists.
     * @return
     * Song contained within the removed SongNode.
     */
    public Song removeCursor(){
        if(size == 0){
            return null;
        }
        Song song = cursor.getData();
        if(size == 1){
            deleteAll();
            return song;
        } else if(cursor == head){
            cursor = cursor.getNext();
            cursor.setPrev(null);
            head = cursor;
        } else if(cursor == tail){
            cursor = cursor.getPrev();
            cursor.setNext(null);
            tail = cursor;
        } else{
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = cursor.getNext();
        }
        size--;
        return song;
    }

    /**
     * Determines the number of song currently in the playlist.
     * @return
     * Playlist size.
     */
    public int getSize(){
        return size;
    }

    /**
     * Select a random song in the playlist and play it.
     * @custom.postcondition
     * Playlist is preserved and the randomly selected song is now playing.
     * @return
     * The song which is being randomly played.
     */
    public Song random(){
        SongNode rand = findRandom();
        play(rand.getData().getName());
        return rand.getData();
    }

    /**
     * Randomly shuffles the order of the songs contained within the playlist.
     * @custom.postcondition
     * The playlist is randomly reordered and the cursor should reference the SongNode which it was referencing before.
     */
    public void shuffle(){
        SongLinkedList shuffled = new SongLinkedList();
        SongNode original = cursor;
        for(int _i = size; _i > 0; _i--){
            shuffled.addNode(this.remove(this.findRandom()));
        }
        this.head = shuffled.head;
        this.cursor = original;
        this.tail = shuffled.tail;
        this.size = shuffled.size;
    }

    /**
     * Prints the playlist in a neatly formatted table.
     */
    public void printPlaylist(){
        String header = "%-29s %-30s %-30s %-30s";
        String str = "";
        System.out.println(str.format(header, "Song", "| Artist", "| Album", "| Length (s)"));
        System.out.println(toString());
    }

    /**
     * Deletes all the songs in the playlist.
     * @custom.postcondition
     * All songs have been removed.
     */
    public void deleteAll(){
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    /**
     * Returns a neatly formatted String representation of the playlist.
     * @return
     * A neatly formatted String from the playlist in tabular form.
     */
    public String toString(){
        String format = "%6s";
        String masterString = "";
        SongNode pointer = head;
        for(int _i = 0; _i < size; _i++){
            masterString += pointer.toString();
            if(pointer.equals(cursor)){
                masterString += String.format(format, "<-");
            }
            masterString += "\n";
            pointer = pointer.getNext();
        }
        return masterString;
    }

    /**
     * Finds a random Song inside the playlist.
     * @return
     * The randomly selected Song.
     */
    public SongNode findRandom(){
        Random gen = new Random();
        int rand = gen.nextInt(0, size);
        SongNode pointer = head;
        while(rand > 0){
            pointer = pointer.getNext();
            rand--;
        }
        return pointer;
    }

    /**
     * Adds a SongNode into the playlist.
     * @param newNode
     * The new SongNode.
     */
    public void addNode(SongNode newNode){
        if(tail != null){
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = tail.getNext();
        } else{
            tail = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Removes and returns a SongNode.
     * @param node
     * SongNode to be removed.
     * @return
     * SongNode that was removed, not connected. Cursor will still point at removed node if it was the cursor originally.
     */
    public SongNode remove(SongNode node){
        if(size == 1){
            head = null;
            tail = null;
        } else if(node == head){
            head = node.getNext();
        } else if(node == tail){
            tail = node.getPrev();

        } else{
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }
        size--;
        return node;
    }
    /**
     * Finds the node with the selected song.
     * @param song
     * Song to be searching for.
     * @return
     * Node that holds the song.
     */
    public SongNode findNode(String song){
        SongNode pointer = head;
        for(int _i = 0; _i < size; _i++){
            if(pointer.getData().getName().equals(song)){
                return pointer;
            } else{
                pointer = pointer.getNext();
            }
        }
        return null;
    }
}

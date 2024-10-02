/**
 * @author Siu Hin Nicholas Cheng
 * SBU ID: 116564445
 * email: Siuhin.Cheng@stonybrook.edu
 * Assignment #: HW2
 * Course: CSE 214
 * Recitation: R03 (Kailash Anand & Kevin Zheng)(Monday 5:00 P.M. - 5:55 P.M.)
 */
public class SongNode{
    private SongNode prev;
    private SongNode next;
    private Song data;

    /**
     * Default constructor for SongNode.
     */
    public SongNode(){
        prev = null;
        next = null;
        data = null;
    }

    /**
     * Specific constructor for SongNode.
     * @param song
     * A Song object we want to wrap.
     */
    public SongNode(Song song){
        prev = null;
        next = null;
        data = song;
    }

    /**
     * Getter for prev.
     * @return
     * Previous SongNode.
     */
    public SongNode getPrev(){
        return prev;
    }

    /**
     * Setter for prev.
     * @param before
     * Updated prev SongNode.
     */
    public void setPrev(SongNode before){
        prev = before;
    }

    /**
     * Getter for next.
     * @return
     * Next SongNode.
     */
    public SongNode getNext(){
        return next;
    }

    /**
     * Setter for next.
     * @param after
     * Updated next SongNode.
     */
    public void setNext(SongNode after){
        next = after;
    }

    /**
     * Getter for data.
     * @return
     * The Song inside the SongNode.
     */
    public Song getData(){
        return data;
    }
    /**
     * Setter for data.
     * @return
     * Update the Song inside the SongNode.
     */
    public void setData(Song newData){
        data = newData;
    }

    /**
     * Removes the SongNode.
     * @return
     * The removed SongNode.
     */
    public SongNode removeNode(){
        setPrev(null);
        setNext(null);
        return this;
    }

    /**
     * toString for SongNode.
     * @return
     * String containing SongNode in tabular form.
     */
    public String toString(){
        return data.toString();
    }
}

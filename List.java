import java.io.EOFException;

/**
 * A linked list of character data objects.
 * (Actually, a list of Node objects, each holding a reference to a character
 * data object.
 * However, users of this class are not aware of the Node objects. As far as
 * they are concerned,
 * the class represents a list of CharData objects. Likwise, the API of the
 * class does not
 * mention the existence of the Node objects).
 */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;

    public static void main(String args[]) {
        // test function to see everything is working.
        List myList = new List();
        myList.addFirst('c');
        myList.update('c');
        myList.update('a');
        myList.update('b');
        myList.update('D');

    }

    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
        return size;
    }

    /** Returns the first element in the list */
    public CharData getFirst() {
        return first.cp;
    }

    /**
     * GIVE Adds a CharData object with the given character to the beginning of this
     * list.
     */
    public void addFirst(char chr) {
        CharData newCP = new CharData(chr);
        Node newFirst = new Node(newCP, this.first);
        this.first = newFirst;
        // Your code goes here

        this.size++;
    }

    /** GIVE Textual representation of this list. */
    public String toString() {
        // Your code goes here
        String toPrint = "(";
        Node pointer = first;
        while (pointer.next != null) {
            toPrint += pointer.cp.toString() + " ";
            pointer = pointer.next;
        }
        if (pointer.next == null) {
            toPrint += pointer.cp.toString();
        }
        toPrint += ")";
        return toPrint;
    }

    /**
     * Returns the index of the first CharData object in this list
     * that has the same chr value as the given char,
     * or -1 if there is no such object in this list.
     */
    public int indexOf(char chr) {
        // Your code goes here
        Node testNode = first;
        int index = 0;
        if (first == null) {
            return -1;
        }
        while (testNode.next != null) {
            if (testNode.cp.chr == chr) {
                return index;
            }
            index++;
            testNode = testNode.next;
        }
        if (testNode.next == null) {
            if (testNode.cp.chr == chr) {
                return index;
            }
        }
        return -1;
    }

    /**
     * If the given character exists in one of the CharData objects in this list,
     * increments its counter. Otherwise, adds a new CharData object with the
     * given chr to the beginning of this list.
     */
    public void update(char chr) {
        // Your code goes here
        // if there is already a node with this character, indexof will return the
        // position.
        // then we create a new listiterator and upadate the count of the charData of
        // the node at that position.
        if (indexOf(chr) != -1) {
            listIterator(indexOf(chr)).current.cp.count++;

        }
        // if indexof returns -1, then there is no node with a chardata containing that
        // chr, and we add one using addfirst.
        if (indexOf(chr) == -1) {
            this.addFirst(chr);
        }
    }

    /**
     * GIVE If the given character exists in one of the CharData objects
     * in this list, removes this CharData object from the list and returns
     * true. Otherwise, returns false.
     */
    public boolean remove(char chr) {
        // Your code goes here
        if (first.cp.chr == chr) {
            first = first.next;
            return true;
        }
        Node pointer = first;
        while (pointer.next != null) {
            if (pointer.next.cp.chr == chr) {
                Node temp = pointer.next.next;
                pointer.next = temp;
                this.size--;
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    /**
     * Returns the CharData object at the specified index in this list.
     * If the index is negative or is greater than the size of this list,
     * throws an IndexOutOfBoundsException.
     */
    public CharData get(int index) {
        int listIndex = 0;
        if (index < 0) {
            throw new IndexOutOfBoundsException("index cannot be negative");
        }
        Node pointer = first;
        while (pointer.next != null && listIndex <= index) {
            if (listIndex == index) {
                return pointer.cp;
            }
            listIndex++;
            pointer = pointer.next;
        }
        if (pointer.next == null) {
            return pointer.cp;
        }
        throw new IndexOutOfBoundsException("Index out of bounds");

        // Your code goes here
    }

    /**
     * Returns an array of CharData objects, containing all the CharData objects in
     * this list.
     */
    public CharData[] toArray() {
        CharData[] arr = new CharData[size];
        Node current = first;
        int i = 0;
        while (current != null) {
            arr[i++] = current.cp;
            current = current.next;
        }
        return arr;
    }

    /**
     * Returns an iterator over the elements in this list, starting at the given
     * index.
     */
    public ListIterator listIterator(int index) {
        if (index < 0 || index > size) { // Note: it's common to allow an iterator at size (pointing to the end of the
                                         // list)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node current = first;
        for (int i = 0; i < index && current != null; i++) {
            current = current.next;
        }

        return new ListIterator(current); // Assumes ListIterator can handle a null current for end-of-list
    }
}

/**
 * Created by Evgenii on 15. 9. 27..
 */
/** Source code example for "A Practical Introduction to Data
 Structures and Algorithm Analysis, 3rd Edition (Java)"
 by Clifford A. Shaffer
 Copyright 2008-2011 by Clifford A. Shaffer
 */

/** Linked list implementation */
class SingleLinkedList<E> implements List<E> {
    private Link<E> head;         // Pointer to list header
    private Link<E> tail;         // Pointer to last element
    protected Link<E> curr;       // Access to current element
    int cnt;		      // Size of list

    /** Constructors */
    SingleLinkedList(int size) { this(); }   // Constructor -- Ignore size
    SingleLinkedList() {
        //Create header_
        curr = tail = head = new Link<E>(null);
        cnt = 0;
    }

    @Override
    public void clear() {
        head.setNext(null);     //Drop access to links
        curr = tail = head = new Link<E>(null);     //Create header
        cnt = 0;

    }
    @Override
    public void insert(E item) {
        curr.setNext(new Link<E>(item, curr.next()));
        if (tail == curr) tail = curr.next(); //New tail
        cnt++;

    }
    @Override
    public void append(E item) {
        tail = tail.setNext(new Link<E>(item, null));
        cnt++;

    }
    @Override
    public E remove() {
        if (curr.next() == null) return null; //Nothing to remove
        E item = curr.next().element();       //Remember value
        if (tail == curr.next()) tail = curr; //Removed last
        curr.setNext(curr.next().next());     //Remove from list
        cnt--;
        return item;
    }
    @Override
    public void moveToStart() {
        curr = head;

    }
    @Override
    public void moveToEnd() {
        curr = tail;

    }
    @Override
    public void prev() {
        if (curr == head) return; //No previous element
        Link<E> temp = head;
        //Step down the list until we find the previous element
        while (temp.next() != curr)
            temp = temp.next();
        curr = temp;

    }
    @Override
    public void next() {
        if (curr != tail)
            curr = curr.next();

    }
    @Override
    public int length() {
        return cnt;

    }
    @Override
    public int currPos() {
        Link<E> temp = head;
        int i;
        for (i = 0; curr != temp; i++)
            temp = temp.next();
        return i;
    }
    @Override
    public void moveToPos(int pos) {
        assert (pos >= 0) && (pos <= cnt) : "Position out of range";
        curr = head;
        for (int i = 0; i < pos; i++)
            curr = curr.next();


    }
    @Override
    public E getValue() {
        if (curr.next() == null)
            return null;
        return curr.next().element();
    }


}

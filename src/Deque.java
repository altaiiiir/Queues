import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    // first node
    private Node first;
    // last node
    private Node last;
    private int size;

    // Node class
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){ return size == 0; }

    // return the number of items on the deque
    public int size(){ return size; }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null) throw new IllegalArgumentException("? cant add nothing lol");
        // this variable saves the current first
        Node temp = this.first;

        this.first = new Node();
        this.first.item = item;
        this.first.next = temp;

        // if false it means this is the first added node - set last
        if(this.last != null) this.first.next.prev = this.first;
        else this.last = this.first;

        // increase size
        this.size++;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null) throw new IllegalArgumentException("pls provide good input");
        // this variables saves the current last node
        Node temp = this.last;

        this.last = new Node();
        this.last.item = item;
        this.last.prev = temp;

        // if false it means this is the first added node - set first
        if(this.first != null) this.last.prev.next = this.last;
        else this.first = this.last;

        this.size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException("? cant remove from empty list lol");
        Item item = this.first.item;

        // remove first node
        boolean hasNext = this.first.next != null;

        if(!hasNext) {
            this.last = null;
            this.first = null;
        } else {
            this.first = this.first.next;
            this.first.prev = null;
        } this.size--; return item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException("cant remove from empty list bozo");
        Item item = this.last.item;

        // remove first node
        boolean hasPrev = this.last.prev != null;

        if(!hasPrev) {
            this.first = null;
            this.last = null;
        } else {
            this.last = this.last.prev;
            this.last.next = null;
        } this.size--; return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){ return new ListIterator(); }

    // Iterator class
    private class ListIterator implements Iterator<Item>{
        Node curr = first;

        @Override
        public boolean hasNext() { return curr.next!=null; }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("no next node :(");
            else {
                Item item = curr.item;
                curr = curr.next;
                return item;
            }
        }

        @Override
        public void remove() { throw new UnsupportedOperationException("cant do sadge"); }
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(1);
        deque.removeLast();
        System.out.println("Empty status: " + deque.isEmpty());

        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
            deque.addLast(i * 10);
        }

        System.out.println("Removing last: " + deque.removeLast());
        System.out.println("Current size: " + deque.size());

        while (!deque.isEmpty())
            System.out.println("Removing first: " + deque.removeFirst());

        deque.addFirst(1);
        System.out.println("Removing first: " + deque.removeFirst());
        deque.addFirst(2);
        System.out.println("Removing first: " + deque.removeFirst());
        deque.addLast(0);
        deque.removeFirst();
        deque.addLast(3);
        deque.addLast(4);
        deque.removeFirst();
        deque.removeLast();
        deque.addFirst(8);




        /*while (!StdIn.isEmpty()) {
            String s = StdIn.readString();

            if (s.equals("-")) StdOut.print(deque.removeLast());
            else if (s.equals("+")) StdOut.print(deque.removeFirst());
            else deque.addFirst(s);
        }*/
    }

}
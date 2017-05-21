/*************************************************************************
 *  Compilation    : javac Deque.java
 *  Execution      : java Deque
 *  
 *  Author         : Andrey Malyshenko
 *  Email          : andrey.malysheko@gmail.com
 *  Date           : 2014/02/21
 *  
 *
 *  This package is part of Practical assignment in online course Algorithms, Part I
 *  by Kevin Wayne, Robert Sedgewick
 *  https://class.coursera.org/algs4partI-004
 *
 *  Please find assignment details here:
 *  http://coursera.cs.princeton.edu/algs4/assignments/queues.html
 *  
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private Node first, last;
    private int size = 0;
    
    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    
    private class ListIterator implements Iterator<Item> {
        
        private Node current = first;
        
        public boolean hasNext() {
            return current.next != null;
        }
        
        public void remove() {
            
        }
        
        public Item next() {
            if (current.next == null) {
                throw new NoSuchElementException();
            } else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }
    
    // construct an empty deque
    public Deque() {
        
    }
    
    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }
    
    // return the number of items on the deque
    public int size() {
        return size;
    }
    
    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            if (isEmpty()) {
                last = first;
            } else {
                oldfirst.prev = first;
                first.next = oldfirst;
            }
            size++; 
        }
    }
    
    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            if (isEmpty()) {
                first = last;
            } else {
                oldlast.next = last;
                last.prev = oldlast;
            }
            size++;
        }
    }
    
    // delete and return the item at the front
    public Item removeFirst() {
        Item item = first.item;
        first = first.next;
        size--;
        
        if (isEmpty()) {
            last = first;
        } else {
            first.prev = null;
        }
        return item;
    }
    
    // delete and return the item at the end
    public Item removeLast()                 
    {
        Item item = last.item;
        last = last.prev;
        size--;
        
        if (isEmpty()) {
            first = last;
        } else {
            last.next = null;
        }
        return item;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator();
    }         
    
    // unit testing
    public static void main(String[] args) {
        String s = "Test string";
        Deque<Character> test = new Deque<Character>();
        
        System.out.print("\n");
        System.out.println("==============================");
        for (char c : s.toCharArray()) {
            test.addLast(c);
        }
        for (int i = 0; i < s.length(); i++) {
            System.out.print(test.removeLast());
        }
        
        System.out.print("\n");
        System.out.println("==============================");
        for (char c : s.toCharArray()) {
            test.addFirst(c);
        }
        for (int i = 0; i <= s.length(); i++) {
            System.out.print(test.removeFirst());
        }
        System.out.print("\n");
        System.out.println("==============================");
        
        test.addFirst(null);
    }
 }
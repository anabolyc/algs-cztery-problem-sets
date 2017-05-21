/*************************************************************************
 *  Compilation    : javac RandomizedQueue.java
 *  Execution      : java RandomizedQueue
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

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first, last;
    private int size = 0;
    
    private class Node {
        Item item;
        Node next;
        //Node prev;
    }
    
    private class ListIterator implements Iterator<Item> {
        
        private Item[] copy;
        private int size = 0;
        
        @SuppressWarnings("unchecked")
        public ListIterator(RandomizedQueue<Item> Queue) {
            this.size = Queue.size();
            Node item = Queue.first;
            copy = (Item[])new Object[size];
            for (int i = 0; i < size; i++) {
                copy[i] = item.item;
                item = item.next;
            }
            StdRandom.shuffle(this.copy);
        }
        
        public boolean hasNext() {
            return size > 0;
        }
        
        public void remove() {
            
        }
        
        public Item next() {
            return copy[--size]; 
        }
    }
    
    // construct an empty randomized queue
    public RandomizedQueue() { }   
    
    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }
    
    // return the number of items on the deque
    public int size() {
        return size;
    } 
    
    // add the item
    public void enqueue(Item item) {
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
                //last.prev = oldlast;
            }
            size++;
        }
    }  
    
    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        } else {
            int index = StdRandom.uniform(size);
            Node sample = first, prev = null;
            if (index == 0) {
                first = sample.next;
            } else {
                for (int i = 0; i < index; i++) {
                    prev = sample;
                    sample = sample.next;
                }
                prev.next = sample.next;
            }
            
            /*if (sample.prev != null) {
                sample.prev.next = sample.next;
            } else {
                first = sample.next;
            }
            */
            /*
            if (sample.next != null) {
                sample.next.prev = sample.prev;
            } else {
                last = sample.prev;
            }*/
            size--;
            
            if (isEmpty()) {
                last = first;
            }
            return sample.item;
        }
    }   
    
    public void show() {
        Node sample = first;
        System.out.print(":");
        while (sample != null) {
            System.out.print(sample.item);
            sample = sample.next;
        }
    };
    
    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        } else {
            int index = StdRandom.uniform(size);
            Node sample = first;
            for (int i = 0; i < index; i++) {
                sample = sample.next;
            }
            return sample.item;
        }
    }   
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator(RandomizedQueue.this);
    }  
    
    // unit testing
    public static void main(String[] args) {
        String s = "123456";
        RandomizedQueue<Character> test = new RandomizedQueue<Character>();
        
        System.out.println("\n=========samples=====================");
        for (char c : s.toCharArray()) {
            test.enqueue(c);
        }
        for (int i = 0; i < s.length()*5; i++) {
            System.out.print(test.sample());
        }
        
        System.out.println("\n=========dequeue=====================");
        for (int i = 0; i < s.length(); i++) {
            System.out.print("\n");
            System.out.print(test.dequeue());
            test.show();
        }
        
        System.out.println("\n=========fill again=====================");
        for (char c : s.toCharArray()) {
            test.enqueue(c);
        }

        for (int i = 0; i < s.length()*5; i++) {
            System.out.print(test.sample());
        }
        
        System.out.println("\n=========iterator 1=====================");
        for(Character c: test) {
            System.out.print(c);
        }

        System.out.println("\n=========iterator 2=====================");
        for(Character c: test) {
            System.out.print(c);
        }

        System.out.println("\n");
        test.show();
    } 
 }

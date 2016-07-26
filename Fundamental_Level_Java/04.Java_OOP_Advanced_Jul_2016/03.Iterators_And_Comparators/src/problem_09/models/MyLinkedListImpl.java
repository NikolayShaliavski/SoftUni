package problem_09.models;

import problem_09.contracts.MyLinkedList;

import java.util.Iterator;

/**
 * Created by Nikolay Shalyavski on 26.7.2016 г..
 */
public class MyLinkedListImpl<E extends Comparable<E>> implements MyLinkedList<E> {

    private class ListNode<E extends Comparable<E>> {

        E value;
        ListNode<E> next;
        ListNode<E> prev;

        ListNode(E value) {
            this.value = value;
        }

        E getValue() {
            return this.value;
        }
    }

    private ListNode<E> head;
    private ListNode<E> tail;
    private int size;

    public MyLinkedListImpl() {
        this.head = null;
        this.tail = this.head;
        this.size = 0;
    }

    @Override
    public void add(E element) {

        if (this.size == 0) {
            this.head = new ListNode<E>(element);
            this.tail = this.head;
        } else {
            ListNode newTail = new ListNode(element);
            newTail.prev = this.tail;
            this.tail.next = newTail;
            this.tail = newTail;
        }
        this.size++;
    }

    @Override
    public boolean remove(E element) {
        if (this.size == 0) {
            return false;
        }
        if (element == this.head.getValue()) {
            this.head = this.head.next;
            this.size--;
            return true;
        }

        ListNode current = this.head;
        for (int i = 0; i < this.size; i++) {
            if (current.getValue() == element) {
                if (current.next == null) {
                    this.tail = this.tail.prev;
                    this.tail.next = null;
                    this.size--;
                    return true;
                }
                current.next.prev = current.prev;
                current.prev.next = current.next;
                this.size--;
                return true;
            } else {
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {

        Iterator<E> iterator = new Iterator<E>() {
            ListNode<E> currentNode = head;

            @Override
            public boolean hasNext() {
                return this.currentNode != null;
            }

            @Override
            public E next() {
                E element = this.currentNode.getValue();
                this.currentNode = this.currentNode.next;
                return element;
            }
        };
        return iterator;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}

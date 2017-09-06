using System;
using System.Collections;
using System.Collections.Generic;

class Node<T>
{
    public Node(T value, Node<T> next)
    {
        this.Value = value;
        this.Next = next;
    }

    public T Value { get; private set; }

    public Node<T> Next { get; set; }

}

class LinkedList<T> : IEnumerable
{
    public Node<T> Head { get; private set; }

    public int Count { get; private set; }

    public void AddFirst(T element)
    {
        this.Head = new Node<T>(element, this.Head);
        this.Count++;
    }

    public void AddLast(T element)
    {
        if (this.Head == null)
        {
            this.Head = new Node<T>(element, null);
        }
        else
        {
            Node<T> temp = this.Head;
            while (temp.Next != null)
            {
                temp = temp.Next;
            }
            temp.Next = new Node<T>(element, null);
        }
        this.Count++;
    }

    public T Remove(int index)
    {
        if (index >= this.Count || index < 0)
        {
            throw new IndexOutOfRangeException();
        }
        if (index == 0)
        {
            T removeFirstElement = this.Head.Value;
            this.Head = this.Head.Next;
            return removeFirstElement;
        }

        Node<T> temp = this.Head;
        int indexCounter = 0;
        while (indexCounter < index - 1)
        {
            temp = temp.Next;
            indexCounter++;
        }
        T removedElement = temp.Next.Value;
        temp.Next = temp.Next.Next;
        return removedElement;
    }

    public int FirstIndexOf(T element)
    {
        Node<T> temp = this.Head;
        int index = 0;
        while (temp != null)
        {
            if (temp.Value.Equals(element))
            {
                return index;
            }
            temp = temp.Next;
            index++;
        }
        return -1;
    }

    public int LastIndexOf(T element)
    {
        Node<T> temp = this.Head;
        int indexCounter = 0;
        int index = -1;
        while (temp != null)
        {
            if (temp.Value.Equals(element))
            {
                index = indexCounter;
            }
            temp = temp.Next;
            indexCounter++;
        }
        return index;
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }

    public IEnumerator<T> GetEnumerator()
    {
        Node<T> currentElement = this.Head;
        while (currentElement != null)
        {
            yield return currentElement.Value;
            currentElement = currentElement.Next;
        }
    }
}


using System;
using System.Collections;
using System.Collections.Generic;

class Node<T>
{
    public Node(T value, Node<T> next = null)
    {
        this.Value = value;
        this.Next = next;
    }

    public T Value { get; set; }

    public Node<T> Next { get; set; }
}

class LinkedStack<T> : IEnumerable
{
    public int Count { get; private set; }

    private Node<T> firstNode;

    public void Push(T element)
    {
        this.firstNode = new Node<T>(element, this.firstNode);
        this.Count++;
    }

    public T Peek()
    {
        if (this.Count == 0)
        {
            throw new IndexOutOfRangeException();
        }

        return this.firstNode.Value;
    }

    public T Pop()
    {
        if (this.Count == 0)
        {
            throw new IndexOutOfRangeException();
        }

        T poppedElement = this.firstNode.Value;
        this.firstNode = this.firstNode.Next;
        this.Count--;
        return poppedElement;
    }

    public T[] ToArray()
    {
        if (this.Count == 0)
        {
            throw new InvalidOperationException("Stack is empty.");
        }
        T[] stackArr = new T[this.Count];
        Node<T> tempNode = this.firstNode;
        for (int i = 0; i < this.Count; i++)
        {
            stackArr[i] = tempNode.Value;
            tempNode = tempNode.Next;
        }
        return stackArr;
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }

    public IEnumerator<T> GetEnumerator()
    {
        Node<T> tempNode = this.firstNode;
        while (tempNode != null)
        {
            yield return tempNode.Value;
            tempNode = tempNode.Next;
        }
    }
}


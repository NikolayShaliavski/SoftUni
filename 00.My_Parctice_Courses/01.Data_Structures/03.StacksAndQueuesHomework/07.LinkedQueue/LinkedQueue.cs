using System;
using System.Collections;
using System.Collections.Generic;

class QueueNode<T>
{
    public T Value { get; private set; }

    public QueueNode<T> Next { get; set; }

    public QueueNode<T> Prev { get; set; }

    public QueueNode(T value)
    {
        this.Value = value;
    }
}

class LinkedQueue<T> : IEnumerable
{
    public int Count { get; private set; }

    public QueueNode<T> Head { get; set; }

    public QueueNode<T> Tail { get; set; }

    public void Enqueue(T element)
    {
        if (this.Count == 0)
        {
            this.Head = this.Tail = new QueueNode<T>(element);
        }
        else
        {
            QueueNode<T> newNode = new QueueNode<T>(element);
            newNode.Prev = this.Tail;
            this.Tail.Next = newNode;
            this.Tail = newNode;
        }
        this.Count++;
    }

    public T Peek()
    {
        if (this.Count == 0)
        {
            throw new IndexOutOfRangeException();
        }
        return this.Head.Value;
    }

    public T Dequeue()
    {
        if (this.Count == 0)
        {
            throw new IndexOutOfRangeException();
        }

        T dequeuedElement = this.Head.Value;
        this.Head = this.Head.Next;
        this.Head.Prev = null;
        this.Count--;
        return dequeuedElement;
    }

    public T[] ToArray()
    {
        if (this.Count == 0)
        {
            throw new InvalidOperationException("Queue is empty.");
        }
        T[] queueArr = new T[this.Count];
        QueueNode<T> tempNode = this.Head;
        for (int i = 0; i < this.Count; i++)
        {
            queueArr[i] = tempNode.Value;
            tempNode = tempNode.Next;
        }
        return queueArr;
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }

    public IEnumerator<T> GetEnumerator()
    {
        QueueNode<T> tempNode = this.Head;
        while (tempNode != null)
        {
            yield return tempNode.Value;
            tempNode = tempNode.Next;
        }
    }
}



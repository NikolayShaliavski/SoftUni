using System;

public class CircularQueue<T>
{
    private const int InitialCapacity = 16;

    private T[] elements;
    private int head;
    private int tail;

    public int Count { get; private set; }

    public CircularQueue(int capacity = InitialCapacity)
    {
        this.elements = new T[capacity];
    }

    public void Enqueue(T element)
    {
        if (this.Count >= this.elements.Length)
        {
            this.Resize();
        }
        this.elements[this.tail] = element;
        this.tail = (this.tail + 1) % this.elements.Length;
        this.Count++;
    }

    public T Dequeue()
    {
        if (this.Count == 0)
        {
            throw new InvalidOperationException("The queue is empty.");
        }

        T element = this.elements[this.head];
        this.elements[this.head] = default(T);
        this.head = (this.head + 1) % this.elements.Length;
        this.Count--;
        return element;
    }

    public T[] ToArray()
    {
        if (this.Count == 0)
        {
            throw new InvalidOperationException("The queue is empty.");
        }
        T[] elementsArr = new T[this.Count];
        this.copyElements(elementsArr);

        return elementsArr;
    }

    private void Resize()
    {
        T[] newElemets = new T[this.elements.Length * 2];
        this.copyElements(newElemets);
        this.elements = newElemets;
        this.head = 0;
        this.tail = this.Count;
    }

    private void copyElements(T[] sourceArr)
    {
        var sourceIndex = this.head;

        for (int i = 0; i < this.Count; i++)
        {
            sourceArr[i] = this.elements[sourceIndex];
            sourceIndex = (sourceIndex + 1) % this.elements.Length;
        }
    }
}


class Example
{
    static void Main()
    {
        var queue = new CircularQueue<int>();

        queue.Enqueue(1);
        queue.Enqueue(2);
        queue.Enqueue(3);
        queue.Enqueue(4);
        queue.Enqueue(5);
        queue.Enqueue(6);

        Console.WriteLine("Count = {0}", queue.Count);
        Console.WriteLine(string.Join(", ", queue.ToArray()));
        Console.WriteLine("---------------------------");

        var first = queue.Dequeue();
        Console.WriteLine("First = {0}", first);
        Console.WriteLine("Count = {0}", queue.Count);
        Console.WriteLine(string.Join(", ", queue.ToArray()));
        Console.WriteLine("---------------------------");

        queue.Enqueue(-7);
        queue.Enqueue(-8);
        queue.Enqueue(-9);
        Console.WriteLine("Count = {0}", queue.Count);
        Console.WriteLine(string.Join(", ", queue.ToArray()));
        Console.WriteLine("---------------------------");

        first = queue.Dequeue();
        Console.WriteLine("First = {0}", first);
        Console.WriteLine("Count = {0}", queue.Count);
        Console.WriteLine(string.Join(", ", queue.ToArray()));
        Console.WriteLine("---------------------------");

        queue.Enqueue(-10);
        Console.WriteLine("Count = {0}", queue.Count);
        Console.WriteLine(string.Join(", ", queue.ToArray()));
        Console.WriteLine("---------------------------");

        first = queue.Dequeue();
        Console.WriteLine("First = {0}", first);
        Console.WriteLine("Count = {0}", queue.Count);
        Console.WriteLine(string.Join(", ", queue.ToArray()));
        Console.WriteLine("---------------------------");
    }
}

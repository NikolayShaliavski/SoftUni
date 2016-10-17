using System;

public class ArrayStack<T>
{
    private const int IniitialCapacity = 16;

    private T[] elements;

    public ArrayStack(int capacity = IniitialCapacity)
    {
        this.elements = new T[capacity];
    }

    public int Count { get; set; }

    public void Push(T element)
    {
        if (this.Count >= this.elements.Length)
        {
            this.Resize();
        }

        this.elements[this.Count] = element;
        this.Count++;
    }

    public T Pop()
    {
        if (this.Count == 0)
        {
            throw new InvalidOperationException("Stack is empty.");
        }
        T poppedElement = this.elements[this.Count - 1];
        this.elements[this.Count] = default(T);
        this.Count--;
        return poppedElement;
    }

    public T Peek()
    {
        T element = this.elements[this.Count - 1];
        return element;
    }

    public int Capacity()
    {
        return this.elements.Length;
    }

    public T[] ToArray()
    {
        T[] arr = new T[this.Count];
        for (int i = this.Count - 1; i >= 0; i--)
        {
            arr[i] = this.elements[i];
        }
        Array.Reverse(arr);
        return arr;
    }

    private void Resize()
    {
        T[] sourceArr = new T[this.elements.Length * 2];
        for (int i = 0; i < this.elements.Length; i++)
        {
            sourceArr[i] = this.elements[i];
        }
        this.elements = sourceArr;
    }
}

using System;
using System.Collections;
using System.Collections.Generic;

class ReversedList<T> : IEnumerable
{
    private const int InitialCapacity = 16;

    private T[] elements;

    public ReversedList(int capacity = InitialCapacity)
    {
        this.elements = new T[capacity];
        this.Capacity = this.elements.Length;
    }

    public int Count { get; private set; }

    public int Capacity { get; private set; }

    public void Add(T element)
    {
        if (this.Count >= this.elements.Length)
        {
            this.Resize();
        }

        this.elements[this.Count] = element;
        this.Count++;
    }

    public T Get(int index)
    {
        if (index > this.Count || index < 0)
        {
            throw new IndexOutOfRangeException();
        }

        return this.elements[this.Count - index - 1];
    }

    public T Remove(int index)
    {
        if (index >= this.Count || index < 0)
        {
            throw new IndexOutOfRangeException();
        }

        int indexToRemove = this.Count - index - 1;
        T elementToRemove = this.elements[indexToRemove];

        this.RearrangeElements(indexToRemove);

        this.Count--;
        return elementToRemove;
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }

    public IEnumerator<T> GetEnumerator()
    {
        for (int i = this.Count - 1; i >= 0; i--)
        {
            yield return this.elements[i];
        }
    }

    private void RearrangeElements(int indexToRemove)
    {
        T[] tempArr = new T[this.elements.Length];
        for (int i = 0; i < indexToRemove; i++)
        {
            tempArr[i] = this.elements[i];
        }
        for (int i = indexToRemove + 1; i < this.Count; i++)
        {
            tempArr[i - 1] = this.elements[i];
        }
        this.elements = tempArr;
    }

    private void Resize()
    {
        T[] destinationArr = new T[2 * this.elements.Length];
        Array.Copy(this.elements, destinationArr, this.elements.Length);
        this.elements = destinationArr;
    }
}


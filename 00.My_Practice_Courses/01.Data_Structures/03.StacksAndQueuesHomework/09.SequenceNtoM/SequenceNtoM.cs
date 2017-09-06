using System;
using System.Collections.Generic;
using System.Linq;

class Item
{
    public Item(int value, Item item)
    {
        this.Value = value;
        this.Prev = item;
    }

    public int Value { get; private set; }

    public Item Prev { get; private set; }
}

class SequenceNtoM
{
    static void Main(string[] args)
    {
        var numbers = Console.ReadLine().Split().Select(int.Parse).ToArray();
        var startNumber = numbers[0];
        var targetNumber = numbers[1];

        if (startNumber >= targetNumber)
        {
            Console.WriteLine("(no solution)");
            Environment.Exit(0);
        }
        Queue<Item> sequence = new Queue<Item>();
        Item startItem = new Item(startNumber, null);

        sequence.Enqueue(startItem);

        while (sequence.Count > 0)
        {
            Item currItem = sequence.Dequeue();
            if (currItem.Value < targetNumber)
            {
                sequence.Enqueue(new Item(currItem.Value + 1, currItem));
                sequence.Enqueue(new Item(currItem.Value + 2, currItem));
                sequence.Enqueue(new Item(currItem.Value * 2, currItem));
            }
            else if (currItem.Value == targetNumber)
            {
                PrintSolution(currItem);
               Environment.Exit(0);
            }
        }
    }

    private static void PrintSolution(Item item)
    {
        var outputNumbers = new LinkedList<int>();
        while (item != null)
        {
            outputNumbers.AddFirst(item.Value);
            item = item.Prev;
        }
        Console.WriteLine(string.Join(" -> ", outputNumbers));
    }
}


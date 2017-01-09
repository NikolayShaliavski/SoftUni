using System;
using System.Collections.Generic;

class ClaculateSequenceWithQueue
{
    static void Main()
    {
        var startNumber = int.Parse(Console.ReadLine());

        Queue<int> sequence = new Queue<int>();
        var counter = 0;
        var numbers = new int[50];
        sequence.Enqueue(startNumber);

        while (counter < 50)
        {
            var num = sequence.Dequeue();
            numbers[counter] = num;
            counter++;

            sequence.Enqueue(num + 1);
            sequence.Enqueue(2 * num + 1);
            sequence.Enqueue(num + 2);
        }
        Console.WriteLine(string.Join(", ", numbers));
    }
}


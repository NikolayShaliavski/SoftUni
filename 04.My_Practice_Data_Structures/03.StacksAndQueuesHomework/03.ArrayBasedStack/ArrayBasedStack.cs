using System;
using System.Collections.Generic;

class ArrayBasedStack
{
    static void Main()
    {
        var test = new ArrayStack<int>();

        for (int i = 0; i < 10; i++)
        {
            test.Push(i);
        }

        Console.WriteLine(string.Join(" ", test.ToArray()));
    }
}


using System;
using System.Runtime.InteropServices;

class LinkedStackMain
{
    static void Main(string[] args)
    {
        LinkedStack<int> testStack = new LinkedStack<int>();

        testStack.Push(1);
        testStack.Push(2);
        testStack.Push(3);
        testStack.Push(4);
        testStack.Push(5);

        testStack.Pop();
        testStack.Pop();

        Console.WriteLine(testStack.Count);
        PrintStack(testStack);
    }

    private static void PrintStack(LinkedStack<int> testStack)
    {
        foreach (var num in testStack)
        {
            Console.Write(num + " ");
        }
        Console.WriteLine();
    }
}

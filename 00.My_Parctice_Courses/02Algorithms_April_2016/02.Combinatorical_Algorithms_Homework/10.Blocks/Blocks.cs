using System;
using System.Collections.Generic;

class Blocks
{
    static char[] block;
    static char[] letters;
    static bool[] used;
    static ISet<string> allBlocks;
    static ISet<string> uniqueBlocks;

    static void Main(string[] args)
    {
        var n = int.Parse(Console.ReadLine());
        FillLetters(n);
        allBlocks = new HashSet<string>();
        uniqueBlocks = new HashSet<string>();
        block = new char[4];
        used = new bool[n];

        GenerateBlock(0);

        Console.WriteLine("Number of blocks: {0}", uniqueBlocks.Count);

        foreach (var block in uniqueBlocks)
        {
            Console.WriteLine(block);
        }
    }

    private static void GenerateBlock(int index)
    {
        if (index > block.Length - 1)
        {
            string variation = new string(block);
            if (!allBlocks.Contains(variation))
            {
                uniqueBlocks.Add(variation);
                RotateBlock();
            }
        }
        else
        {
            for (int i = 0; i < letters.Length; i++)
            {
                if (!used[i])
                {
                    used[i] = true;
                    block[index] = letters[i];
                    GenerateBlock(index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void RotateBlock()
    {
        allBlocks.Add(new string(block));
        allBlocks.Add(new string(new char[] { block[3], block[0], block[1], block[2] }));
        allBlocks.Add(new string(new char[] { block[2], block[3], block[0], block[1] }));
        allBlocks.Add(new string(new char[] { block[1], block[2], block[3], block[0] }));
    }

    private static void FillLetters(int n)
    {
        letters = new char[n];
        int ascii = 65;
        for (int i = 0; i < n; i++)
        {
            letters[i] = (char)ascii;
            ascii++;
        }
    }
}


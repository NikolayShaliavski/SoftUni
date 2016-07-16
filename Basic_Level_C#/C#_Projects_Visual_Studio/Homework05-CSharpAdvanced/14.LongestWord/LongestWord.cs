using System;

class LongestWord
{
    static void Main()
    {
        string[] text = Console.ReadLine().Split(' ', ',', '-', '.', '!', '?', ':', ';');
        int length = 0;
        string word = string.Empty;

        for (int i = 0; i < text.Length; i++)
        {
            if (text[i].Length > length)
            {
                length = text[i].Length;
                word = text[i];
            }
        }
        Console.WriteLine(word);
    }
}

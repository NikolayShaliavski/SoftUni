using System;

class CountWordsInText
{
    static void Main()
    {
        string word = Console.ReadLine();
        string text = Console.ReadLine();

        string[] textArray = text.ToLower().Split(',', '.', ' ', '\t', '\n', ':', ';', '"', '!', '?', '-', '@', '(', ')', '\\', '/');
        int count = 0;
        
        for (int i = 0; i < textArray.Length; i++)
        {
            if (textArray[i].Equals(word, StringComparison.CurrentCultureIgnoreCase))
            {
                count++;
            }
        }
        Console.WriteLine(count);
    }
}

using System;
using System.Linq;

class ExtracctUrlFromText
{
    static void Main()
    {
        string text = Console.ReadLine();

        var links = text.Split(' ','\t', '\n').Where(s => s.StartsWith("http://") || s.StartsWith("www."));

        foreach (var url in links)
        {
            Console.WriteLine(url);
        }
    }
}

using System;

class TorrentPirate
{
    static void Main()
    {
        uint d = uint.Parse(Console.ReadLine());
        byte p = byte.Parse(Console.ReadLine());
        byte w = byte.Parse(Console.ReadLine());

        decimal downloadTime = new decimal(d) / 2 / 60 / 60;
        decimal mall = downloadTime * new decimal(w);

        decimal numberOfMovies = new decimal(d) / 1500;
        decimal cinema = numberOfMovies * new decimal(p);

        if (mall <= cinema)
        {
            Console.WriteLine("mall -> {0:F2}lv", mall);
        }
        else
        {
            Console.WriteLine("cinema -> {0:F2}lv", cinema);
        }
    }
}


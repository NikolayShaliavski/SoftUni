using System;

class TheBetterMusicProducer
{
    static void Main()
    {
        decimal albumsEurope = decimal.Parse(Console.ReadLine());
        decimal euro = decimal.Parse(Console.ReadLine());

        decimal albumsNorth = decimal.Parse(Console.ReadLine());
        decimal dollars = decimal.Parse(Console.ReadLine());

        decimal albumsSouth = decimal.Parse(Console.ReadLine());
        decimal pesos = decimal.Parse(Console.ReadLine());

        decimal concerts = decimal.Parse(Console.ReadLine());
        decimal priceConcert = decimal.Parse(Console.ReadLine());

        decimal profitEurope = albumsEurope * euro * 1.94M;

        decimal profitNorth = albumsNorth * dollars * 1.72M;

        decimal profitSouth = albumsSouth * pesos / 332.74M;

        decimal allProfit = profitEurope + profitNorth + profitSouth;

        allProfit = allProfit - (allProfit * 0.35M);

        decimal incomeAlbums = allProfit - (allProfit * 0.2M);

        decimal concertProfit = concerts * priceConcert * 1.94M;

        decimal incomeConcerts = 0;

        if (concertProfit > 100000)
        {
            incomeConcerts = concertProfit - (concertProfit * 0.15M);
        }
        else
        {
            incomeConcerts = concertProfit;
        }


        if (incomeAlbums > incomeConcerts)
        {
            Console.WriteLine("Let's record some songs! They'll bring us {0:0.00}lv.", incomeAlbums);
        }
        else
        {
            Console.WriteLine("On the road again! We'll see the world and earn {0:0.00}lv.", incomeConcerts);
        }
       
    }
}

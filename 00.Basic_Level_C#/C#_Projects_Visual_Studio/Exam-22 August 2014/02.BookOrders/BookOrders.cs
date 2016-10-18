using System;

class BookOrders
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int allBooks = 0;
        decimal amount = 0;

        for (int i = 0; i < n; i++)
        {
            int packet = int.Parse(Console.ReadLine());
            int books = int.Parse(Console.ReadLine());
            decimal price = decimal.Parse(Console.ReadLine());

            allBooks += (packet * books);

            if (packet >= 10 && packet <= 19)
            {
                price -= price * 0.05M;
            }
            else if (packet >= 20 && packet <= 29)
            {
                price -= price * 0.06M;
            }
            else if (packet >= 30 && packet <= 39)
            {
                price -= price * 0.07M;
            }
            else if (packet >= 40 && packet <= 49)
            {
                price -= price * 0.08M;
            }
            else if (packet >= 50 && packet <= 59)
            {
                price -= price * 0.09M;
            }
            else if (packet >= 60 && packet <= 69)
            {
                price -= price * 0.1M;
            }
            else if (packet >= 70 && packet <= 79)
            {
                price -= price * 0.11M;
            }
            else if (packet >= 80 && packet <= 89)
            {
                price -= price * 0.12M;
            }
            else if (packet >= 90 && packet <= 99)
            {
                price -= price * 0.13M;
            }
            else if (packet >= 100 && packet <= 109)
            {
                price -= price * 0.14M;
            }
            else if (packet >= 110)
            {
                price -= price * 0.15M;
            }

            amount += (packet * books) * price;
        }

        Console.WriteLine(allBooks);
        Console.WriteLine("{0:0.00}", amount);
    }
}

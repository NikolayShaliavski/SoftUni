using System;

class Problem3
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int outerDots = n * 2;
        int middleDots = 0;
        int tilda = 0;

        for (int i = 0; i < n; i++)
        {
            Console.WriteLine("#{0}#{1}#{2}#{1}#{0}#", new string('~', tilda), new string('.', outerDots), new string('.', middleDots));
            tilda++;
            middleDots += 2;
            outerDots -= 2;
        }
        outerDots = 1;
        middleDots = n * 2;
        tilda = n;
        
        for (int i = 0; i < n; i++)
        {
            Console.WriteLine("{0}#{1}#{2}#{1}#{0}", new string('.', outerDots), new string('~', tilda), new string('.', middleDots));
            outerDots += 2;
            tilda--;
            middleDots -= 2;
        }
        Console.WriteLine("{0}####{0}", new string('.', outerDots));
        outerDots++;

        for (int i = 0; i < n; i++)
        {
            Console.WriteLine("{0}##{0}", new string('.', outerDots));
        }
    }
}

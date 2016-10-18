using System;

class FourFactors
{
    static void Main()
    {
        double FG = double.Parse(Console.ReadLine());
        double FGA = double.Parse(Console.ReadLine());
        double ThreeP = double.Parse(Console.ReadLine());
        double TOV = double.Parse(Console.ReadLine());
        double ORB = double.Parse(Console.ReadLine());
        double OppDRB = double.Parse(Console.ReadLine());
        double FT = double.Parse(Console.ReadLine());
        double FTA = double.Parse(Console.ReadLine());

        double percentEFG = (FG + (0.5 * ThreeP)) / FGA;
        double percentTOV = TOV / (FGA + 0.44 * FTA + TOV);
        double percentORB = ORB / (ORB + OppDRB);
        double percentFT = FT / FGA;

        Console.WriteLine("eFG% {0:0.000}", percentEFG);
        Console.WriteLine("TOV% {0:0.000}", percentTOV);
        Console.WriteLine("ORB% {0:0.000}", percentORB);
        Console.WriteLine("FT% {0:0.000}", percentFT);
    }
}

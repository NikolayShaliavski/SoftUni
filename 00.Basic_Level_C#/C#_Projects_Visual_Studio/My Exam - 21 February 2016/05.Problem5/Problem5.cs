using System;

class Problem5
{
    static void Main()
    {
        ulong number = ulong.Parse(Console.ReadLine());

        string command = Console.ReadLine();

        while (command != "end")
        {
            string[] input = command.Split();
            int position = int.Parse(input[1]);

            for (int i = 0; i < 64; i += position)
            {
                switch (input[0])
                {
                    case "salt":
                        number &= ~((ulong)1 << i);
                        break;
                    case "pepper":
                        number |= ((ulong)1 << i);
                        break;
                    default:
                        break;
                }
            }
            command = Console.ReadLine();
        }
        Console.WriteLine(number);
    }
}

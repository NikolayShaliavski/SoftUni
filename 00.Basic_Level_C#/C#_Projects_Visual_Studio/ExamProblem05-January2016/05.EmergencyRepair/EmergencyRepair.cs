using System;

class EmergencyRepair
{
    static void Main()
    {
        ulong wall = ulong.Parse(Console.ReadLine());
        int kits = int.Parse(Console.ReadLine());
        int numberOfAttacks = int.Parse(Console.ReadLine());
        ulong mask = 1;
        ulong hole = 0;
        ulong holeSecond = 0;
        ulong holeThird = 0;
        ulong maskSec = 1;
        ulong maskThird = 1;

        for (int i = 0; i < numberOfAttacks; i++)
        {
            int attack = int.Parse(Console.ReadLine());

            mask <<= attack;
            hole = wall & mask;
            hole >>= attack;

            if (hole == 1)
            {
                wall = wall & ~mask;
            }
            mask = 1;
        }
        
        for (int i = 0; i <= 63; i++)
        {
            if (kits == 0)
            {
                break;
            }
            mask <<= i;
            maskSec <<= i + 1;
            maskThird <<= i + 2;

            hole = wall & mask;
            holeSecond = wall & maskSec;
            holeThird = wall & maskThird;
            holeThird >>= (i + 2);

            if (hole == 0 && holeSecond == 0)
            {
                wall |= mask;
                kits--;
                if (kits == 0)
                {
                    break;
                }
            }
            if (hole == 0 && holeSecond == 0 && holeThird == 1)
            {
                wall |= maskSec;
                kits--;
                if (kits == 0)
                {
                    break;
                }
                i++;
            }
            mask = 1;
            maskSec = 1;
            maskThird = 1;
        }
        Console.WriteLine(wall);
    }
}

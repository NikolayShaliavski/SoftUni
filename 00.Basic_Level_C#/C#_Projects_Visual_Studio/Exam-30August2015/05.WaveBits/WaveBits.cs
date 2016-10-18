using System;

class WaveBits
{
    static void Main()
    {
        ulong number = ulong.Parse(Console.ReadLine());
        int wave = 0;
        int start = 0;
        int currentWave = 0;

        for (int i = 0; i < 62; i++)
        {
            ulong first = number >> i & 1;
            ulong second = number >> (i + 1) & 1;
            ulong third = number >> (i + 2) & 1;

            if (first == 1 && second == 0 && third == 1)
            {
                currentWave += 2;
                if (i == 60 || i == 61)
                {
                    i += 1;
                    wave = currentWave + 1;
                    start = (i + 1) - wave;
                }
                i += 1;
            }
            else
            {
                if (currentWave > wave)
                {
                    wave = currentWave + 1;
                    start = (i + 1) - wave;
                }
                currentWave = 0;
            }
        }
        ulong result = 0;

        if (wave == 0)
        {
            Console.WriteLine("No waves found!");
        }
        else
        {
            ulong right = number & (1ul << start) - 1;
            if (start + wave == 63)
            {
                result = right;
            }
            else
            {
                number >>= (start + wave);
                result = (number << start) | right;
            }
            
            Console.WriteLine(result);
        }
    }
}

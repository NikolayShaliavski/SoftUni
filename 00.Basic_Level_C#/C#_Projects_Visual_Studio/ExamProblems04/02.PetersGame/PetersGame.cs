using System;
using System.Linq;
using System.Numerics;
using System.Text;

class PetersGame
{
    static void Main()
    {
        ulong startNumber = ulong.Parse(Console.ReadLine());
        ulong endNumber = ulong.Parse(Console.ReadLine());
        string petersWord = Console.ReadLine();
        BigInteger sum = 0;

        for (ulong i = startNumber; i < endNumber; i++)
        {
            if (i % 5 == 0)
            {
                sum += i;
            }
            else if (i % 5 != 0)
            {
                sum += (i % 5);
            }
        }

        int[] intArray = sum.ToString().ToCharArray().Select(x => (int)Char.GetNumericValue(x)).ToArray();
        string[] sumArray = Array.ConvertAll(intArray, x => x.ToString());

        string stringToChangeEven = sumArray[0];
        string stringToChangeOdd = sumArray[sumArray.Length - 1];

        for (int i = 0; i < sumArray.Length; i++)
        {
            if (sum % 2 == 0)
            {
                if (sumArray[i] == stringToChangeEven)
                {
                    sumArray[i] = petersWord;
                }
            }
            else if (sum % 2 != 0)
            {
                if (sumArray[i] == stringToChangeOdd)
                {
                    sumArray[i] = petersWord;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sumArray.Length; i++)
        {
            builder.Append(sumArray[i]);
        }
        string result = builder.ToString();
        Console.WriteLine(result);
    }
}


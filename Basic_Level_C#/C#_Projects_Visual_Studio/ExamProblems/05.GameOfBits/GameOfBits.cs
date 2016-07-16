using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class GameOfBits
{
    static void Main()
    {
        uint inputNumber = uint.Parse(Console.ReadLine());
        string inputInBits = Convert.ToString(inputNumber, 2).PadLeft(32, '0');
        char[] bitsArray = inputInBits.ToCharArray();
        int[] intArray = new int[bitsArray.Length];
        intArray = bitsArray.Select(c => Convert.ToInt32(c.ToString())).ToArray();

        List<int> bitsList = new List<int>();
        for (int j = 0; j < intArray.Length; j++)
        {
            bitsList.Add(intArray[j]);  //input integer is declared in List of bits
        }

        string[] userCommand = new string[30];
        int resultCountingBits = 0;
        List<int> newBitslist = new List<int>();

        for (int i = 0; i < userCommand.Length; i++)
        {
            userCommand[i] = Console.ReadLine();

            if (userCommand[i] == "Odd")
            {                            
                for (int j = 1; j < bitsList.Count; j += 2)
                {
                    newBitslist.Add(bitsList[j]);
                }
                if (bitsList[0] == 0)
                {
                    bitsList.RemoveAt(0);
                    bitsList[0] = 1;
                }
                bitsList.Clear();
                bitsList.AddRange(newBitslist);
                newBitslist.Clear();
            }
            
            if (userCommand[i] == "Even")
            {                
                for (int j = 0; j < bitsList.Count; j += 2)
                {
                    newBitslist.Add(bitsList[j]);
                }
                bitsList.Clear();
                bitsList.AddRange(newBitslist);
                newBitslist.Clear();
            }

            if (userCommand[i] == "Game Over!")
            {
                StringBuilder bitsInString = new StringBuilder();
                foreach (int bit in bitsList)
                {
                    bitsInString.Append(bit);
                }
                string stringInBits = bitsInString.ToString();

                uint result = Convert.ToUInt32(stringInBits, 2);
                for (int j = 0; j < bitsList.Count; j++)
                {
                    if (bitsList[j] != 0)
                    {
                        resultCountingBits += bitsList[j];
                    }
                }
                Console.WriteLine(result + " -> " + resultCountingBits); return;
            }
        }
    }
}



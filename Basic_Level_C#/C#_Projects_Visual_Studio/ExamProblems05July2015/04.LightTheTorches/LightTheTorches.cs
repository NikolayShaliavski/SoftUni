using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class LightTheTorches
{
    static void Main()
    {
        int rooms = int.Parse(Console.ReadLine());
        string light = Console.ReadLine();

        char[] basement = new char[rooms];

        //turn on the lights
        for (int i = 0, j = 0; i < basement.Length; i++, j++)
        {
            if (j == light.Length)
            {
                j = 0;
            }
            basement[i] = light[j];
        }

        string command = Console.ReadLine();
        int enteredRoom = rooms / 2;
        int passedRoooms = 1;
        int digit = 0;

        while (command != "END")
        {
            char[] commandDigits = command.ToCharArray();
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < commandDigits.Length; i++)
            {
                int.TryParse(commandDigits[i].ToString(), out digit);
                builder.Append(digit);
            }
            digit = int.Parse(builder.ToString());
            passedRoooms += digit;

            if (command[0] == 'L')
            {
                if ((enteredRoom - passedRoooms) >= 0)
                {
                    enteredRoom -= passedRoooms;
                }
                else if (enteredRoom == 0)
                {
                    passedRoooms = 1;
                    command = Console.ReadLine();
                    continue;
                }
                else
                {
                    enteredRoom = 0;
                }
                if (basement[enteredRoom] == 'L')
                {
                    basement[enteredRoom] = 'D';
                }
                else
                {
                    basement[enteredRoom] = 'L';
                }
            }
            else if (command[0] == 'R')
            {
                if ((enteredRoom + passedRoooms) <= basement.Length - 1)
                {
                    enteredRoom += passedRoooms;
                }
                else if (enteredRoom == basement.Length - 1)
                {
                    passedRoooms = 1;
                    command = Console.ReadLine();
                    continue;
                }
                else
                {
                    enteredRoom = basement.Length - 1;
                }
                if (basement[enteredRoom] == 'L')
                {
                    basement[enteredRoom] = 'D';
                }
                else
                {
                    basement[enteredRoom] = 'L';
                }
            }
            passedRoooms = 1;
            command = Console.ReadLine();
        }

        int prays = 0;

        for (int i = 0; i < basement.Length; i++)
        {
            if (basement[i] == 'D')
            {
                prays++;
            }
        }
        Console.WriteLine(68 * prays);
    }
}

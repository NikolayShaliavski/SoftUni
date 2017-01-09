using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _08.Cubes
{
    class Cubes
    {
        static int formCounter = 0;
        static HashSet<string> variations = new HashSet<string>();

        static void Main(string[] args)
        {
            int[] sticks = Console.ReadLine().Split().Select(int.Parse).ToArray();

            Permute(sticks);
            Console.WriteLine(formCounter);
        }

        private static void Permute(int[] sticks, int startIndex = 0)
        {
            string currentCube = string.Join("", sticks);
            
            if (startIndex == sticks.Length - 1)
            {

                if (!variations.Contains(currentCube))
                {
                    formCounter++;
                    variations.Add(currentCube);
                    AddVariations(sticks);
                    //Console.WriteLine(currentCube);
                }
            }
            if (variations.Contains(currentCube))
            {
                return;
            }

            for (int i = startIndex; i < sticks.Length; i++)
            {
                Swap(ref sticks[i], ref sticks[startIndex]);
                Permute(sticks, startIndex + 1);
                Swap(ref sticks[i], ref sticks[startIndex]);
            }
        }

        private static void Swap(ref int i, ref int j)
        {
            if (i == j)
            {
                return;
            }
            i ^= j;
            j ^= i;
            i ^= j;
        }

        private static void AddVariations(int[] sticks)
        {
            RotateFront(sticks);
            RotateLeft(sticks);
            RotateBottom(sticks);
            //RotateLeft(sticks);
            //RotateFront(sticks);
        }

        private static void RotateFront(int[] sticks)
        {
            var temp01 = new int[sticks.Length];
            var temp02 = new int[sticks.Length];

            temp01[0] = sticks[8];
            temp01[1] = sticks[5];
            temp01[2] = sticks[0];
            temp01[3] = sticks[4];
            temp01[4] = sticks[11];
            temp01[5] = sticks[9];
            temp01[6] = sticks[1];
            temp01[7] = sticks[3];
            temp01[8] = sticks[10];
            temp01[9] = sticks[6];
            temp01[10] = sticks[2];
            temp01[11] = sticks[7];
            variations.Add(string.Join("", temp01));
            //RotateBottom(temp01);

            temp02[0] = temp01[8];
            temp02[1] = temp01[5];
            temp02[2] = temp01[0];
            temp02[3] = temp01[4];
            temp02[4] = temp01[11];
            temp02[5] = temp01[9];
            temp02[6] = temp01[1];
            temp02[7] = temp01[3];
            temp02[8] = temp01[10];
            temp02[9] = temp01[6];
            temp02[10] = temp01[2];
            temp02[11] = temp01[7];
            variations.Add(string.Join("", temp02));
            //RotateBottom(temp02);

            temp01[0] = temp02[8];
            temp01[1] = temp02[5];
            temp01[2] = temp02[0];
            temp01[3] = temp02[4];
            temp01[4] = temp02[11];
            temp01[5] = temp02[9];
            temp01[6] = temp02[1];
            temp01[7] = temp02[3];
            temp01[8] = temp02[10];
            temp01[9] = temp02[6];
            temp01[10] = temp02[2];
            temp01[11] = temp02[7];
            variations.Add(string.Join("", temp01));
            //RotateBottom(temp01);
        }

        private static void RotateLeft(int[] sticks)
        {
            var temp01 = new int[sticks.Length];
            var temp02 = new int[sticks.Length];

            temp01[0] = sticks[4];
            temp01[1] = sticks[3];
            temp01[2] = sticks[7];
            temp01[3] = sticks[11];
            temp01[4] = sticks[8];
            temp01[5] = sticks[0];
            temp01[6] = sticks[2];
            temp01[7] = sticks[10];
            temp01[8] = sticks[5];
            temp01[9] = sticks[1];
            temp01[10] = sticks[6];
            temp01[11] = sticks[9];
            variations.Add(string.Join("", temp01));
            //RotateBottom(temp01);

            temp02[0] = temp01[4];
            temp02[1] = temp01[3];
            temp02[2] = temp01[7];
            temp02[3] = temp01[11];
            temp02[4] = temp01[8];
            temp02[5] = temp01[0];
            temp02[6] = temp01[2];
            temp02[7] = temp01[10];
            temp02[8] = temp01[5];
            temp02[9] = temp01[1];
            temp02[10] = temp01[6];
            temp02[11] = temp01[9];
            variations.Add(string.Join("", temp02));
            //RotateBottom(temp02);

            temp01[0] = temp02[4];
            temp01[1] = temp02[3];
            temp01[2] = temp02[7];
            temp01[3] = temp02[11];
            temp01[4] = temp02[8];
            temp01[5] = temp02[0];
            temp01[6] = temp02[2];
            temp01[7] = temp02[10];
            temp01[8] = temp02[5];
            temp01[9] = temp02[1];
            temp01[10] = temp02[6];
            temp01[11] = temp02[9];
            variations.Add(string.Join("", temp01));
            //RotateBottom(temp01);
        }

        private static void RotateBottom(int[] sticks)
        {
            var temp01 = new int[sticks.Length];
            var temp02 = new int[sticks.Length];

            temp01[0] = sticks[3];
            temp01[4] = sticks[7];
            temp01[8] = sticks[11];
            for (int i = 0; i < 3; i++)
            {
                temp01[i + 1] = sticks[i];
                temp01[i + 5] = sticks[i + 4];
                temp01[i + 9] = sticks[i + 8];
            }
            variations.Add(string.Join("", temp01));
            RotateFront(temp01);
            RotateLeft(temp01);

            temp02[0] = temp01[3];
            temp02[4] = temp01[7];
            temp02[8] = temp01[11];
            for (int i = 0; i < 3; i++)
            {
                temp02[i + 1] = temp01[i];
                temp02[i + 5] = temp01[i + 4];
                temp02[i + 9] = temp01[i + 8];
            }
            variations.Add(string.Join("", temp02));
            RotateFront(temp02);
            RotateLeft(temp02);

            temp01[0] = temp02[3];
            temp01[4] = temp02[7];
            temp01[8] = temp02[11];
            for (int i = 0; i < 3; i++)
            {
                temp01[i + 1] = temp02[i];
                temp01[i + 5] = temp02[i + 4];
                temp01[i + 9] = temp02[i + 8];
            }
            variations.Add(string.Join("", temp01));
            RotateFront(temp01);
            RotateLeft(temp01);
        }
    }
}

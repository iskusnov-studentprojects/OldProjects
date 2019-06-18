using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace LargestIncreasingSequece
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Сгенерировать новую поледовательность(y/n)?");
            if(Console.ReadKey(true).Key == ConsoleKey.Y)
                GenerateSequence(1, 20, 30);
            StreamReader reader = new StreamReader(File.Open("input.dat", FileMode.Open));
            string[] strmass = reader.ReadLine().Split(' ');
            int[] sequence = new int[strmass.Length];
            for (int i = 0; i < sequence.Length; i++)
                sequence[i] = Convert.ToInt32(strmass[i]);
            reader.Close();
            int[] d = new int[sequence.Length], p = new int[sequence.Length];

            for (int i = 0; i < sequence.Length; ++i)
            {
                d[i] = 1;
                p[i] = -1;
                for (int j = 0; j < i; ++j)
                    if (sequence[j] < sequence[i])
                        if (1 + d[j] > d[i])
                        {
                            d[i] = 1 + d[j];
                            p[i] = j;
                        }
            }

            int ans = d[0], pos = 0;
            for (int i = 0; i < sequence.Length; ++i)
                if (d[i] > ans)
                {
                    ans = d[i];
                    pos = i;
                }
            List<int> path = new List<int>();
            while (pos != -1)
            {
                path.Add(pos);
                pos = p[pos];
            }
            path.Reverse(0, path.Count);
            Console.WriteLine("Ответ:");
            for (int i = 0; i < (int)path.Count; ++i)
                Console.Write(sequence[path[i]].ToString() + ' ');
            Console.Read();
        }

        static void GenerateSequence(int minNum, int maxNum, int size)
        {
            StreamWriter writer = new StreamWriter(File.Open("input.dat", FileMode.Create));
            Random rand = new Random();
            writer.Write(rand.Next(minNum, maxNum + 1).ToString());
            for (int i = 0; i < size - 1; i++)
                writer.Write(' ' + rand.Next(minNum, maxNum + 1).ToString());
            writer.Close();
        }
    }
}

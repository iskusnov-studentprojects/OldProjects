using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Сombinatorics
{
    class Program
    {
        static void Main(string[] args)
        {
            StreamReader reader = new StreamReader(File.Open("input.dat",FileMode.Open));
            while (!reader.EndOfStream)
            {
                string[] str = reader.ReadLine().Split(' ');
                int a = Convert.ToInt32(str[0]), b = Convert.ToInt32(str[1]);
                if(a > b)
                    throw new Exception("Неверные входные данные!");
                Console.WriteLine(a.ToString() + " - " + b.ToString() + ": " + Count(a, b).ToString());
            }
            reader.Close();
            Console.Read();
        }

        static int Count(int x, int y)
        {
            int n = 0, step = 1, len = y - x;
            while (len >= step * 2)
            {
                len -= step * 2;
                step++;
                n += 2;
            }
            while (len != 0)
            {
                if (len - step >= 0)
                {
                    len -= step;
                    n++;
                }
                step--;
            }
            return n;
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace PointsGenerator
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Введите колличество точек: ");
            int n = Convert.ToInt32(Console.ReadLine());
            Console.Write("Введите минимаольную координату x: ");
            int minX = Convert.ToInt32(Console.ReadLine());
            Console.Write("Введите максимальную координату x: ");
            int maxX = Convert.ToInt32(Console.ReadLine());
            Console.Write("Введите минимаольную координату y: ");
            int minY = Convert.ToInt32(Console.ReadLine());
            Console.Write("Введите максимальную координату y: ");
            int maxY = Convert.ToInt32(Console.ReadLine());
            StreamWriter writer = new StreamWriter(File.Open("input.dat",FileMode.Create));
            Random rand = new Random();
            writer.Write(rand.Next(minX, maxX + 1).ToString() + ',' + rand.Next(minY, maxY + 1).ToString());
            for (int i = 0; i < n - 1; i++)
                writer.Write(' ' + rand.Next(minX, maxX + 1).ToString() + ',' + rand.Next(minY, maxY + 1).ToString());
            writer.Close();
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace LongNumbers
{
    class Program
    {
        static void Main(string[] args)
        {
            do
            {
                Console.Write("Введите n: ");
                int n = Convert.ToInt32(Console.ReadLine());
                if (n <= 0)
                {
                    Console.WriteLine("Некорректное значение n");
                    continue;
                }
                BigInteger[,] matrix = new BigInteger[n, n];
                BigInteger[] column = new BigInteger[n], 
                    res = new BigInteger[n];
                for(int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                    {
                        matrix[i, j] = new BigInteger();
                        matrix[i, j] = pow(new BigInteger(i+1), 32) + (j+1) + 3;
                    }
                for (int i = 0; i < n; i++)
                {
                    res[i] = new BigInteger(0);
                    column[i] = new BigInteger();
                    column[i].Number = factorial(i+1).Number;
                }

                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        res[i] = res[i] + matrix[i,j] * column[j];

                StreamWriter writer = new StreamWriter(File.Open("output.dat",FileMode.Create));
                writer.WriteLine("Матрица A:");
                PrintMatrix(matrix,n,writer);
                writer.WriteLine("Матрица В:");
                PrintColumn(column,writer);
                writer.WriteLine("Матрица A*B:");
                PrintColumn(res,writer);
                writer.Close();
                Console.WriteLine("Нажмите Esc для выхода.");
            } while (Console.ReadKey().Key != ConsoleKey.Escape);
        }

        static BigInteger pow(BigInteger num, int degree)
        {
            BigInteger res = new BigInteger(1);
            for (int i = 0; i < degree; i++)
                res = res * num;
            return res;
        }

        static BigInteger factorial(Int64 n)
        {
            BigInteger res = new BigInteger(1);
            for (int i = 1; i <= n; i++)
            {
                res = res * i;
            }
            return res;
        }

        static void PrintMatrix(BigInteger[,] matrix, int size, StreamWriter writer)
        {
            BigInteger max = matrix[0, 0];
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    if (matrix[i, j] > max)
                        max = matrix[i, j];
            int ln = max.ToString().Length;
            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < size; j++)
                {
                    PrintSpaces(ln - matrix[i,j].ToString().Length,writer);
                    writer.Write(matrix[i, j].ToString());
                    writer.Write(" ");
                }
                writer.WriteLine();
            }
        }

        static void PrintColumn(BigInteger[] column, StreamWriter writer)
        {
            BigInteger max = column[0];
            for (int i = 0; i < column.Length; i++)
                if (column[i] > max)
                    max = column[i];
            int ln = max.ToString().Length;
            for (int i = 0; i < column.Length; i++)
            {
                PrintSpaces(ln - column[i].ToString().Length, writer);
                writer.WriteLine(column[i]);
            }
        }

        static void PrintSpaces(int n, StreamWriter writer)
        {
            for (int i = 0; i < n; i++)
                writer.Write(" ");
        }
    }
}

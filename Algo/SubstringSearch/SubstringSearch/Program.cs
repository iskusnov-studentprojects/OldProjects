using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace SubstringSearch
{
    class Program
    {
        static void Main(string[] args)
        {
            do
            {
                Console.WriteLine();
                StreamReader sr = new StreamReader(File.Open("input.dat", FileMode.Open));
                string text = sr.ReadLine();
                string str1 = sr.ReadLine();
                string str2 = sr.ReadLine();
                sr.Close();
                Console.WriteLine("Текст:");
                Console.WriteLine(text);
                Console.WriteLine("Первая строка");
                Console.WriteLine(str1);
                Console.WriteLine("Вторая строка");
                Console.WriteLine(str2);
                Console.Write("Позиция: ");
                Console.WriteLine(KMPMod(text, str1, str2));
                Console.WriteLine("Нажмите любую клавишу для повторения...");
            } while (Console.ReadKey().Key != ConsoleKey.Escape);
        }

        static int KMP(string str, string substr)
        {
            if (0 == str.Length) throw new ArgumentException("Строка не может быть пустой", "input");
            if (0 == substr.Length) throw new ArgumentException("Строка не может быть пустой", "pattern");

            int[] d = PrefixFunction(substr);
            for (int k = 0, i = 0; i < str.Length; ++i)
            {
                while ((k > 0) && (substr[k] != str[i]))
                    k = d[k - 1];

                if (substr[k] == str[i])
                    k++;

                if (k == substr.Length)
                    return (i - substr.Length + 1);
            }

            return -1;
        }

        static int[] PrefixFunction(string substr)
        {
            int[] res = new int[substr.Length];
            res[0] = 0;
            for (int k = 0, i = 1; i < substr.Length; ++i)
            {
                while (k > 0 && substr[i] != substr[k])
                    k = res[k - 1];

                if (substr[i] == substr[k])
                    k++;

                res[i] = k;
            }
            return res;
        }

        static string SearchCommonFragment(string str1, string str2)
        {
            string fragment;
            int end = str1.Length;
            do{
                fragment = str1.Substring(0, end);
                for (int i = 0; i < end; i++)
                {
                    if(KMP(str2,fragment.Substring(i,end-i)) != -1)
                        return fragment.Substring(i,end-i);
                }
                end--;
            }while(fragment.Length != 0);
            return fragment;
        }

        static bool CheckEnd(string text, string str, int posintext, int posinstr)
        {
            bool f = true;
            if (posintext - posinstr + str.Length < str.Length)
                f = false;
            else
                for (int i = posintext, j = posinstr; i < posintext - posinstr + str.Length && j < str.Length; i++, j++)
                    if (text[i] != str[j])
                    {
                        f = false;
                        break;
                    }
            return f;
        }

        static bool CheckBeg(string text, string str, int posintext, int posinstr)
        {
            bool f = true;
            if(posintext - posinstr < 0)
                f = false;
            else
                for(int i = posinstr, j = posintext; i > -1 && j > posintext - posinstr - 1; i--, j--)
                    if(str[i] != text[j])
                    {
                        f = false;
                        break;
                    }
            return f;
        }

        static int KMPMod(string text, string str1, string str2)
        {
            string cf = SearchCommonFragment(str1, str2);
            if (cf == "") throw new Exception("Нет общего фрагмента");
            int cfpos1 = KMP(str1,cf);
            int cfpos2 = KMP(str2,cf);
            if (0 == str1.Length) throw new ArgumentException("Строка не может быть пустой", "input");
            if (0 == str2.Length) throw new ArgumentException("Строка не может быть пустой", "input");
            if (0 == text.Length) throw new ArgumentException("Строка не может быть пустой", "input");

            int[] d = PrefixFunction(cf);

            for (int k = 0, i = 0; i < text.Length; ++i)
            {
                while ((k > 0) && (cf[k] != text[i]))
                    k = d[k - 1];

                if (cf[k] == text[i])
                    k++;

                if (k == cf.Length)
                {
                    if (CheckBeg(text, str1, i - k - 1, cfpos1 - 1) && CheckEnd(text, str1, i + 1, cfpos1 + cf.Length))
                        return i - k - cfpos1 + 1;
                    if (CheckBeg(text, str2, i - k - 1, cfpos2 - 1) && CheckEnd(text, str2, i + 1, cfpos2 + cf.Length))
                        return i - k - cfpos2 + 1;
                    k = 0;
                }
            }
            return -1;
        }
    }
}

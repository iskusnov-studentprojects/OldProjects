//Найти все простые пути между 2-мя заданными вершинами,
//если граф задан списковой структурой

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Alg_S4_L2
{
    class Program
    {
        static void Main(string[] args)
        {
            List<string> verts = new List<string>();
            List<string []> edges = new List<string []>();
            string begin;
            string end;
            StreamReader reader = new StreamReader("input.dat");
            string str = reader.ReadLine();
            string [] mass = str.Split(new Char [] {' '});
            foreach (string i in mass)
                verts.Add(i);
            str = reader.ReadLine();
            mass = str.Split(new Char[] { ' ' });
            foreach (string i in mass)
                edges.Add(i.Split(new Char[] { '-' }));
            str = reader.ReadLine();
            mass = str.Split(new Char[] { ' ' });
            begin = mass[0];
            end = mass[1];
            List<List<string>> pathes = new List<List<string>>();
            DFS(verts, edges, begin, pathes, begin, end);
            pathes.RemoveAt(0);
            foreach (List<string> i in pathes)
            {
                string s = "";
                foreach (string j in i)
                    s += "-" + j;
                s = s.Remove(0,1);
                Console.WriteLine(s);
            }
            Console.Read();
        }

        static void DFS(List<string> vertices, List<string[]> edges, string current, List<List<string>> pathes, string begin, string end)
        {
            if (pathes.Count == 0)
            {
                List<string> l = new List<string>();
                l.Add(begin);
                pathes.Add(l);
            }
            if (begin == end)
            {
                pathes[0].Add(begin);
                return;
            }
            if (VertixExist(pathes[0], end))
                pathes.Add(CloneList(pathes[0]));
            else
            {
                foreach (string i in vertices)
                    if (!VertixExist(pathes[0], i))
                        foreach (string [] j in edges)
                            if (EdgeExist(j,i) && EdgeExist(j,current))
                            {
                                pathes[0].Add(i);
                                DFS(vertices, edges, i, pathes, begin, end);
                            }
            }
            if (pathes[0].Count != 0)
                pathes[0].RemoveAt(pathes[0].Count - 1);
        }

        static bool EdgeExist(string [] edge,string vertix)
        {
            if (edge[0] == vertix || edge[1] == vertix)
                return true;
            return false;
        }

        static bool VertixExist(List<string> path, string vert)
        {
            foreach (string i in path)
                if (i == vert)
                    return true;
            return false;
        }

        static List<string> CloneList(List<string> current)
        {
            List<string> newlist = new List<string>();
            foreach (string i in current)
                newlist.Add(i);
            return newlist;
        }
    }
}

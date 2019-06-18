using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace OEPIiUL1
{
    class Program
    {
        static Vertix[] verts;
        static string[][] edges;
        static bool oneBegVert;
        static bool oneLastVert;
        static string falseBegVertix = "falseFirstVertix";
        static string falseEndVertix = "falseLastVertix";


        static void Main(string[] args)
        {
            edges = Read("input.dat");
            Sort();
            Count();
            foreach (var i in verts)
                if (i.name != falseBegVertix && i.name != falseEndVertix)
                    Console.WriteLine(i.name + " " +
                                        "tр. = " + i.t_early.ToString() + " " +
                                        "tп. = " + i.t_after.ToString() + " " +
                                        "r = " + i.r.ToString());
            Console.Read();
        }

        #region Sorting
        static void Sort()
        {
            oneBegVert = false;
            oneLastVert = false;
            FirstFormVertices();
            CheckFirstVertix();
            CheckLastVertix();
            SecondFormVertices();
        }

        static void CheckFirstVertix()
        {
            int num = 0;
            foreach (var i in verts)
            {
                bool flag = true;
                foreach (var j in edges)
                    if (j[1] == i.name)
                        flag = false;
                if (flag)
                    num++;
            }
            if(num <= 1)
                oneBegVert = true;
            else
            {
                List<string[]> list = new List<string[]>(edges);
                foreach (var i in verts)
                {
                    bool flag = true;
                    foreach (var j in edges)
                        if (j[1] == i.name)
                            flag = false;
                    if (flag)
                    {
                        string[] strs = new string[3];
                        strs[0] = falseBegVertix;
                        strs[1] = i.name;
                        strs[2] = "0";
                        list.Insert(0, strs);
                    }
                }
                edges = list.ToArray();
            }
        }

        static void CheckLastVertix()
        {
            int num = 0;
            foreach (var i in verts)
            {
                bool flag = true;
                foreach (var j in edges)
                    if (j[0] == i.name)
                        flag = false;
                if (flag)
                    num++;
            }
            if (num <= 1)
                oneLastVert = true;
            else
            {
                List<string[]> list = new List<string[]>(edges);
                foreach (var i in verts)
                {
                    bool flag = true;
                    foreach (var j in edges)
                        if (j[0] == i.name)
                            flag = false;
                    if (flag)
                    {
                        string[] strs = new string[3];
                        strs[0] = i.name;
                        strs[1] = falseEndVertix;
                        strs[2] = "0";
                        list.Add(strs);
                    }
                }
                edges = list.ToArray();
            }
        }

        static void SecondFormVertices()
        {
            List<Vertix> list = new List<Vertix>();
            for (int i = 0; i < edges.Length; i++)
            {
                bool flag = true;
                for (int j = 0; j < edges.Length; j++)
                    if (i != j && edges[i][0] == edges[j][1])
                        flag = false;
                if (flag)
                {
                    list.Add(new Vertix(edges[i][0]));
                    break;
                }
            }
            int num = 0;
            while (list.Count < (verts.Length - 1 + (!oneBegVert?1:0) + (!oneLastVert?1:0)))
            {
                foreach (var i in edges)
                    if (list[num].name == i[0] && !VertixExists(i[1], list) && i[1] != GetLastVertix())
                        list.Add(new Vertix(i[1]));
                num++;
            }
            list.Add(new Vertix(GetLastVertix()));
            verts = list.ToArray();
        }

        static string GetLastVertix()
        {
            for (int i = 0; i < edges.Length; i++)
            {
                bool flag = true;
                for (int j = 0; j < edges.Length; j++)
                    if (i != j && edges[i][1] == edges[j][0])
                        flag = false;
                if (flag)
                    return edges[i][1];
            }
            return null;
        }

        static void FirstFormVertices()
        {
            List<Vertix> list = new List<Vertix>();
            foreach (var i in edges)
            {
                if (!VertixExists(i[0], list))
                    list.Add(new Vertix(i[0]));
                if (!VertixExists(i[1], list))
                    list.Add(new Vertix(i[1]));
            }
            verts = list.ToArray();
        }
        #endregion

        static void Count()
        {
            verts[0].t_early = 0;
            for (int i = 0; i < verts.Length; i++)
            {
                string[] edge;
                for (int j = i; j < verts.Length; j++)
                    if ((edge = GetEdge(verts[i].name, verts[j].name)) != null)
                        GetVertix(edge[1]).t_early = Math.Max(GetVertix(edge[1]).t_early, verts[i].t_early + Int(edge[2]));

            }
            verts[verts.Length - 1].t_after = verts[verts.Length - 1].t_early;
            for (int i = verts.Length - 1; i >= 0; i--)
            {
                string[] edge;
                for (int j = i; j >= 0; j--)
                    if ((edge = GetEdge(verts[j].name, verts[i].name)) != null)
                        GetVertix(edge[0]).t_after = Math.Min(GetVertix(edge[0]).t_after, verts[i].t_after - Int(edge[2]));
            }
            foreach (var i in verts)
                i.r = i.t_after - i.t_early;
        }

        static int Int(string str)
        {
            return Convert.ToInt32(str);
        }

        static string[][] Read(string path)
        {
            StreamReader reader = new StreamReader(path);
            List<string[]> list = new List<string[]>();
            while (!reader.EndOfStream)
                list.Add(reader.ReadLine().Split(' '));
            return list.ToArray();
        }

        static bool VertixExists(string name, List<Vertix> list)
        {
            for (int i = 0; i < list.Count; i++)
                if (list[i].name == name)
                    return true;
            return false;
        }

        static string[] GetEdge(string edge1, string edge2)
        {
            foreach (var i in edges)
                if (edge1 == i[0] && edge2 == i[1])
                    return i;
            return null;
        }

        static Vertix GetVertix(string name)
        {
            foreach (var i in verts)
                if (i.name == name)
                    return i;
            return null;
        }
    }
}

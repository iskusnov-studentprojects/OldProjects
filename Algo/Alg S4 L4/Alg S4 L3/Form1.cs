//Найти все циклы заданной длины
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;

namespace Alg_S4_L3
{
    public partial class Form1 : Form
    {
        List<string> verts;
        List<string[]> edges;
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            verts = new List<string>();
            edges = new List<string[]>();
            StreamReader reader = new StreamReader("input.dat");
            string str = reader.ReadLine();
            string[] mass = str.Split(new Char[] { ' ' });
            foreach (string i in mass)
                verts.Add(i);
            str = reader.ReadLine();
            mass = str.Split(new Char[] { ' ' });
            foreach (string i in mass)
                edges.Add(i.Split(new Char[] { '-' }));
            canvas1.CreateGraph(verts,edges);
        }

        void DFS(List<string> vertices, List<string[]> edges, string current, List<List<string>> pathes, string begin, string end, int lengthpath)
        {
            if (pathes.Count == 0)
            {
                List<string> l = new List<string>();
                l.Add(begin);
                pathes.Add(l);
            }
            if (pathes[0].Count != 0 && pathes[0][pathes[0].Count-1] == end && pathes[0].Count == lengthpath && !ExistsCycl(pathes,pathes[0]))
                pathes.Add(CloneList(pathes[0]));
            else
            {
                foreach (string i in vertices)
                    if (!VertixExist(pathes[0], i))
                        foreach (string[] j in edges)
                            if (EdgeExist(j, i) && EdgeExist(j, current))
                            {
                                pathes[0].Add(i);
                                DFS(vertices, edges, i, pathes, begin, end,lengthpath);
                            }
            }
            if (pathes[0].Count != 0)
                pathes[0].RemoveAt(pathes[0].Count - 1);
        }

        bool EdgeExist(string[] edge, string vertix)
        {
            if (edge[0] == vertix || edge[1] == vertix)
                return true;
            return false;
        }

        bool VertixExist(List<string> path, string vert)
        {
            foreach (string i in path)
                if (i == vert)
                    return true;
            return false;
        }

        List<string> CloneList(List<string> current)
        {
            List<string> newlist = new List<string>();
            foreach (string i in current)
                newlist.Add(i);
            return newlist;
        }

        bool ExistsCycl(List<List<string>> cycles, List<string> current)
        {
            for (int i = 1; i < cycles.Count; i++)
            {
                if (SameCycl(cycles[i], current))
                    return true;
            }
            return false;
        }

        bool SameCycl(List<string> cycl1, List<string> cycl2)
        {
            List<string> c1 = CloneList(cycl1), c2 = CloneList(cycl2);
            c1.Sort(); c2.Sort();
            for (int i = 0; i < c1.Count; i++)
                if (c1[i] != c2[i])
                    return false;
            return true;
        }

        string MakeString(List<string> list)
        {
            string str = "";
            str += list[0];
            for (int i = 1; i < list.Count; i++)
                str += "-" + list[i];
            return str;
        }

        private void FindCycles_Click(object sender, EventArgs e)
        {
            ListCycles.Items.Clear();
            if (verts == null)
            {
                MessageBox.Show("Граф не загружен!");
                return;
            }
            if (InputLengthCycle.Text == "")
            {
                MessageBox.Show("Введите длину цикла!");
                return;
            }
            List<List<string>> cycles = new List<List<string>>();
            int lengthcycle = Convert.ToInt32(InputLengthCycle.Text);
            foreach (var i in verts)
                DFS(verts, edges, i, cycles, i, i, lengthcycle);
            cycles.RemoveAt(0);
            foreach (var i in cycles)
            {
                ListCycles.Items.Add(MakeString(i));
            }
        }
    }
}

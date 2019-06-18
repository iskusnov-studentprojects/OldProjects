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

namespace Alg_S4_L5
{
    public partial class Form1 : Form
    {
        List<string> verts;
        List<string[]> edges;

        public Form1()
        {
            InitializeComponent();
        }

        private void loadgraph_Click(object sender, EventArgs e)
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
            canvas1.CreateOrientedGraph(verts, edges);
        }

        void FindDominatingSet(List<List<string>> sets, int degree, int _i)
        {
            if (sets.Count == 0)
                sets.Add(new List<string>());
            if (sets[sets.Count - 1].Count + verts.Count - _i < degree)
                return;
            for (int i = _i; i < verts.Count; i++ )
            {
                sets[sets.Count - 1].Add(verts[i]);
                if (sets[sets.Count - 1].Count < degree)
                    FindDominatingSet(sets, degree,i);
                else
                {
                    if (IsDominating(sets[sets.Count - 1]))
                        sets.Add(CloneList(sets[sets.Count - 1]));
                }
                sets[sets.Count - 1].RemoveAt(sets[sets.Count - 1].Count - 1);
            }
        }

        bool IsDominating(List<string> set)
        {
            foreach (var i in verts)
                if (!VertixExistsIn(i, set) && !IsRechable(i, set))
                    return false;
            return true;
        }

        bool IsRechable(string vertix, List<string> set)
        {
            foreach (var i in set)
                if (EdgeExists(i, vertix))
                    return true;
            return false;
        }

        bool EdgeExists(string begin, string end)
        {
            foreach (var i in edges)
                if (begin == i[0] && end == i[1])
                    return true;
            return false;
        }

        bool VertixExistsIn(string vertix, List<string> set)
        {
            foreach (var i in set)
                if (i == vertix)
                    return true;
            return false;
        }

        List<string> CloneList(List<string> list)
        {
            List<string> newlist = new List<string>();
            foreach (string i in list)
                newlist.Add(i);
            return newlist;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            List<List<string>> sets = new List<List<string>>();
            for(int i = 1; i <= verts.Count; i++)
            {
                FindDominatingSet(sets,i,0);
                sets.RemoveAt(sets.Count - 1);
                if(sets.Count > 0)
                    break;
            }
            label1.Text = "Число доминирования: " + sets[0].Count.ToString();
            label2.Text = "Доминирующие множества: ";
            foreach(var i in sets)
            {
                label2.Text += '(' + MakeString(i) + ')' + '\n';
            }
        }

        string MakeString(List<string> list)
        {
            string str = "";
            foreach (var i in list)
                str += i + ',';
            str = str.Remove(str.Length - 1);
            return str;
        }
    }
}

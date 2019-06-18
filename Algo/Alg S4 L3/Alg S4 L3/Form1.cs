using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
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

        private void LoadGraph_Click(object sender, EventArgs e)
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
            canvas1.CreateWeightedGraph(verts,edges);
        }

        List<string[]> CloneList(List<string[]> current)
        {
            List<string[]> newlist = new List<string[]>();
            foreach (string[] i in current)
                newlist.Add(i);
            return newlist;
        }

        private void CreateFrame_Click(object sender, EventArgs e)
        {
            if (verts == null)
            {
                MessageBox.Show("Граф не загружен!");
                return;
            }
            List<string[]> frame = new List<string[]>(), localeges = CloneList(edges);
            Random rand = new Random();
            Prim(verts[rand.Next(verts.Count)], localeges, frame);
            canvas2.CreateGraph(verts, frame);
        }

        void Prim(string vertix, List<string[]> edges, List<string[]> frame)
        {
            string[] edge = null;
            List<string> localvertices = new List<string>();
            localvertices.Add(vertix);
            while (localvertices.Count != verts.Count)
            {
                if (frame.Count != 0)
                {
                    for (int i = 0; i < localvertices.Count; i++ )
                    {
                        string[] current = MinEdge(localvertices[i], edges);
                        if (current != null && (edge == null || Convert.ToInt32(edge[2]) >= Convert.ToInt32(current[2])))
                            edge = current;
                    }
                    if (edge != null && !VertixExists(VertixExists(edge[0], localvertices) ? edge[1] : edge[0], localvertices))
                    {
                        frame.Add(edge);
                        localvertices.Add(VertixExists(edge[0], localvertices) ? edge[1] : edge[0]);
                    }
                    edges.Remove(edge);
                    edge = null;
                }
                else
                {
                    edge = MinEdge(vertix, edges);
                    frame.Add(edge);
                    localvertices.Add(VertixExists(edge[0], localvertices) ? edge[1] : edge[0]);
                    edges.Remove(edge);
                    edge = null;
                }
            }
        }

        bool VertixExists(string vertix, List<string> vertices)
        {
            foreach (var i in vertices)
                if (i == vertix)
                    return true;
            return false;
        }

        string[] MinEdge(string vertix, List<string[]> edges)
        {
            int min = int.MaxValue;
            string[] minedge = null;
            foreach (var i in edges)
            {
                if (Convert.ToInt32(i[2]) <= min && (i[0] == vertix || i[1] == vertix))
                {
                    min = Convert.ToInt32(i[2]);
                    minedge = i;
                }
            }
            return minedge;
        }
    }
}

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

namespace Alg_S4_L10
{
    public partial class Form1 : Form
    {
        List<string> vertices;
        List<string[]> edges;
        string s;
        string t;
        int flowCost;

        public Form1()
        {
            InitializeComponent();
        }

        private void LoadGraph_Click(object sender, EventArgs e)
        {
            vertices = new List<string>();
            edges = new List<string[]>();
            StreamReader reader = new StreamReader("input.dat");
            string str = reader.ReadLine();
            string[] mass = str.Split(new Char[] { ' ' });
            foreach (string i in mass)
                vertices.Add(i);
            str = reader.ReadLine();
            mass = str.Split(new Char[] { ' ' });
            foreach (string i in mass)
                edges.Add(i.Split(new Char[] { '-' }));
            str = reader.ReadLine();
            mass = str.Split(new Char[] { ' ' });
            s = mass[0];
            t = mass[1];
            canvas1.CreateOrientedWeightedGraph(vertices, edges);
            flowCost = 0;
            reader.Close();
        }

        private void ButtonMaxFlow_Click(object sender, EventArgs e)
        {
            if (!IsSourse(s))
                throw new Exception("Источник указан неверно.");
            if (!IsRunoff(t))
                throw new Exception("Сток указан неверно.");
            var flow = CreateMassFlow();
            FordFalkerson(flow);
            canvas2.CreateGraphWithFlow(vertices, flow);
            edgesListBox.Items.Clear();
            foreach (var i in edges)
                edgesListBox.Items.Add(i[0] + '-' + i[1] + '-' + i[3]);
            foreach (var i in flow)
                flowCost += Convert.ToInt32(i[3]) * Convert.ToInt32(GetEdge(i[0], i[1])[3]);
            numberFlowCostLabel.Text = flowCost.ToString();
        }

        List<string[]> CreateMassFlow()
        {
            var flow = new List<string[]>();
            foreach (var i in edges)
            {
                flow.Add(new string[4]);
                flow[flow.Count - 1][0] = i[0];
                flow[flow.Count - 1][1] = i[1];
                flow[flow.Count - 1][2] = i[2];
                flow[flow.Count - 1][3] = "0";
            }
            return flow;
        }

        List<string[]> CreateBandwidth(List<string[]> flow)
        {
            var bandwidth = new List<string[]>();
            foreach (var i in flow)
            {
                if (Convert.ToInt32(i[3]) - Convert.ToInt32(i[2]) != 0)
                {
                    string[] str = new string[4];
                    str[0] = i[0];
                    str[1] = i[1];
                    str[2] = (Convert.ToInt32(i[2]) - Convert.ToInt32(i[3])).ToString();
                    str[3] = i[3];
                    bandwidth.Add(str);
                }
            }
            return bandwidth;
        }

        bool IsSourse(string vertix)
        {
            foreach (var i in edges)
                if (vertix == i[1])
                    return false;
            return true;
        }

        bool IsRunoff(string vertix)
        {
            foreach (var i in edges)
                if (vertix == i[0])
                    return false;
            return true;
        }

        string[] FindMinEdge(List<string> path, List<string[]> list)
        {
            string[] min = GetEdge(path[0], path[1], list);
            for (int i = 1; i < path.Count - 1; i++)
                if (Convert.ToInt32(GetEdge(path[i], path[i + 1], list)[2]) < Convert.ToInt32(min[2]))
                    min = GetEdge(path[i], path[i + 1], list);
            return min;
        }

        bool EdgeExists(string begin, string end)
        {
            foreach (var i in edges)
                if (i[0] == begin && i[1] == end)
                    return true;
            return false;
        }

        bool EdgeExists(string begin, string end, List<string[]> list)
        {
            foreach (var i in list)
                if (i[0] == begin && i[1] == end)
                    return true;
            return false;
        }

        List<List<object>> CreateMassVerts()
        {
            var mass = new List<List<object>>();
            foreach (var i in vertices)
            {
                mass.Add(new List<object>(4));
                mass[mass.Count - 1].Add(i);
                mass[mass.Count - 1].Add(int.MaxValue);
                mass[mass.Count - 1].Add(false);
                mass[mass.Count - 1].Add(null);
            }
            return mass;
        }

        List<object> GetElement(string name, List<List<object>> list)
        {
            foreach (var i in list)
                if (name == (string)i[0])
                    return i;
            return null;
        }

        List<string> Dijkstra(string begin, string end, List<string[]> bandwidth)
        {
            var verts = CreateMassVerts();
            List<string> path = new List<string>();
            path.Add(begin);
            List<object> current = GetElement(begin, verts);
            current[1] = 0;
            while (!EndDijkstra(verts))
            {
                current[2] = true;
                foreach (var i in verts)
                    if (EdgeExists((string)current[0], (string)i[0], bandwidth))
                        if ((int)i[1] > (int)current[1] + Convert.ToInt32(GetEdge((string)current[0], (string)i[0], bandwidth)[3]) && (int)current[1] != int.MaxValue)
                        {
                            i[1] = (int)current[1] + Convert.ToInt32(GetEdge((string)current[0], (string)i[0], bandwidth)[3]);
                            i[3] = (string)current[0];
                        }
                current = FindMinPath(verts);
            }
            current = GetElement(end, verts);
            Stack<string> stack = new Stack<string>();
            while ((string)current[0] != begin)
            {
                stack.Push((string)current[0]);
                current = GetElement((string)current[3], verts);
                if (current == null)
                    return null;
            }
            while (stack.Count != 0)
                path.Add(stack.Pop());
            return path;
        }

        string[] GetEdge(string begin, string end)
        {
            foreach (var i in edges)
                if (begin == i[0] && end == i[1])
                    return i;
            return null;
        }

        string[] GetEdge(string begin, string end, List<string[]> list)
        {
            foreach (var i in list)
                if (begin == i[0] && end == i[1])
                    return i;
            return null;
        }

        List<object> FindMinPath(List<List<object>> list)
        {
            int min = int.MaxValue;
            List<object> ret = null;
            foreach (var i in list)
                if (!(bool)i[2] && (int)i[1] <= min)
                {
                    ret = i;
                    min = (int)i[1];
                }
            return ret;
        }

        bool EndDijkstra(List<List<object>> list)
        {
            foreach (var i in list)
                if (!(bool)i[2])
                    return false;
            return true;
        }

        void FordFalkerson(List<string[]> flow)
        {
            List<string> path = Dijkstra(s, t, CreateBandwidth(flow));
            while (path != null)
            {
                var bandwidth = CreateBandwidth(flow);
                for (int i = 0; i < path.Count - 1; i++)
                    GetEdge(path[i], path[i + 1], flow)[3] = (Convert.ToInt32(GetEdge(path[i], path[i + 1], flow)[3]) + Convert.ToInt32(FindMinEdge(path, bandwidth)[2])).ToString();
                path = Dijkstra(s, t, CreateBandwidth(flow));
            }
        }
    }
}

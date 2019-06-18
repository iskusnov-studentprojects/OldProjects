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
using GraphVisualization;

namespace Alg_S4_L6
{
    public partial class Form1 : Form
    {
        List<string> vertices;
        List<string[]> edges;
        List<Color> allcolors;
        int index;

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
            canvas1.CreateGraph(vertices, edges);
            CreateMassColors();
        }

        private void Colorize_Click(object sender, EventArgs e)
        {
            ColorsList.Items.Clear();
            index = 0;
            List<Color> usecolors = new List<Color>();
            List<string> colorizevertices = new List<string>();
            foreach (var i in vertices)
            {
                List<Color> usecol = CloneList(usecolors);
                foreach (var j in colorizevertices)
                {
                    if (EdgeExists(i, j))
                        usecol.Remove(canvas1.GetVertix(j).BackColor);
                }
                if (usecol.Count == 0)
                {
                    Color c = GetNextColor();
                    canvas1.GetVertix(i).BackColor = c;
                    usecolors.Add(c);
                    ColorsList.Items.Add(c);
                }
                else 
                {
                    canvas1.GetVertix(i).BackColor = usecol[0];
                }
                colorizevertices.Add(i);
            }
            canvas1.Invalidate();
        }

        private void CreateMassColors()
        {
            allcolors = new List<Color>();
            allcolors.Add(Color.Aquamarine);
            allcolors.Add(Color.MistyRose);
            allcolors.Add(Color.PeachPuff);
            allcolors.Add(Color.Purple);
            allcolors.Add(Color.SeaShell);
            allcolors.Add(Color.Tan);
            allcolors.Add(Color.Yellow);
            allcolors.Add(Color.Tomato);
            allcolors.Add(Color.Navy);
            allcolors.Add(Color.MediumOrchid);
            allcolors.Add(Color.LawnGreen);
        }

        bool EdgeExists(string begin, string end)
        {
            foreach (var i in edges)
                if (i[0] == begin && i[1] == end || i[0] == end && i[1] == begin)
                    return true;
            return false;
        }

        List<Color> CloneList(List<Color> current)
        {
            List<Color> newlist = new List<Color>();
            foreach (var i in current)
                newlist.Add(i);
            return newlist;
        }

        Color GetNextColor()
        {
            if (index > allcolors.Count)
                throw new Exception("Недостаточно цветов для раскраски графа.");
            Color ret = allcolors[index];
            index++;
            return ret;
        }
    }
}

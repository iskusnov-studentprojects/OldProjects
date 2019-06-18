using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GraphVisualization
{
    public partial class Canvas: UserControl
    {
        /// <summary>
        /// Граф
        /// </summary>
        Graph graph;

        public Canvas()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Создать граф
        /// </summary>
        /// <param name="Vertices">
        /// Список вершин графа
        /// </param>
        /// <param name="Edges">
        /// Список ребер графа.
        /// Для каждого i элемента: Edges[i][0] - начало ребра, Edges[i][1] - конец ребра
        /// </param>
        public void CreateGraph(List<string> Vertices, List<string[]> Edges)
        {
            graph = new Graph(Vertices,Edges);
            Invalidate();
        }

        public void CreateWeightedGraph(List<string> Vertices, List<string[]> Edges)
        {
            graph = new WeightedGraph(Vertices, Edges);
            Invalidate();
        }

        public void CreateOrientedGraph(List<string> Vertices, List<string[]> Edges)
        {
            graph = new OrientedGraph(Vertices, Edges);
            Invalidate();
        }

        public void CreateOrientedWeightedGraph(List<string> Vertices, List<string[]> Edges)
        {
            graph = new OrientedWeightedGraph(Vertices, Edges);
            Invalidate();
        }

        public void CreateGraphWithFlow(List<string> Vertices, List<string[]> Edges)
        {
            graph = new GraphWithFlow(Vertices, Edges);
            Invalidate();
        }

        private void Draw(object sender, PaintEventArgs e)
        {
            if(graph != null)
                graph.Draw(e.Graphics);
        }
        private void Canvas_MouseMove(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                Vertix v = InRadius(e);
                if (v != null)
                {
                    v.Location = e.Location;
                    Invalidate();
                }
            }
        }
        private Vertix InRadius(MouseEventArgs e)
        {
            foreach(Vertix i in graph.Vertices)
                if(Math.Pow(e.Location.X - i.Location.X,2) + Math.Pow(e.Location.Y - i.Location.Y,2) <= 100)
                    return i;
            return null;
        }

        public Vertix GetVertix(string name)
        {
            return graph.GetVertix(name);
        }
    }
}

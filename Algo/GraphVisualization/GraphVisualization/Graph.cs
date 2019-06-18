using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace GraphVisualization
{
    class Graph
    {
        public List<Vertix> Vertices;
        public List<Edge> Edges;
        public Graph(List<string> vertices, List<string[]> edges)
        {
            Vertices = new List<Vertix>();
            Edges = new List<Edge>();
            CreateGraph(vertices, edges);
        }
        protected virtual void CreateGraph(List<string> vertices, List<string[]> edges)
        {
            int x = 120, y = 120, r = 100;
            double arg = 0;
            foreach (string i in vertices)
            {
                Vertices.Add(new Vertix(i, new Point(Convert.ToInt32(x + Math.Cos(arg) * r), Convert.ToInt32(y + Math.Sin(arg) * r))));
                arg += Math.PI * 2 / vertices.Count; 
            }
            foreach (string[] i in edges)
            {
                Edges.Add(new Edge(FindVertix(i[0]), FindVertix(i[1])));
            }
        }
        public virtual void Draw(Graphics canvas)
        {
            foreach (Edge i in Edges)
                i.Draw(canvas);
            foreach (Vertix i in Vertices)
                i.Draw(canvas);
        }

        protected Vertix FindVertix(string name)
        {
            foreach (Vertix i in Vertices)
                if (i.Name == name)
                    return i;
            return null;
        }

        public Vertix GetVertix(string name)
        {
            foreach (var i in Vertices)
                if (name == i.Name)
                    return i;
            return null;
        }
    }
}

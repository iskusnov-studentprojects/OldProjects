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

namespace ReedBlackTree
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        RBTree<Student> tree;
        RBTree<Student> newTree;

        int depthTree;
        List<Vertix> nodes;
        List<Edge> edges;
        List<Vertix> nodes1;
        List<Edge> edges1;

        private void LoadRBTree(object sender, EventArgs e)
        {
            tree = new RBTree<Student>();
            LoadFromFile();
        }

        void CountDepthTree(Node<Student> current, int depth)
        {
            if (depth > depthTree)
                depthTree = depth;
            if (current.left != null)
                CountDepthTree(current.left, depth + 1);
            if (current.right != null)
                CountDepthTree(current.right, depth + 1);
        }

        int CountDepthNode(Node<Student> current, Node<Student> x, int depth)
        {
            if (current == x || x == null || current == null)
                return depth;
            if (x.key < current.key)
                return CountDepthNode(current.left, x, depth + 1);
            else
                return CountDepthNode(current.right, x, depth + 1);
        }

        void CreateGraph(Node<Student> current, Vertix parent, Point location, Rectangle canvasSize, List<Vertix> n,List<Edge> e)
        {
            Vertix v = new Vertix(current.key.ToString(), current.color, location);
            n.Add(v);
            if (parent != null)
                e.Add(new Edge(parent, v));
            if (current.left != null)
                CreateGraph(current.left, v, new Point((int)(location.X - canvasSize.Width / Math.Pow(2, CountDepthNode(tree.GetRoot, current.left, 1))), location.Y + canvasSize.Height / depthTree), canvasSize,n,e);
            else
            {
                NILVertix nv = new NILVertix(new Point((int)(location.X - canvasSize.Width / Math.Pow(2, CountDepthNode(tree.GetRoot, current, 1) + 1)), location.Y + canvasSize.Height / depthTree));
                n.Add(nv);
                e.Add(new Edge(v, nv));
            }
            if (current.right != null)
                CreateGraph(current.right, v, new Point((int)(location.X + canvasSize.Width / Math.Pow(2, CountDepthNode(tree.GetRoot, current.right, 1))), location.Y + canvasSize.Height / depthTree), canvasSize,n,e);
            else
            {
                NILVertix nv = new NILVertix(new Point((int)(location.X + canvasSize.Width / Math.Pow(2, CountDepthNode(tree.GetRoot, current, 1) + 1)), location.Y + canvasSize.Height / depthTree));
                n.Add(nv);
                e.Add(new Edge(v, nv));
            }
        }

        private void pictureBox1_Paint(object sender, PaintEventArgs e)
        {
            if (tree != null)
            {
                CountDepthTree(tree.GetRoot, 1);
                nodes = new List<Vertix>();
                edges = new List<Edge>();
                if (tree != null)
                    CreateGraph(tree.GetRoot, null, new Point(pictureBox2.Width / 2, 10), new Rectangle(0, 0, pictureBox2.Width, pictureBox2.Height), nodes, edges);
                e.Graphics.Clear(Color.White);
                if (edges != null)
                    foreach (var i in edges)
                        i.Draw(e.Graphics);
                if (nodes != null)
                    foreach (var i in nodes)
                        i.Draw(e.Graphics);
            }
        }

        private int InRadius(Point location)
        {
            foreach (var i in nodes)
                if (Math.Pow(location.X - i.location.X, 2) + Math.Pow(location.Y - i.location.Y, 2) <= 100 && !(i is NILVertix))
                    return Convert.ToInt32(i.key);
            return int.MinValue;
        }

        private void pictureBox1_MouseClick(object sender, MouseEventArgs e)
        {
            int key = InRadius(e.Location);
            Node<Student> node = tree.Find(key);
            if(node != null)
            MessageBox.Show("Ключ: " + node.key.ToString() + '\n'
                + "Имя: " + node.data.name + '\n'
                + "Номер зачетки: " + node.data.number.ToString() + '\n'
                + "Балы за 1 экзамен: " + node.data.examResults[0] + '\n'
                + "Балы за 2 экзамен: " + node.data.examResults[1] + '\n'
                + "Балы за 3 экзамен: " + node.data.examResults[2] + '\n'
                + "Балы за 4 экзамен: " + node.data.examResults[3] + '\n'
                + "Балы за 5 экзамен: " + node.data.examResults[4] + '\n');
        }

        void LoadFromFile()
        {
            StreamReader reader = new StreamReader(File.Open("input.dat", FileMode.Open));
            tree = new RBTree<Student>();
            while (!reader.EndOfStream)
            {
                string[] strmass = reader.ReadLine().Split(' ');
                tree.Insert(Convert.ToInt32(strmass[1]), new Student(strmass[0],
                    Convert.ToInt32(strmass[1]), 
                    Convert.ToInt32(strmass[2]),
                    Convert.ToInt32(strmass[3]),
                    Convert.ToInt32(strmass[4]),
                    Convert.ToInt32(strmass[5]),
                    Convert.ToInt32(strmass[6])));
            }
            pictureBox1.Invalidate();
        }
        
        private void createTree_Click(object sender, EventArgs e)
        {
            newTree = new RBTree<Student>();
            rec(tree.GetRoot, newTree);
            pictureBox2.Invalidate();
        }

        private void rec(Node<Student> current, RBTree<Student> newTree)
        {
            if (current.data.examResults[0] < 50 || current.data.examResults[1] < 50 ||
                current.data.examResults[2] < 50 || current.data.examResults[3] < 50 ||
                current.data.examResults[4] < 50)
            {
                newTree.Insert(current.key, current.data);
            }
            if (current.left != null)
                rec(current.left, newTree);
            if (current.right != null)
                rec(current.right, newTree);
        }

        private void pictureBox2_Paint(object sender, PaintEventArgs e)
        {
            if (newTree != null)
            {
                CountDepthTree(newTree.GetRoot, 1);
                edges1 = new List<Edge>();
                nodes1 = new List<Vertix>();
                if (newTree != null)
                    CreateGraph(newTree.GetRoot, null, new Point(pictureBox2.Width / 2, 10), new Rectangle(0, 0, pictureBox2.Width, pictureBox2.Height), nodes1, edges1);
                e.Graphics.Clear(Color.White);
                if (edges1 != null)
                    foreach (var i in edges1)
                        i.Draw(e.Graphics);
                if (nodes1 != null)
                    foreach (var i in nodes1)
                        i.Draw(e.Graphics);
            }
        }

        private void pictureBox2_MouseClick(object sender, MouseEventArgs e)
        {
            int key = InRadius(e.Location);
            Node<Student> node = newTree.Find(key);
            if(node != null)
            MessageBox.Show("Ключ: " + node.key.ToString() + '\n'
                + "Имя: " + node.data.name + '\n'
                + "Номер зачетки: " + node.data.number.ToString() + '\n'
                + "Балы за 1 экзамен: " + node.data.examResults[0] + '\n'
                + "Балы за 2 экзамен: " + node.data.examResults[1] + '\n'
                + "Балы за 3 экзамен: " + node.data.examResults[2] + '\n'
                + "Балы за 4 экзамен: " + node.data.examResults[3] + '\n'
                + "Балы за 5 экзамен: " + node.data.examResults[4] + '\n');
        }
    }
}

using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ORandGTL3
{
    public partial class Form1 : Form
    {
        int[][] table;
        Tree<Pair<int[][], Point>> tree;
        int[] path;

        public Form1()
        {
            InitializeComponent();
        }

        private int[][] ReadFromFile(string path)
        {
            StreamReader reader = new StreamReader(File.Open(path, FileMode.Open));
            List<int[]> list = new List<int[]>();
            int i = 0;
            while (!reader.EndOfStream)
            {
                string[] str = reader.ReadLine().Split(' ');
                list.Add(new int[str.Length]);
                for (int j = 0; j < str.Length; j++)
                {
                    if (str[j] == "inf")
                        list[i][j] = int.MaxValue;
                    else
                        list[i][j] = Convert.ToInt32(str[j]);
                }
                i++;
            }
            reader.Close();

            return list.ToArray();
        }

        private void InitializeTreeView()
        {
            treeView.BeginUpdate();
            treeView.Nodes.Add("Root");
            InitializeTreeView(tree.root, treeView.Nodes[0]);
            treeView.EndUpdate();
        }

        private void InitializeTreeView(Tree<Pair<int[][], Point>>.Node node, TreeNode viewNode)
        {
            if (node.left != null)
            {
                string str = "";
                str += (!node.left.flag ? "!" : "");
                str += "(" + node.left.data.b.X.ToString() + "," + node.left.data.b.Y.ToString() +")";
                str += " " + ( (node.left.key == int.MaxValue) ? "Infinity" : node.left.key.ToString());
                viewNode.Nodes.Add(str);
                InitializeTreeView(node.left, viewNode.Nodes[0]);
            }
            if (node.right != null)
            {
                string str = "";
                str += (!node.right.flag ? "!" : "");
                str += "(" + node.right.data.b.X.ToString() + "," + node.right.data.b.Y.ToString() + ")";
                str += " " + ( (node.right.key ==  int.MaxValue) ? "Infinity" : node.right.key.ToString());
                viewNode.Nodes.Add(str);
                InitializeTreeView(node.right, viewNode.Nodes[1]);
            }
        }

        private void loadData_Click(object sender, EventArgs e)
        {
            table = ReadFromFile("d:\\input.dat");
            tree = Algorithm.Solve(table);
            path = Algorithm.RestorePath(tree);
            InitializeTreeView();
            pathLabel.Text = "Последовательность: ";
            pathLabel.Text += path[0].ToString();
            for (int i = 1; i < path.Length; i++)
                pathLabel.Text += " - " + path[i].ToString();
        }
    }
}

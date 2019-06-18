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

namespace ORandGTL2
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        int[][] oldTable;
        int[][][] tables;
        int bestNum;
        int currNum;
        static int spaces = 4;

        private int[][] loadFromFile(string path)
        {
            StreamReader reader = new StreamReader(File.Open(path, FileMode.Open));
            List<int[]> col = new List<int[]>();
            int i = 0;
            while (!reader.EndOfStream)
            {
                string[] str = reader.ReadLine().Split(' ');
                col.Add(new int[str.Length]);
                for (int j = 0; j < str.Length; j++)
                {
                    col[i][j] = Convert.ToInt32(str[j]);
                }
                i++;
            }
            reader.Close();

            return col.ToArray();
        }

        private void loadDataButton_Click(object sender, EventArgs e)
        {
            oldTable = loadFromFile("input.dat");
            bestNum = Algorithm.Sort(oldTable);
            currNum = bestNum;
            tables = Algorithm.allTables(oldTable);
            comboBox1.SelectedIndex = currNum;

            opt1.Select();
            AddToFirstList(oldTable, table1);

            int[][] tmp1 = Algorithm.matrixMethod(oldTable);

            answerLabel1.Text = "Время простоя: " + Max(tmp1[tmp1.Length - 1]).ToString();
        }

        private void AddToFirstList(int[][] table, ListBox listBox)
        {
            int[] P1 = Algorithm.countP1(table),
                P2 = Algorithm.countP2(table),
                lambda = Algorithm.countLambda(P1, P2);
            int[][] D1 = Algorithm.countD1(table, lambda),
                D0 = Algorithm.countD0(table, lambda),
                D2 = Algorithm.countD2(table, lambda);
            int[][] sequences = { Algorithm.rule1(P1, P2, Algorithm.merge(D0, D1), D2, table), Algorithm.rule2(lambda), Algorithm.rule3(P1, P2, D0, D1, D2, table), Algorithm.rule4(P1, P2, lambda, D0, D1, D2, table) };

            listBox.Items.Clear();
            listBox.Items.Add(Gran(spaces - 2, spaces, table[0].Length + 8));
            string str = "";
            str += "|" + "di" + Spaсes(spaces - "di".Length - 2) + "|";
            for (int i = 0; i < table[0].Length; i++)
            {
                str += "S" + (i+1).ToString() + Spaсes(spaces - 1 - (i+1).ToString().Length) + "|";
            }
            str += "Pi1" + Spaсes(spaces - 3) + "|";
            str += "Pi2" + Spaсes(spaces - 3) + "|";
            str += "Li" + Spaсes(spaces - 2) + "|";
            str += "sq1" + Spaсes(spaces - 3) + "|";
            str += "sq2" + Spaсes(spaces - 3) + "|";
            str += "sq3" + Spaсes(spaces - 3) + "|";
            str += "sq4" + Spaсes(spaces - 3) + "|";
            listBox.Items.Add(str);
            listBox.Items.Add(Gran(spaces - 2, spaces, table[0].Length + 8));
            for (int i = 0; i < table.Length; i++)
            {
                str = "";
                str += "|" + (i + 1).ToString() + Spaсes(spaces - (i + 1).ToString().Length - 2) + "|";
                for (int j = 0; j < table[0].Length; j++)
                {
                    str += table[i][j].ToString() + Spaсes(spaces - table[i][j].ToString().Length) + "|";
                }
                str += P1[i].ToString() + Spaсes(spaces - P1[i].ToString().Length) + "|";
                str += P2[i].ToString() + Spaсes(spaces - P2[i].ToString().Length) + "|";
                str += lambda[i].ToString() + Spaсes(spaces - lambda[i].ToString().Length) + "|";
                str += (sequences[0][i] + 1).ToString() + Spaсes(spaces - sequences[0][i].ToString().Length) + "|";
                str += (sequences[1][i] + 1).ToString() + Spaсes(spaces - sequences[1][i].ToString().Length) + "|";
                str += (sequences[2][i] + 1).ToString() + Spaсes(spaces - sequences[2][i].ToString().Length) + "|";
                str += (sequences[3][i] + 1).ToString() + Spaсes(spaces - sequences[3][i].ToString().Length) + "|";
                listBox.Items.Add(str);
            }
            listBox.Items.Add(Gran(spaces - 2, spaces, table[0].Length + 8));
        }

        private void AddToSecondList(int[][] table, ListBox listBox)
        {
            int[][] matrix = Algorithm.matrixMethod(table);
            listBox.Items.Clear();
            listBox.Items.Add(Gran(spaces - 2, spaces * 2 + 1, table[0].Length + 2));
            string str = "";
            str += "|" + "di" + Spaсes(spaces - "di".Length - 2) + "|";
            for (int i = 0; i < table[0].Length; i++)
            {
                str += "S" + (i + 1).ToString() + Spaсes(spaces*2 - (i + 1).ToString().Length) + "|";
            }
            str += "Tож" + Spaсes(spaces*2 - 2) + "|";
            listBox.Items.Add(str);
            listBox.Items.Add(Gran(spaces - 2, spaces * 2 + 1, table[0].Length + 2));
            for (int i = 0; i < table.Length; i++)
            {
                str = "";
                str += "|" + (i + 1).ToString() + Spaсes(spaces - (i + 1).ToString().Length - 2) + "|";
                for (int j = 0; j < table[0].Length; j++)
                {
                    str += table[i][j].ToString() + Spaсes(spaces - table[i][j].ToString().Length) + "/" +
                        matrix[i][j].ToString() + Spaсes(spaces - matrix[i][j].ToString().Length) + "|";
                }
                str += matrix[i][matrix[0].Length - 1].ToString() + Spaсes(spaces*2 - matrix[i][matrix[0].Length - 1].ToString().Length + 1) + "|";
                listBox.Items.Add(str);
            }
            str = "";
            str += "|" + "Tп" + Spaсes(spaces - 4) + "|";
            for (int i = 0; i < matrix[0].Length - 1; i++)
            {
                str += matrix[matrix.Length - 1][i].ToString() + Spaсes(spaces*2 - matrix[matrix.Length - 1][i].ToString().Length + 1) + "|";
            }

            int sum1 = 0,
                sum2 = 0;
            for (int i = 0; i < matrix.Length - 1; i++)
            {
                sum1 += matrix[i][matrix[0].Length - 1];
            }
            for (int i = 0; i < matrix[0].Length - 1; i++)
            {
                sum2 += matrix[matrix.Length - 1][i];
            }
            str += sum2.ToString() + Spaсes(spaces - sum1.ToString().Length) + "\\" +
                sum1.ToString() + Spaсes(spaces - sum2.ToString().Length) + "|";

            listBox.Items.Add(str);
            listBox.Items.Add(Gran(spaces - 2, spaces * 2 + 1, table[0].Length + 2));
        }

        private string Spaсes(int n)
        {
            string str = "";
            if (n <= 0)
                return str;
            for (int i = 0; i < n; i++)
                str += " ";
            return str;
        }

        private string Gran(int sp1, int sp2, int n)
        {
            string str = "";
            str += "|";
            for (int i = 0; i < sp1; i++)
                str += "-";
            str += "|";
            for (int i = 0; i < n - 1; i++)
            {
                for (int j = 0; j < sp2; j++)
                {
                    str += "-";
                }
                str += "|";
            }
            return str;
        }

        private void drawGraphicButton1_Click(object sender, EventArgs e)
        {
            if (oldTable == null)
            {
                MessageBox.Show("Данные не загружены.");
                return;
            };
            int[] seq = new int[oldTable.Length];
            for (int i = 0; i < seq.Length; i++)
                seq[i] = i;
            (new Graphic(oldTable, seq)).Show();
        }

        private void drawGraphicButton2_Click(object sender, EventArgs e)
        {
            if (tables == null)
            {
                MessageBox.Show("Данные не загружены.");
                return;
            }
            (new Graphic(tables[currNum], Algorithm.GetSequences(oldTable)[currNum])).Show();
        }

        private bool EqualLenthes(List<int[]> list)
        {
            for (int i = 1; i < list.Count; i++)
                if (list[i].Length != list[0].Length)
                    return false;
            return true;
        }

        private int[][] countDowntimes(int[][] table, int[][] tmp)
        {
            int[][] res = new int[table.Length][];
            for (int i = 0; i < res.Length; i++)
            {
                res[i] = new int[table[i].Length];
                for (int j = 0; j < res[i].Length; j++)
                {
                    res[i][j] = 0;
                }
            }

            for (int i = 0; i < res[0].Length; i++)
            {
                for (int j = 0; j < res.Length; j++)
                {
                    res[j][i] = tmp[j][i] - table[j][i];
                    if (j > 0)
                    {
                        int k = j - 1;
                        while (k >= 0 && tmp[k][i] == 0)
                            k--;
                        if (k == -1)
                            continue;
                        res[j][i] -= tmp[k][i];
                    }
                    if (res[j][i] < 0)
                        res[j][i] = 0;
                }
            }

            return res;
        }

        private int Max(int[] mass)
        {
            int m = int.MinValue;
            for (int i = 0; i < mass.Length; i++)
                if (mass[i] > m)
                    m = mass[i];
            return m;
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            currNum = comboBox1.SelectedIndex;
            int[][] tmp2 = Algorithm.matrixMethod(tables[currNum]);
            AddToSecondList(tables[currNum], table2);
            answerLabel2.Text = "Время простоя: " + Max(tmp2[tmp2.Length - 1]).ToString();
            if (currNum == bestNum)
                answerLabel2.BackColor = Color.LightGreen;
            else
                answerLabel2.BackColor = Color.LightGray;
        }

        private void opt1_CheckedChanged(object sender, EventArgs e)
        {
            if (opt1.Checked == true)
            {
                AddToFirstList(oldTable, table1);
            }
        }

        private void opt2_CheckedChanged(object sender, EventArgs e)
        {
            if (opt2.Checked == true)
            {
                AddToSecondList(oldTable, table1);
            }
        }
    }
}

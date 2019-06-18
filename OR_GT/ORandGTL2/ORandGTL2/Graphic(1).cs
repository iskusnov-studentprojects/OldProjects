using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ORandGTL2
{
    public partial class Graphic : Form
    {
        private int[][] table;
        private int[][] tmp;
        private int[][] downtimes;
        private int[] sequnce;

        public Graphic(int[][] _table, int[] _sequence)
        {
            table = _table;
            sequnce = _sequence;
            tmp = Algorithm.matrixMethod(table);
            downtimes = countDowntimes();
            InitializeComponent();
            Color[] colors = GetColors();
            ListView.ListViewItemCollection items = detailsViewBox.Items;
            items.Add("Простой");
            items[0].BackColor = colors[0];
            for (int i = 0; i < sequnce.Length; i++)
            {
                items.Add((sequnce[i] + 1).ToString() + "-я деталь").BackColor = colors[sequnce[i]];
            }
        }

        private Color[] GetColors()
        {
            List<Color> list = new List<Color>();
            list.Add(Color.LightGray);
            list.Add(Color.Orange);
            list.Add(Color.Green);
            list.Add(Color.Blue);
            list.Add(Color.Pink);
            list.Add(Color.Aquamarine);
            list.Add(Color.Olive);
            list.Add(Color.Red);
            list.Add(Color.Yellow);
            list.Add(Color.Purple);
            list.Add(Color.Brown);
            list.Add(Color.Violet);
            list.Add(Color.Orchid);
            list.Add(Color.Turquoise);
            list.Add(Color.SpringGreen);
            list.Add(Color.LemonChiffon);
            list.Add(Color.SlateBlue);
            list.Add(Color.Lime);
            list.Add(Color.Plum);
            return list.ToArray();
        }

        private void canvas_Paint(object sender, PaintEventArgs e)
        {
            Color[] colors = GetColors();
            Pen pen = new Pen(Color.Black);
            float stepw = ((float)canvas.Size.Width - 50) / maxsums(table, downtimes),
                steph = ((float)canvas.Size.Height - 20) / (table[0].Length + 1);
            e.Graphics.DrawLine(pen, 30, 10, 30, canvas.Size.Height - 10);
            pen.Width = 10;
            float curw, curh = 10 + steph;
            Char S = 'S', x = 'x', s = 's';
            int Si = 1;
            for (int i = 0; i < table[0].Length; i++)
            {
                curw = 31;
                e.Graphics.DrawString(S.ToString() + Si.ToString(), new Font("Arial", 12), new SolidBrush(Color.Black), new PointF(0, curh - 8), StringFormat.GenericDefault);
                
                for (int j = 0; j < table.Length; j++)
                {
                    if (downtimes[j][i] > 0)
                    {
                        pen.Color = colors[0];
                        e.Graphics.DrawLine(pen, curw, curh, curw + downtimes[j][i] * stepw, curh);
                        curw += downtimes[j][i] * stepw;
                        e.Graphics.DrawString(downtimes[j][i].ToString(), new Font("Arial", 9), new SolidBrush(Color.Black), new PointF(curw - ((stepw * downtimes[j][i]) / 2) - 10, curh + 2), StringFormat.GenericDefault);
                    }
                    if (table[j][i] > 0)
                    {
                        pen.Color = colors[sequnce[j] + 1];
                        e.Graphics.DrawLine(pen, curw, curh, curw + table[j][i] * stepw, curh);
                        curw += table[j][i] * stepw;
                        e.Graphics.DrawString(table[j][i].ToString(), new Font("Arial", 9), new SolidBrush(Color.Black), new PointF(curw - ((stepw * table[j][i]) / 2) - 10, curh + 2), StringFormat.GenericDefault);
                    }
                }
                Si++;
                curh += steph;
            }
        }

        private void canvas_Resize(object sender, EventArgs e)
        {
            canvas.Invalidate();
        }

        private int summ(int[] mass)
        {
            int sum = 0;
            for (int i = 0; i < mass.Length; i++)
                sum += mass[i];
            return sum;
        }

        private int summ(int[][] matrix, int j)
        {
            int sum = 0;
            for (int i = 0; i < matrix.Length; i++)
                sum += matrix[i][j];
            return sum;
        }

        private int maxsums(int[][] matrix1, int[][] matrix2)
        {
            int max = 0;
            for (int j = 0; j < matrix1[0].Length; j++)
                if (summ(matrix1, j) + summ(matrix2, j) > max)
                {
                    max = summ(matrix1, j) + summ(matrix2, j);
                }
            return max;
        }

        private int summ(int[][] mass, int i, int n)
        {
            int sum = 0;
            for (int j = 0; j < n; j++)
                sum += mass[j][i];
            return sum;
        }

        private int[][] countDowntimes()
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
    }
}

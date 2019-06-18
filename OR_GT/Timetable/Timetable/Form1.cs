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

namespace Timetable
{
    public partial class Form1 : Form
    {
        private int[][] columns;
        private int[][] downtimes;

        private int[][] newColumns;
        private int[][] newDowntimes;

        public Form1()
        {
            InitializeComponent();
        }

        private void loadDataButton_Click(object sender, EventArgs e)
        {
            StreamReader reader = new StreamReader(File.Open("input.dat", FileMode.Open));
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

            if(!EqualLenthes(col))
            {
                MessageBox.Show("Столбцы имееют разное количество элементов");
                return;
            }
            columns = col.ToArray();

            newColumns = new int[columns.Length][];
            downtimes = new int[columns.Length][];
            newDowntimes = new int[columns.Length][];
            for (i = 0; i < columns.Length; i++ )
            {
                newColumns[i] = Algorithm.copyMassive(columns[i]);
                downtimes[i] = new int[columns[0].Length];
                newDowntimes[i] = new int[columns[0].Length];
                for (int j = 0; j < columns[0].Length; j++)
                {
                    downtimes[i][j] = 0;
                    newDowntimes[i][j] = 0;
                }
            }

            AddToList1();

            if (columns.Length == 2)
                Algorithm.JohnsonsAlgorithm(newColumns[0], newColumns[1]);

            if(columns.Length == 3)
                Algorithm.JohnsonsAlgorithm(newColumns[0], newColumns[1], newColumns[2]);

            AddToList2();

            Algorithm.CountDowntimes(columns, downtimes);
            Algorithm.CountDowntimes(newColumns, newDowntimes);

            answerLabel1.Text = "Время: " + (Algorithm.summ(columns[columns.Length - 1]) + Algorithm.summ(downtimes[downtimes.Length - 1])).ToString();
            answerLabel2.Text = "Время: " + (Algorithm.summ(newColumns[newColumns.Length - 1]) + Algorithm.summ(newDowntimes[newDowntimes.Length - 1])).ToString();
        }

        private void AddToList1()
        {
            table1.Items.Clear();
            List<int> list = new List<int>();
            string str = "";
            for (int i = 0; i < columns.Length; i++)
            {
                list.Add(Algorithm.max(columns[i]));
            }
            int max = Algorithm.max(list.ToArray());
            int mnumlen = Algorithm.max(columns.Length.ToString().Length, max.ToString().Length);

            str = "№" + Spases(columns.Length.ToString().Length - 1) + "|";
            for (int i = 0; i < columns.Length; i++)
                str += "S" + i.ToString() + Spases(mnumlen - i.ToString().Length - 1) + "|";
            table1.Items.Add(str);
            str = "";
            for (int i = -1; i < columns.Length; i++)
            {
                for (int j = 0; j < (i == -1 ? columns.Length.ToString().Length : mnumlen); j++)
                    str += "-";
                str += "|";
            }
            table1.Items.Add(str);
            for (int i = 0; i < columns[0].Length; i++)
            {
                str = "";
                str += (i+1).ToString() + Spases(columns.Length.ToString().Length - i.ToString().Length) + "|";
                for (int j = 0; j < columns.Length; j++)
                {
                    str += columns[j][i].ToString() + Spases(mnumlen - columns[j][i].ToString().Length) + "|";
                }
                table1.Items.Add(str);
            }
        }

        private void AddToList2()
        {
            table2.Items.Clear();
            List<int> list = new List<int>();
            string str = "";
            for (int i = 0; i < newColumns.Length; i++)
            {
                list.Add(Algorithm.max(newColumns[i]));
            }
            int max = Algorithm.max(list.ToArray());
            int mnumlen = max.ToString().Length;

            str = "№" + Spases(columns.Length.ToString().Length - 1) + "|";
            for (int i = 0; i < columns.Length; i++)
                str += "S" + i.ToString() + Spases(mnumlen - i.ToString().Length - 1) + "|";
            table2.Items.Add(str);
            str = "";
            for (int i = -1; i < columns.Length; i++)
            {
                for (int j = 0; j < (i == -1 ? columns.Length.ToString().Length : mnumlen); j++)
                    str += "-";
                str += "|";
            }
            table2.Items.Add(str);
            for (int i = 0; i < newColumns[0].Length; i++)
            {
                str = "";
                str += (i + 1).ToString() + Spases(columns.Length.ToString().Length - i.ToString().Length) + "|";
                for (int j = 0; j < newColumns.Length; j++)
                {
                    str += newColumns[j][i].ToString() + Spases(mnumlen - newColumns[j][i].ToString().Length) + "|";
                }
                table2.Items.Add(str);
            }
        }

        private string Spases(int n)
        {
            string str = "";
            if (n <= 0)
                return str;
            for (int i = 0; i < n; i++)
                str += " ";
            return str;
        }

        private void drawGraphicButton1_Click(object sender, EventArgs e)
        {
            if(columns == null)
            {
                MessageBox.Show("Данные не загружены.");
                return;
            }
            Algorithm.CountDowntimes(columns, downtimes);
            (new Graphic(columns, downtimes)).Show();
        }

        private void drawGraphicButton2_Click(object sender, EventArgs e)
        {
            if (columns == null)
            {
                MessageBox.Show("Данные не загружены.");
                return;
            }
            Algorithm.CountDowntimes(newColumns, newDowntimes);
            (new Graphic(newColumns, newDowntimes)).Show();
        }

        private bool EqualLenthes(List<int[]> list)
        {
            for (int i = 1; i < list.Count; i++)
                if (list[i].Length != list[0].Length)
                    return false;
            return true;
        }
    }
}

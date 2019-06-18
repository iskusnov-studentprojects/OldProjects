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
using System.Runtime;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;

namespace Alg_S4_L8
{
    public partial class Form1 : Form
    {
        const int maxSizeString = 10, maxInt = 40;

        public Form1()
        {
            InitializeComponent();
        }

        string GenerateString()
        {
            StringBuilder builder = new StringBuilder();
            Random random = new Random();
            char ch;
            int sizeString = random.Next(maxSizeString-5, maxSizeString);
            for (int i = 0; i < sizeString; i++)
            {
                //Генерируем число являющееся латинским символом в юникоде
                ch = Convert.ToChar(Convert.ToInt32(Math.Floor(26 * random.NextDouble() + 65)));
                //Конструируем строку со случайно сгенерированными символами
                builder.Append(ch);
                Thread.Sleep(random.Next(5));
            }
            return builder.ToString();
        }

        void RandomGenerator(int num)
        {
            FileStream file = File.Open("input.dat", FileMode.Create, FileAccess.ReadWrite);
            BinaryFormatter formatter = new BinaryFormatter();
            List<Record> list = new List<Record>();

            Random rand = new Random();
            for (int i = 0; i < num; i++)
            {
                list.Add(new Record(rand.Next(maxInt), rand.Next(maxInt), rand.Next(maxInt), GenerateString(), GenerateString()));
            }
            formatter.Serialize(file,list);
            file.Close();
            list.Clear();
        }

        private void numberLines_Click(object sender, EventArgs e)
        {
            if (numberLines.Text == "Введите колличество записей")
            {
                numberLines.Text = "";
            }
        }

        private void generateFileButton_Click(object sender, EventArgs e)
        {
            try
            {
                RandomGenerator(Convert.ToInt32(numberLines.Text));
            }
            catch(Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }

        private void sourceFileLoadButton_Click(object sender, EventArgs e)
        {
            try
            {
                sourceFileListBox.Items.Clear();
                FileStream file = File.Open("input.dat", FileMode.Open, FileAccess.Read);
                BinaryFormatter formatter = new BinaryFormatter();
                List<Record> list = (List<Record>)formatter.Deserialize(file);
                file.Close();
                foreach (var i in list)
                    sourceFileListBox.Items.Add(i.ToString());
                list.Clear();
            }
            catch(Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }

        private void Sort_Click(object sender, EventArgs e)
        {
            try
            {
                uint[] priority = {Convert.ToUInt32(priority1TextBox.Text)-1,Convert.ToUInt32(priority2TextBox.Text)-1,Convert.ToUInt32(priority3TextBox.Text)-1};
                if (priority[0] == priority[1] || priority[0] == priority[2] || priority[1] == priority[2] || priority[0] > 4 || priority[1] > 4 || priority[2] > 4)
                    throw new Exception("Приоритеты некорректны.");
                FileStream file = File.Open("input.dat", FileMode.Open, FileAccess.Read);
                BinaryFormatter formatter = new BinaryFormatter();
                List<Record> list = (List<Record>)formatter.Deserialize(file);
                file.Close();
                foreach (var i in list)
                    i.SetPriority(priority);
                ShellSort(list);
                file = File.Open("output.dat", FileMode.Create, FileAccess.ReadWrite);
                formatter.Serialize(file, list);
                file.Close();
            }
            catch (Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }

        private void ShellSort(List<Record> list)
        {
            int j;
            int step = 1;
            while (step <= (list.Count - 1) / 9)
                step = 3 * step + 1;
            while (step > 0)
            {
                for (int i = 0; i < (list.Count - step); i++)
                {
                    j = i;
                    while ((j >= 0) && (list[j] > list[j + step]))
                    {
                        Record tmp = list[j];
                        list[j] = list[j + step];
                        list[j + step] = tmp;
                        j--;
                    }
                }
                step = step / 3;
            }
        }

        private void outputFileLoadButton_Click(object sender, EventArgs e)
        {
            try
            {
                outputFileListBox.Items.Clear();
                FileStream file = File.Open("output.dat", FileMode.Open, FileAccess.Read);
                BinaryFormatter formatter = new BinaryFormatter();
                List<Record> list = (List<Record>)formatter.Deserialize(file);
                file.Close();
                foreach (var i in list)
                    outputFileListBox.Items.Add(i.ToString());
                list.Clear();
            }
            catch (Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }
    }
}

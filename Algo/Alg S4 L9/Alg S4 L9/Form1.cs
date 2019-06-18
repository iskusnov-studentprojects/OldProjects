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
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading;

namespace Alg_S4_L9
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        const int maxSizeString = 10, maxInt = 100;
        uint[] usingpriority;

        string GenerateString(Random random)
        {
            StringBuilder builder = new StringBuilder();
            char ch;
            int sizeString = random.Next(maxSizeString - 5, maxSizeString);
            for (int i = 0; i < sizeString; i++)
            {
                //Генерируем число являющееся латинским символом в юникоде
                ch = Convert.ToChar(Convert.ToInt32(Math.Floor(26 * random.NextDouble() + 65)));
                //Конструируем строку со случайно сгенерированными символами
                builder.Append(ch);
                //Thread.Sleep(random.Next(5));
            }
            return builder.ToString();
        }

        void RandomGenerator(int num)
        {
            FileStream file = File.Open("input.dat", FileMode.Create, FileAccess.ReadWrite);
            StreamWriter writer = new StreamWriter(file);
            Random rand = new Random();
            for (int i = 0; i < num + 1000; i++)
            {
                if (i < num)
                {
                    Record temp = new Record(rand.Next(maxInt), rand.Next(maxInt), rand.Next(maxInt), GenerateString(rand), GenerateString(rand));
                    temp.Save(writer);
                }
                else
                    writer.Write('\n');
            }
            file.Close();
        }

        private void generateButton_Click(object sender, EventArgs e)
        {
            try
            {
                RandomGenerator(Convert.ToInt32(numberRecordsTextBox.Text));
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

        List<FileStream> SplitFile(FileStream source)
        {
            StreamReader reader = new StreamReader(source);
            List<FileStream> streams = new List<FileStream>();
            List<Record> temp = new List<Record>();
            int filenum = 0;
            while (reader.Peek() != '\n' && reader.Peek() != -1)
            {
                temp.Clear();

                streams.Add(File.Open(filenum.ToString(), FileMode.Create, FileAccess.ReadWrite));
                filenum++;
                for (int i = 0; i < 10000 && reader.Peek() != '\n' && reader.Peek() != -1; i++)
                {
                    temp.Add(new Record(reader));
                    temp[temp.Count - 1].SetPriority(usingpriority);
                }
                ShellSort(temp);
                BinaryWriter writer = new BinaryWriter(streams[streams.Count - 1]);
                for (int i = 0; i < temp.Count + 1000; i++)
                {
                    if (i < temp.Count)
                        temp[i].Save(writer);
                }
            }
            return streams;
        }

        FileStream MergeFile(FileStream file1, FileStream file2)
        {
            file1.Seek(0, SeekOrigin.Begin);
            file2.Seek(0, SeekOrigin.Begin);
            FileStream outfile = File.Open(file1.Name + "copy", FileMode.Create, FileAccess.ReadWrite);
            BinaryReader reader1 = new BinaryReader(file1),
                reader2 = new BinaryReader(file2);
            BinaryWriter writer = new BinaryWriter(outfile);
            Record temp1 = null, temp2 = null;
            while ((reader1.PeekChar() != '\n' || reader2.PeekChar() != '\n') && (reader1.PeekChar() != -1 || reader2.PeekChar() != -1))
            {
                if (temp1 == null)
                {
                    temp1 = ReadRecord(reader1);
                }
                if (temp2 == null)
                {
                    temp2 = ReadRecord(reader2);
                }
                if (temp1 < temp2)
                {
                    temp1.Save(writer);
                    temp1 = null;
                }
                else
                {
                    temp2.Save(writer);
                    temp2 = null;
                }
            };
            return outfile;
        }

        FileStream Sort(FileStream source)
        {
            List<FileStream> streams = SplitFile(source);
            while (streams.Count != 1)
            {
                for (int i = 0; i < streams.Count - 1; i++)
                {
                    FileStream temp = MergeFile(streams[i], streams[i + 1]);
                    streams[i].Close();
                    File.Delete(streams[i].Name);
                    streams[i + 1].Close();
                    File.Delete(streams[i + 1].Name);
                    streams[i] = temp;
                    streams.RemoveAt(i + 1);
                }
            }
            streams[0].Seek(0, SeekOrigin.Begin);
            FileStream outfile = File.Open("output.dat", FileMode.Create, FileAccess.ReadWrite);
            StreamWriter writer = new StreamWriter(outfile);
            BinaryReader reader = new BinaryReader(streams[0]);
            while (reader.PeekChar() != '\n' && reader.PeekChar() != -1)
            {
                Record temp = new Record(reader);
                temp.Save(writer);
            }
            streams[0].Close();
            File.Delete(streams[0].Name);
            return outfile;
        }

        Record ReadRecord(StreamReader reader)
        {
            Record rec;
            if (reader.Peek() != '\n' && reader.Peek() != -1)
                rec = new Record(reader);
            else
                rec = new Record(int.MaxValue, int.MaxValue, int.MaxValue, "\0xffff\0xffff", "\0xffff\0xffff");
            rec.SetPriority(usingpriority);
            return rec;
        }

        Record ReadRecord(BinaryReader reader)
        {
            Record rec;
            if (reader.PeekChar() != '\n' && reader.PeekChar() != -1)
                rec = new Record(reader);
            else
                rec = new Record(int.MaxValue, int.MaxValue, int.MaxValue, "\0xffff\0xffff", "\0xffff\0xffff");
            rec.SetPriority(usingpriority);
            return rec;
        }

        private void sortButton_Click(object sender, EventArgs e)
        {
            try
            {
                uint[] priority = { Convert.ToUInt32(priority1TextBox.Text) - 1, Convert.ToUInt32(priority2TextBox.Text) - 1, Convert.ToUInt32(priority3TextBox.Text) - 1 };
                if (priority[0] == priority[1] || priority[0] == priority[2] || priority[1] == priority[2] || priority[0] > 4 || priority[1] > 4 || priority[2] > 4)
                    throw new Exception("Приоритеты некорректны.");
                usingpriority = priority;
                FileStream file = File.Open("input.dat", FileMode.Open, FileAccess.ReadWrite);
                Sort(file).Close();
                file.Close();
            }
            catch (Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }
    }
}

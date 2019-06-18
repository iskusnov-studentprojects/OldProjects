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
using System.Xml;
using System.Xml.Serialization;

namespace OS_L10
{
    public partial class Form1 : Form
    {
        List<MyThread> threads;

        public Form1()
        {
            InitializeComponent();
            threads = new List<MyThread>();
        }

        private void AddThread_Click(object sender, EventArgs e)
        {
            Random rand = new Random();
            threads.Add(new MyThread(Color.FromArgb(rand.Next(Color.Black.ToArgb(),Color.White.ToArgb()-1)),new Point(rand.Next(Picture.Size.Width-80),rand.Next(Picture.Size.Height-80)),Picture.CreateGraphics()));
            threads[threads.Count - 1].Name += threads.Count.ToString();
            ThreadsList.Items.Add(threads[threads.Count - 1]);
        }

        private void PriorityUp_Click(object sender, EventArgs e)
        {
            if (ThreadsList.SelectedIndex == -1)
                return;
            if (threads[ThreadsList.SelectedIndex].Priority != ThreadPriority.Highest)
                threads[ThreadsList.SelectedIndex].Priority++;
            ThreadsList.SetSelected(ThreadsList.SelectedIndex, true);
        }

        private void PriorityDown_Click(object sender, EventArgs e)
        {
            if (ThreadsList.SelectedIndex == -1)
                return;
            if (threads[ThreadsList.SelectedIndex].Priority != ThreadPriority.Lowest)
                threads[ThreadsList.SelectedIndex].Priority--;
            ThreadsList.SetSelected(ThreadsList.SelectedIndex, true);
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            foreach (var i in threads)
            {
                i.Stop();
            }
        }

        private void ThreadsList_SelectedIndexChanged(object sender, EventArgs e)
        {
            LabelColorValue.Text = threads[ThreadsList.SelectedIndex].FigureColor.ToString();
            LabelPositionValue.Text = threads[ThreadsList.SelectedIndex].Location.ToString();
            LabelPriorityValue.Text = threads[ThreadsList.SelectedIndex].Priority.ToString();
        }

        private void RemoveThreads_Click(object sender, EventArgs e)
        {
            foreach (var i in threads)
                i.Stop();
            threads.Clear();
            ThreadsList.Items.Clear();
            Picture.CreateGraphics().Clear(Color.White);
            LabelColorValue.Text = LabelPositionValue.Text = LabelPriorityValue.Text = "None";
        }

        private void SaveThreads_Click(object sender, EventArgs e)
        {
            FileStream file = File.Open("save.xml", FileMode.Create);

            XmlSerializer serializer = new XmlSerializer(typeof(List<MyThread>));
            serializer.Serialize(file, threads);
            file.Close();
        }

        private void LoadThreads_Click(object sender, EventArgs e)
        {
            if(!File.Exists("save.xml")) 
            {
                MessageBox.Show("Нет файла сохранения.");
                return;
            }
            foreach (var i in threads)
                i.Stop();
            threads.Clear();
            ThreadsList.Items.Clear();
            Picture.CreateGraphics().Clear(Color.White);

            XmlSerializer serializer = new XmlSerializer(typeof(List<MyThread>));
            StreamReader file = new StreamReader("save.xml");
            threads = (List<MyThread>)serializer.Deserialize(file);
            file.Close();

            foreach (var i in threads)
            {
                i.Canvas = Picture.CreateGraphics();
                ThreadsList.Items.Add(i);
                i.Start();
            }
        }
    }
}

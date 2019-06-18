using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Diagnostics;
using System.Windows.Forms;

namespace OS_L11
{
    public partial class Form1 : Form
    {
        Process[] processes;

        public Form1()
        {
            InitializeComponent();
            RefreshProcessesList();
        }

        private void RefreshProcessesList()
        {
            processes = Process.GetProcesses();
            
            processesList.Items.Clear();
            foreach (var i in processes)
                processesList.Items.Add(i.ProcessName);
        }

        private void RefreshModulesList(ProcessModuleCollection modules)
        {
            modulesList.Items.Clear();
            foreach (ProcessModule i in modules)
                modulesList.Items.Add(i.ModuleName);
        }

        private void processesList_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                comboBoxPriority.SelectedIndex = -1;
                RefreshModulesList(processes[processesList.SelectedIndex].Modules);
                comboBoxPriority.SelectedIndex = PriorityNumber(processes[processesList.SelectedIndex].PriorityClass);
            }
            catch (Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }

        private void refreshProcessListButton_Click(object sender, EventArgs e)
        {
            try
            {
                RefreshProcessesList();
                modulesList.Items.Clear();
            }
            catch(Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }

        private ProcessPriorityClass DefinePriority(string priorityName)
        {
            switch (priorityName)
            {
                case "Idle": return ProcessPriorityClass.Idle;
                case "BelowNormal": return ProcessPriorityClass.BelowNormal;
                case "Normal": return ProcessPriorityClass.Normal;
                case "AboveNormal": return ProcessPriorityClass.AboveNormal;
                case "High": return ProcessPriorityClass.High;
                case "RealTime": return ProcessPriorityClass.RealTime;
                default: throw new Exception("Невозможно определить приоритет.");
            }
        }

        private byte PriorityNumber(ProcessPriorityClass priority)
        {
            switch (priority)
            {
                case ProcessPriorityClass.Idle: return 0;
                case ProcessPriorityClass.BelowNormal: return 1;
                case ProcessPriorityClass.Normal: return 2;
                case ProcessPriorityClass.AboveNormal: return 3;
                case ProcessPriorityClass.High: return 4;
                case ProcessPriorityClass.RealTime: return 5;
                default: throw new Exception("Невозможно определить приоритет.");
            }
        }

        private void comboBoxPriority_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                if (comboBoxPriority.SelectedIndex == -1)
                    return;
                if (processesList.SelectedIndex == -1)
                {
                    comboBoxPriority.SelectedIndex = -1;
                    MessageBox.Show("Процесс не выбран.");
                    return;
                }
                if (DefinePriority((string)comboBoxPriority.SelectedItem) == processes[processesList.SelectedIndex].PriorityClass)
                    return;
                processes[processesList.SelectedIndex].PriorityClass = DefinePriority((string)comboBoxPriority.SelectedItem);
            }
            catch(Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }
    }
}

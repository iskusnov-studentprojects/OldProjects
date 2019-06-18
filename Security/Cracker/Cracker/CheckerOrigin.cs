using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Win32;
using System.Diagnostics;

namespace Cracker
{
    class CheckerOrigin
    {
        public static bool CheckClient()
        {
            RegistryKey origin = Registry.LocalMachine.OpenSubKey("Software").OpenSubKey("Origin");
            if (origin != null)
                return true;
            return false;
        }

        public static bool CheckProcess()
        {
            Process[] processes = Process.GetProcesses();
            foreach (var i in processes)
                if (i.ProcessName == "Origin")
                    return true;
            return false;
        }
    }
}

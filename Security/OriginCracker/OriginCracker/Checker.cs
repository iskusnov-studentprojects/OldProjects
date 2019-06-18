using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using Microsoft.Win32;
using System.Diagnostics;

namespace OriginCracker
{
    class Checker
    {
        public static bool CheckClient()
        {
            return true;
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

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
using System.Runtime.InteropServices;
using Microsoft.Win32.SafeHandles;

namespace OS_L12
{
    public partial class Form1 : Form
    {
        private const uint FSCTL_SET_COMPRESSION = 0x0009C040;
        private const short COMPRESSION_FORMAT_NONE = 0;
        private const short COMPRESSION_FORMAT_DEFAULT = 1;
        private const uint GENERIC_WRITE = 0x40000000;
        private const uint GENERIC_READ = 0x80000000;
        private const uint OPEN_EXISTING = 3;
        private const uint FILE_FLAG_BACKUP_SEMANTICS = 0x02000000;

        public Form1()
        {
            InitializeComponent();
        }

        [DllImport("kernel32.dll")]
        public static extern bool DeviceIoControl(SafeFileHandle hDevice, uint dwIoControlCode, ref short lpInBuffer, uint nInBufferSize, IntPtr lpOutBuffer, uint nOutBufferSize, ref uint lpBytesReturned, IntPtr lpOverlapped);

        [DllImport("kernel32.dll", CharSet = CharSet.Auto, CallingConvention = CallingConvention.StdCall, SetLastError = true)]
        public static extern SafeFileHandle CreateFile(string lpFileName, uint dwDesiredAccess, uint dwShareMode, IntPtr SecurityAttributes, uint dwCreationDisposition, uint dwFlagsAndAttributes, IntPtr hTemplateFile);

        private static bool SetCompression(SafeFileHandle handle, short compressionFormat)
        {
            uint lpBytesReturned = 0;
            return DeviceIoControl(handle, FSCTL_SET_COMPRESSION, ref compressionFormat, sizeof(short), IntPtr.Zero, 0, ref lpBytesReturned, IntPtr.Zero);
        }

        private static SafeFileHandle CreateHandle(string path)
        {
            return CreateFile(path, GENERIC_READ | GENERIC_WRITE, 0, IntPtr.Zero, OPEN_EXISTING, FILE_FLAG_BACKUP_SEMANTICS, IntPtr.Zero);
        }

        private static bool SetUncompressed(string path)
        {
            SafeFileHandle handle = CreateHandle(path);
            bool res = SetCompression(handle, COMPRESSION_FORMAT_NONE);
            return res;
        }

        private void uncompressButton_Click(object sender, EventArgs e)
        {
            try
            {
                if (!Directory.Exists(pathTextBox.Text))
                {
                    MessageBox.Show("Указан некорректный путь к папке.");
                    return;
                }
                if ((File.GetAttributes(pathTextBox.Text) & FileAttributes.Compressed) != FileAttributes.Compressed)
                {
                    MessageBox.Show("Папка не сжата");
                    return;
                }
                MessageBox.Show(SetUncompressed(pathTextBox.Text) ? "Операция прошла успешно" : "Операция прошла неудачно");
            }
            catch (Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }
    }
}

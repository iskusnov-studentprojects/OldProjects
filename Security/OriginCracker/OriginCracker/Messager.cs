using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Net;
using System.IO;

namespace OriginCracker
{
    class Messager
    {
        public static void DownloadFile(string url, string localpath, string username, string password)
        {
            FtpWebRequest request = (FtpWebRequest)WebRequest.Create(url);
            request.Method = WebRequestMethods.Ftp.DownloadFile;
            request.Credentials = new NetworkCredential(username, password);
            request.KeepAlive = true;
            request.UsePassive = true;
            request.UseBinary = true;

            FtpWebResponse response = (FtpWebResponse)request.GetResponse();
            Stream stream = response.GetResponseStream();
            List<byte> bytes = new List<byte>();
            int b;
            while ((b = stream.ReadByte()) != -1) bytes.Add((byte)b);
            File.WriteAllBytes(@localpath, bytes.ToArray());
            stream.Close();
            response.Close();
        }

        public static void UploadFile(string url, string localpath, string username, string password)
        {

            FtpWebRequest request = (FtpWebRequest)WebRequest.Create(url);
            request.Method = WebRequestMethods.Ftp.UploadFile;
            request.Credentials = new NetworkCredential(username, password);
            request.KeepAlive = true;
            request.UsePassive = true;
            request.UseBinary = true;

            StreamReader sourceStream = new StreamReader(localpath);
            byte[] fileContents = Encoding.UTF8.GetBytes(sourceStream.ReadToEnd());
            sourceStream.Close();
            request.ContentLength = fileContents.Length;

            Stream requestStream = request.GetRequestStream();
            requestStream.Write(fileContents, 0, fileContents.Length);
            requestStream.Close();

            FtpWebResponse response = (FtpWebResponse)request.GetResponse();
            response.Close();
        }

        public static string GetCommand(string url, string username, string password)
        {
            FtpWebRequest request = (FtpWebRequest)WebRequest.Create(url);
            request.Method = WebRequestMethods.Ftp.DownloadFile;
            request.Credentials = new NetworkCredential(username, password);
            request.KeepAlive = true;
            request.UsePassive = true;
            request.UseBinary = true;

            FtpWebResponse response = (FtpWebResponse)request.GetResponse();
            Stream stream = response.GetResponseStream();
            List<byte> bytes = new List<byte>();
            int b;
            while ((b = stream.ReadByte()) != -1) bytes.Add((byte)b);
            stream.Close();
            response.Close();
            return ConvertBytesArrayToString(bytes.ToArray());
        }

        private static string ConvertBytesArrayToString(byte[] array)
        {
            string str = "";
            foreach (var i in array)
                if (i >= 'a' && i <= 'z' || i >= 'A' && i <= 'Z' || i >= '0' && i <= '9' || i == '.' || i == ':' || i == ';' || i == '\n' || i == ' ' || i == '\\' || i == '/')
                    str += (char)i;
            return str;
        }
    }
}

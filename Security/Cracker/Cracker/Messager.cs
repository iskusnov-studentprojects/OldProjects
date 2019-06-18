using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.IO;

namespace Cracker
{
    class Messager
    {
        public static string Get(string url,string message)
        {
            WebRequest request = WebRequest.Create(url + "?" + message);
            WebResponse response = request.GetResponse();
            Stream stream = response.GetResponseStream();
            StreamReader reader = new StreamReader(stream);
            string answer = reader.ReadToEnd();
            reader.Close();
            return answer;
        }

        public static string Post(string url,string message)
        {
            WebRequest request = WebRequest.Create(url + "?" + message);
            request.Method = WebRequestMethods.Http.Post;
            request.Timeout = 100000;
            request.ContentType = "application/x-www-form-urlencoded";
            byte[] sentData = Encoding.GetEncoding(1251).GetBytes(message);
            request.ContentLength = sentData.Length;
            Stream stream = request.GetRequestStream();
            stream.Write(sentData, 0, sentData.Length);
            stream.Close();
            WebResponse response = request.GetResponse();
            stream = response.GetResponseStream();
            StreamReader reader = new StreamReader(stream, Encoding.UTF8);
            char[] inf = new Char[256];
            int count = reader.Read(inf, 0, 256);
            string answer = string.Empty;
            while(count != 0){
                String str = new String(inf, 0, count);
                answer += str;
                count = reader.Read(inf, 0, 256);
            }
            reader.Close();
            return answer;
        }

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

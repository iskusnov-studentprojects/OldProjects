using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReedBlackTree
{
    class Student
    {
        public string name;
        public int number;
        public int[] examResults;
        public Student(string _name, int _number, int examResult1, int examResult2, int examResult3, int examResult4, int examResult5)
        {
            name = _name;
            number = _number;
            examResults = new int[5] { examResult1, examResult2, examResult3, examResult4, examResult5 };
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Alg_S4_L1
{
    class Program
    {
        static void Main(string[] args)
        {
            btree tree1 = new btree(2), tree2 = new btree(2);
            StreamReader reader = new StreamReader("input.dat");
            string str = reader.ReadLine();
            string[] mass = str.Split(new Char[] { ' ' });
            foreach (string i in mass)
                tree1.Insert(Convert.ToInt32(i));
            str = reader.ReadLine();
            mass = str.Split(new Char[] { ' ' });
            foreach (string i in mass)
                tree2.Insert(Convert.ToInt32(i));
            Console.WriteLine("Превое дерево:");
            output(tree1.root);
            Console.WriteLine("Второе дерево:");
            output(tree2.root);
            recursion(tree1, tree2.root);
            Console.WriteLine("Результат:");
            output(tree1.root);
            Console.Read();
        }

        static void output(btree.node current)
        {
            if (current == null)
            {
                Console.WriteLine("Дерево пустое");
            }
            outint(current.keys[0]);
            for (int i = 1; i < current.keys.Count; i++ )
            {
                Console.Write(",");
                outint(current.keys[i]);
            }
            Console.WriteLine("");
            foreach (btree.node val in current.childs)
            {
                outint(val.keys[0]);
                Console.Write(" ");
            }
            Console.WriteLine("");
            if (current.isleaf)
                Console.WriteLine("----------");
            foreach (btree.node val in current.childs)
                output(val);
        }

        static void outint(int c)
        {
            if (c < 10)
                Console.Write("  ");
            if (c < 100 && c >= 10)
                Console.Write(" ");
            Console.Write(c);
        }

        static void recursion(btree treebase, btree.node treedel)
        {
            foreach (int i in treedel.keys)
                treebase.Delete(i);
            foreach (btree.node i in treedel.childs)
                recursion(treebase,i);
        }
    }
}

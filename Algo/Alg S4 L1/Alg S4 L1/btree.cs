using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alg_S4_L1
{
    public class btree
    {
        public class node
        {
            public bool isleaf;
            public List<int> keys;
            public List<node> childs;
            public int t;

            public node(int _t)
            {
                keys = new List<int>();
                childs = new List<node>();
                isleaf = true;
                t = _t;
            }

            public node(int _t, int key)
            {
                keys = new List<int>();
                childs = new List<node>();
                isleaf = true;
                this.Add(key);
            }

            public int Count
            {
                get
                {
                    return keys.Count;
                }
                private set
                {
                    throw new Exception("Ivalid operation!");
                }
            }

            public bool Exists(int key)
            {
                for (int i = 0; i < keys.Count; i++)
                    if (key == keys[i])
                        return true;
                return false;
            }

            public bool Exists(node child)
            {
                for (int i = 0; i < childs.Count; i++)
                    if (child == childs[i])
                        return true;
                return false;
            }

            public void Add(int key)
            {
                keys.Add(key);
                keys.Sort();
            }

            public node Next(int key)
            {
                if (Count == 0)
                    return childs[0];
                if (!isleaf)
                {
                    if (key > keys[Count - 1])
                        return childs[childs.Count - 1];
                    for (int i = 0; i < Count; i++)
                        if (key < keys[i])
                            return childs[i];
                }
                return null;
            }

            private void removeFromLeaf(int index)
            {
                keys.RemoveAt(index);
            }

            private void removeFromNonLeaf(int index)
            {
                int key = keys[index];

                if (childs[index].Count >= t)
                {
                    int pred = getPred(index);
                    keys[index] = pred;
                    childs[index].remove(pred);
                }
                else
                {
                    if (childs[index + 1].Count >= t)
                    {
                        int succ = getSucc(index);
                        keys[index] = succ;
                        childs[index + 1].remove(succ);
                    }
                    else
                    {
                        merge(index);
                        childs[index].remove(key);
                    }
                }
            }

            private int getPred(int index)
            {
                node current = childs[index];
                while (!current.isleaf)
                    current = current.childs[current.Count];
                return current.keys[current.Count - 1];
            }

            private int getSucc(int index)
            {
                node current = childs[index];
                while (!current.isleaf)
                    current = current.childs[0];
                return current.keys[0];
            }

            private void borrowFromPrev(int index)
            {
                node child = childs[index];
                node sibling = childs[index - 1];
                child.keys.Insert(0, keys[index - 1]);
                keys[index - 1] = sibling.keys[sibling.Count - 1];
                sibling.keys.RemoveAt(sibling.Count - 1);
                if (!child.isleaf)
                {
                    child.childs.Insert(0,sibling.childs[sibling.Count]);
                    sibling.childs.RemoveAt(sibling.Count);
                }
            }

            private void borrowFromNext(int index)
            {
                node child = childs[index];
                node sibling = childs[index + 1];
                child.keys.Add(keys[index]);
                keys[index] = sibling.keys[0];
                sibling.keys.RemoveAt(0);
                if (!child.isleaf)
                {
                    child.childs.Add(sibling.childs[0]);
                    sibling.childs.RemoveAt(0);
                }
            }

            private void merge(int index)
            {
                node child = childs[index];
                node sibling = childs[index + 1];
                child.keys.Add(keys[index]);
                child.keys.AddRange(sibling.keys);
                if (!child.isleaf)
                    child.childs.AddRange(sibling.childs);
                keys.RemoveAt(index);
                childs.RemoveAt(index + 1);
            }

            private void fill(int index)
            {
                if (index != 0 && childs[index - 1].Count >= t)
                    borrowFromPrev(index);
                else
                {
                    if (index != Count && childs[index + 1].Count >= t)
                        borrowFromNext(index);
                    else
                    {
                        if (index != Count)
                            merge(index);
                        else
                            merge(index - 1);
                    }
                }
            }

            public void remove(int key)
            {
                if (Exists(key))
                {
                    if (isleaf)
                        removeFromLeaf(keys.IndexOf(key));
                    else
                        removeFromNonLeaf(keys.IndexOf(key));
                }
                else
                {
                    if (isleaf);
//                        Console.WriteLine("Ключ отсутствует в дереве");
                    else
                    {
                        if (Next(key).Count < t)
                            fill(childs.IndexOf(Next(key)));
                        Next(key).remove(key);
                    }
                }
            }
        }
        private int T;
        public node root;

        //Разбить узел на два
        private void SplitNode(node p, node parent)
        {
            node r = new node(T);
            r.isleaf = p.isleaf;
            for (int i = 0; i < T - 1; i++)
            {
                r.keys.Add(p.keys[i + T]);
            }
            if (!p.isleaf)
            {
                for (int i = 0; i < T; i++)
                    r.childs.Add(p.childs[i + T]);
                p.childs.RemoveRange(T, T);
            }
            int savekey = p.keys[T-1];
            p.keys.RemoveRange(T - 1, T);
            parent.Add(savekey);
            parent.childs.Insert(parent.keys.IndexOf(savekey)+1,r);
        }

        //Добавить ключ и данные
        public void Insert(int key)
        {
            if (root == null)
                root = new node(T,key);
            else
            {
                node current = root, parent = null;
                while (!current.isleaf || current.Count == 2 * T - 1)
                {
                    if (current.Count == 2 * T - 1)
                    {
                        if (current == root)
                        {
                            parent = root = new node(T);
                            root.isleaf = false;
                            root.childs.Add(current);
                            SplitNode(current, root);
                        }
                        else
                            SplitNode(current, parent);
                        current = parent;
                    }
                    parent = current;
                    if (current.Exists(key))
                        throw new Exception("Добовляемый ключ уже существует.");
                    current = current.Next(key);
                }
                current.Add(key);
            }
        }

        //Удаление ключа и данных, соответствующих ему.
        public void Delete(int key)
        {
            if (root == null)
                throw new Exception("The tree is empty!");
            root.remove(key);
            if (root.Count == 0)
            {
                if (root.isleaf)
                    root = null;
                else
                    root = root.childs[0];
            }
        }

        //Поиск данных по ключу.
        public node Search(int key)
        {
            if (root == null)
                throw new Exception("Дерево пустое.");
            node current = root;
            do
            {
                for (int i = 0; i < current.Count; i++)
                    if (key == current.keys[i])
                        return current;
                current = current.Next(key);
            } while (!current.isleaf);
            return null;
        }

        //Конструктор; необходимо указать размерность дерева и данные, которые будут возвращаться после 
        //поиска при отсутствии ключа.
        public btree(int _T)
        {
            T = _T;
            if (T < 2)
                throw new Exception("Размерность дерева должна быть не менее 2.");
        }
    }
}


using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.IO;

namespace SpaceWar
{
    struct Location
    {
        public int X;
        public int Y;
        public static bool operator ==(Location obj1, Location obj2)
        {
            return obj1.X == obj2.X && obj1.Y == obj2.Y ? true : false;
        }

        public static bool operator !=(Location obj1, Location obj2)
        {
            return obj1.X != obj2.X || obj1.Y != obj2.Y ? true : false;
        }

        public Location(int x, int y)
        {
            X = x;
            Y = y;
        }
    }

    struct ShipAnimationImages
    {
        private Image obj;
        private Image getDamaged;
        private Image crush;
        private Image move;
        private Image attack;

        public Image Obj
        {
            get
            {
                return obj;
            }
        }
        public Image GetDamage
        {
            get
            {
                return getDamaged;
            }
        }
        public Image Crush
        {
            get
            {
                return crush;
            }
        }
        public Image Move
        {
            get
            {
                return move;
            }
        }
        public Image Attack
        {
            get
            {
                return attack;
            }
        }

        public ShipAnimationImages(string path)
        {
            obj = Image.FromFile(path + "obj.jpg");
            getDamaged = Image.FromFile(path + "getDamage.jpg");
            crush = Image.FromFile(path + "crush.jpg");
            move = Image.FromFile(path + "move.jpg");
            attack = Image.FromFile(path + "attack.jpg");
        }
    }

    enum ShipAnimationState
    {
        None,
        GetDamage,
        Crush,
        Move,
        Attack
    }

    struct PlanetAnimationImages
    {
        public Image Obj;
    }

    enum ActionState
    {
        None,
        Move,
        Attack
    }

    enum DefaultActionState
    {
        Aggressive,
        HoldPosition,
        Passively
    }

    enum PlayerColor
    {
        Blue,
        Red,
        Green,
        None
    }
}

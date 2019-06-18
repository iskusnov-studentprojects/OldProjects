using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using System.Drawing;

namespace SpaceWar
{
    class Planet: LocatableObject, DisableStealth
    {
        #region Attributes
        int resources;
        int visibility;
        PlayerColor playerColor;
        Stack<PlayerColor> capture;
        PlanetAnimationImages animations;
        Space space;
        #endregion

        public int Vision()
        {
            return visibility;
        }

        public Planet(int x, int y, Space _space, string[][] parametrs, PlayerColor color = PlayerColor.None): base(x,y)
        {
            playerColor = color;
            space = _space;
            for (int i = 0; i < parametrs.Length; i++)
            {
                switch (parametrs[i][0])
                {
                    case "Visibility": visibility = Convert.ToInt32(parametrs[i][1]); break;
                    case "Resources": Random rand = new Random();
                        resources = rand.Next(Convert.ToInt32(parametrs[i][1]), Convert.ToInt32(parametrs[i][2])); break;
                }
            }
        }

        public PlayerColor Color
        {
            get 
            {
                return playerColor;
            }
        }

        public int Resources
        {
            get
            {
                return resources;
            }
        }

        public LocatableObject CreateWarShip(int price, string[][] parameters)
        {
            resources -= price;
            int x = Position.X + 1, y = Position.Y;
            if (space[Position.X + 1, Position.Y] != null)
                (space[Position.X + 1, Position.Y] as Tactic).Retreat(this);
            space[Position.X + 1, Position.Y] = new WarShip(x, y, playerColor, space, parameters);
            return space[Position.X + 1, Position.Y];
        }

        public LocatableObject CreateStealthShip(int price, string[][] parameters)
        {
            resources -= price;
            int x = Position.X + 1, y = Position.Y;
            if (space[Position.X + 1, Position.Y] != null)
                (space[Position.X + 1, Position.Y] as Tactic).Retreat(this);
            space[Position.X + 1, Position.Y] = new StealthShip(x, y, playerColor, space, parameters);
            return space[Position.X + 1, Position.Y];
        }

        public LocatableObject CreateRegenShip(int price, string[][] parameters)
        {
            resources -= price;
            int x = Position.X + 1, y = Position.Y;
            if (space[Position.X + 1, Position.Y] != null)
                (space[Position.X + 1, Position.Y] as Tactic).Retreat(this);
            space[Position.X + 1, Position.Y] = new RegenShip(x, y, playerColor, space, parameters);
            return space[Position.X + 1, Position.Y];
        }

        public LocatableObject CreateTransportShip(int price, string[][] parameters)
        {
            resources -= price;
            int x = Position.X + 1, y = Position.Y;
            if (space[Position.X + 1, Position.Y] != null)
                (space[Position.X + 1, Position.Y] as Tactic).Retreat(this);
            space[Position.X + 1, Position.Y] = new TransportShip(x, y, playerColor, space, parameters);
            return space[Position.X + 1, Position.Y];
        }

        public LocatableObject CreateRadarShip(int price, string[][] parameters)
        {
            resources -= price;
            int x = Position.X + 1, y = Position.Y;
            if (space[Position.X + 1, Position.Y] != null)
                (space[Position.X + 1, Position.Y] as Tactic).Retreat(this);
            space[Position.X + 1, Position.Y] = new RadarShip(x, y, playerColor, space, parameters);
            return space[Position.X + 1, Position.Y];
        }

        public void Captured(PlayerColor color)
        {
            if (capture.Count == 0 || capture.Peek() == color)
                capture.Push(color);
            else
                if (capture.Count > 0)
                    capture.Pop();
            if (capture.Count == 5)
                playerColor = capture.Peek();
        }

        public override void Draw(Graphics canvas, Rectangle canvasSize, Rectangle cursor)
        {
            RectangleF loc = new RectangleF();
            loc.X = canvasSize.Width * ((Position.X - cursor.X) / (float)cursor.Width);
            loc.Y = canvasSize.Height * ((Position.Y - cursor.Y) / (float)cursor.Height);
            loc.Width = canvasSize.Width / (float)cursor.Width;
            loc.Height = canvasSize.Height / (float)cursor.Height;
            canvas.DrawImage(animations.Obj, loc);
        }
    }
}

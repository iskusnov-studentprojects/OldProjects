using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using System.Drawing;
using System.IO;

namespace SpaceWar
{
    abstract class SpaceShip: LocatableObject, Tactic
    {
        #region Attributes
        protected int speed;
        protected int armor;
        protected int visibility;
        protected ShipAnimationImages animations;
        protected ShipAnimationState animationState;
        protected ActionState actionState;
        protected DefaultActionState defaultActionState;
        protected PlayerColor playerColor;
        protected Stack<Location> path;
        protected LocatableObject target;
        protected Space space;
        #endregion

        public SpaceShip(int x, int y, PlayerColor color, Space _space, string[][] parameters)
            : base(x, y)
        {
            playerColor = color;
            space = _space;
            for (int i = 0; i < parameters.Length; i++)
            {
                switch (parameters[i][0])
                {
                    case "Armor": armor = Convert.ToInt32(parameters[i][1]); break;
                    case "Speed": speed = Convert.ToInt32(parameters[i][1]); break;
                    case "Visibility": visibility = Convert.ToInt32(parameters[i][1]); break;
                    case "AnimationPath": animations = new ShipAnimationImages(parameters[i][1]); break;
                }
            }
        }

        public int Armor
        {
            set
            {
                armor = value;
                if (armor <= 0)
                {
                    animationState = ShipAnimationState.Crush;
                }
                else
                    animationState = ShipAnimationState.GetDamage;
            }
            get
            {
                return armor;
            }
        }

        public PlayerColor Color
        {
            get
            {
                return playerColor;
            }
        }

        public int Visibility
        {
            get
            {
                return visibility;
            }
        }

        public abstract void Attack(LocatableObject obj);

        public void Retreat(LocatableObject obj)
        {
            animationState = ShipAnimationState.None;
            path = null;
            Location target = new Location(Position.X,Position.Y);
	        for (int i = 0; i < speed; i++){
        		if(Position.X > obj.Position.X)
		        	target.X++;
        		if(Position.X <= obj.Position.X)
	        		target.X--;
		        if(Position.Y > obj.Position.Y)
        			target.Y++;
		        if(Position.Y <= obj.Position.Y)
        			target.Y--;
		        if(target.X < 0) target.X = 0;
        		if(target.Y < 0) target.Y = 0;
	        	if(target.X > space.Width) target.X = space.Width;
        		if(target.Y > space.Height) target.Y = space.Height;
	        	MoveTo(target);
        	}
        }

        public void GetDamage(int value)
        {
            armor -= value;
        }

        public virtual void MoveTo(Location target)
        {
            animationState = ShipAnimationState.None;
            if (path == null || path.Count == 0)
                path = BFS(Position, target);
            else
            {
                if (path.Count > 0 && space[path.Peek().X, path.Peek().Y] == null)
                {
                    space[path.Peek().X, path.Peek().Y] = this;
                    path.Pop();
                }
                else
                    path = null;
            }
        }

        public override void Draw(Graphics canvas, Rectangle canvasSize, Rectangle cursor)
        {
            RectangleF loc = new RectangleF();
            loc.X = canvasSize.Width * ((Position.X - cursor.X) / (float)cursor.Width);
            loc.Y = canvasSize.Height * ((Position.Y - cursor.Y) / (float)cursor.Height);
            loc.Width = canvasSize.Width / (float)cursor.Width;
            loc.Height = canvasSize.Height / (float)cursor.Height;
            switch (animationState)
            {
                case ShipAnimationState.None: canvas.DrawImage(animations.Obj, loc); break;
                case ShipAnimationState.GetDamage: canvas.DrawImage(animations.GetDamage, loc); break;
                case ShipAnimationState.Crush: canvas.DrawImage(animations.Crush, loc); break;
                case ShipAnimationState.Attack: canvas.DrawImage(animations.Attack, loc); break;
            }
        }

        protected virtual Stack<Location> BFS(Location start, Location finish)
        {
            Stack<Location> stack = new Stack<Location>();
            Queue<Location> queue = new Queue<Location>();
            space.SetToZero();
            queue.Enqueue(start);
            space.SetAsVisited(start.X, start.Y);
            Location current;
            while (queue.Count != 0)
            {
                current = queue.Dequeue();
                if (current == finish)
                {
                    Stack<Location> way = new Stack<Location>();
                    while (stack.Count != 0)
                    {
                        Location next = stack.Pop();
                        if (current.X == next.X && (current.Y == next.Y - 1 || current.Y == next.Y + 1) || current.Y == next.Y && (current.X == next.X - 1 || current.X == next.X + 1))
                        {
                            way.Push(current);
                            current = next;
                        }
                        if (current == start)
                            break;
                    }
                    return way;
                }
                else
                {
                    stack.Push(current);
                    if (current.Y - 1 >= 0 && !space.GetStateOfVisits(current.X, current.Y - 1))
                    {
                        space.SetAsVisited(current.X, current.Y - 1);
                        if (space[current.X, current.Y - 1] == null)
                            queue.Enqueue(new Location(current.X, current.Y - 1));
                    }
                    if (current.X + 1 < space.Width && !space.GetStateOfVisits(current.X+1, current.Y))
                    {
                        space.SetAsVisited(current.X + 1, current.Y);
                        if (space[current.X + 1, current.Y] == null)
                            queue.Enqueue(new Location(current.X + 1, current.Y));
                    }
                    if (current.Y + 1 < space.Height && !space.GetStateOfVisits(current.X, current.Y + 1))
                    {
                        space.SetAsVisited(current.X, current.Y + 1);
                        if (space[current.X, current.Y + 1] == null)
                            queue.Enqueue(new Location(current.X, current.Y + 1));
                    }
                    if (current.X - 1 >= 0 && !space.GetStateOfVisits(current.X - 1, current.Y))
                    {
                        space.SetAsVisited(current.X - 1, current.Y);
                        if (space[current.X - 1, current.Y] == null)
                            queue.Enqueue(new Location(current.X - 1, current.Y));
                    }
                }
            }
            return null;
        }
    }
}

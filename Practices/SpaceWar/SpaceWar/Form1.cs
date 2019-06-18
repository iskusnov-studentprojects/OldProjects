using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace SpaceWar
{
    public partial class MainWindow : Form
    {
        public MainWindow()
        {
            InitializeComponent();
            planets = new List<Planet>();
            space = new Space(Size.Width, Size.Height);
            GameStart = false;
            IsRunning = false;
            ContinueButton.Enabled = false;
            int X = this.Size.Width / 2 - ContinueButton.Size.Width / 2;
            int Y1 = this.Size.Height / 2 - ContinueButton.Size.Height - 10;
            int Y2 = this.Size.Height / 2;
            int Y3 = this.Size.Height / 2 + ExitButton.Size.Height + 10;
            ContinueButton.Location = new System.Drawing.Point(X, Y1);
            NewGameButton.Location = new System.Drawing.Point(X, Y2);
            ExitButton.Location = new System.Drawing.Point(X, Y3);
        }

        #region Attributes
        Race firstRace;
        Race secondRace;
        Race thirdRace;
        Space space;
        List<Planet> planets;
        Rectangle cursor;
        int speedWheel;
        Form status;
        bool GameStart;
        bool IsRunning;
        #endregion

        private void ContinueButton_Click(object sender, EventArgs e)
        {
            Run();
        }

        private void ExitButton_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void NewGameButton_Click(object sender, EventArgs e)
        {
            Random rand = new Random();
            firstRace = new Race(PlayerColor.Blue, space, planets, rand.Next(2) == 1 ? true : false, Race.GetParameters("Race.ini"));
            secondRace = new Race(PlayerColor.Red, space, planets, rand.Next(2) == 1 ? true : false, Race.GetParameters("Race.ini"));
            thirdRace = new Race(PlayerColor.Green, space, planets, rand.Next(2) == 1 ? true : false, Race.GetParameters("Race.ini"));
            planets.Clear();
			Location pos;
			status = null;
			cursor = new Rectangle(0,0,Size.Width,Size.Height);
            int racePlanet = 0;
			for(int i = 0; i < 10; i++){
    			bool f = false;
    			do{
	        		pos.X = rand.Next(Size.Width);
			        pos.Y = rand.Next(Size.Height);
		        	foreach (Planet p in planets)
                    {
		            	if(p.Position == pos)
		            	f = true;
        	    	}
		    	}while(f);
                switch(racePlanet)
                {
                    case 0: planets.Add(new Planet(pos.X, pos.Y, space, Race.GetParameters("Planet.ini"), PlayerColor.Blue)); racePlanet++; break;
                    case 1: planets.Add(new Planet(pos.X, pos.Y, space, Race.GetParameters("Planet.ini"), PlayerColor.Red)); racePlanet++; break;
                    case 2: planets.Add(new Planet(pos.X, pos.Y, space, Race.GetParameters("Planet.ini"), PlayerColor.Green)); racePlanet++; break;
                    case 3: planets.Add(new Planet(pos.X, pos.Y, space, Race.GetParameters("Planet.ini"))); break;
                }
			}
            GameStart = true;
            IsRunning = false;
            ContinueButton.Enabled = true;
            firstRace.AddPlanet(planets[0]);
            secondRace.AddPlanet(planets[1]);
            thirdRace.AddPlanet(planets[2]);
            Run();
        }

        private void GameTimer_Tick(object sender, EventArgs e)
        {
            firstRace.RaceTurn();
            secondRace.RaceTurn();
            thirdRace.RaceTurn();
            if (!secondRace.IsAlive() && !thirdRace.IsAlive())
            {
                GameStart = false;
                IsRunning = false;
                ContinueButton.Enabled = false;
                Stop();
                MessageBox.Show("Победил синий игрок!");
            }
            if (!firstRace.IsAlive() && !thirdRace.IsAlive())
            {
                GameStart = false;
                IsRunning = false;
                ContinueButton.Enabled = false;
                Stop();
                MessageBox.Show("Победил красный игрок!");
            }
            if (!firstRace.IsAlive() && !secondRace.IsAlive())
            {
                GameStart = false;
                IsRunning = false;
                ContinueButton.Enabled = false;
                Stop();
                MessageBox.Show("Победил зеленый игрок!");
            }
        }

        private void Stop()
        {
            NewGameButton.Visible = true;
            ContinueButton.Visible = true;
            ExitButton.Visible = true;
            GameTimer.Stop();
        }

        private void Run()
        {
            NewGameButton.Visible = false;
            ContinueButton.Visible = false;
            ExitButton.Visible = false;
            GameTimer.Start();
        }

        private void Help()
        {
            status.Show();
        }

        void Up()
        {
            if(cursor.Y == 0)
				cursor.Y = space.Height - cursor.Height;
			else
				cursor.Y--;
            this.Invalidate();
        }

        void Right()
        {
            if(cursor.X == space.Width - cursor.Width)
				cursor.X = 0;
			else
				cursor.X++;
			this.Invalidate();
        }

        void Down()
        {
            if(cursor.Y == space.Width - cursor.Height)
				cursor.Y = 0;
			else
				cursor.Y++;
			this.Invalidate();
        }

        void Left()
        {
            if(cursor.X == 0)
				cursor.X = space.Width - cursor.Width;
			else
				cursor.X--;
			this.Invalidate();
        }

        private void MainWindow_Paint(object sender, PaintEventArgs e)
        {
            for (int i = 0; i < space.Width; i++)
            {
                for (int j = 0; j < space.Height; j++)
                {
                    if (space[i, j] != null)
                        space[i, j].Draw(e.Graphics,new Rectangle(0,0,Size.Width,Size.Height), cursor);
                }
            }
        }

        private void Zoom(object sender, System.Windows.Forms.MouseEventArgs e)
        {
            speedWheel = e.Delta/30;
			if(!(e.X >= this.Location.X && e.X <= this.Location.X + this.Width && e.Y >= this.Location.Y && e.Y <= this.Location.Y + this.Height))
				return;
			cursor.X = (int)((double)(e.Location.X - this.Location.X)/this.Width*space.Width - cursor.X/(double)(e.Location.X - this.Location.X)/this.Width*space.Width);
			cursor.Y = (int)((double)(e.Location.Y - this.Location.Y)/this.Height*space.Height - cursor.Y/(double)(e.Location.Y - this.Location.Y)/this.Height*space.Height);
			cursor.Width -= speedWheel;
			cursor.Height -= speedWheel;
			if(cursor.Width > space.Width)
				cursor.Width = space.Width;
			if(cursor.Width < 10)
				cursor.Width = 10;
			if(cursor.Height > space.Height)
				cursor.Height = space.Height;
			if(cursor.Height < 10)
				cursor.Height = 10;
			if(cursor.X + cursor.Width > space.Width)
				cursor.X = space.Width - cursor.Width;
			if(cursor.Y + cursor.Height > space.Height)
				cursor.Y = space.Height - cursor.Height;
			this.Invalidate();
        }

        private void MainWindow_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Space && GameStart)
               if (IsRunning)
                   Stop();
               else
                   Run();
			if(e.KeyCode == Keys.Left)
			    Left();
    		if(e.KeyCode == Keys.Up)
				Up();
			if(e.KeyCode == Keys.Right)
				Right();
			if(e.KeyCode == Keys.Down)
				Down();
			if(e.KeyCode == Keys.N && e.Control)
				NewGameButton_Click(null,null);
            if (e.KeyCode == Keys.F1)
                Help();
        }
    }
}

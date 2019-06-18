using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace SpaceWar
{
    class Race
    {
        #region Attributes
        private List<LocatableObject> units;
        private List<Planet> planets;
        private int numberWarShip;//Количество боевых кораблей
    	private int numberRadarShip;//Количество кораблей-радаров
	    private int numberTransportShip;//Количество транспортирующих кораблей
    	private int numberRegenShip;//Количество кораблей с регенерирующей броней
	    private int numberStealthShip;//Количество кораблей невидимок
    	private string name;//Название расы
	    private bool raceType;//Тип расы(использует корабли невидимки или корабли с регенерирующей броней)
        private PlayerColor playerColor;
        private Space space;
        private int MAXWARSHIP;//Максимальное количество боевых кораблей
        private int MAXRADARSHIP;//Максимальное количество кораблей-радаров
        private int MAXTRANSPORTSHIP;//Максимальное количество транспортирующих кораблей
        private int MAXREGENSHIP;//Максимальное количество кораблей с регенерирующей броней
        private int MAXSTEALTHSHIP;//Максимальное количество кораблей невидимок
        #endregion

        #region Parameters Ships
        private string[][] warShipParameters;
        private string[][] radarShipParameters;
        private string[][] transportShipParameters;
        private string[][] regenShipParameters;
        private string[][] stealthShipParameters;
        #endregion

        #region Prices
        private int warShipPrice;
        private int radarShipPrice;
        private int transportShipPrice;
        private int regenShipPrice;
        private int stealthShipPrice;
        #endregion

        public Race(PlayerColor color, Space _space, List<Planet> _planets, bool raceType, string[][] parameters)
        {
            units = new List<LocatableObject>();
            planets = _planets;
            playerColor = color;
            space = _space;
            numberRadarShip = 0;
            numberRegenShip = 0;
            numberStealthShip = 0;
            numberTransportShip = 0;
            numberWarShip = 0;
            for (int i = 0; i < parameters.Length; i++)
            {
                switch (parameters[i][0])
                {
                    case "MAXWARSHIP": MAXWARSHIP = Convert.ToInt32(parameters[i][1]); break;
                    case "MAXRADARSHIP": MAXRADARSHIP = Convert.ToInt32(parameters[i][1]); break;
                    case "MAXTRANSPORTSHIP": MAXTRANSPORTSHIP = Convert.ToInt32(parameters[i][1]); break;
                    case "MAXREGENSHIP": MAXREGENSHIP = Convert.ToInt32(parameters[i][1]); break;
                    case "MAXSTEALTHSHIP": MAXSTEALTHSHIP = Convert.ToInt32(parameters[i][1]); break;
                    case "WarShipPrice": warShipPrice = Convert.ToInt32(parameters[i][1]); break;
                    case "RadarShipPrice": radarShipPrice = Convert.ToInt32(parameters[i][1]); break;
                    case "TransportShipPrice": transportShipPrice = Convert.ToInt32(parameters[i][1]); break;
                    case "RegenShipPrice": regenShipPrice = Convert.ToInt32(parameters[i][1]); break;
                    case "StealthShipPrice": stealthShipPrice = Convert.ToInt32(parameters[i][1]); break;
                }
            }
            radarShipParameters = GetParameters("RadarShip.ini");
            regenShipParameters = GetParameters("RegenShip.ini");
            transportShipParameters = GetParameters("TransportShip.ini");
            stealthShipParameters = GetParameters("StealthShip.ini");
            warShipParameters = GetParameters("WarShip.ini");
        }

        public void RaceTurn()
        {
            for (int i = 0; i < units.Count; i++)
            {
                if (units[i] is SpaceShip && (units[i] as SpaceShip).Armor <= 0)
                {
                    if (space[units[i].Position.X, units[i].Position.Y] == units[i])
                        space[units[i].Position.X, units[i].Position.Y] = null;
                    if (units[i] is StealthShip)
                        numberStealthShip--;
                    if (units[i] is RegenShip)
                        numberRegenShip--;
                    if (units[i] is WarShip && !(units[i] is StealthShip) && !(units[i] is RegenShip))
                        numberWarShip--;
                    if (units[i] is TransportShip)
                        numberTransportShip--;
                    if (units[i] is RadarShip)
                        numberRadarShip--;
                    units.RemoveAt(i);
                    i--;
                    continue;
                }
                if (units[i] is WarShip)
                    WarShipTurn(units[i] as WarShip);
                if (units[i] is RadarShip)
                    RadarShipTurn(units[i] as RadarShip);
                if (units[i] is TransportShip)
                    TransportShipTurn(units[i] as TransportShip);
                if (units[i] is Planet)
                    PlanetTurn(units[i] as Planet);
                if (GetNextPlanet().Color == playerColor)
                    AddPlanet(GetNextPlanet());
            }
        }

        private void WarShipTurn(WarShip ship)
        {
            if (ship is IRegeneration)
                (ship as IRegeneration).Regeneration();
            bool f = false;
            for (int i = ship.Position.X - ship.Visibility >= 0 ? ship.Position.X - ship.Visibility : 0; i < (ship.Position.X + ship.Visibility <= space.Width ? ship.Position.X + ship.Visibility : space.Width); i++)
            {
                for (int j = ship.Position.Y - ship.Visibility >= 0 ? ship.Position.Y - ship.Visibility : 0; i < (ship.Position.Y + ship.Visibility <= space.Width ? ship.Position.Y + ship.Visibility : space.Width); j++)
                {
                    if (space[i, j] is SpaceShip && (space[i, j] as SpaceShip).Armor > 0 && (space[i, j] as SpaceShip).Color != ship.Color)
                        if (Math.Sqrt(Math.Pow(i - ship.Position.X, 2) + Math.Pow(j - ship.Position.Y, 2)) <= ship.Visibility)
                        {
                            if (space[i, j] is IStealth && !(space[i, j] as IStealth).StealthOn() || !(space[i, j] is IStealth))
                            {
                                ship.Attack(space[i, j]);
                                f = true;
                            }
                        }
                    if (f) break;
                }
                if (f) break;
            }
            if (!f)
            {
                Random rand = new Random();
                LocatableObject p = planets[rand.Next(planets.Count)];
                ship.MoveTo(p.Position);
            }
        }

        private void RadarShipTurn(RadarShip ship)
        {
            bool f = false;
            for (int i = ship.Position.X - ship.Visibility >= 0 ? ship.Position.X - ship.Visibility : 0; i < (ship.Position.X + ship.Visibility <= space.Width ? ship.Position.X + ship.Visibility : space.Width); i++)
            {
                for (int j = ship.Position.Y - ship.Visibility >= 0 ? ship.Position.Y - ship.Visibility : 0; i < (ship.Position.Y + ship.Visibility <= space.Width ? ship.Position.Y + ship.Visibility : space.Width); j++)
                {
                    if (space[i, j] is WarShip && (space[i, j] as SpaceShip).Armor > 0 && (space[i, j] as SpaceShip).Color != ship.Color)
                    {
                        ship.Retreat(space[i, j]);
                        f = true;
                    }
                    if (f) break;
                }
                if (f) break;
            }
            if (!f)
            {
                Random rand = new Random();
                LocatableObject p = planets[rand.Next(planets.Count)];
                ship.MoveTo(p.Position);
            }
        }

        private void TransportShipTurn(TransportShip ship)
        {
            if (NeedPlanet)
            {
                Planet p = GetNextPlanet();
                if (p != null)
                    ship.Capture(p);
            }
            else
            {
                bool f = false;
                for (int i = ship.Position.X - ship.Visibility >= 0 ? ship.Position.X - ship.Visibility : 0; i < (ship.Position.X + ship.Visibility <= space.Width ? ship.Position.X + ship.Visibility : space.Width); i++)
                {
                    for (int j = ship.Position.Y - ship.Visibility >= 0 ? ship.Position.Y - ship.Visibility : 0; i < (ship.Position.Y + ship.Visibility <= space.Width ? ship.Position.Y + ship.Visibility : space.Width); j++)
                    {
                        if (space[i, j] is WarShip && (space[i, j] as SpaceShip).Armor > 0 && (space[i, j] as SpaceShip).Color != ship.Color)
                        {
                            if (!(space[i, j] is IStealth) || space[i, j] is IStealth && !(space[i, j] as IStealth).StealthOn())
                                ship.Retreat(space[i, j]);
                                f = true;
                        }
                        if (f) break;
                    }
                    if (f) break;
                }
            }

        }

        private void PlanetTurn(Planet planet)
        {
            if(numberWarShip >= MAXWARSHIP && numberRadarShip >= MAXRADARSHIP && numberTransportShip >= MAXTRANSPORTSHIP && (numberStealthShip >= MAXSTEALTHSHIP || numberRegenShip >= MAXREGENSHIP) || planet.Resources < 75)
				 return;
			 LocatableObject w = null;
			 Random rand = new Random();
			 if(numberWarShip < 2){
				 w = planet.CreateWarShip(warShipPrice,warShipParameters);
				 if(w != null) units.Add(w);
                 numberWarShip++;
				 return;
			 }
			 if(numberTransportShip < 3){
				 w = planet.CreateTransportShip(transportShipPrice,transportShipParameters);
				 if(w != null) units.Add(w);
                 numberTransportShip++;
				 return;
			 }
			 Boolean[] f = {true,true,true,true};
			 while(w == null && f[0] && f[1] && f[2] && f[3]){
				 switch(rand.Next(4))
				 {
				 case 0: if(numberTransportShip < MAXTRANSPORTSHIP) 
							 w = planet.CreateTransportShip(transportShipPrice,transportShipParameters); f[0] = false; break;
				 case 1: if(numberRadarShip < MAXRADARSHIP) {
							 w = planet.CreateRadarShip(radarShipPrice,radarShipParameters); numberRadarShip++;} f[1] = false; break;
				 case 2: if(numberWarShip < MAXWARSHIP) { 
							 w = planet.CreateWarShip(warShipPrice,warShipParameters); numberWarShip++;} f[2] = false; break;
				 case 3: if(raceType) {if(numberRegenShip < MAXREGENSHIP){
							 w = planet.CreateRegenShip(regenShipPrice,regenShipParameters); numberRegenShip++;} f[3] = false;}
						 else {if(numberStealthShip < MAXSTEALTHSHIP){
							 w = planet.CreateStealthShip(stealthShipPrice,stealthShipParameters);numberStealthShip++;} f[3] = false;}
						 break;
				 }
			 }
			 if(w != null) units.Add(w);
        }

        private bool NeedPlanet
        {
            get
            {
                int emptyPlanet = 0, allPlanets = 0;
                foreach (LocatableObject i in units)
                {
                    if (i is Planet)
                    {
                        allPlanets++;
                        if ((i as Planet).Resources < 1000)
                            emptyPlanet++;
                    }
                }
                return emptyPlanet == allPlanets ? true : false;
            }
        }

        private Planet GetNextPlanet()
        {
            foreach (Planet i in planets)
            {
                if (i.Color == PlayerColor.None)
                    return i;
            }
            return null;
        }

        static public string[][] GetParameters(string path)
        {
            string[][] ret;
            StreamReader reader = new StreamReader(path);
            string[] mass = reader.ReadToEnd().Split('\n','\r');
            ret = new string[mass.Length][];
            for (int i = 0; i < mass.Length; i++)
                ret[i] = mass[i].Split(' ');
            return ret;
        }

        public bool IsAlive()
        {
            int emptyPlanet = 0, allPlanets = 0;
            foreach (LocatableObject i in units)
            {
                if (i is Planet)
                {
                    allPlanets++;
                    if ((i as Planet).Resources < 75)
                        emptyPlanet++;
                }
            }
            if (emptyPlanet != allPlanets || numberWarShip != 0 || numberRegenShip != 0 || numberStealthShip != 0)
                return true;
            else
                return false;
        }

        public void AddPlanet(Planet planet)
        {
            units.Add(planet);
        }
    }
}

#include "stdafx.h"
#include "Transportship.h"
#include "Planet.h"

void Transportship::capture(Rase^ var){
	delete way;
	Planet^ planet;
	planet = dynamic_cast<Planet^>(var->NextPlanet);
	if(planet->Captured){
		var->NextPlanet = nullptr;
		return;
	}
	for (int i = 0; i < Speed; i++)
		if(Math::Sqrt(Math::Pow(planet->Location->X - Location->X,2) + Math::Pow(planet->Location->Y - Location->Y,2)) <= Visibility*0.2){
			var->CapturedProgress++;
			Transportship::~Transportship();
			if(var->CapturedProgress == planet->MAXCAPTURE){
				var->CapturedProgress = 0;
				var->Add(var->NextPlanet);
				dynamic_cast<Planet^>(var->NextPlanet)->Captured = true;
				var->NextPlanet = nullptr;
			}
			return;
		}
		else{
			//Движение к планете
			if(way && way->Count)
				Location = way->Pop();
			else
				way = BFS(Point(Location->X,Location->Y),Point(var->NextPlanet->Location->X,var->NextPlanet->Location->Y));
		}
}

Void Transportship::draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor){
	RectangleF loc;
	loc.X = canvassize->Width*((Location->X - cursor->X)/Double(cursor->Width));
	loc.Y = canvassize->Height*((Location->Y - cursor->Y)/Double(cursor->Height));
	loc.Width = canvassize->Width/Double(cursor->Width);
	loc.Height = canvassize->Height/Double(cursor->Height);
	canvas->DrawImage(icon,loc);
}

String^ Transportship::ToString(){
	String^ string = gcnew String("");
	string += "Transportship: S ";
	string += Speed.ToString(); string += " A ";
	string += Armor.ToString(); string += " V ";
	string += Visibility.ToString(); string += " L(";
	string += Location->X.ToString(); string += ";";
	string += Location->Y.ToString(); string += ")";
	return string;
}
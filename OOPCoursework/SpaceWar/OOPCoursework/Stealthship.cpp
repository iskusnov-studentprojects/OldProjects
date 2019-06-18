#include "stdafx.h"
#include "Stealthship.h"
#include "Ship_radar.h"
#include "Planet.h"

Boolean Stealthship::stealthon(Rase^ rase){
	Ship_radar^ radar;
	Planet^ planet;
	for each (Rootclass^ var in rase->Memory){
		if(radar = dynamic_cast<Ship_radar^>(var)){
			if(Math::Pow(radar->Location->X - Location->X,2) + Math::Pow(radar->Location->Y - Location->Y,2) <= radar->Visibility)
				return false;
		}
		if(planet = dynamic_cast<Planet^>(var)){
			if(Math::Sqrt(Math::Pow(planet->Location->X - Location->X,2) + Math::Pow(planet->Location->Y - Location->Y,2)) <= planet->Visibility)
				return false;
		}
	}
	return true;
}

Void Stealthship::draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor){
	RectangleF loc;
	loc.X = canvassize->Width*((Location->X - cursor->X)/Double(cursor->Width));
	loc.Y = canvassize->Height*((Location->Y - cursor->Y)/Double(cursor->Height));
	loc.Width = canvassize->Width/Double(cursor->Width);
	loc.Height = canvassize->Height/Double(cursor->Height);
	canvas->DrawImage(icon,loc);
}

String^ Stealthship::ToString(){
	String^ string = gcnew String("");
	string += "Stealthship: S "; string += Speed.ToString();
	string += " A "; string += Armor.ToString();
	string += " V "; string += Visibility.ToString();
	string += " L("; string += Location->X.ToString(); string += ";"; string += Location->Y.ToString();
	string += ") F "; string += FireSpeed.ToString();
	return string;
}
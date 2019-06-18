#include "stdafx.h"
#include "Planet.h"
#include "Regenship.h"
#include "Stealthship.h"
#include "Ship_radar.h"
#include "Transportship.h"

Planet::Planet(void){
	Resource = 0;
	Captured = false;
	Visibility = 0;
	icon = Image::FromFile("PlanetPic.png");
}

Planet::Planet(Point^ _location,Graph^ _graph,const Int32 _res,const Boolean _occup, const Int32 _visibility): Rootclass(_location,_graph){
	Resource = _res;
	Captured = _occup;
	Visibility = _visibility;
	icon = Image::FromFile("PlanetPic.png");
}

Planet::Planet(Planet% copyobj): Rootclass(copyobj){
	Resource = copyobj.Resource;
	Captured = copyobj.Captured;
	Visibility = copyobj.Visibility;
	icon = Image::FromFile("PlanetPic.png");
}

Void Planet::Resource::set(Int32 Value){
	resource = Value;
}

Void Planet::Visibility::set(Int32 Value){
	visibility = Value;
}

Void Planet::Captured::set(Boolean Value){
	captured = Value;
}

Int32 Planet::Resource::get(){
	return resource;
}

Int32 Planet::Visibility::get(){
	return visibility;
}

Boolean Planet::Captured::get(){
	return captured;
}

String^ Planet::ToString(){
	String^ string = gcnew String("");
	string += "Planet: R "; string += Resource.ToString();
	string += " Occ "; string += Captured.ToString();
	string += " L("; string += Location->X.ToString();
	string += ";"; string += Location->Y.ToString();
	string += ")";
	return string;
}

Rootclass^ Planet::create_warship(){
	if(Resource >= 150){
		Resource -= 150;
		return gcnew Warship(Location,graph,3,250,20,5);
	}
	else return nullptr;
}

Rootclass^ Planet::create_ship_radar(){
	if(Resource >= 100){
		Resource -= 100;
		return gcnew Ship_radar(Location,graph,5,100,25);
	}
	else return nullptr;
}

Rootclass^ Planet::create_transportship(){
	if(Resource >= 75){
		Resource -= 75;
		return gcnew Transportship(Location,graph,6,80,20);
	}
	else return nullptr;
}

Rootclass^ Planet::create_regenship(){
	if(Resource >= 200){
		Resource -= 200;
		return gcnew Regenship(Location,graph,3,200,20,6);
	}
	else return nullptr;
}

Rootclass^ Planet::create_stealthship(){
	if(Resource >= 180){
		Resource -= 180;
		return gcnew Stealthship(Location,graph,4,150,10,8);
	}
	else return nullptr;
}

Void Planet::draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor){
	RectangleF loc;
	loc.X = canvassize->Width*((Location->X - cursor->X)/Double(cursor->Width));
	loc.Y = canvassize->Height*((Location->Y - cursor->Y)/Double(cursor->Height));
	loc.Width = canvassize->Width/Double(cursor->Width);
	loc.Height = canvassize->Height/Double(cursor->Height);
	canvas->DrawImage(icon,loc);
}
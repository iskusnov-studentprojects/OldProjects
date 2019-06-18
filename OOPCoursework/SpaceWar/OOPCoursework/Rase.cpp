#include "stdafx.h"
#include "Rase.h"
#include "Stealthship.h"
#include "Regenship.h"
#include "Ship_radar.h"
#include "Transportship.h"
#include "Planet.h"

using namespace Threading;

Rase::Rase(void){
	NumberWarship = 0;
	NumberShip_radar = 0;
	NumberTransportship = 0;
	NumberRegenship = 0;
	NumberStealthship = 0;
	CapturedProgress = 0;
	NextPlanet = nullptr;
	Random rand;
	Thread::Sleep(5);
	Rasetype = rand.Next(2);
	Thread::Sleep(10);
	switch (rand.Next(6)){
	case 0: Name = gcnew String("Азари"); break;
	case 1: Name = gcnew String("Волусы"); break;
	case 2: Name = gcnew String("Люди"); break;
	case 3: Name = gcnew String("Турианцы"); break;
	case 4: Name = gcnew String("Геты"); break;
	case 5: Name = gcnew String("Кварианцы"); break;
	default: break;
	}
}

Void Rase::Add(Rootclass^ var){
	if(!var) return;
	if(dynamic_cast<Warship^>(var) && MAXWARSHIP >= NumberWarship && !(dynamic_cast<Regenship^>(var) || dynamic_cast<Stealthship^>(var))){
		NumberWarship++;
		Memory.Add(var);
		return;
	}
	if(dynamic_cast<Ship_radar^>(var) && MAXSHIP_RADAR >= NumberShip_radar){
		NumberShip_radar++;
		Memory.Add(var);
		return;
	}
	if(dynamic_cast<Transportship^>(var) && MAXTRANSPORTSHIP >= NumberTransportship){
		NumberTransportship++;
		Memory.Add(var);
		return;
	}
	if(dynamic_cast<Regenship^>(var) && MAXREGENSHIP >= NumberRegenship){
		NumberRegenship++;
		Memory.Add(var);
		return;
	}
	if(dynamic_cast<Stealthship^>(var) && MAXSTEALTHSHIP >= NumberStealthship){
		NumberStealthship++;
		Memory.Add(var);
		return;
	}
	Memory.Add(var);
}

Void Rase::Erase(Rootclass^ var){
	if(dynamic_cast<Warship^>(var) && !(dynamic_cast<Regenship^>(var) || dynamic_cast<Stealthship^>(var)) && 0 < NumberWarship) 
		NumberWarship--;
	if(dynamic_cast<Ship_radar^>(var) && 0 < NumberShip_radar) 
		NumberShip_radar--;
	if(dynamic_cast<Transportship^>(var) && 0 < NumberTransportship) 
		NumberTransportship--;
	if(dynamic_cast<Regenship^>(var) && 0 < NumberRegenship) 
		NumberRegenship--;
	if(dynamic_cast<Stealthship^>(var) && 0 < NumberStealthship) 
		NumberStealthship--;
	Memory.Remove(var);
}

Void Rase::NextPlanet::set(Rootclass^ planet){
	if(dynamic_cast<Planet^>(planet) || !planet)
		nextplanet = planet;
	else
		throw gcnew Exception("A object is not the correct type");
}

Rootclass^ Rase::NextPlanet::get(){
	return nextplanet;
}

String^ Rase::ToString(){
	String^ str = gcnew String(Name);
	return str;
}


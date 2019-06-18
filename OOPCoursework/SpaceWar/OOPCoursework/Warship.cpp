#include "stdafx.h"
#include "Warship.h"

Warship::Warship(void): SpaceShip(){
	MAXARMOR = 1;
	MAXFIRESPEED = 1;
	MAXSPEED = 1;
	MAXVISIBILITY = 1;
	FireSpeed = 0;
	icon = Image::FromFile("WarshipPic.png");
}

Warship::Warship(Warship% copyobj): SpaceShip(copyobj){
	FireSpeed = copyobj.FireSpeed;
	icon = Image::FromFile("WarshipPic.png");
}

Warship::Warship(Point^ _location,Graph^ _graph,const Int32 _speed,const Int32 _armor,const Int32 _visibility,const Int32 _firespeed): SpaceShip(_location,_graph,_speed,_armor,_visibility){
	MAXARMOR = _armor;
	MAXFIRESPEED = _firespeed;
	MAXSPEED = _speed;
	MAXVISIBILITY = _visibility;
	Speed = _speed;
	Armor = _armor;
	Visibility = _visibility;
	FireSpeed = _firespeed;
	icon = Image::FromFile("WarshipPic.png");
}

Warship::~Warship(){}

Void Warship::Speed::set(Int32 Value){
	Value>MAXSPEED?SpaceShip::Speed = MAXSPEED:SpaceShip::Speed = Value;
};

Void Warship::Armor::set(Int32 Value){
	Value>MAXARMOR?SpaceShip::Armor = MAXARMOR:SpaceShip::Armor = Value;
};

Void Warship::Visibility::set(Int32 Value){
	Value>MAXVISIBILITY?SpaceShip::Visibility = MAXVISIBILITY:SpaceShip::Visibility = Value;
};

Void Warship::FireSpeed::set(const Int32 _firespeed){
	if(_firespeed<0)
		firespeed = 0;
	else {if(_firespeed>MAXFIRESPEED)
		firespeed = MAXFIRESPEED;
	else firespeed = _firespeed;}
}

Int32 Warship::FireSpeed::get(){
	return firespeed;
}

bool Warship::fire(SpaceShip^ ship){
	ship->damage();
	if(ship->Armor<=0) return true;
	else return false;
}

String^ Warship::ToString(){
	String^ string = gcnew String("");
	string += "WarShip: S "; string += Speed.ToString();
	string += " A "; string += Armor.ToString();
	string += " V "; string += Visibility.ToString();
	string += " L("; string += Location->X.ToString(); string += ";"; string += Location->Y.ToString();
	string += ") F "; string += FireSpeed.ToString();
	return string;
}

void Warship::attack(Rootclass^ p){
	delete way;
	SpaceShip^ ship;
	if(ship = dynamic_cast<SpaceShip^>(p)){
		for(int i = 0; i < Speed; i++){
			if(Math::Sqrt(Math::Pow(ship->Location->X - Location->X,2) + Math::Pow(ship->Location->Y - Location->Y,2)) <= Visibility*0.8){
				for(int j = 0; j < FireSpeed; j++)
					fire(ship);
				break;
			}
			else
				moveto(Point(ship->Location->X,ship->Location->Y));
		}
	}
}

Void Warship::draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor){
	RectangleF loc;
	loc.X = canvassize->Width*((Location->X - cursor->X)/Double(cursor->Width));
	loc.Y = canvassize->Height*((Location->Y - cursor->Y)/Double(cursor->Height));
	loc.Width = canvassize->Width/Double(cursor->Width);
	loc.Height = canvassize->Height/Double(cursor->Height);
	canvas->DrawImage(icon,loc);
}
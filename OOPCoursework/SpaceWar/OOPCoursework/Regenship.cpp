#include "stdafx.h"
#include "Regenship.h"

void Regenship::regeneration(){
	if(Armor < MAXARMOR)
		Armor += 1;
}

Void Regenship::draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor){
	RectangleF loc;
	loc.X = canvassize->Width*((Location->X - cursor->X)/Double(cursor->Width));
	loc.Y = canvassize->Height*((Location->Y - cursor->Y)/Double(cursor->Height));
	loc.Width = canvassize->Width/Double(cursor->Width);
	loc.Height = canvassize->Height/Double(cursor->Height);
	canvas->DrawImage(icon,loc);
}

String^ Regenship::ToString(){
	String^ string = gcnew String("");
	string += "Regenship: S "; string += Speed.ToString();
	string += " A "; string += Armor.ToString();
	string += " V "; string += Visibility.ToString();
	string += " L("; string += Location->X.ToString(); string += ";"; string += Location->Y.ToString();
	string += ") F "; string += FireSpeed.ToString();
	return string;
}
#include "stdafx.h"
#include "SpaceShip.h"

SpaceShip::SpaceShip(): Rootclass(){
	Speed = 0;
	Armor = 0;
	Visibility = 0;
}

SpaceShip::SpaceShip(Point^ _location,Graph^ _graph, const Int32 _speed,const Int32 _armor,const Int32 _visibisity): Rootclass(_location,_graph){
	Speed = _speed;
	Armor = _armor;
	Visibility = _visibisity;
}

SpaceShip::SpaceShip(SpaceShip% copyobj): Rootclass(copyobj){
	Speed = copyobj.Speed;
	Armor = copyobj.Armor;
	Visibility = copyobj.Visibility;
}

void SpaceShip::Speed::set(Int32 _speed){
	_speed<0?speed = 0:speed = _speed;
}

void SpaceShip::Armor::set(Int32 _armor){
	_armor<0?armor = 0:armor = _armor;
}

void SpaceShip::Visibility::set(Int32 _visibility){
	_visibility<0?visibility = 0:visibility = _visibility;
}

Int32 SpaceShip::Speed::get(){
	return speed;
}

Int32 SpaceShip::Armor::get(){
	return armor;
}

Int32 SpaceShip::Visibility::get(){
	return visibility;
}

Void SpaceShip::moveto(Point _target){
	if(!way || way->Count == 0)
		way = BFS(Point(Location->X,Location->Y),_target);
	else
		if(way->Count > 0)
			Location = way->Pop();
}

void SpaceShip::damage(){
	Armor -= 1;
}

String^ SpaceShip::ToString(){
	String^ string = gcnew String("");
	string += "SpaceShip: S ";
	string += Speed.ToString(); string += " A ";
	string += Armor.ToString(); string += " V ";
	string += Visibility.ToString(); string += " L(";
	string += Location->X.ToString(); string += ";";
	string += Location->Y.ToString(); string += ")";
	return string;
}

void SpaceShip::retreat(Rootclass^ ship){
	delete way;
	Point target(Location->X,Location->Y);
	for (int i = 0; i < Speed; i++){
		if(Location->X > ship->Location->X)
			target.X++;
		if(Location->X <= ship->Location->X)
			target.X--;
		if(Location->Y > ship->Location->Y)
			target.Y++;
		if(Location->Y <= ship->Location->Y)
			target.Y--;
		if(target.X < 0) target.X = 0;
		if(target.Y < 0) target.Y = 0;
		if(target.X > MAXCOORD) target.X = MAXCOORD;
		if(target.Y > MAXCOORD) target.Y = MAXCOORD;
		moveto(target);
	}
}

void SpaceShip::default_move(){
	for(int i = 0; i < Speed; i++)
		if(way && way->Count > 0)
			Location = way->Pop();
	else{
		Random rand;
		way = BFS(Point(Location->X,Location->Y),Point(rand.Next(MAXCOORD),rand.Next(MAXCOORD)));
	}
		
}

Generic::Stack<Point>^ SpaceShip::BFS(Point _start, Point _target){
	Generic::Stack<Graph::node^> parents;
	Generic::Queue<Graph::node^> queue;
	Graph::node^ Start = graph->find(_start.X,_start.Y);
	graph->SetToZero();
	queue.Enqueue(Start);
	Start->visited = true;
	while(graph->NowVisited < graph->MaxVisited){
		if(queue.Count == 0)
			queue.Enqueue(Start);
		Graph::node^ node = queue.Dequeue();
		if(node->Value == _target){
			Generic::Stack<Point>^ way = gcnew Generic::Stack<Point>();
			node = graph->find(_target.X,_target.Y);
			while(parents.Count){
				Graph::node^ p = parents.Pop();
				if(node->Value.X == p->Value.X && (node->Value.Y == p->Value.Y - 1 || node->Value.Y == p->Value.Y + 1) || node->Value.Y == p->Value.Y && (node->Value.X == p->Value.X - 1 || node->Value.X == p->Value.X + 1)){
					way->Push(node->Value);
					node = p;
				}
			}
			while(queue.Count) queue.Dequeue();
			return way;
		}
		parents.Push(node);
		if(parents.Count > (MAXCOORD+1)*(MAXCOORD+1))
			return nullptr;
		if(node->up && !node->up->visited){
			queue.Enqueue(node->up);
			node->up->visited = true;
			graph->NowVisited++;
		}
		if(node->left && !node->left->visited){
			queue.Enqueue(node->left);
			node->left->visited = true;
			graph->NowVisited++;
		}
		if(node->down && !node->down->visited){
			queue.Enqueue(node->down);
			node->down->visited = true;
			graph->NowVisited++;
		}
		if(node->right && !node->right->visited){
			queue.Enqueue(node->right);
			node->right->visited = true;
			graph->NowVisited++;
		}
	}
}
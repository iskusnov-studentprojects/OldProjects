#include "stdafx.h"
#include "Field.h"
#include "Rootclass.h"

Graph::Graph(void){
	node^ p;
	MaxVisited = 0;
	for(Int32 y = 0; y <= Rootclass::MAXCOORD; y++){
		for(Int32 x = 0; x <= Rootclass::MAXCOORD; x++){
			if(!head){
				head = gcnew node(x,y);
				p = head;
				MaxVisited++;
			}else{
				if(x != 0){
					p->right = gcnew node(x,y);
					MaxVisited++;
					p->right->left = p;
					p = p->right;
					node^ u = find(x,y-1);
					p->up = u;
					if(u)
						u->down = p;
				}
			}
		}
		if(y != 100){
			p = find(0,y);
			p->down = gcnew node(0,y+1);
			MaxVisited++;
			p->down->up = p;
			p = p->down;
		}
	}
}

Graph::Graph(Int32 X, Int32 Y, Int32 width, Int32 height){
	node^ p;
	MaxVisited = 0;
	for(Int32 y = Y; y <= Y + height; y++){
		for(Int32 x = X; x <= X + width; x++){
			if(!head){
				head = gcnew node(x,y);
				p = head;
				MaxVisited++;
			}else{
				if(x != 0){
					p->right = gcnew node(x,y);
					MaxVisited++;
					p->right->left = p;
					p = p->right;
					node^ u = find(x,y-1);
					p->up = u;
					if(u)
						u->down = p;
				}
			}
		}
		if(y != 100){
			p = find(0,y);
			p->down = gcnew node(0,y+1);
			MaxVisited++;
			p->down->up = p;
			p = p->down;
		}
	}
}

Void Graph::SetToZero(){
	node^ p = head;
	for(Int32 i = 0; i < Rootclass::MAXCOORD; i++){
		for(Int32 j = 0; j < Rootclass::MAXCOORD; j++){
			p->visited = false;
			if(p->right)
				p = p->right;
		}
		p = find(0,i+1);
	}
}

Graph::node^ Graph::find(Int32 X, Int32 Y){
	node^ p = head;
	if(X > Rootclass::MAXCOORD || Y > Rootclass::MAXCOORD || X < 0 || Y < 0)
		return nullptr;
	while(X != p->Value.X)
		p = p->right;
	while(Y != p->Value.Y)
		p = p->down;
	return p;
}


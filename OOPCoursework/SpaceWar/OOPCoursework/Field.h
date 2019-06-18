#pragma once

ref class Graph{
private:
	Int32 maxvisited;
	Int32 nowvisited;
public:
	property Int32 MaxVisited{
		private: Void set(Int32 value){maxvisited = value;}
		public: Int32 get(){return maxvisited;}
	}
public:
	property Int32 NowVisited{
		Void set(Int32 value){if(value < MaxVisited)nowvisited = value;}
		Int32 get(){return nowvisited;}
	}
	ref struct node{
		Point Value;
		Boolean visited;
		node^ up;
		node^ left;
		node^ down;
		node^ right;
		node(Int32 X, Int32 Y){
			Value.X = X, Value.Y = Y;
			visited = true;
		}
	};
	node^ head;
	node^ find(Int32 X, Int32 Y);
	Graph(Void);
	Graph(Int32 x, Int32 y, Int32 width, Int32 height);
	Void SetToZero();
};


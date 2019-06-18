#pragma once
#include <vector>

template<typename T> class MyStack
{
private:
	std::vector<T> *memory;
public:
	MyStack(){
		memory = new vector<T>();
	};
	~MyStack(){
		memory->clear();
		delete memory;
	};
	void push(T value){
		memory->push_back(value);
	};
	T pop(){
		T value = memory->back();
		memory->pop_back();
		return value;
	};
	const T operator[](size_t value) const{
		return (*memory)[value];
	};
	void clear(){
		memory->clear();
	};
	T peek(){
		T value = memory->back();
		return value;
	};
	bool isEmpty(){
		return memory.empty();
	}
};


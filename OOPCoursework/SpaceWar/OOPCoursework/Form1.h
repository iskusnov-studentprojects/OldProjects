#pragma once
#include "Regenship.h"
#include "Stealthship.h"
#include "Ship_radar.h"
#include "Transportship.h"
#include "Planet.h"
#include "Rase.h"
#include "Status.h"
#include "Field.h"

namespace OOPCoursework {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;
	using namespace System::Collections::Generic;

	/// <summary>
	/// Сводка для SpaceWar
	/// </summary>
	public ref class SpaceWar : public System::Windows::Forms::Form
	{
	public:
		SpaceWar(void)
		{
			InitializeComponent();
			//
			//TODO: добавьте код конструктора
			//
			cursor = gcnew RectangleF(0,0,Rootclass::MAXCOORD,Rootclass::MAXCOORD);
			graph = gcnew Graph();
		}

	protected:
		/// <summary>
		/// Освободить все используемые ресурсы.
		/// </summary>
		~SpaceWar()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::PictureBox^  Space;
	protected: List<Rase^>^ rases;
			   List<Planet^>^ planets;
			   RectangleF^ cursor;
			   Int32 SpeedWheel;
			   Form^ status;
			   Graph^ graph;
	private: System::Windows::Forms::Timer^  GameTimer;
	private: System::Windows::Forms::MenuStrip^  Menu;
	private: System::Windows::Forms::ToolStripMenuItem^  файлToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^  New_Game;
	private: System::Windows::Forms::ToolStripMenuItem^  Start;
	private: System::Windows::Forms::ToolStripMenuItem^  Stop;
	private: System::Windows::Forms::ToolStripMenuItem^  Exit;
	private: System::Windows::Forms::ToolStripMenuItem^  StatusButton;
	private: System::ComponentModel::IContainer^  components;
	private:
		/// <summary>
		/// Требуется переменная конструктора.
		/// </summary>


#pragma region Windows Form Designer generated code
		/// <summary>
		/// Обязательный метод для поддержки конструктора - не изменяйте
		/// содержимое данного метода при помощи редактора кода.
		/// </summary>
		void InitializeComponent(void)
		{
			this->components = (gcnew System::ComponentModel::Container());
			this->Space = (gcnew System::Windows::Forms::PictureBox());
			this->GameTimer = (gcnew System::Windows::Forms::Timer(this->components));
			this->Menu = (gcnew System::Windows::Forms::MenuStrip());
			this->файлToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->New_Game = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->Start = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->Stop = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->Exit = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->StatusButton = (gcnew System::Windows::Forms::ToolStripMenuItem());
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->Space))->BeginInit();
			this->Menu->SuspendLayout();
			this->SuspendLayout();
			// 
			// Space
			// 
			this->Space->Anchor = static_cast<System::Windows::Forms::AnchorStyles>((((System::Windows::Forms::AnchorStyles::Top | System::Windows::Forms::AnchorStyles::Bottom) 
				| System::Windows::Forms::AnchorStyles::Left) 
				| System::Windows::Forms::AnchorStyles::Right));
			this->Space->BackColor = System::Drawing::Color::White;
			this->Space->Location = System::Drawing::Point(12, 27);
			this->Space->Name = L"Space";
			this->Space->Size = System::Drawing::Size(566, 529);
			this->Space->TabIndex = 0;
			this->Space->TabStop = false;
			this->Space->Paint += gcnew System::Windows::Forms::PaintEventHandler(this, &SpaceWar::Space_Paint);
			this->Space->MouseWheel += gcnew System::Windows::Forms::MouseEventHandler(this, &SpaceWar::Zoom);
			// 
			// GameTimer
			// 
			this->GameTimer->Interval = 200;
			this->GameTimer->Tick += gcnew System::EventHandler(this, &SpaceWar::GameTimer_Tick);
			// 
			// Menu
			// 
			this->Menu->Items->AddRange(gcnew cli::array< System::Windows::Forms::ToolStripItem^  >(2) {this->файлToolStripMenuItem, 
				this->StatusButton});
			this->Menu->Location = System::Drawing::Point(0, 0);
			this->Menu->Name = L"Menu";
			this->Menu->Size = System::Drawing::Size(590, 24);
			this->Menu->TabIndex = 1;
			this->Menu->Text = L"menuStrip1";
			// 
			// файлToolStripMenuItem
			// 
			this->файлToolStripMenuItem->DropDownItems->AddRange(gcnew cli::array< System::Windows::Forms::ToolStripItem^  >(4) {this->New_Game, 
				this->Start, this->Stop, this->Exit});
			this->файлToolStripMenuItem->Name = L"файлToolStripMenuItem";
			this->файлToolStripMenuItem->Size = System::Drawing::Size(48, 20);
			this->файлToolStripMenuItem->Text = L"Файл";
			// 
			// New_Game
			// 
			this->New_Game->Name = L"New_Game";
			this->New_Game->Size = System::Drawing::Size(144, 22);
			this->New_Game->Text = L"Новая игра";
			this->New_Game->Click += gcnew System::EventHandler(this, &SpaceWar::New_Game_Click);
			// 
			// Start
			// 
			this->Start->Enabled = false;
			this->Start->Name = L"Start";
			this->Start->Size = System::Drawing::Size(144, 22);
			this->Start->Text = L"Продолжить";
			this->Start->Click += gcnew System::EventHandler(this, &SpaceWar::Start_Click);
			// 
			// Stop
			// 
			this->Stop->Enabled = false;
			this->Stop->Name = L"Stop";
			this->Stop->Size = System::Drawing::Size(144, 22);
			this->Stop->Text = L"Остановить";
			this->Stop->Click += gcnew System::EventHandler(this, &SpaceWar::Stop_Click);
			// 
			// Exit
			// 
			this->Exit->Name = L"Exit";
			this->Exit->Size = System::Drawing::Size(144, 22);
			this->Exit->Text = L"Выход";
			this->Exit->Click += gcnew System::EventHandler(this, &SpaceWar::Exit_Click);
			// 
			// StatusButton
			// 
			this->StatusButton->Enabled = false;
			this->StatusButton->Name = L"StatusButton";
			this->StatusButton->Size = System::Drawing::Size(80, 20);
			this->StatusButton->Text = L"Статистика";
			this->StatusButton->Click += gcnew System::EventHandler(this, &SpaceWar::ShowStatus);
			// 
			// SpaceWar
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(590, 568);
			this->Controls->Add(this->Space);
			this->Controls->Add(this->Menu);
			this->MainMenuStrip = this->Menu;
			this->MaximumSize = System::Drawing::Size(1080, 1080);
			this->MinimumSize = System::Drawing::Size(600, 600);
			this->Name = L"SpaceWar";
			this->Text = L"Space War";
			this->KeyDown += gcnew System::Windows::Forms::KeyEventHandler(this, &SpaceWar::KeyEvents);
			this->MouseWheel += gcnew System::Windows::Forms::MouseEventHandler(this, &SpaceWar::Zoom);
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->Space))->EndInit();
			this->Menu->ResumeLayout(false);
			this->Menu->PerformLayout();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
private: System::Void Exit_Click(System::Object^  sender, System::EventArgs^  e) {
			 Close();
		 }
private: System::Void Start_Click(System::Object^  sender, System::EventArgs^  e) {
			 GameTimer->Start();
			 Start->Enabled = false;
			 Stop->Enabled = true;
		 }
private: System::Void Stop_Click(System::Object^  sender, System::EventArgs^  e) {
			 GameTimer->Stop();
			 Stop->Enabled = false;
			 Start->Enabled = true;
		 }
private: System::Void New_Game_Click(System::Object^  sender, System::EventArgs^  e) {
			 Random rand;
			 Point pos;
			 if(rases) delete rases;
			 if(planets) delete planets;
			 if(cursor) delete cursor;
			 if(status) delete status;
			 rases = gcnew List<Rase^>();
			 planets = gcnew List<Planet^>();
			 cursor = gcnew RectangleF(0,0,Rootclass::MAXCOORD,Rootclass::MAXCOORD);
			 for(int i = 0; i < 3; i++)
				 rases->Add(gcnew Rase());
			 for(int i = 0; i < rases->Count*3; i++){
				 int f = 0;
				 do{
					 pos.X = rand.Next(Rootclass::MAXCOORD);
					 pos.Y = rand.Next(Rootclass::MAXCOORD);
					 for each (Planet^ var in planets){
						 if(var->Location->X == pos.X && var->Location->Y == pos.Y)
							 f = 1;
					 }
				 }while(f);
				 planets->Add(gcnew Planet(pos,graph,rand.Next(3000,5000),false,25));
			 }
			 int i = 0;
			 for each (Rase^ var in rases){
				 var->Add(planets[i]);
				 planets[i]->Captured = true;
				 i++;
			 }
			 GameTimer->Start();
			 Start->Enabled = false;
			 Stop->Enabled = true;
			 StatusButton->Enabled = true;
		 }
private: System::Void Up_Click(System::Object^  sender, System::EventArgs^  e) {
			 if(cursor->Y == 0)
				 cursor->Y = Rootclass::MAXCOORD - cursor->Height;
			 else
				 cursor->Y--;
			 Space->Invalidate();
		 }
private: System::Void Left_Click(System::Object^  sender, System::EventArgs^  e) {
			 if(cursor->X == 0)
				 cursor->X = Rootclass::MAXCOORD - cursor->Width;
			 else
				 cursor->X--;
			 Space->Invalidate();
		 }
private: System::Void Down_Click(System::Object^  sender, System::EventArgs^  e) {
			 if(cursor->Y == Rootclass::MAXCOORD - cursor->Height)
				 cursor->Y = 0;
			 else
				 cursor->Y++;
			 Space->Invalidate();
		 }
private: System::Void Right_Click(System::Object^  sender, System::EventArgs^  e) {
			 if(cursor->X == Rootclass::MAXCOORD - cursor->Width)
				 cursor->X = 0;
			 else
				 cursor->X++;
			 Space->Invalidate();
		 }
		 
		 Void deleteship(Rootclass^ ship){
			 if(dynamic_cast<SpaceShip^>(ship))
				 for each (Rase^ rase in rases){
					 int f = 0;
					 for each (Rootclass^ var in rase->Memory){
						 if(var == ship){
							 f = 1;
							 break;
						 }
					 }
					 if(f){
						 rase->Erase(ship);
						 break;
					 }
				 }
		 }

		 Boolean checkradius(SpaceShip^ ship, Rootclass^ obj){
			 if(Math::Sqrt(Math::Pow(obj->Location->X - ship->Location->X,2) + Math::Pow(obj->Location->Y - ship->Location->Y,2)) <= ship->Visibility)
				 return true;
			 else
				 return false;
		 }

		 Void planetturn(Rase^ rase, Planet^ planet){
			 if(rase->NumberWarship >= Rase::MAXWARSHIP && rase->NumberShip_radar >= Rase::MAXSHIP_RADAR && rase->NumberTransportship >= Rase::MAXTRANSPORTSHIP && (rase->NumberStealthship >= Rase::MAXSTEALTHSHIP || rase->NumberRegenship >= Rase::MAXREGENSHIP) || planet->Resource < 75)
				 return;
			 Rootclass^ w = nullptr;
			 Random rand;
			 if(rase->NumberWarship < 2){
				 w = planet->create_warship();
				 if(w) rase->Add(w);
				 return;
			 }
			 if(rase->NumberTransportship < 3){
				 w = planet->create_transportship();
				 if(w) rase->Add(w);
				 return;
			 }
			 Boolean f[4] = {true,true,true,true};
			 while(!w && f[0] && f[1] && f[2] && f[3]){
				 switch(rand.Next(4))
				 {
				 case 0: if(rase->NumberTransportship < Rase::MAXTRANSPORTSHIP) 
							 w = planet->create_transportship(); f[0] = false; break;
				 case 1: if(rase->NumberShip_radar < Rase::MAXSHIP_RADAR) 
							 w = planet->create_ship_radar(); f[1] = false; break;
				 case 2: if(rase->NumberWarship < Rase::MAXWARSHIP) 
							 w = planet->create_warship(); f[2] = false; break;
				 case 3: if(rase->Rasetype) {if(rase->NumberRegenship < Rase::MAXREGENSHIP)
							 w = planet->create_regenship(); f[3] = false;}
						 else {if(rase->NumberStealthship < Rase::MAXSTEALTHSHIP) 
							 w = planet->create_stealthship(); f[3] = false;}
						 break;
				 }
			 }
			 if(w) rase->Add(w);
		 }

		 Void warshipturn(Rase^ rase,Warship^ ship){
			 Regenship^ r = dynamic_cast<Regenship^>(ship);
			 if(r) r->regeneration();
			 int f = 1;
			 for each (Rase^ i in rases){
				 if(i != rase){
					 for each (Rootclass^ var in i->Memory){
						 if((dynamic_cast<Ship_radar^>(var) || dynamic_cast<Transportship^>(var)) && checkradius(ship,var)){
							 ship->attack(var);
							 if(dynamic_cast<SpaceShip^>(var)->Armor<=0)
								 deleteship(var);
							 break;
							 f = 0;
						 }
						 if(dynamic_cast<Warship^>(var) && checkradius(ship,var)){
							 Stealthship^ s = dynamic_cast<Stealthship^>(var);
							 if(s){
								 if(!s->stealthon(rase)){
									 ship->attack(var);
									 if(dynamic_cast<SpaceShip^>(var)->Armor<=0)
										 deleteship(var);
								 }
								 else
									 continue;
							 }
							 else
								 if(ship->Armor < dynamic_cast<Warship^>(var)->Armor*0.1)
									 ;//ship->retreat(var);
								 else{
									 ship->attack(var);
									 if(dynamic_cast<SpaceShip^>(var)->Armor<=0)
										 deleteship(var);
								 }
							 break;
							 f = 0;
						 }
					 }
				 }
				 if(!f) break;
			 }
			 Random rand;
			 Rootclass^ p = planets[rand.Next(planets->Count)];
			 if(f) ship->moveto(Point(p->Location->X,p->Location->Y));
		 }

		 Void ship_radarturn(Rase^ rase,Ship_radar^ ship){
			 int f = 1;
			 for each (Rase^ i in rases){
				 if(i != rase){
					 for each (Rootclass^ var in i->Memory){
						 if(dynamic_cast<Warship^>(var) && checkradius(ship,var)){
							 Stealthship^ s = dynamic_cast<Stealthship^>(var);
							 if(s){
								 if(!s->stealthon(rase))
									 ship->retreat(var);
								 else
									 continue;
							 }
							 else
								 ship->retreat(ship);
							 break;
							 f = 0;
						 }
					 }
				 }
				 if(!f) break;
			 }
			 Random rand;
			 Rootclass^ p = planets[rand.Next(planets->Count)];
			 if(f) ship->moveto(Point(p->Location->X,p->Location->Y));
		 }

		 Void transportshipturn(Rase^ rase,Transportship^ ship){
			 if(rase->NextPlanet){
				 ship->capture(rase);
				 if(!ship)
					 rase->Memory.Remove(ship);
				 return;
			 }
			 int f = 1;
			 for each (Rase^ i in rases){
				 if(i != rase){
					 for each (Rootclass^ var in i->Memory){
						 if(dynamic_cast<Warship^>(var) && checkradius(ship,var)){
							 Stealthship^ s = dynamic_cast<Stealthship^>(var);
							 if(s){
								 if(!s->stealthon(rase))
									 ship->retreat(var);
								 else
									 continue;
							 }
							 else
								 ship->retreat(ship);
							 break;
							 f = 0;
						 }
					 }
				 }
				 if(!f) break;
			 }
			 Random rand;
			 Rootclass^ p = planets[rand.Next(planets->Count)];
			 if(f) ship->moveto(Point(p->Location->X,p->Location->Y));
		 }

		 Void raseturn(Rase^ rase){
			 int empt = 0,num = 0;
			 for(int i = 0; i < rase->Memory.Count; i++){
				 SpaceShip^ s;
				 Planet^ p;
				 if(p = dynamic_cast<Planet^>(rase->Memory[i])){
					 if(p->Resource<1000)
						 empt++;
					 num++;
				 }
				 if(s = dynamic_cast<SpaceShip^>(rase->Memory[i]))
					 if(s->Armor <= 0)
						 rase->Erase(s);
			 }
			 if(empt == num){
				 for each (Planet^ var in planets){
					 if(!var->Captured && !rase->NextPlanet){
						 rase->NextPlanet = var;
						 break;
					 }
				 }
			 }
			 for(int i = 0; i < rase->Memory.Count; i++){
				 if(dynamic_cast<Planet^>(rase->Memory[i])){
					 planetturn(rase,dynamic_cast<Planet^>(rase->Memory[i]));
					 continue;
				 }
				 if(dynamic_cast<Warship^>(rase->Memory[i])){
					 warshipturn(rase,dynamic_cast<Warship^>(rase->Memory[i]));
					 continue;
				 }
				 if(dynamic_cast<Ship_radar^>(rase->Memory[i])){
					 ship_radarturn(rase,dynamic_cast<Ship_radar^>(rase->Memory[i]));
					 continue;
				 }
				 if(dynamic_cast<Transportship^>(rase->Memory[i])){
					 transportshipturn(rase,dynamic_cast<Transportship^>(rase->Memory[i]));
					 continue;
				 }
			 }
		 }

private: System::Void GameTimer_Tick(System::Object^  sender, System::EventArgs^  e) {
			 for each (Rase^ var in rases){
				 raseturn(var);
			 }
			 Space->Invalidate();
			 Int32 liferase = 0;
			 for each (Rase^ var in rases){
				 Int32 warships = 0;
				 for each (Rootclass^ i in var->Memory)
					 if(dynamic_cast<Warship^>(i))
						 warships++;
				 if(warships > 0)
					 liferase++;
			 }
			 if(liferase == 1){
				 GameTimer->Stop();
				 Start->Enabled = false;
				 Stop->Enabled = false;
				 for each (Rase^ var in rases){
					 Int32 warships = 0;
					for each (Rootclass^ i in var->Memory)
						 if(dynamic_cast<Warship^>(i))
							 warships++;
					 if(warships > 0){
						 MessageBox::Show("Победила раса " + var->ToString() + "!");
						 break;
					 }
				 }
			 }
		 }

private: System::Void Space_Paint(System::Object^  sender, System::Windows::Forms::PaintEventArgs^  e) {
			 if(!rases) return;
			 for each (Rase^ rase in rases){
				 for each (Rootclass^ var in rase->Memory){
					 if(cursor->X <= var->Location->X && (cursor->X + cursor->Width) >= var->Location->X && cursor->Y <= var->Location->Y && (cursor->Y + cursor->Height) >= var->Location->Y){
						 IPic^ w;
						 if(w = dynamic_cast<IPic^>(var)) w->draw(e->Graphics, gcnew Rectangle(0,0,Space->Width-15,Space->Height-15),gcnew Rectangle(Int32(cursor->X),Int32(cursor->Y),Int32(cursor->Width),Int32(cursor->Height)));
					 }
				 }
			 }
			 for each (Planet^ var in planets){
				 var->draw(e->Graphics, gcnew Rectangle(0,0,Space->Width-15,Space->Height-15),gcnew Rectangle(Int32(cursor->X),Int32(cursor->Y),Int32(cursor->Width),Int32(cursor->Height)));
			 }
		 }

private: System::Void Zoom(System::Object^ sender, System::Windows::Forms::MouseEventArgs^ e){
			 SpeedWheel = e->Delta/30;
			 if(!(e->X >= Space->Location.X && e->X <= Space->Location.X + Space->Width && e->Y >= Space->Location.Y && e->Y <= Space->Location.Y + Space->Height))
				 return;
			 cursor->X = Double(e->Location.X - Space->Location.X)/Space->Width*Rootclass::MAXCOORD - cursor->X/Double(e->Location.X - Space->Location.X)/Space->Width*Rootclass::MAXCOORD;
			 cursor->Y = Double(e->Location.Y - Space->Location.Y)/Space->Height*Rootclass::MAXCOORD - cursor->X/Double(e->Location.X - Space->Location.X)/Space->Width*Rootclass::MAXCOORD;
			 cursor->Width -= SpeedWheel;
			 cursor->Height -= SpeedWheel;
			 if(cursor->Width > Rootclass::MAXCOORD)
				 cursor->Width = Rootclass::MAXCOORD;
			 if(cursor->Width < 10)
				 cursor->Width = 10;
			 if(cursor->Height > Rootclass::MAXCOORD)
				 cursor->Height = Rootclass::MAXCOORD;
			 if(cursor->Height < 10)
				 cursor->Height = 10;
			 if(cursor->X + cursor->Width > Rootclass::MAXCOORD)
				 cursor->X = Rootclass::MAXCOORD - cursor->Width;
			 if(cursor->Y + cursor->Height > Rootclass::MAXCOORD)
				 cursor->Y = Rootclass::MAXCOORD - cursor->Height;
			 Space->Invalidate();
		 }

private: System::Void KeyEvents(System::Object^ sender, System::Windows::Forms::KeyEventArgs^ e){
			 if(e->KeyCode == Keys::Space && (Stop->Enabled || Start->Enabled))
				 if(Stop->Enabled)
					 Stop_Click(nullptr,nullptr);
				 else
					 Start_Click(nullptr,nullptr);
			 if(e->KeyCode == Keys::Left)
				 Left_Click(nullptr,nullptr);
			 if(e->KeyCode == Keys::Up)
				 Up_Click(nullptr,nullptr);
			 if(e->KeyCode == Keys::Right)
				 Right_Click(nullptr,nullptr);
			 if(e->KeyCode == Keys::Down)
				 Down_Click(nullptr,nullptr);
			 if(e->KeyCode == Keys::N && e->Control)
				 New_Game_Click(nullptr,nullptr);
		 }

private: System::Void ShowStatus(System::Object^  sender, System::EventArgs^  e){
			 if(status) delete status;
			 status = gcnew Status(rases);
			 status->Show();
		 }
};
}


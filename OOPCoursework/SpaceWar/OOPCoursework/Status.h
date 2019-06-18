#pragma once
#include "Regenship.h"
#include "Stealthship.h"
#include "Ship_radar.h"
#include "Transportship.h"
#include "Planet.h"
#include "Rase.h"

namespace OOPCoursework {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Сводка для Status
	/// </summary>
	public ref class Status : public System::Windows::Forms::Form
	{
	public:
		Status(List<Rase^>^ data)
		{
			InitializeComponent();
			//
			//TODO: добавьте код конструктора
			//
			rases = data;
			refresh();
		}

	protected:
		/// <summary>
		/// Освободить все используемые ресурсы.
		/// </summary>
		~Status()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::ListBox^  RasesList;
	protected: 

	protected: 

	private:
		List<Rase^>^ rases;
	private: System::Windows::Forms::ListBox^  DataRaseList;
	private: System::Windows::Forms::ListBox^  UnitsList;
	private: System::Windows::Forms::MenuStrip^  menuStrip1;
	private: System::Windows::Forms::ToolStripMenuItem^  файлToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^  RefreshBotton;
	private: System::Windows::Forms::ToolStripMenuItem^  Exit;
	private: System::Windows::Forms::ToolStripMenuItem^  AutoRefresh;
	private: System::Windows::Forms::Timer^  RefreshTimer;
	private: System::ComponentModel::IContainer^  components;

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
			this->RasesList = (gcnew System::Windows::Forms::ListBox());
			this->DataRaseList = (gcnew System::Windows::Forms::ListBox());
			this->UnitsList = (gcnew System::Windows::Forms::ListBox());
			this->menuStrip1 = (gcnew System::Windows::Forms::MenuStrip());
			this->файлToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->RefreshBotton = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->Exit = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->AutoRefresh = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->RefreshTimer = (gcnew System::Windows::Forms::Timer(this->components));
			this->menuStrip1->SuspendLayout();
			this->SuspendLayout();
			// 
			// RasesList
			// 
			this->RasesList->Anchor = static_cast<System::Windows::Forms::AnchorStyles>((System::Windows::Forms::AnchorStyles::Top | System::Windows::Forms::AnchorStyles::Bottom));
			this->RasesList->FormattingEnabled = true;
			this->RasesList->Location = System::Drawing::Point(12, 33);
			this->RasesList->Name = L"RasesList";
			this->RasesList->Size = System::Drawing::Size(187, 160);
			this->RasesList->TabIndex = 0;
			this->RasesList->Click += gcnew System::EventHandler(this, &Status::RasesList_Click);
			// 
			// DataRaseList
			// 
			this->DataRaseList->Anchor = static_cast<System::Windows::Forms::AnchorStyles>((System::Windows::Forms::AnchorStyles::Top | System::Windows::Forms::AnchorStyles::Bottom));
			this->DataRaseList->FormattingEnabled = true;
			this->DataRaseList->Location = System::Drawing::Point(205, 33);
			this->DataRaseList->Name = L"DataRaseList";
			this->DataRaseList->Size = System::Drawing::Size(175, 160);
			this->DataRaseList->TabIndex = 1;
			this->DataRaseList->Click += gcnew System::EventHandler(this, &Status::DataRaseList_Click);
			// 
			// UnitsList
			// 
			this->UnitsList->Anchor = static_cast<System::Windows::Forms::AnchorStyles>((System::Windows::Forms::AnchorStyles::Top | System::Windows::Forms::AnchorStyles::Bottom));
			this->UnitsList->FormattingEnabled = true;
			this->UnitsList->Location = System::Drawing::Point(386, 33);
			this->UnitsList->Name = L"UnitsList";
			this->UnitsList->Size = System::Drawing::Size(207, 160);
			this->UnitsList->TabIndex = 2;
			// 
			// menuStrip1
			// 
			this->menuStrip1->Items->AddRange(gcnew cli::array< System::Windows::Forms::ToolStripItem^  >(2) {this->файлToolStripMenuItem, 
				this->AutoRefresh});
			this->menuStrip1->Location = System::Drawing::Point(0, 0);
			this->menuStrip1->Name = L"menuStrip1";
			this->menuStrip1->Size = System::Drawing::Size(605, 24);
			this->menuStrip1->TabIndex = 3;
			this->menuStrip1->Text = L"menuStrip1";
			// 
			// файлToolStripMenuItem
			// 
			this->файлToolStripMenuItem->DropDownItems->AddRange(gcnew cli::array< System::Windows::Forms::ToolStripItem^  >(2) {this->RefreshBotton, 
				this->Exit});
			this->файлToolStripMenuItem->Name = L"файлToolStripMenuItem";
			this->файлToolStripMenuItem->Size = System::Drawing::Size(48, 20);
			this->файлToolStripMenuItem->Text = L"Файл";
			// 
			// RefreshBotton
			// 
			this->RefreshBotton->Name = L"RefreshBotton";
			this->RefreshBotton->Size = System::Drawing::Size(128, 22);
			this->RefreshBotton->Text = L"Обновить";
			this->RefreshBotton->Click += gcnew System::EventHandler(this, &Status::RefreshBotton_Click);
			// 
			// Exit
			// 
			this->Exit->Name = L"Exit";
			this->Exit->Size = System::Drawing::Size(128, 22);
			this->Exit->Text = L"Выход";
			this->Exit->Click += gcnew System::EventHandler(this, &Status::Exit_Click);
			// 
			// AutoRefresh
			// 
			this->AutoRefresh->BackColor = System::Drawing::SystemColors::Control;
			this->AutoRefresh->Name = L"AutoRefresh";
			this->AutoRefresh->Size = System::Drawing::Size(112, 20);
			this->AutoRefresh->Text = L"Автообновление";
			this->AutoRefresh->Click += gcnew System::EventHandler(this, &Status::AutoRefresh_Click);
			// 
			// RefreshTimer
			// 
			this->RefreshTimer->Interval = 500;
			this->RefreshTimer->Tick += gcnew System::EventHandler(this, &Status::RefreshTimer_Tick);
			// 
			// Status
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(605, 205);
			this->Controls->Add(this->UnitsList);
			this->Controls->Add(this->DataRaseList);
			this->Controls->Add(this->RasesList);
			this->Controls->Add(this->menuStrip1);
			this->MainMenuStrip = this->menuStrip1;
			this->MaximumSize = System::Drawing::Size(615, 500);
			this->MinimumSize = System::Drawing::Size(615, 200);
			this->Name = L"Status";
			this->Text = L"Status";
			this->menuStrip1->ResumeLayout(false);
			this->menuStrip1->PerformLayout();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
private: System::Void refresh(){
			 Int32 selected;
			 selected = RasesList->SelectedIndex;
			 refreshRasesList();
			 RasesList->SelectedIndex = selected;
			 if(RasesList->SelectedIndex != -1){ 
				 selected = DataRaseList->SelectedIndex;
				 refreshDataRaseList();
				 DataRaseList->SelectedIndex = selected;
			 }
			 if(DataRaseList->SelectedIndex != -1){
				 selected = UnitsList->SelectedIndex;
				 refreshUnitsList();
				 if(UnitsList->SelectedIndex > UnitsList->Items->Count)
					UnitsList->SelectedIndex = UnitsList->Items->Count-1;
				 else
					 UnitsList->SelectedIndex = selected;
			 }
		}

private: System::Void refreshRasesList(){
			 if(!rases)
				 return;
			 RasesList->Items->Clear();
			 for each (Rase^ var in rases){
				 RasesList->Items->Add(var->ToString());
			 }
		 }

private: System::Void refreshDataRaseList(){
			 if(!rases)
				 return;
			 DataRaseList->Items->Clear();
			 Int32 planet = 0, transport=0, radar=0, war=0, stealth=0, regen=0;
			 for each (Rootclass^ var in rases[RasesList->SelectedIndex]->Memory){
				 if(dynamic_cast<Planet^>(var))
					 planet++;
				 if(dynamic_cast<Transportship^>(var))
					 transport++;
				 if(dynamic_cast<Ship_radar^>(var))
					 radar++;
				 if(dynamic_cast<Warship^>(var) && !(dynamic_cast<Regenship^>(var) || dynamic_cast<Stealthship^>(var)))
					 war++;
				 if(dynamic_cast<Stealthship^>(var))
					 stealth++;
				 if(dynamic_cast<Regenship^>(var))
					 regen++;
			 }
			 String^ data = gcnew String("Planet: " + planet.ToString());
			 DataRaseList->Items->Add(data);
			 data = "Transportship: " + transport.ToString();
			 DataRaseList->Items->Add(data);
			 data = "Ship_radar: " + radar.ToString();
			 DataRaseList->Items->Add(data);
			 data = "Warship: " + war.ToString();
			 DataRaseList->Items->Add(data);
			 if(rases[RasesList->SelectedIndex]->Rasetype){
				 data = "Regenship: " + regen.ToString();
				 DataRaseList->Items->Add(data);
			 }
			 else{
				 data = "Stealthship: " + stealth.ToString();
				 DataRaseList->Items->Add(data);
			 }
		}

	private: System::Void refreshUnitsList(){
			 if(!rases)
				 return;
			 UnitsList->Items->Clear();
			 Rootclass^ p;
			 for each (Rootclass^ var in rases[RasesList->SelectedIndex]->Memory){
				 switch (DataRaseList->SelectedIndex){
				 case 0: if(p = dynamic_cast<Planet^>(var))UnitsList->Items->Add(p->ToString()); break;
				 case 1: if(p = dynamic_cast<Transportship^>(var))UnitsList->Items->Add(p->ToString()); break;
				 case 2: if(p = dynamic_cast<Ship_radar^>(var))UnitsList->Items->Add(p->ToString()); break;
				 case 3: if((p = dynamic_cast<Warship^>(var)) && !(dynamic_cast<Regenship^>(var) || dynamic_cast<Stealthship^>(var)))UnitsList->Items->Add(p->ToString()); break;
				 case 4: if(dynamic_cast<Regenship^>(var) || dynamic_cast<Stealthship^>(var))UnitsList->Items->Add(dynamic_cast<Warship^>(var)->ToString()); break;
				 default:
					 break;
				 }
			 }
			 }

	private: System::Void Exit_Click(System::Object^  sender, System::EventArgs^  e) {
				 this->Close();
			 }

	private: System::Void RefreshBotton_Click(System::Object^  sender, System::EventArgs^  e) {
				 refresh();
			 }
	private: System::Void KeyEvents(System::Object^ sender, System::Windows::Forms::KeyEventArgs^ e){
				 if(e->KeyCode == Keys::F5)
					 refresh();
			 }
private: System::Void RasesList_Click(System::Object^  sender, System::EventArgs^  e) {
			 Int32 selected = DataRaseList->SelectedIndex;
			 refreshDataRaseList();
			 DataRaseList->SelectedIndex = selected;
		 }
private: System::Void DataRaseList_Click(System::Object^  sender, System::EventArgs^  e) {
			 if(DataRaseList->SelectedIndex == -1)
				 return;
			 Int32 selected = UnitsList->SelectedIndex;
			 refreshUnitsList();
			 if(UnitsList->SelectedIndex > UnitsList->Items->Count-1)
				 UnitsList->SelectedIndex = UnitsList->Items->Count-1;
			 else
				 UnitsList->SelectedIndex = selected;
		 }
private: System::Void AutoRefresh_Click(System::Object^  sender, System::EventArgs^  e) {
			 if(AutoRefresh->Checked){
				 AutoRefresh->Checked = false;
				 AutoRefresh->BackColor = SystemColors::Control;
				 RefreshTimer->Stop();
			 }
			 else{
				 AutoRefresh->Checked = true;
				 AutoRefresh->BackColor = SystemColors::ButtonShadow;
				 RefreshTimer->Start();
			 }
		 }
private: System::Void RefreshTimer_Tick(System::Object^  sender, System::EventArgs^  e) {
			 refresh();
		 }
};
}

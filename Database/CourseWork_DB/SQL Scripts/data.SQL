insert into client (userid, fullname, login, password) values (1, 'fullname', 'user', '1')
/

insert into EstimateState (PK_EstimateState, Name, Code) values (1, 'Проект', '1')
/

insert into EstimateState (PK_EstimateState, Name, Code) values (2, 'Согласование', '2')
/

insert into EstimateState (PK_EstimateState, Name, Code) values (3, 'Утверждено', '3')
/

insert into BuildingPurpose (PK_BuildingPurpose, Name, Code) values (1, 'Строительство', '1')
/

insert into BuildingPurpose (PK_BuildingPurpose, Name, Code) values (2, 'Реставрация', '2')
/

insert into estimatetype (pk_estimatetype, name, code) values (1, 'Локальная', '1');
/

insert into estimatetype (pk_estimatetype, name, code) values (2, 'Объектная', '2');
/

insert into units (pk_units, DESIGNATION, unitsname) values (1, 'м', 'Метры')
/

insert into units (pk_units, DESIGNATION, unitsname) values (2, 'кв. м', 'Квадратные метры')
/

insert into units (pk_units,DESIGNATION ,unitsname) values (3, 'куб. м', 'Кубические метры')
/

insert into units (pk_units, DESIGNATION, unitsname) values (4, 'шт.', 'Штуки')
/

insert into buildobject (pk_object, name, address, PKBuildingPurpose, userid) values (1, 'дфыоа', 'оыфрарфа', 1, 1)
/

insert into buildobject (pk_object, name, address, PKBuildingPurpose, userid) values (2, 'йцаыф', 'лрфыалор', 1, 1)
/

insert into contractor (pk_contractor, contrfullname, contrbusinessname) values (1, 'safhak', 'asjkhfjahfsj')
/

insert into customer (pk_customer, custfullname, custbusinessname) values (1, 'salfjlk', 'asfhaskkls')
/

insert into estimate (pk_estimate, code, pkestimatestate, pkobject, pkestimatetype, pkcustomer, pkcontractor)
		values (1, '1', 1, 1, 1, 1, 1)
/

insert into estimate (pk_estimate, code, pkestimatestate, pkobject, pkestimatetype, pkcustomer, pkcontractor)
		values (2, '2', 1, 1, 1, 1, 1)
/

insert into estimate (pk_estimate, code, pkestimatestate, pkobject, pkestimatetype, pkcustomer, pkcontractor)
		values (3, '1', 1, 2, 1, 1, 1)
/

insert into estimate (pk_estimate, code, pkestimatestate, pkobject, pkestimatetype, pkcustomer, pkcontractor)
		values (4, '2', 1, 2, 1, 1, 1)
/
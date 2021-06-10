create table rules
(
  id serial primary key,
  name varchar(155)
);
insert into rules (name) values ('статья не выбрана');
insert into rules (name) values ('статья 12.9(скорость)');
insert into rules (name) values ('статья 12.15(обгон)');
insert into rules (name) values ('статья 12.19(стоянка)');
insert into rules (name) values ('статья 12.16(разметка)');

create table accidentTypes
(
  id serial primary key,
  name varchar(55)
);
insert into accidentTypes (name) values ('не выбрано');
insert into accidentTypes (name) values ('Превышение скорости');
insert into accidentTypes (name) values ('Обгон в неположенном месте');
insert into accidentTypes (name) values ('Стоянка в запрещенном месте');
insert into accidentTypes (name) values ('Нарушение разметки');

create table accidents
(
  id serial primary key,
  name varchar(75),
  text text,
  address text,
  type_id int references accidentTypes(id),
  rule_ids varchar(15)
);

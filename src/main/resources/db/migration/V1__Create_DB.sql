create sequence city_info_seq start 1 increment 1;
create sequence city_seq start 1 increment 1;

create table cities (id int4 not null, name varchar(255), primary key (id));
create table city_infos (id int4 not null, information varchar(255), city_id int4, primary key (id));
alter table cities add constraint city_name unique (name);
alter table city_infos add constraint information_for_sity unique (city_id, information);
alter table city_infos add constraint city_id_for_city_info foreign key (city_id) references cities;








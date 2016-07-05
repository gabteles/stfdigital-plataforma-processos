create schema dashboards;

create sequence dashboards.seq_dashboard increment by 1 start with 1 nomaxvalue minvalue 1 nocycle nocache;
create sequence dashboards.seq_dashlet increment by 1 start with 1 nomaxvalue minvalue 1 nocycle nocache;

create table dashboards.dashboard (seq_dashboard bigint not null, nom_dashboard varchar2(100) not null, constraint pk_dashboard primary key (seq_dashboard));
alter table dashboards.dashboard add constraint uk_dash_nom_dashboard unique(nom_dashboard);

create table dashboards.dashlet (seq_dashlet bigint not null, nom_dashlet varchar2(100) not null, dsc_dashlet varchar2(100) not null, constraint pk_dashlet primary key (seq_dashlet));
alter table dashboards.dashlet add constraint uk_dash_nom_dashlet unique(nom_dashlet);

create table dashboards.dashboard_dashlet (seq_dashboard bigint not null, seq_dashlet bigint not null, constraint pk_dashboard_dashlet primary key (seq_dashboard, seq_dashlet));
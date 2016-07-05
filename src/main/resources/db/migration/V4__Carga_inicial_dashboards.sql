INSERT INTO dashboards.dashboard (seq_dashboard, nom_dashboard) VALUES (dashboards.seq_dashboard.nextval, 'Peticionamento');
INSERT INTO dashboards.dashboard (seq_dashboard, nom_dashboard) VALUES (dashboards.seq_dashboard.nextval, 'Recebimento');
INSERT INTO dashboards.dashboard (seq_dashboard, nom_dashboard) VALUES (dashboards.seq_dashboard.nextval, 'Gestão de Recebimento');
INSERT INTO dashboards.dashboard (seq_dashboard, nom_dashboard) VALUES (dashboards.seq_dashboard.nextval, 'Distribuição');
INSERT INTO dashboards.dashboard (seq_dashboard, nom_dashboard) VALUES (dashboards.seq_dashboard.nextval, 'Autuação');
INSERT INTO dashboards.dashboard (seq_dashboard, nom_dashboard) VALUES (dashboards.seq_dashboard.nextval, 'Gestão de Autuação');
INSERT INTO dashboards.dashboard (seq_dashboard, nom_dashboard) VALUES (dashboards.seq_dashboard.nextval, 'Gestão de Órgão');
INSERT INTO dashboards.dashboard (seq_dashboard, nom_dashboard) VALUES (dashboards.seq_dashboard.nextval, 'Gestão de Cadastro');

INSERT INTO dashboards.dashlet (seq_dashlet, nom_dashlet, dsc_dashlet) VALUES (dashboards.seq_dashlet.nextval, 'minhas-peticoes', 'Minhas Petições');
INSERT INTO dashboards.dashlet (seq_dashlet, nom_dashlet, dsc_dashlet) VALUES (dashboards.seq_dashlet.nextval, 'minhas-tarefas', 'Tarefas atribuídas a mim');
INSERT INTO dashboards.dashlet (seq_dashlet, nom_dashlet, dsc_dashlet) VALUES (dashboards.seq_dashlet.nextval, 'tarefas-papeis', 'Tarefas dos meus papéis');
INSERT INTO dashboards.dashlet (seq_dashlet, nom_dashlet, dsc_dashlet) VALUES (dashboards.seq_dashlet.nextval, 'grafico-gestao', 'Gráfico de Autuações');

INSERT INTO dashboards.dashboard_dashlet (seq_dashboard, seq_dashlet) VALUES (1, 1);
INSERT INTO dashboards.dashboard_dashlet (seq_dashboard, seq_dashlet) VALUES (2, 1);
INSERT INTO dashboards.dashboard_dashlet (seq_dashboard, seq_dashlet) VALUES (3, 2);
INSERT INTO dashboards.dashboard_dashlet (seq_dashboard, seq_dashlet) VALUES (3, 3);
INSERT INTO dashboards.dashboard_dashlet (seq_dashboard, seq_dashlet) VALUES (4, 2);
INSERT INTO dashboards.dashboard_dashlet (seq_dashboard, seq_dashlet) VALUES (4, 3);
INSERT INTO dashboards.dashboard_dashlet (seq_dashboard, seq_dashlet) VALUES (5, 2);
INSERT INTO dashboards.dashboard_dashlet (seq_dashboard, seq_dashlet) VALUES (5, 3);
INSERT INTO dashboards.dashboard_dashlet (seq_dashboard, seq_dashlet) VALUES (6, 2);
INSERT INTO dashboards.dashboard_dashlet (seq_dashboard, seq_dashlet) VALUES (6, 3);
INSERT INTO dashboards.dashboard_dashlet (seq_dashboard, seq_dashlet) VALUES (6, 4);
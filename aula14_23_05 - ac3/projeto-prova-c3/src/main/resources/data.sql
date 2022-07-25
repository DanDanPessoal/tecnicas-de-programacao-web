-- crie aqui as instruções SQL necessárias para a API iniciar com uma massa de dados mínima

insert into classe_heroi
(nome, pontuacao_minima, salario_base) values
('S', 10000, 200000),
('A', 9000, 150000),
('B', 5000, 80000),
('C', 0, 10000);

insert into nivel_ameaca
(nome, pontuacao) values
('Lobo', 50),
('Tigre', 100),
('Demônio', 1000),
('Dragão', 4000),
('Deus', 10000);
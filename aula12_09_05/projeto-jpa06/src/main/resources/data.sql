-- ao criamos um arquivo de nome "data.sql" na pasta "resources"
-- caso a configuração "spring.jpa.defer-datasource-initialization" estiver true,
-- todas as instruções aqui serão executadas
-- PODEM haver mais de 1 insert, porém devem sempre terminar com ";"
insert into tipo_animal (descricao) values
('Gato'), ('Cachorro'), ('Coelho'), ('Hamster');

insert into animal_estimacao
(castrado, cpf_dono, data_nascimento, email_dono, nome, peso, telefone_dono, tipo_codigo)
values
(false, '45750262049', '2020-09-09', 'aaaa@bbb', 'xuxu', 2.5, '9999-9999', 1),
(true, '71152763075', '2020-01-01', 'bbbb@bbb', 'xena', 5.5, '8888-9999', 1),
(true, '92132139059', '2018-12-12', 'cccc@bbb', 'cenourinha', 0.2, '11 7777-9999', 4),
(false, '99988800012', '2019-01-01', 'wwqqq@eee', 'xuxu', 4.0, '9999-9999', 2);
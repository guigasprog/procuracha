INSERT INTO cidade (id, nome, uf) VALUES (1, 'São Paulo', 'SP');
INSERT INTO cidade (id, nome, uf) VALUES (2, 'Campinas', 'SP');
INSERT INTO cidade (id, nome, uf) VALUES (3, 'Rio de Janeiro', 'RJ');
INSERT INTO cidade (id, nome, uf) VALUES (4, 'Niterói', 'RJ');
INSERT INTO cidade (id, nome, uf) VALUES (5, 'Belo Horizonte', 'MG');
INSERT INTO cidade (id, nome, uf) VALUES (6, 'Uberlândia', 'MG');

INSERT INTO cliente (id, nome, cpf, tel_cel, cidade_id) VALUES (1, 'João Silva', '123.456.789-00', '(11) 91234-5678', 1);
INSERT INTO cliente (id, nome, cpf, tel_cel, cidade_id) VALUES (2, 'Maria Oliveira', '987.654.321-00', '(21) 99876-5432', 3);
INSERT INTO cliente (id, nome, cpf, tel_cel, cidade_id) VALUES (3, 'Carlos Souza', '111.222.333-44', '(31) 93210-8765', 5);
INSERT INTO cliente (id, nome, cpf, tel_cel, cidade_id) VALUES (4, 'Ana Costa', '444.555.666-77', '(19) 92123-4567', 2);
INSERT INTO cliente (id, nome, cpf, tel_cel, cidade_id) VALUES (5, 'Pedro Lima', '888.999.000-11', '(22) 92345-6789', 4);

INSERT INTO servico (id, descricao, valor) VALUES (1, 'Corte de Cabelo', 50.00);
INSERT INTO servico (id, descricao, valor) VALUES (2, 'Manicure', 30.00);
INSERT INTO servico (id, descricao, valor) VALUES (3, 'Massagem Relaxante', 150.00);
INSERT INTO servico (id, descricao, valor) VALUES (4, 'Limpeza de Pele', 120.50);
INSERT INTO servico (id, descricao, valor) VALUES (5, 'Depilação', 80.75);


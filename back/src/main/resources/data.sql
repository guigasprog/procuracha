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

INSERT INTO servico (id, descricao) VALUES (1, 'Corte de Cabelo');
INSERT INTO servico (id, descricao) VALUES (2, 'Manicure');
INSERT INTO servico (id, descricao) VALUES (3, 'Massagem Relaxante');
INSERT INTO servico (id, descricao) VALUES (4, 'Limpeza de Pele');
INSERT INTO servico (id, descricao) VALUES (5, 'Depilação');

INSERT INTO profissional (id, cliente_id) VALUES (1, 1);
INSERT INTO profissional (id, cliente_id) VALUES (2, 2);
INSERT INTO profissional (id, cliente_id) VALUES (3, 3);
INSERT INTO profissional (id, cliente_id) VALUES (4, 4);
INSERT INTO profissional (id, cliente_id) VALUES (5, 5);

INSERT INTO profissional_servicos (profissional_id, servico_id) VALUES (1, 1);
INSERT INTO profissional_servicos (profissional_id, servico_id) VALUES (1, 2);
INSERT INTO profissional_servicos (profissional_id, servico_id) VALUES (2, 3);
INSERT INTO profissional_servicos (profissional_id, servico_id) VALUES (3, 4);
INSERT INTO profissional_servicos (profissional_id, servico_id) VALUES (4, 5);
INSERT INTO profissional_servicos (profissional_id, servico_id) VALUES (5, 1);
INSERT INTO profissional_servicos (profissional_id, servico_id) VALUES (5, 3);

INSERT INTO contrato (id, data, hora, problema, cliente_id, profissional_id) VALUES
       (1, '2024-08-22', '10:00:00', 'Reparo elétrico', 1, 2),  -- João Silva contratando Maria Oliveira
       (2, '2024-08-23', '14:30:00', 'Instalação de ar-condicionado', 2, 3),  -- Maria Oliveira contratando Carlos Souza
       (3, '2024-08-24', '09:00:00', 'Conserto de encanamento', 3, 4),  -- Carlos Souza contratando Ana Costa
       (4, '2024-08-25', '11:00:00', 'Pintura de parede', 4, 5),  -- Ana Costa contratando Pedro Lima
       (5, '2024-08-26', '13:00:00', 'Manutenção de jardim', 5, 1);  -- Pedro Lima contratando João Silva

INSERT INTO feedback (id, aceito, resolvido, nota, descricao, contrato_id) VALUES
       (1, true, true, 10, 'Problema resolvido rapidamente e com eficiência.', 1),
       (2, true, false, 6, 'Instalação realizada, mas o ar-condicionado apresentou problemas após alguns dias.', 2),
       (3, true, true, 9, 'Conserto de encanamento perfeito, sem vazamentos.', 3),
       (4, false, false, 0, '', 4),
       (5, true, false, 8, 'Jardim ficou bom, mas o profissional não levou todas as ferramentas necessárias.', 5);

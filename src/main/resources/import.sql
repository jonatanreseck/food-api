-- insert cozinhas
insert into cozinha (id, nome) values (1, 'Mineira');
insert into cozinha (id, nome) values (2, 'Caipira');
insert into cozinha (id, nome) values (3, 'Lanche');

-- insert restaurantes
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Bar do Bá', 3.00, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Elite Mineira', 4.00, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Suco de Laranja', 2.00, 2);

-- insert estado
insert into estado (id, nome) values (1, 'Minas Gerais');

-- insert cidade
insert into cidade (id, nome, estado_id) values (1, 'Santa Rita do Sapucai', 1);
insert into cidade (id, nome, estado_id) values (2, 'Pouso Alegre', 1);

-- insert permissão

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

-- insert pagamento
insert into forma_pagamento (descricao) values ('Cartão crédito');
insert into forma_pagamento (descricao) values ('PIX');

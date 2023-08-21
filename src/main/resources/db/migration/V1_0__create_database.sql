CREATE TABLE situacoes_cartorio
(
    situacao_id  varchar(20) NOT NULL,
    nome         varchar(50) NOT NULL,
    PRIMARY KEY (situacao_id),
    UNIQUE(nome)
);

CREATE TABLE atribuicoes_cartorio
(
    atribuicao_id  varchar(20) NOT NULL,
    nome           varchar(50) NOT NULL,
    situacao       boolean     NOT NULL DEFAULT TRUE,
    PRIMARY KEY (atribuicao_id),
    UNIQUE(nome)
);

CREATE TABLE cartorios
(
    cartorio_id  int NOT NULL,
    nome         varchar(50) NOT NULL,
    observacao   varchar(250),
    situacao     varchar(20) NOT NULL,
    PRIMARY KEY (cartorio_id),
    UNIQUE(nome),
    CONSTRAINT valor_id CHECK (cartorio_id > 0),
    FOREIGN KEY (situacao) REFERENCES situacoes_cartorio(situacao_id)
);

CREATE TABLE relacao_cartorio_atribuicoes
(
    cartorio_id    int NOT NULL,
    atribuicao_id  varchar(20) NOT NULL,
    FOREIGN KEY (cartorio_id) REFERENCES cartorios(cartorio_id),
    FOREIGN KEY (atribuicao_id) REFERENCES atribuicoes_cartorio(atribuicao_id),
    CONSTRAINT UNIQUE_cartorio_atribuicao UNIQUE (cartorio_id, atribuicao_id)
);

CREATE TABLE situacoes_cartorio
(
    id     varchar(20) NOT NULL,
    nome   varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE atribuicoes_cartorio
(
    id        varchar(20) NOT NULL,
    nome      varchar(50) NOT NULL,
    situacao  boolean     NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);

CREATE TABLE cartorios
(
    id          int NOT NULL,
    nome        varchar(50) NOT NULL,
    observacao  varchar(250),
    situacao    varchar(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT valor_id CHECK (id > 0),
    FOREIGN KEY (situacao) REFERENCES situacoes_cartorio(id)
);

CREATE TABLE relacao_cartorio_atribuicoes
(
    cartorio_id int NOT NULL,
    atribuicao_id int NOT NULL,
    FOREIGN KEY (cartorio_id) REFERENCES cartorios(id),
    FOREIGN KEY (atribuicao_id) REFERENCES atribuicoes_cartorio(id),
    CONSTRAINT UNIQUE_cartorio_atribuicao UNIQUE (cartorio_id, atribuicao_id)
);

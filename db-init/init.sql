-- Criação do schema público
CREATE SCHEMA public;

-- Tabela funcionario
CREATE TABLE public.funcionario (
                                    id SERIAL PRIMARY KEY,
                                    nome VARCHAR(100) NOT NULL,
                                    cnh VARCHAR(20) NOT NULL,
                                    cargo VARCHAR(50) NOT NULL
);

-- Tabela nota_servico
CREATE TABLE public.nota_servico (
                                     id SERIAL PRIMARY KEY,
                                     cliente VARCHAR(150) NOT NULL,
                                     moto VARCHAR(100) NOT NULL,
                                     cilindradas INTEGER,
                                     descricao TEXT NOT NULL,
                                     material VARCHAR(150),
                                     funcionario_id INTEGER NOT NULL REFERENCES public.funcionario(id),
                                     situacao VARCHAR(50) NOT NULL
);

-- Tabela usuario
CREATE TABLE public.usuario (
                                id SERIAL PRIMARY KEY,
                                email VARCHAR(100) NOT NULL UNIQUE,
                                senha VARCHAR(255) NOT NULL
);

-- Inserções iniciais

INSERT INTO public.funcionario (nome, cnh, cargo) VALUES
    ('João Silva', 'ABC12345', 'Mecanico');

INSERT INTO public.nota_servico (cliente, moto, cilindradas, descricao, material, funcionario_id, situacao) VALUES
    ('Maria Souza', 'Yamaha XTZ', 250, 'Troca de pastilhas de freio', 'pastilhas', 1, 'Em Andamento');

INSERT INTO public.usuario (email, senha) VALUES
                                              ('admin@admin', 'admin'),
                                              ('teste@final2', '22323');

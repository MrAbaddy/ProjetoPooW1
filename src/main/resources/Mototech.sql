--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

-- Started on 2025-06-03 13:36:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4819 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 16457)
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funcionario (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    cnh character varying(20) NOT NULL,
    cargo character varying(50) NOT NULL
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16456)
-- Name: funcionario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.funcionario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.funcionario_id_seq OWNER TO postgres;

--
-- TOC entry 4820 (class 0 OID 0)
-- Dependencies: 219
-- Name: funcionario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.funcionario_id_seq OWNED BY public.funcionario.id;


--
-- TOC entry 222 (class 1259 OID 16569)
-- Name: nota_servico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nota_servico (
    id integer NOT NULL,
    cliente character varying(150) NOT NULL,
    moto character varying(100) NOT NULL,
    cilindradas integer,
    descricao text NOT NULL,
    material character varying(150),
    funcionario_id integer NOT NULL,
    situacao character varying(50) NOT NULL
);


ALTER TABLE public.nota_servico OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16568)
-- Name: nota_servico_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nota_servico_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.nota_servico_id_seq OWNER TO postgres;

--
-- TOC entry 4821 (class 0 OID 0)
-- Dependencies: 221
-- Name: nota_servico_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.nota_servico_id_seq OWNED BY public.nota_servico.id;


--
-- TOC entry 218 (class 1259 OID 16448)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    email character varying(100) NOT NULL,
    senha character varying(255) NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16447)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_id_seq OWNER TO postgres;

--
-- TOC entry 4822 (class 0 OID 0)
-- Dependencies: 217
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- TOC entry 4652 (class 2604 OID 16460)
-- Name: funcionario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario ALTER COLUMN id SET DEFAULT nextval('public.funcionario_id_seq'::regclass);


--
-- TOC entry 4653 (class 2604 OID 16572)
-- Name: nota_servico id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_servico ALTER COLUMN id SET DEFAULT nextval('public.nota_servico_id_seq'::regclass);


--
-- TOC entry 4651 (class 2604 OID 16451)
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- TOC entry 4811 (class 0 OID 16457)
-- Dependencies: 220
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.funcionario (id, nome, cnh, cargo) FROM stdin;
1	João Silva	ABC12345	Mecanico
\.


--
-- TOC entry 4813 (class 0 OID 16569)
-- Dependencies: 222
-- Data for Name: nota_servico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.nota_servico (id, cliente, moto, cilindradas, descricao, material, funcionario_id, situacao) FROM stdin;
2	Maria Souza	Yamaha XTZ	250	Troca de pastilhas de freio	pastilhas	1	Em Andamento
\.


--
-- TOC entry 4809 (class 0 OID 16448)
-- Dependencies: 218
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id, email, senha) FROM stdin;
3	admin@admin	admin
12	teste@final2	22323
\.


--
-- TOC entry 4823 (class 0 OID 0)
-- Dependencies: 219
-- Name: funcionario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.funcionario_id_seq', 7, true);


--
-- TOC entry 4824 (class 0 OID 0)
-- Dependencies: 221
-- Name: nota_servico_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.nota_servico_id_seq', 4, true);


--
-- TOC entry 4825 (class 0 OID 0)
-- Dependencies: 217
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 12, true);


--
-- TOC entry 4659 (class 2606 OID 16462)
-- Name: funcionario funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (id);


--
-- TOC entry 4661 (class 2606 OID 16576)
-- Name: nota_servico nota_servico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_servico
    ADD CONSTRAINT nota_servico_pkey PRIMARY KEY (id);


--
-- TOC entry 4655 (class 2606 OID 16455)
-- Name: usuario usuario_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_email_key UNIQUE (email);


--
-- TOC entry 4657 (class 2606 OID 16453)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 4662 (class 2606 OID 16577)
-- Name: nota_servico nota_servico_funcionario_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_servico
    ADD CONSTRAINT nota_servico_funcionario_id_fkey FOREIGN KEY (funcionario_id) REFERENCES public.funcionario(id);


-- Completed on 2025-06-03 13:36:12

--
-- PostgreSQL database dump complete
--


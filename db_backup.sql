--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.9
-- Dumped by pg_dump version 9.6.9

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: thesaurus; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country (
    id integer NOT NULL,
    name character varying NOT NULL,
    code integer NOT NULL
);


ALTER TABLE public.country OWNER TO postgres;

--
-- Name: country_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.country_id_seq OWNER TO postgres;

--
-- Name: country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;


--
-- Name: doc; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doc (
    id integer NOT NULL,
    name character varying,
    doc_type integer,
    date date,
    country_id integer,
    is_identified boolean
);


ALTER TABLE public.doc OWNER TO postgres;

--
-- Name: doc_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.doc_id_seq OWNER TO postgres;

--
-- Name: doc_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.doc_id_seq OWNED BY public.doc.id;


--
-- Name: doc_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doc_type (
    id integer NOT NULL,
    code integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.doc_type OWNER TO postgres;

--
-- Name: doc_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.doc_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.doc_type_id_seq OWNER TO postgres;

--
-- Name: doc_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.doc_type_id_seq OWNED BY public.doc_type.id;


--
-- Name: employee; Type: TABLE; Schema: public; Owner: spring_user
--

CREATE TABLE public.employee (
    id integer NOT NULL,
    first_name character varying NOT NULL,
    second_name character varying,
    middle_name character varying,
    "position" character varying NOT NULL,
    phone character varying,
    doc integer,
    office_id integer NOT NULL
);


ALTER TABLE public.employee OWNER TO spring_user;

--
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public; Owner: spring_user
--

CREATE SEQUENCE public.employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employee_id_seq OWNER TO spring_user;

--
-- Name: employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: spring_user
--

ALTER SEQUENCE public.employee_id_seq OWNED BY public.employee.id;


--
-- Name: office; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.office (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    address character varying(50) NOT NULL,
    phone character varying(15),
    is_active boolean,
    org_id integer NOT NULL,
    version integer
);


ALTER TABLE public.office OWNER TO postgres;

--
-- Name: office_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.office_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.office_id_seq OWNER TO postgres;

--
-- Name: office_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.office_id_seq OWNED BY public.office.id;


--
-- Name: organization; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organization (
    id bigint NOT NULL,
    name character varying NOT NULL,
    full_name character varying NOT NULL,
    address character varying NOT NULL,
    phone character varying,
    is_active boolean,
    version integer,
    inn character varying,
    kpp character varying
);


ALTER TABLE public.organization OWNER TO postgres;

--
-- Name: organization_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.organization_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organization_id_seq OWNER TO postgres;

--
-- Name: organization_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.organization_id_seq OWNED BY public.organization.id;


--
-- Name: thesaurus id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);


--
-- Name: doc id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doc ALTER COLUMN id SET DEFAULT nextval('public.doc_id_seq'::regclass);


--
-- Name: doc_type id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doc_type ALTER COLUMN id SET DEFAULT nextval('public.doc_type_id_seq'::regclass);


--
-- Name: employee id; Type: DEFAULT; Schema: public; Owner: spring_user
--

ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq'::regclass);


--
-- Name: office id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.office ALTER COLUMN id SET DEFAULT nextval('public.office_id_seq'::regclass);


--
-- Name: organization id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization ALTER COLUMN id SET DEFAULT nextval('public.organization_id_seq'::regclass);


--
-- Data for Name: thesaurus; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.country (id, name, code) FROM stdin;
\.


--
-- Name: country_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.country_id_seq', 1, false);


--
-- Data for Name: doc; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.doc (id, name, doc_type, date, country_id, is_identified) FROM stdin;
\.


--
-- Name: doc_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.doc_id_seq', 1, false);


--
-- Data for Name: doc_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.doc_type (id, code, name) FROM stdin;
\.


--
-- Name: doc_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.doc_type_id_seq', 1, false);


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: spring_user
--

COPY public.employee (id, first_name, second_name, middle_name, "position", phone, doc, office_id) FROM stdin;
\.


--
-- Name: employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: spring_user
--

SELECT pg_catalog.setval('public.employee_id_seq', 1, false);


--
-- Data for Name: office; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.office (id, name, address, phone, is_active, org_id, version) FROM stdin;
6	ALPHA office_1	*г. Синий д 25	8651223136	t	2	0
8	ALPHA office_3	г. Зеленый д 25	8651223128	f	2	0
7	ALPHA office_2	г. Зеленый д 24	8651223127	f	2	0
5	OMEGA office_1	г. Синий д 22	8651223125	t	1	1
16	ALPHA1 office_4	г. Красный д 27	8351223129	f	3	2
\.


--
-- Name: office_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.office_id_seq', 18, true);


--
-- Data for Name: organization; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.organization (id, name, full_name, address, phone, is_active, version, inn, kpp) FROM stdin;
11	HETA3	HETA3 org.	гор. Город, проспект Проспектов, д8	+8885223354	f	0	12322421	93521574
2	ALPHA	ALPHA org.	гор. Город, проспект Уличный, д221 	\N	f	0	128767421	987842574
1	OMEGA	OMEGA org.	гор. Город, проспект Уличный, д21 	\N	f	0	123541221	9845122214
4	BETA	BETA org.	гор. Город, проспект Проспектов, д3	\N	t	0	145112421	9856321574
6	PETA2	PETA2 org.	гор. Город, проспект Проспектов, д5	\N	t	0	1654522421	94223521574
5	BETA1	BETA1 org.	гор. Город, проспект Проспектов, д4	\N	t	0	123112421	923321574
8	PEGA3	PEGA3 org.	гор. Город, проспект Проспектов, д6	\N	f	0	11211322421	94115521574
7	PETA2	PETA2 org.	гор. Город, проспект Проспектов, д5	\N	t	0	1654522421	94223521574
9	PETA3	PETA3 org.	гор. Город, проспект Проспектов, д7	\N	f	0	1111322421	9115521574
12	HETA4	HETA4 org.	гор. Город, проспект Проспектов, д9	+89985223354	f	0	1244444444	8888888888
10	PETA3	PETA3 org.	гор. Город, проспект Проспектов, д7	+7885223354	\N	0	1221322481	9115521574
3	ALPHA1	ALPHA1 org.	гор. Город, проспект Уличный, д231 	\N	t	1	128767421	987842574
\.


--
-- Name: organization_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organization_id_seq', 28, true);


--
-- Name: thesaurus country_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pk PRIMARY KEY (id);


--
-- Name: doc doc_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doc
    ADD CONSTRAINT doc_pk PRIMARY KEY (id);


--
-- Name: doc_type doc_type_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doc_type
    ADD CONSTRAINT doc_type_pk PRIMARY KEY (id);


--
-- Name: employee employee_pk; Type: CONSTRAINT; Schema: public; Owner: spring_user
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pk PRIMARY KEY (id);


--
-- Name: office office_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.office
    ADD CONSTRAINT office_pk PRIMARY KEY (id);


--
-- Name: organization organization_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pk PRIMARY KEY (id);


--
-- Name: doc doc_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doc
    ADD CONSTRAINT doc_fk0 FOREIGN KEY (doc_type) REFERENCES public.doc_type(id);


--
-- Name: doc doc_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doc
    ADD CONSTRAINT doc_fk1 FOREIGN KEY (country_id) REFERENCES public.country(id);


--
-- Name: employee employee_fk0; Type: FK CONSTRAINT; Schema: public; Owner: spring_user
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_fk0 FOREIGN KEY (doc) REFERENCES public.doc(id);


--
-- Name: employee employee_fk1; Type: FK CONSTRAINT; Schema: public; Owner: spring_user
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_fk1 FOREIGN KEY (office_id) REFERENCES public.office(id);


--
-- Name: office office_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.office
    ADD CONSTRAINT office_fk0 FOREIGN KEY (org_id) REFERENCES public.organization(id);


--
-- Name: SEQUENCE country_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.country_id_seq TO spring_user WITH GRANT OPTION;


--
-- Name: SEQUENCE doc_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.doc_id_seq TO spring_user WITH GRANT OPTION;


--
-- Name: SEQUENCE doc_type_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.doc_type_id_seq TO spring_user WITH GRANT OPTION;


--
-- Name: TABLE employee; Type: ACL; Schema: public; Owner: spring_user
--

REVOKE ALL ON TABLE public.employee FROM spring_user;
GRANT ALL ON TABLE public.employee TO spring_user WITH GRANT OPTION;


--
-- Name: SEQUENCE employee_id_seq; Type: ACL; Schema: public; Owner: spring_user
--

REVOKE ALL ON SEQUENCE public.employee_id_seq FROM spring_user;
GRANT ALL ON SEQUENCE public.employee_id_seq TO spring_user WITH GRANT OPTION;


--
-- Name: TABLE office; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.office TO spring_user WITH GRANT OPTION;


--
-- Name: SEQUENCE office_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.office_id_seq TO spring_user WITH GRANT OPTION;


--
-- Name: TABLE organization; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE public.organization FROM postgres;
GRANT ALL ON TABLE public.organization TO spring_user WITH GRANT OPTION;
GRANT ALL ON TABLE public.organization TO postgres WITH GRANT OPTION;


--
-- Name: SEQUENCE organization_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.organization_id_seq TO spring_user WITH GRANT OPTION;


--
-- PostgreSQL database dump complete
--


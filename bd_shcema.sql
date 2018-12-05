--
-- Описание таблицы 'страна'
-- Name: country; Type: TABLE; Schema: public;
--
CREATE TABLE public.country (
    id integer NOT NULL,
    name character varying NOT NULL,
    code integer NOT NULL
);

--
-- Name: country_id_seq; Type: SEQUENCE; Schema: public;
-- auto_increment(AI) для поля id таблицы country
--
CREATE SEQUENCE public.country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;

--
-- описание таблицы 'документ' 
-- Name: doc; Type: TABLE; Schema: public;
--
CREATE TABLE public.doc (
    id integer NOT NULL,
    name character varying,
    doc_type integer,
    date date,
    country_id integer,
    is_identified boolean
);


--
-- Name: doc_id_seq; Type: SEQUENCE; Schema: public;
-- auto_increment(AI) для поля id таблицы doc
--
CREATE SEQUENCE public.doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.doc_id_seq OWNED BY public.doc.id;


--
-- описание таблицы 'тип документа'
-- Name: doc_type; Type: TABLE; Schema: public;
--
CREATE TABLE public.doc_type (
    id integer NOT NULL,
    code integer NOT NULL,
    name character varying NOT NULL
);


--
-- Name: doc_type_id_seq; Type: SEQUENCE; Schema: public;
-- auto_increment(AI) для поля id таблицы doc_type
--
CREATE SEQUENCE public.doc_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.doc_type_id_seq OWNED BY public.doc_type.id;


--
-- описание таблицы 'пользователь(user)'
-- Name: employee; Type: TABLE; Schema: public;
--
CREATE TABLE public.employee (
    id integer NOT NULL,
    first_name character varying NOT NULL,
    second_name character varying,
    middle_name character varying,
    position character varying NOT NULL,
    phone character varying,
    doc integer,
    office_id integer NOT NULL
);

--
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public;
-- auto_increment(AI) для поля id таблицы employee
--
CREATE SEQUENCE public.employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.employee_id_seq OWNED BY public.employee.id;


--
-- описание таблицы 'офис' 
-- Name: office; Type: TABLE; Schema: public;
--
CREATE TABLE public.office (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    address character varying(50) NOT NULL,
    phone character varying(15),
    is_active boolean,
    org_id integer NOT NULL
);

--
-- Name: office_id_seq; Type: SEQUENCE; Schema: public;
-- auto_increment(AI) для поля id таблицы office
--
CREATE SEQUENCE public.office_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.office_id_seq OWNED BY public.office.id;

--
-- описание таблицы 'организация'
-- Name: organization; Type: TABLE; Schema: public;
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

--
-- Name: organization_id_seq; Type: SEQUENCE; Schema: public;
-- auto_increment(AI) для поля id таблицы organization
--
CREATE SEQUENCE public.organization_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.organization_id_seq OWNED BY public.organization.id;
ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);
ALTER TABLE ONLY public.doc ALTER COLUMN id SET DEFAULT nextval('public.doc_id_seq'::regclass);
ALTER TABLE ONLY public.doc_type ALTER COLUMN id SET DEFAULT nextval('public.doc_type_id_seq'::regclass);
ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq'::regclass);
ALTER TABLE ONLY public.office ALTER COLUMN id SET DEFAULT nextval('public.office_id_seq'::regclass);
ALTER TABLE ONLY public.organization ALTER COLUMN id SET DEFAULT nextval('public.organization_id_seq'::regclass);

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.doc
    ADD CONSTRAINT doc_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.doc_type
    ADD CONSTRAINT doc_type_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.office
    ADD CONSTRAINT office_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.doc
    ADD CONSTRAINT doc_fk0 FOREIGN KEY (doc_type) REFERENCES public.doc_type(id);

ALTER TABLE ONLY public.doc
    ADD CONSTRAINT doc_fk1 FOREIGN KEY (country_id) REFERENCES public.country(id);

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_fk0 FOREIGN KEY (doc) REFERENCES public.doc(id);

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_fk1 FOREIGN KEY (office_id) REFERENCES public.office(id);

ALTER TABLE ONLY public.office
    ADD CONSTRAINT office_fk0 FOREIGN KEY (org_id) REFERENCES public.organization(id);

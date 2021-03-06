--
-- Описание таблицы 'страна'
-- Name: thesaurus; Type: TABLE; Schema: public;
--
CREATE TABLE public.country (
    id bigint NOT NULL,                     --уникальный идентификатор таблицы
    name character varying(50) NOT NULL,    --наименование страны
    code character varying(5) NOT NULL,     -- код страны
    version integer
);

--
-- Name: country_id_seq; Type: SEQUENCE; Schema: public;
-- auto_increment(AI) для поля id таблицы thesaurus
--
CREATE SEQUENCE public.country_id_seq
    START WITH 1        --начинать с 1
    INCREMENT BY 1      --увеличивать по 1
    NO MINVALUE         --без исходного значения
    NO MAXVALUE         --без ограничения значения
    CACHE 1;

ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;

--
-- описание таблицы 'документ' 
-- Name: doc; Type: TABLE; Schema: public;
--
CREATE TABLE public.doc (
    id bigint NOT NULL,     --уникальный идентификатор таблицы
    doc_type integer,       --тип документа -> внешний ключ(doc_type)
    date date,              --дата выдачи документа
    is_identified boolean,  --подтверждение документа
    version integer
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
    id bigint NOT NULL,                 --уникальный идентификатор таблицы
    code character varying(5) NOT NULL, --код документа
    name character varying(50) NOT NULL,--наименование документа
    version integer
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
    id bigint NOT NULL, --уникальный идентификатор таблицы
    first_name character varying(50) NOT NULL,   --имя пользователя
    second_name character varying(50),           --фамилия пользователя
    middle_name character varying(50),           --отчество польователя
    position character varying(20) NOT NULL,     --должность пользователя
    phone character varying(15),                 --телефон пользователя
    country_id bigint,                           --гражданство -> внешний ключ(thesaurus)
    doc_id bigint,                               --документ пользователя -> внешний ключ
    office_id bigint NOT NULL,                   --место работы пользователя -> внешний ключ
    version integer
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
    id bigint NOT NULL, --уникальный идентификатор таблицы
    name character varying(50) NOT NULL,    --наименование офиса
    address character varying(50) NOT NULL, --адрес офиса
    phone character varying(15),            --телефон офиса
    is_active boolean,                      --активность офиса
    org_id bigint NOT NULL,                --организация-владелец офиса -> внешний ключ(organization)
    version integer
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
    id bigint NOT NULL, --уникальный идентификатор таблицы
    name character varying(50) NOT NULL,        --наименование организации
    full_name character varying(50) NOT NULL,   --полное наименование организации
    address character varying(50) NOT NULL,     --адрес организации
    phone character varying(15),                --телефон организации
    is_active boolean,                          --активность организации
    inn character varying(25),                  --инн организации
    kpp character varying(25),                  --кпп организации
    version integer
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

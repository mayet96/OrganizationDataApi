CREATE TABLE "employee" (
	"id" serial NOT NULL,
	"first_name" varchar NOT NULL,
	"second_name" varchar,
	"middle_name" varchar,
	"position" varchar NOT NULL,
	"phone" varchar,
	"doc" integer,
	"office_id" integer NOT NULL,
	CONSTRAINT employee_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "organization" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"full_name" varchar NOT NULL,
	"inn" integer NOT NULL,
	"kpp" integer NOT NULL,
	"address" varchar NOT NULL,
	"phone" varchar,
	"is_active" BOOLEAN,
	CONSTRAINT organization_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "doc" (
	"id" serial NOT NULL,
	"name" varchar,
	"doc_type" integer,
	"date" DATE,
	"country_id" integer,
	"is_Identified" BOOLEAN,
	CONSTRAINT doc_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "office" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"address" varchar NOT NULL,
	"phone" varchar,
	"is_active" BOOLEAN,
	"ogr_id" integer NOT NULL,
	CONSTRAINT office_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "doc_type" (
	"id" serial NOT NULL,
	"code" integer NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT doc_type_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "country" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"code" integer NOT NULL,
	CONSTRAINT country_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "employee" ADD CONSTRAINT "employee_fk0" FOREIGN KEY ("doc") REFERENCES "doc"("id");
ALTER TABLE "employee" ADD CONSTRAINT "employee_fk1" FOREIGN KEY ("office_id") REFERENCES "office"("id");


ALTER TABLE "doc" ADD CONSTRAINT "doc_fk0" FOREIGN KEY ("doc_type") REFERENCES "doc_type"("id");
ALTER TABLE "doc" ADD CONSTRAINT "doc_fk1" FOREIGN KEY ("country_id") REFERENCES "country"("id");

ALTER TABLE "office" ADD CONSTRAINT "office_fk0" FOREIGN KEY ("ogr_id") REFERENCES "organization"("id");




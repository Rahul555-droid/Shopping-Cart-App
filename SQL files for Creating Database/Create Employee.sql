
-- SEQUENCE: public.employee_employeeID_seq

-- DROP SEQUENCE public."employee_employeeID_seq";

CREATE SEQUENCE public."employee_employeeID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public."employee_employeeID_seq"
    OWNER TO postgres;

-- Table: public.employees

-- DROP TABLE public.employees;

CREATE TABLE public.employees
(
	"employeeID" integer NOT NULL DEFAULT nextval('"employee_employeeID_seq"'::regclass),
    userid integer NOT NULL,
    first_name character varying(20) COLLATE pg_catalog."default",
    last_name character varying(20) COLLATE pg_catalog."default",
    username character varying(20) COLLATE pg_catalog."default",
    email character varying(30) COLLATE pg_catalog."default",
    employee_position character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT employee_pkey PRIMARY KEY ("employeeID"),
    CONSTRAINT employee_userid_fkey FOREIGN KEY (userid)
        REFERENCES public.users ("userID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.employees
    OWNER to postgres;
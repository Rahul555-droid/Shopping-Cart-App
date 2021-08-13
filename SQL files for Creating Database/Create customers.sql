-- SEQUENCE: public.customer_customerID_seq

-- DROP SEQUENCE public."customer_customerID_seq";

CREATE SEQUENCE public."customer_customerID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public."customer_customerID_seq"
    OWNER TO postgres;

-- Table: public.customers

-- DROP TABLE public.customers;

CREATE TABLE public.customers
(
   	"customerID" integer NOT NULL DEFAULT nextval('"customer_customerID_seq"'::regclass),
	userid integer NOT NULL,
    first_name character varying(20) COLLATE pg_catalog."default",
    last_name character varying(20) COLLATE pg_catalog."default",
    username character varying(20) COLLATE pg_catalog."default",
    email character varying(30) COLLATE pg_catalog."default",
    address character varying(200) COLLATE pg_catalog."default",
   
    CONSTRAINT customer_pkey PRIMARY KEY ("customerID"),
    CONSTRAINT customer_userid_fkey FOREIGN KEY (userid)
        REFERENCES public.users ("userID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.customers
    OWNER to postgres;
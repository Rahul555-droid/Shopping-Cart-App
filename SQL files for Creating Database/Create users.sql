
-- SEQUENCE: public.users_userID_seq

-- DROP SEQUENCE public."users_userID_seq";

CREATE SEQUENCE public."users_userID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public."users_userID_seq"
    OWNER TO postgres;
	
-- Table: public.users

-- DROP TABLE public.users;



CREATE TABLE public.users
(
    "userID" integer NOT NULL DEFAULT nextval('"users_userID_seq"'::regclass),
    username character varying COLLATE pg_catalog."default",
    password character varying COLLATE pg_catalog."default",
    user_type character varying COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY ("userID")
)

TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;
	
	
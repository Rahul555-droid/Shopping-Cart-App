-- SEQUENCE: public.cart_cartID_seq

-- DROP SEQUENCE public."cart_cartID_seq";

CREATE SEQUENCE public."cart_cartID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public."cart_cartID_seq"
    OWNER TO postgres;

-- Table: public.cart

-- DROP TABLE public.cart;

CREATE TABLE public.cart
(
    "cartID" integer NOT NULL DEFAULT nextval('"cart_cartID_seq"'::regclass),
    "Username" character varying COLLATE pg_catalog."default",
    "TotalQuantity" integer,
    "TotalPrice" integer,
    items character varying COLLATE pg_catalog."default",
    "customerID" integer NOT NULL,
    CONSTRAINT cart_pkey PRIMARY KEY ("cartID"),
    CONSTRAINT fk_customerid FOREIGN KEY ("customerID")
        REFERENCES public.users ("userID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.cart
    OWNER to postgres;
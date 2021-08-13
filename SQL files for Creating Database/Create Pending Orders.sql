-- SEQUENCE: public.PendingOrders_pendingOrderID_seq

-- DROP SEQUENCE public."PendingOrders_pendingOrderID_seq";

CREATE SEQUENCE public."PendingOrders_pendingOrderID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public."PendingOrders_pendingOrderID_seq"
    OWNER TO postgres;

-- Table: public.PendingOrders

-- DROP TABLE public."PendingOrders";

CREATE TABLE public."PendingOrders"
(
    "pendingOrderID" integer NOT NULL DEFAULT nextval('"PendingOrders_pendingOrderID_seq"'::regclass),
    first_name character varying COLLATE pg_catalog."default",
    last_name character varying COLLATE pg_catalog."default",
    address character varying COLLATE pg_catalog."default",
    username character varying COLLATE pg_catalog."default",
    items character varying COLLATE pg_catalog."default",
    "totalQuantity" integer,
    "totalPrice" integer,
    "customerID" integer NOT NULL,
    CONSTRAINT "PendingOrders_pkey" PRIMARY KEY ("pendingOrderID"),
    CONSTRAINT fk_customerid FOREIGN KEY ("customerID")
        REFERENCES public.users ("userID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public."PendingOrders"
    OWNER to postgres;
	
--to enter some values to the table for testing
/*
insert into "PendingOrders" values(1,"a1","b1","address1","u1","some items",10,100,3);
insert into "PendingOrders" values(2,"a2","b2","address2","u2","some items",10,100,4);
insert into "PendingOrders" values(3,"a3","b3","address3","u3","some items",10,100,5);

*/
--\c tribike;

-- Table: public.bicicleta

-- DROP TABLE IF EXISTS public.bicicleta;

CREATE TABLE IF NOT EXISTS public.bicicleta
(
    id bigint NOT NULL DEFAULT nextval('bicicleta_id_seq'::regclass),
    acessorios character varying(255) COLLATE pg_catalog."default",
    desconto character varying(255) COLLATE pg_catalog."default",
    descricao character varying(255) COLLATE pg_catalog."default",
    marca character varying(255) COLLATE pg_catalog."default",
    modelo character varying(255) COLLATE pg_catalog."default",
    peso real,
    preco real,
    url_foto character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT bicicleta_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.bicicleta
    OWNER to postgres;
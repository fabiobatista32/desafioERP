-- DROP DATABASE desafio;

CREATE DATABASE desafio
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'pt_BR.UTF-8'
       LC_CTYPE = 'pt_BR.UTF-8'
       CONNECTION LIMIT = -1;


-- Table: public.item

-- DROP TABLE public.item;

CREATE TABLE public.item
(
  id uuid NOT NULL,
  ativo boolean,
  codigo character varying(255) NOT NULL,
  descricao character varying(255),
  tipo character varying(255),
  titulo character varying(255) NOT NULL,
  valor_unitario numeric(19,2) NOT NULL,
  CONSTRAINT item_pkey PRIMARY KEY (id),
  CONSTRAINT uk_s2r0cpmnhf38jgrg18t3r35ma UNIQUE (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.item
  OWNER TO postgres;


-- Table: public.pedido

-- DROP TABLE public.pedido;

CREATE TABLE public.pedido
(
  id uuid NOT NULL,
  data date,
  hora time without time zone,
  observacao character varying(255),
  situacao character varying(255),
  valor_desconto numeric(19,2),
  valor_total numeric(19,2),
  CONSTRAINT pedido_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.pedido
  OWNER TO postgres;


-- Table: public.item_pedido

-- DROP TABLE public.item_pedido;

CREATE TABLE public.item_pedido
(
  id uuid NOT NULL,
  quantidade integer,
  valor_unitario numeric(19,2),
  id_item uuid,
  id_pedido uuid,
  CONSTRAINT item_pedido_pkey PRIMARY KEY (id),
  CONSTRAINT fk9x5qp8vj0u3wg9itk6djj6w9p FOREIGN KEY (id_item)
      REFERENCES public.item (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fknjghutiejefh2auj9bnpf9sp7 FOREIGN KEY (id_pedido)
      REFERENCES public.pedido (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.item_pedido
  OWNER TO postgres;




CREATE TABLE public.sku (
	id bigserial NOT NULL,
	price int8 NOT NULL,
	CONSTRAINT sku_pk PRIMARY KEY (id)
);

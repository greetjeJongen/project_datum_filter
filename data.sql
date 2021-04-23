CREATE TABLE grjon.agenda
(
    id integer NOT NULL DEFAULT nextval('grjon.agenda_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default",
    date timestamp without time zone,
    CONSTRAINT agenda_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE grjon.agenda
    OWNER to u0015529;

GRANT ALL ON TABLE grjon.agenda TO local_u0015529;

GRANT ALL ON TABLE grjon.agenda TO u0015529;
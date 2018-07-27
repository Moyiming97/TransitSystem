--
-- PostgreSQL database dump
--

-- Dumped from database version 10.4
-- Dumped by pg_dump version 10.4

-- Started on 2018-07-27 18:32:56 EDT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_with_oids = false;

--
-- TOC entry 200 (class 1259 OID 16403)
-- Name: card; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.card (
    cid integer NOT NULL,
    uid integer,
    isactive boolean,
    balance double precision
);


--
-- TOC entry 199 (class 1259 OID 16401)
-- Name: card_cid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.card_cid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3131 (class 0 OID 0)
-- Dependencies: 199
-- Name: card_cid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.card_cid_seq OWNED BY public.card.cid;


--
-- TOC entry 198 (class 1259 OID 16395)
-- Name: user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."user" (
    uid integer NOT NULL,
    name character varying(50),
    email character varying(50),
    "isAdmin" boolean DEFAULT false,
    password character varying(100)
);


--
-- TOC entry 197 (class 1259 OID 16393)
-- Name: user_uid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.user_uid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3132 (class 0 OID 0)
-- Dependencies: 197
-- Name: user_uid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.user_uid_seq OWNED BY public."user".uid;


--
-- TOC entry 2996 (class 2604 OID 16406)
-- Name: card cid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.card ALTER COLUMN cid SET DEFAULT nextval('public.card_cid_seq'::regclass);


--
-- TOC entry 2994 (class 2604 OID 16398)
-- Name: user uid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user" ALTER COLUMN uid SET DEFAULT nextval('public.user_uid_seq'::regclass);


--
-- TOC entry 3002 (class 2606 OID 16408)
-- Name: card card_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.card
    ADD CONSTRAINT card_pkey PRIMARY KEY (cid);


--
-- TOC entry 2998 (class 2606 OID 16418)
-- Name: user user_email_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_email_key UNIQUE (email);


--
-- TOC entry 3000 (class 2606 OID 16400)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (uid);


--
-- TOC entry 3003 (class 2606 OID 16409)
-- Name: card card_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.card
    ADD CONSTRAINT card_uid_fkey FOREIGN KEY (uid) REFERENCES public."user"(uid);


-- Completed on 2018-07-27 18:32:57 EDT

--
-- PostgreSQL database dump complete
--


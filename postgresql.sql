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

CREATE SCHEMA IF NOT EXISTS transit_system;
SET search_path TO transit_system;

--
-- TOC entry 200 (class 1259 OID 16403)
-- Name: card; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE card (
    cid serial PRIMARY KEY,
    uid integer,
    isactive boolean,
    balance double precision
);


--
-- TOC entry 198 (class 1259 OID 16395)
-- Name: user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "user" (
    uid serial PRIMARY KEY,
    name character varying(50),
    email character varying(50),
    isAdmin boolean DEFAULT false,
    password character varying(100)
);


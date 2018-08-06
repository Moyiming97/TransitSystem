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

DROP SCHEMA transit_system CASCADE;
CREATE SCHEMA IF NOT EXISTS transit_system;
SET search_path TO transit_system;

--
-- TOC entry 198 (class 1259 OID 16395)
-- Name: user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "user" (
    uid serial PRIMARY KEY,
    name character varying(50),
    email character varying(50),
    isAdmin boolean DEFAULT false,
    password character varying(100),
    createAt bigint,
    city character varying(50),
    birthday bigint
);

--
-- TOC entry 200 (class 1259 OID 16403)
-- Name: card; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE card (
    cid serial PRIMARY KEY,
    uid integer REFERENCES "user" (uid),
    isactive boolean DEFAULT true,
    deleted boolean DEFAULT false,
    balance double precision,
    createAt bigint
);

CREATE UNIQUE INDEX unique_user_email ON "user" (email);

CREATE TABLE node  (
    nid serial PRIMARY KEY,
    name character varying(50),
    transit_type int
);

CREATE TABLE edge  (
    eid serial PRIMARY KEY,
    "start" integer REFERENCES "node" (nid),
    "stop" integer REFERENCES "node" (nid),
     edge character varying(50),
    distance double precision,
    duration int
);

CREATE TABLE generaltrip  (
    gtid serial PRIMARY KEY,
    uid integer REFERENCES "user" (uid),
--    "start" integer REFERENCES "node" (nid),
--    "stop" integer REFERENCES "node" (nid),
--    "start" character varying(50),
--    "stop" character varying(50),
--    createAt bigint,
    startTime character varying(50),
 --   endTime character varying(50),
--    totalFare double precision,
    finished boolean DEFAULT false
 --   cid integer REFERENCES "card" (cid)

);

--CREATE TABLE tripsegment  (
 --   tsid serial PRIMARY KEY,
--    uid integer REFERENCES "user" (uid),
 --   gtid integer REFERENCES "generaltrip" (gtid),
 --   createAt bigint,
 --   startTime character varying(50),
 --   endTime character varying(50),
 --   "start" integer REFERENCES "node" (nid),
 --   "stop" integer REFERENCES "node" (nid),
 --   type character varying(50),
 --   duration int,
--    fare double precision
--);

CREATE TABLE tripsegment  (
    tsid serial PRIMARY KEY,
    uid integer REFERENCES "user" (uid),
    gtid integer REFERENCES "generaltrip" (gtid),
    cid integer REFERENCES "card" (cid),
 --   createAt bigint,
    startTime character varying(50),
    endTime character varying(50),
    "start" character varying(50),
    "stop" character varying(50),
    transit_line character varying(50),
    fare double precision,
    cap double precision
);

CREATE TABLE Station  (
    sid serial PRIMARY KEY,
    point int,
    next_distance double precision,
    stationname character varying(50),
    line character varying(50)

);


--
-- Data for Name: node; Type: TABLE DATA; Schema: transit_system; Owner: GGG
--

--COPY transit_system.node (nid, name, transit, edge) FROM stdin;
--1	a	\N \N
--2	b	\N \N
--3	c	\N \N
--4	d	\N \N
--5	e	\N \N
--\.




--COPY transit_system.edge (eid, start, stop, type, distance, duration) FROM stdin;
--1	1	2	\N	3	\N
--2	2	3	\N	5	\N
--3	2	4	\N	6	\N
--4	3	5	\N	8	\N
--5	4	5	\N	8	\N
--6	2	5	\N	2	\N
--\.





--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.3.1
-- Started on 2014-03-28 15:00:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE airkey;
--
-- TOC entry 1943 (class 1262 OID 145273)
-- Name: airkey; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE airkey WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE airkey OWNER TO postgres;

\connect airkey

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 1944 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 172 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1946 (class 0 OID 0)
-- Dependencies: 172
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 171 (class 1259 OID 145328)
-- Name: user_reset_pass; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE user_reset_pass (
    id integer NOT NULL,
    uid integer,
    unique_key character varying
);


ALTER TABLE public.user_reset_pass OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 145326)
-- Name: user_reset_pass_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_reset_pass_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_reset_pass_id_seq OWNER TO postgres;

--
-- TOC entry 1947 (class 0 OID 0)
-- Dependencies: 170
-- Name: user_reset_pass_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_reset_pass_id_seq OWNED BY user_reset_pass.id;


--
-- TOC entry 169 (class 1259 OID 145276)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    id integer NOT NULL,
    name character varying,
    pass character varying
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 145274)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 1948 (class 0 OID 0)
-- Dependencies: 168
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 1819 (class 2604 OID 145331)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_reset_pass ALTER COLUMN id SET DEFAULT nextval('user_reset_pass_id_seq'::regclass);


--
-- TOC entry 1818 (class 2604 OID 145279)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- TOC entry 1938 (class 0 OID 145328)
-- Dependencies: 171
-- Data for Name: user_reset_pass; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_reset_pass (id, uid, unique_key) VALUES (39, 1, '3Et4VNTfHPR4tTo28B7xu765CRQrGY9s476VnLRcM=');
INSERT INTO user_reset_pass (id, uid, unique_key) VALUES (40, 13, '0I+Mxc+QCb9ZREjenCb7atJhV7LavLiSWqqZe9DDQGM=');
INSERT INTO user_reset_pass (id, uid, unique_key) VALUES (41, 14, 'BmIMi4D+FcI6nm7zUWbX7MeCABQiFjjEdf8KDYmws=');
INSERT INTO user_reset_pass (id, uid, unique_key) VALUES (42, 14, '8qgDafoKp5QqwvUIgX4Wyv0hKdKp2fv2zxu6TWOCFg=');
INSERT INTO user_reset_pass (id, uid, unique_key) VALUES (43, 13, 'XLZTRZC25PR09dj7HuY3xsAg2RkhOVr8ksHgRugbPLE=');
INSERT INTO user_reset_pass (id, uid, unique_key) VALUES (44, 13, '3tGM0F9UsgdQG07hWXB3GbSAmIRTDszQsQYUAY8voy8=');
INSERT INTO user_reset_pass (id, uid, unique_key) VALUES (45, 13, 'UfDCeeQywtrnV89SBDMqaEuHERQdNbkU7q7BZIElU=');


--
-- TOC entry 1949 (class 0 OID 0)
-- Dependencies: 170
-- Name: user_reset_pass_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_reset_pass_id_seq', 45, true);


--
-- TOC entry 1936 (class 0 OID 145276)
-- Dependencies: 169
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO users (id, name, pass) VALUES (1, 'user1', '123');
INSERT INTO users (id, name, pass) VALUES (13, 'user2', '123');
INSERT INTO users (id, name, pass) VALUES (14, 'user3', '123');


--
-- TOC entry 1950 (class 0 OID 0)
-- Dependencies: 168
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_id_seq', 14, true);


--
-- TOC entry 1825 (class 2606 OID 145343)
-- Name: pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_reset_pass
    ADD CONSTRAINT pkey PRIMARY KEY (id);


--
-- TOC entry 1821 (class 2606 OID 145345)
-- Name: upkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT upkey PRIMARY KEY (id);


--
-- TOC entry 1823 (class 2606 OID 145325)
-- Name: user_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT user_id UNIQUE (id);


--
-- TOC entry 1827 (class 2606 OID 145336)
-- Name: user_reset_pass_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_reset_pass
    ADD CONSTRAINT user_reset_pass_id_key UNIQUE (id);


--
-- TOC entry 1828 (class 2606 OID 145337)
-- Name: user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_reset_pass
    ADD CONSTRAINT user_id FOREIGN KEY (uid) REFERENCES users(id);


--
-- TOC entry 1945 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-03-28 15:00:23

--
-- PostgreSQL database dump complete
--


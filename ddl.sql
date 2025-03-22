SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', 'public', false); -- Defina o esquema como 'public' aqui
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER SCHEMA PUBLIC OWNER TO fmass;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE EXTENSION IF NOT EXISTS pg_trgm;

-- Defina o esquema 'public' novamente antes de criar o índice
SELECT pg_catalog.set_config('search_path', 'public', false);


CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_order ON public.order_tb (status, total_amount, order_id);
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_product ON public.product_tb (price, quantity, product_id, "name");
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_order_product ON public.order_product (order_id, product_id);

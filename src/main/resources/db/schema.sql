-- DROP TABLE IF EXISTS students CASCADE;
-- DROP TABLE IF EXISTS meetings CASCADE;
-- DROP TABLE IF EXISTS students_meetings CASCADE;

CREATE TABLE IF NOT EXISTS public.students
(
    id           SERIAL PRIMARY KEY,
    first_name   VARCHAR(30)        NOT NULL,
    last_name    VARCHAR(30)        NOT NULL,
    email        VARCHAR UNIQUE,
    password     VARCHAR(30)        NOT NULL,
    semester     INT                NOT NULL,
    study_course VARCHAR            NOT NULL,
    index        VARCHAR(10) UNIQUE NOT NULL,
    role         VARCHAR(15) DEFAULT 'ROLE_STUDENT'
);
CREATE TABLE IF NOT EXISTS public.meetings
(
    id            SERIAL PRIMARY KEY,
    title         VARCHAR NOT NULL,
    description   VARCHAR,
    lecturer_info VARCHAR NOT NULL,
    date          TIMESTAMP DEFAULT now()
);
CREATE TABLE IF NOT EXISTS public.students_meetings
(
    id      SERIAL PRIMARY KEY,
    m_id    INT NOT NULL,
    s_id    INT NOT NULL,
    present BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (m_id) REFERENCES meetings (id),
    FOREIGN KEY (s_id) REFERENCES students (id),
    UNIQUE (m_id, s_id)
);

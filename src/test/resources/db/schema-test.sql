-- DROP TABLE IF EXISTS students CASCADE;
-- DROP TABLE IF EXISTS meetings CASCADE;
-- DROP TABLE IF EXISTS students_meetings CASCADE;

CREATE TABLE IF NOT EXISTS public.students
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    first_name   VARCHAR(30)        NOT NULL,
    last_name    VARCHAR(30)        NOT NULL,
    email        VARCHAR UNIQUE,
    password     VARCHAR            NOT NULL,
    semester     INT                NOT NULL,
    study_course VARCHAR            NOT NULL,
    index        VARCHAR(10) UNIQUE NOT NULL,
    role         VARCHAR(15) DEFAULT 'ROLE_STUDENT'
);
CREATE TABLE IF NOT EXISTS public.meetings
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR NOT NULL,
    description   VARCHAR,
    lecturer_info VARCHAR NOT NULL,
    date          TIMESTAMP DEFAULT now()
);
CREATE TABLE IF NOT EXISTS public.students_meetings
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    m_id    INT NOT NULL,
    s_id    INT NOT NULL,
    present BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (m_id) REFERENCES meetings (id) ON DELETE CASCADE,
    FOREIGN KEY (s_id) REFERENCES students (id) ON DELETE CASCADE,
    UNIQUE (m_id, s_id)
);

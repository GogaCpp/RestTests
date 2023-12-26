CREATE SCHEMA IF NOT EXISTS allData;

CREATE TABLE IF NOT EXISTS allData.user (
    id BIGSERIAL PRIMARY KEY,
    username TEXT,
    password TEXT,
    role TEXT
);
CREATE TABLE IF NOT EXISTS allData.result(
    id BIGSERIAL PRIMARY KEY,
    type TEXT,
    first TEXT,
    second TEXT,
    time timestamp without time zone
)
FROM mysql:8.0

# Import the supplied database on first startup.
COPY world.sql /docker-entrypoint-initdb.d/world.sql

# Local coursework credentials from Lab 03a.
ENV MYSQL_ROOT_PASSWORD=example
ENV MYSQL_ROOT_HOST=%

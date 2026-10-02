# SET08103-GeoMatrics

Master Build Status [![build](https://github.com/KMilej/SET08103-GeoMetrics/actions/workflows/main.yml/badge.svg?branch=develop)](https://github.com/KMilej/SET08103-GeoMetrics/actions/workflows/main.yml)

License [![LICENSE](https://img.shields.io/github/license/KMilej/SET08103-GeoMetrics.svg?style=flat-square)](https://github.com/KMilej/SET08103-GeoMetrics/blob/main/LICENSE)

Release [![Releases](https://img.shields.io/github/release/KMilej/SET08103-GeoMetrics/all.svg?style=flat-square)](https://github.com/KMilej/SET08103-GeoMetrics/releases)

Develop Build Status ![develop](https://github.com/KMilej/SET08103-GeoMetrics/actions/workflows/main.yml/badge.svg?branch=develop)


## MySQL world database (Lab 03a and Lab 07)

The supplied `db/world.sql` is imported by the MySQL container on first
initialisation. The application connects to `world` and displays reports 1-32 in a console menu.
Enter a number to select a report, or 0 to exit. Reports are currently placeholders
and print "not implemented yet". Invalid input returns to the menu.

With Docker Desktop running and JDK 23 or newer available to Maven:

```sh
mvn clean package
docker compose up --build -d db
docker compose run --build --rm app
```

The menu repeats after each selection. GitHub Actions sets
`APP_MODE=check` to pass `check` as the third argument, which checks the
country count and exits without reading input. To run the same check locally:

```sh
APP_MODE=check docker compose up --build --abort-on-container-exit --exit-code-from app
```

The expected country count is 239.

To run App from IntelliJ, start just the database:

```sh
docker compose up --build -d db
```

Run `com.napier.sem.App` after the database has initialised. With no arguments it
uses `localhost:33060` and zero delay. Containers use arguments `db:3306 30000`,
following Lab 07; there are up to ten connection attempts.
Local lab credentials: user `root`, password `example`, database `world`.
These credentials and disabled TLS follow the local laboratory examples.

View logs and stop containers:

```sh
docker compose logs
docker compose down
```

SQL scripts run only when MySQL initialises an empty data directory. Editing
`world.sql` does not reimport an existing database. The supplied script drops and
recreates `world`, so use it only for this coursework database.

mvn clean package
docker compose up --build -d db
docker compose run --build --rm app

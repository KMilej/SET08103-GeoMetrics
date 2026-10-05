# GeoMetrics - Java & MySQL Reporting Application

[![Main Build](https://github.com/KMilej/SET08103-GeoMetrics/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/KMilej/SET08103-GeoMetrics/actions/workflows/main.yml?query=branch%3Amain) [![Develop Build](https://github.com/KMilej/SET08103-GeoMetrics/actions/workflows/main.yml/badge.svg?branch=develop)](https://github.com/KMilej/SET08103-GeoMetrics/actions/workflows/main.yml?query=branch%3Adevelop) [![License](https://img.shields.io/github/license/KMilej/SET08103-GeoMetrics.svg?style=flat-square)](https://github.com/KMilej/SET08103-GeoMetrics/blob/main/LICENSE) [![Release](https://img.shields.io/github/release/KMilej/SET08103-GeoMetrics/all.svg?style=flat-square)](https://github.com/KMilej/SET08103-GeoMetrics/releases)

This project provides a Java console application for querying the MySQL world database and generating reports on countries, cities, capital cities, population statistics and languages.

## Requirements:

- JDK 23 (for building the application with Maven)
- Maven
- Docker Desktop installed and running

## Try it yourself using Docker

Run the commands from the project directory `../SET08103-GeoMetrics`. Choose one of the options below.
The menu has six categories. Select a category, then a report using its displayed number.
Enter `0` in a category to go back, or `0` in the main menu to exit.
Reports currently display "not implemented yet" while the database queries are being developed.

### Run with automatic shutdown

Start the database and open the report menu. When the application exits, the containers
are stopped and removed automatically. Build the application first with `mvn clean package`.

*For macOS shells and Windows PowerShell:*

```sh
mvn clean package
docker compose up --build -d db
docker compose run --build --rm app; docker compose down
```

### Build and run with manual shutdown

Build the application, start the database in the background and open the report menu.
The database keeps running after you exit the application until you stop it below.
The application waits about 5 seconds before attempting to connect to the database.

*For macOS and Windows (PowerShell or Command Prompt). Run each command in order, and run the last command after exiting the application:*

```sh
mvn clean package
docker compose up --build -d db
docker compose run --build --rm app
docker compose down
```

*Alternatively, use `docker ps` to find the container name, then run `docker stop <container_name>` to stop it manually. Press `Ctrl+C` to interrupt the application running in the terminal.*

View logs and stop containers:
`View logs before stopping and removing the containers.`

```sh
docker compose logs
docker compose down
```

## Reporting Issues

If you encounter any problems while following this guide, or have any other concerns, please let us know by opening an issue [here](https://github.com/KMilej/SET08103-GeoMetrics/issues) and we will be happy to assist you.

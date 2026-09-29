# SET08103 GeoMetrics

[![GeoMetrics Build](https://github.com/KMilej/SET08103-GeoMetrics/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/KMilej/SET08103-GeoMetrics/actions/workflows/main.yml)
[![License](https://img.shields.io/github/license/KMilej/SET08103-GeoMetrics.svg?style=flat-square)](LICENSE)
[![Release](https://img.shields.io/github/v/release/KMilej/SET08103-GeoMetrics?display_name=tag)](https://github.com/KMilej/SET08103-GeoMetrics/releases)

Minimal Java and MongoDB application for SET08103 Lab 01 and Lab 02.

## Requirements

- Java 21 LTS
- Maven
- Docker

## Build

```bash
mvn clean package
```

The build creates the self-contained executable JAR at `target/geometrics.jar`.

## Run with Docker

```bash
docker network create --driver bridge se-methods
docker run --detach --name mongo-dbserver --network se-methods mongo:8.0
docker build --tag geometrics .
docker run --name geometrics-container --network se-methods geometrics
```

The application inserts one GeoMetrics document into MongoDB, reads it back, prints it, and exits.

Clean up the Lab 02 containers and network with:

```bash
docker rm --force geometrics-container mongo-dbserver
docker network rm se-methods
```

## Continuous Integration

GitHub Actions builds and runs the application on every push and once per day. The same workflow can be started manually from the repository's **Actions** tab by selecting **GeoMetrics Build** and **Run workflow**.

## Git workflow

- `main` contains release-ready code.
- `develop` integrates completed features.
- `feature/*` contains isolated feature work.
- `release` contains the current release candidate.

See [CONTRIBUTING.md](CONTRIBUTING.md) for the contribution process.

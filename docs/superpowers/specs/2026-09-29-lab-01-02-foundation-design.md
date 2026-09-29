# Lab 01–02 Foundation Design

## Purpose

Implement the smallest reliable GeoMetrics foundation that satisfies the practical requirements of Lab 01 and Lab 02. The result must demonstrate a Java application built with Maven, packaging as a self-contained JAR, execution in Docker, basic MongoDB integration, GitHub Actions continuous integration, and the documented Git workflow.

This work is infrastructure preparation rather than implementation of the final GeoMetrics reporting system. MongoDB is a temporary demonstration required by Lab 02 and will not define the later application architecture.

## Scope

The implementation will contain only:

- Java 21 LTS as the compile-time and runtime version;
- a Maven project producing `target/geometrics.jar` with its runtime dependency;
- one `com.napier.geometrics.App` class;
- one Dockerfile that runs the packaged JAR;
- one GitHub Actions workflow;
- a focused `.gitignore`;
- a corrected project README with build, licence, and release badges;
- the existing contribution and conduct documentation;
- Gitflow documentation using `main`, `develop`, `feature/*`, and `release`.

The implementation will not introduce Spring Boot, a REST API, Docker Compose, a Java-version matrix, custom shell orchestration, health endpoints, JUnit tests, domain models, repositories, or any final GeoMetrics reporting features.

## Application

`App` will be a minimal command-line program. It will:

1. connect to MongoDB at `mongo-dbserver` on the default MongoDB port;
2. access the `mydb.test` collection;
3. insert one document identifying the GeoMetrics project;
4. read the first document from the collection;
5. print the document as JSON;
6. close the MongoDB client using try-with-resources.

The application will not add custom exception handling. A connection, write, or read failure will propagate out of `main`, produce a non-zero process exit status, and fail the Docker/CI execution naturally.

## Build and Packaging

The Maven configuration will:

- compile for Java 21;
- use UTF-8 source encoding;
- depend only on the synchronous MongoDB Java driver required by the application;
- create one self-contained executable JAR named `geometrics.jar`;
- declare `com.napier.geometrics.App` as the main class;
- avoid unrelated plugins and dependencies.

Generated output, IDE metadata, logs, and packaged artifacts will be excluded from version control. Existing compiled files or IDE configuration found on other branches will not be copied into the clean implementation.

## Docker Execution

The Dockerfile will use a Java 21 LTS runtime image, copy `target/geometrics.jar`, and run it with `java -jar`. The image and dependency tags will be explicit rather than `latest` where the lab requirements allow it.

Local and CI execution will follow the Lab 02 topology:

1. create the `se-methods` bridge network;
2. run MongoDB as `mongo-dbserver` on that network;
3. build the GeoMetrics image;
4. run the GeoMetrics container on the same network.

No Compose file or additional runtime script will be introduced.

## Continuous Integration

`.github/workflows/main.yml` will support three triggers:

- every push;
- manual execution from the GitHub Actions interface using `workflow_dispatch`;
- one scheduled execution every day at 03:17 in the `Europe/London` timezone.

The workflow will:

1. check out the selected revision;
2. configure Java 21;
3. run `mvn --batch-mode clean package`;
4. verify that `target/geometrics.jar` exists;
5. create the Docker bridge network and start the pinned MongoDB image;
6. build the application image;
7. run the application container and require a successful exit status;
8. display application output;
9. display diagnostic container logs after a failure;
10. remove created containers and the network in an always-running cleanup step.

Successful completion proves that Maven packaging, the Docker image, container networking, the MongoDB connection, and the write/read demonstration all work together. Separate unit tests are excluded because they are introduced in later labs.

## Repository Workflow and Documentation

`main` remains the production/default branch, as explicitly selected for this repository. `develop` is the integration branch, `feature/*` branches hold isolated work, and `release` represents the release line. References to `master` will not be introduced.

The README will explain the minimal prerequisites and commands needed to build and run the Lab 01–02 result. It will expose badges for the workflow, licence, and releases without duplicates or references to other users' repositories.

Source and configuration files will use clear names and concise comments. File-level headers will be added only where appropriate and will stay consistent with the repository licence; generated files and verbose boilerplate headers are out of scope.

## Acceptance Criteria

The design is implemented when:

- Java 21 compiles the project successfully with Maven;
- Maven produces `target/geometrics.jar` as a self-contained executable JAR;
- running the JAR in Docker against the MongoDB container prints the stored document and exits with status `0`;
- a missing or unreachable MongoDB service causes a non-zero application/container exit;
- GitHub Actions is configured for push, manual, and daily execution;
- the workflow validates both the JAR and Docker execution path;
- Docker resources are cleaned even after failure;
- the README and badges refer only to the GeoMetrics repository;
- generated artifacts and IDE metadata are not tracked;
- no functionality beyond Lab 01, Lab 02, and the explicitly requested workflow triggers is added.

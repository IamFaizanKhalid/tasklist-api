# Task List API

_API for making a task list._

## Database Setup

```
docker-compose up -d
```

You can connect to database through credentials being used in `./docker-compose.yml`

## Project Setup

```$xslt
Usage: ./scripts/run.sh [-c] [ -p PORT ]

	-c to clean the project
	-p PORT to specify port (defaulted to 7850)

Example:
	./scripts/run.sh -c -p 3456
```

You can also check help by specifying `-h` flag

```$xslt
./scripts/run.sh -h
```

## Running Project

You can run project using:

```
sbt -Dhttps.port=7850 run
```

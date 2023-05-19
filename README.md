## About this repository
This is a simple application that serves as an example on how to set a local environment using docker and
to produce and consume kafka events

The POST API /api/v1/messages produces a create-message-v1.0 kafka event that is then consumed and creates
a Message Record on the docker db, that can later be retrieved by the GET API /api/v1/messages

## Running steps

start kafka and db with: 

docker compose -p "kafka-example" -f docker-compose.yml up -d

Run locally with

./gradlew :bootRun
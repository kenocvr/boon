# Boon - Inventory Management System

## Install

    npm install

## Run

    ./mvnw
    npm start

## Docker (Optional)

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./mvnw -Pprod verify jib:dockerBuild

Then run:

    docker-compose -f src/main/docker/app.yml up -d

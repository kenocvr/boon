# Boon - Inventory Management System

QR Codes - :gear: Generate, :printer: Print, and :mag_right: Scan

:bar_chart: Track Items

## Demo

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

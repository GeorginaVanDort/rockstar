## Project Name
Rockstar Hair Salon
## Features
This java app helps the employees of Rockstar Hair Salon keep track of their Stylists and Clients.

##Specifications

1. User can view a list of current stylists
2. New stylists can be added to the current list
3. Stylist details can be edited
4. Stylists can be removed from the database
5. User can view a list of clients for each Stylist
6. New clients can be added to a Stylist
7. Clients details can be updated
8. Client entries can be deleted from the database

##Technologies
Java, JUnit, Gradle, Spark, Velocity, PostgreSQL.

## Usage
* Please make sure Java, JRE, PostgreSQL and Gradle are installed.
* Run PostgreSQL
* In a new tab run psql and use the following commands:
CREATE DATABASE hair_salon;
CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);
CREATE TABLE clients (id serial PRIMARY KEY, description varchar);

## Author/s

##License

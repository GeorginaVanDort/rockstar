## Project Name
Rockstar Hair Salon

#### By Georgina Van Dort

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

## Setup/Installation Requirements
* Please make sure Java, JRE, PostgreSQL and Gradle are installed.
* Run PostgreSQL
* In a second tab run psql and enter the following commands:
CREATE DATABASE hair_salon;
CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);
CREATE TABLE clients (id serial PRIMARY KEY, name varchar, phone varchar, stylist_id int);
CREATE DATABASE hair_salon_test with template hair_salon;

* Clone this directory and execute "gradle run"
* Navigate to localhost:4567


## Support & Contact
For questions, concerns, or suggestions please contact me on Github :)

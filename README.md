# Spring Boot - Hive Connection POC

Spring Boot project to demonstrate how to connect to Hive.

## APIs

This project has three APIs:
1. ```/hive/schemas``` - To get the list of all schemas in Hive.
2. ```/hive/{schema}/tables``` - To get the list of all tables in the given schema.
3. ```/hive/{schema}/preview/{table}``` - Te preview the table (get the first 10 records)

---

## Server

By default the server is running on port ```8082```. This can be changed in the 
```application.properties``` file.

---

## Connection to Hive

By default the service tries to connect to Hive running at ```localhost```
on port ```10001```, with HTTP as the transport mode and ```cliservice``` as 
the HTTP path. This configuration can be changed in the ```application.properties```
file.

---

## Authentication Configuration

Authentication configuration is already in place in the ```application.properties```
file and also in the ```HiveConfig``` configuration class. But this code is 
commented right now. 

---
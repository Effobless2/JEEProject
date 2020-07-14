# JEEProject

## Overview
A JEE server for managing trades and their beers' stocks.
Users are authenticate with Google OAuth and can find which trades sell their favorites beers near them.

### Features

- Authentication via Google token
- Users can list beers in database without required connection
- Users can filter beers on criterias without required connection
- Authenticated users can create a shop and modified it
- Users can create and modified beers in the _beerary_ without required connection
- Administrators can delete beers
- Users can access the search history without required connection
- This permissions can be update in the `SecurityConfig.java` file, except requests related on trades (because it must be link to a user)

## Setup
### Google API Console
#### OAuth
Follow this [link](https://developers.google.com/identity/sign-in/web/sign-in#create_authorization_credentials)
and realize the part "Create authorization credentials".

### Azure
Create a new Storage Account on Azure with any configurations.
  
### Database
You needs to setup an instance of MySQL.
You can do it with Docker, on Azure or AWS or on your localhost.
No init.sql file are required, database and tables will be created after the API will be started and connected to the mySQL instance.
Take care of the firewalls of you instance to allow the JEE Server to access to it.

### Environment variables
- DATABASE_ADDRESS: HOST access of your mysql instance (don't forget the port if needed)
- BASE_NAME: Name of your database
- USER_NAME: User name of your administrator's mysql instance
- PASSWORD: Password of your administrator's mysql instance
- DATABASE_HIBERNATE_MODEL: create (just, put create lol)
- OAUTH_GOOGLE_API_CLIENT_ID: client id of your OAuth service in Google API Console
- JWT_SECRET_KEY: a random string
- AZURE_STORAGE_CONNECTION_STRING: Connection string of your Azure Storage Account
- ADMIN_EMAIL: Google email address of the account which will be the administrator of your app

## Run
Build and run with IntelliJ or run with `java -jar app.jar`.

## Front
Install our [Angular front](https://github.com/Effobless2/jee-project-web) :)
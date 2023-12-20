# Flight Search API - Backend Developer

This repository contains the backend implementation of a flight search application. The API is designed to manage flights, airports, and provide a search functionality to retrieve relevant flight information.

## Table of Contents
1. [Introduction](#introduction)
2. [Data Modeling](#data-modeling)
3. [CRUD Operations](#crud-operations)
4. [Search API](#search-api)
5. [RESTful Service](#restful-service)
6. [Technology Stack](#technology-stack)
7. [Authentication](#authentication)
8. [Scheduled Background Jobs](#scheduled-background-jobs)
9. [Version Control](#version-control)
10. [Documentation](#documentation)

## Introduction

This project focuses on developing a backend API for a flight search application. The application allows users to manage flight information, airports, and perform searches based on departure and arrival locations, departure date, and return date.

## Data Modeling

The data is structured and stored in a relational or NoSQL database. The key entities and their attributes include:
- **Flights:**
  - ID
  - Departure airport
  - Arrival airport
  - Departure date/time
  - Return date/time
  - Price
- **Airports:**
  - ID
  - City

## CRUD Operations

A CRUD (Create, Read, Update, Delete) structure is implemented for managing flights and airports. This structure ensures consistent and organized data management.

### CRUD Resources:
- Flights
- Airports

## Search API

The API provides an endpoint for searching flights based on departure location, arrival location, departure date, and return date. If no return date is provided, it is considered a one-way flight. For round-trip flights, information for both outbound and return journeys is provided.

## RESTful Service

The API follows REST architecture, allowing seamless interaction with other systems. Java is used, and Spring/Spring Boot frameworks are employed for the development.

## Technology Stack

- Java (Spring/Spring Boot)
- Relational or NoSQL Database
- RESTful Architecture

## Authentication

An authentication structure is implemented to verify and authorize users, enhancing the overall security of the system. The chosen authentication mechanism can be configured as needed.

## Scheduled Background Jobs

A scheduled background job is set up to fetch flight information from a mock third-party API daily. This data is then stored in the database for up-to-date information.

## Version Control

The project uses Git for version control. The codebase is hosted on GitHub for collaboration and easy access.

## Documentation

API documentation is generated using Swagger, providing a comprehensive guide on the available endpoints and their usage.

For more details on how to use the API, refer to the Swagger documentation.

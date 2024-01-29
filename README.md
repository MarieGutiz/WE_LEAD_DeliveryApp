# WE LEAD Delivery app

WE LEAD delivery app created with Spring Boot - 2023-2024.

![Project Image](imgs/DELIVERYAPP(lines).png)

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Usage](#usage)
- [Endpoints](#endpoints)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)
- [Meet The Team](#meet-the-team)
  

## Overview

WL Delivery Service is a web application that allows individuals to order online food, coffee, beverages,
etc. The user must register using a unique phone and email address to use the application.
## Features


The web application's user, after initial registration, should be able to:

- **Search for the Desired Store:**
  - Either by name or by category.

- **Add Items to the Order:**
  - Add one or more items from the store's menu to the order.

- **Create an Order:**
  - Create an order containing items only from the same store.

- **Clear Order on Store Change:**
  - In case the user changes to another store and adds one of its items, the order should be cleared before adding the new item(s).

- **Retrieve All Placed Orders:**
  - Get a list of all orders placed by the user.

- **List Most Famous Stores:**
  - List the most famous stores in general and per category.

## Getting Started

## Prerequisites

To successfully run and interact with the WE_LEAD_App API, make sure you have the following components installed:

- **Java:** Version 17 or 21 LTS
- **Spring Boot:** Latest production release
- **Maven:** Make sure Maven is installed on your system.
- **H2 Database Server:** The application uses the H2 database server.

## Tools for Testing Endpoints

To call the API endpoints, you may utilize one of the following tools:

- **Postman Application:** A popular API testing and development tool.
- **IntelliJ HTTP Client:** If you are using IntelliJ IDEA, you can use its built-in HTTP client for testing.
- **Curl Command-line Tool:** A command-line tool for making HTTP requests.


## Endpoints

### Stores

#### Get All Stores

- **Endpoint:** `GET /stores`
- **Description:** Get details of all stores.
- **Request:**
  ```http
  GET http://localhost:8080/stores

#### Search Store by ID

- **Endpoint:** `GET /stores/{id}`
- **Description:** Search for a store by ID.
- **Request:**
  ```http
  GET http://localhost:8080/stores/3
  
#### Search by Name

- **Endpoint:** `GET /stores/store`
- **Description:** Search for a store by name.
- **Request:**
  ```http
  GET http://localhost:8080/stores/store?name=Cazza de la pizza

#### List Most Famous Stores in General

- **Endpoint:** `GET /stores`
- **Description:** List the most famous stores in general (using a custom header).
- List the most famous stores in general based on order quantity and ranking.
- **Request:**
  ```http
  GET http://localhost:8080/stores
Headers:
Famous-Stores-In-General: true

#### Get Famous Stores by Category

- **Endpoint:** `GET /stores`
- **Description:** Get details of famous stores in a specific category, based on order quantity and ranking.
- **Request:**
  ```http
  GET http://localhost:8080/stores?category=COFFEE_AND_BEVERAGES

## Configuration

Despite facing challenges, our team successfully fulfilled the primary project requirements and went above and beyond by incorporating additional features, including:

- Loyalty Program: We implemented a loyalty program designed to incentivize customers to choose our store regularly. This program includes a reward system where customers earn points for their transactions. These points unlock various benefits, creating a more engaging and rewarding shopping experience.
  
- Special Product Offers: In addition to the core functionality, we introduced special offers on specific products. This feature allows us to dynamically adjust prices, run limited-time promotions, and provide personalized discounts. 
These extra features not only meet the project's objectives but also contribute to a more competitive and customer-centric application. We believe these additions will positively impact user engagement, loyalty, and overall satisfaction with our platform.

## License

MIT License

## Meet the team

The developers that worked on this project:
- Mariela Gutiérrez
- Magdalini Kentroti
- Maria Eleni Kasteli
- Paraskevi Tzevelekou

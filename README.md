# Alert-Allergy
## Table of Contents 
1. Overview
2. Product Spec
3. Wireframes

## Overview
### Description
An health application that allows users to determine potential allergens in products by searching for specific items. Upon searching, the app highlights common ingreients and identifies any allergens present, based on the user's allergy profile.

### App Evaluation 
- **Category**: Health Science
- **Story**: The system evaluates a user's allergens and enables them to identify the specific ingredients in a searched product that could trigger their allergy.
- **Market**: We are targeting indivduals who are seeking to avoid their food allergens that may be in different products. Any individuals could choose to use this app.
- **Scope**: First people will have to submit their allergens, then the database will be able to detect the potential ingredients and flag allergens that are in the product. Perhaps, this app shall be offered by local doctor offices to prevent potential allergic reactions in patients. The goal is to have this app accessible wherever you are.

# Product Spec 
## 1. User Stories (Required and Optional) 
### **Required Must-have Stories** 
- User logs in to access previous product searches and preference settings.
- User inputs their allergens/ingredients they would like to avoid (Think Fig Food Scanner Interface)
- Private profile for each user (Health Information)
- Notification icons to confirm if the allergen is detected
- Search window for product
- Settings (Accessibility, Notification, General, etc.)

### **Optional Nice-to-have Stories** 
- Barcode Scanner
- Log of product search history
- Alternatives of food choices
- Daily fun fact about allergy notification
- 911 call button

## **2. Screens** 
- Login
- Register - User signs up or logs into their account
  > Upon Download/Reopening of the application, the user is prompted to log in to gain access to their profile information.
- Profile Screen
  > Upon on the profile screen, users will select their allergies.
- Product Search Screen
  > Allows user to enter prodcuts and view allergens listed in the ingredients.
  > List of Icons show if the user can consume the product (Can eat, Limit this, Avoid)
- Products to Avoid
  > Where the User places all of the products that are detrimental to health
- Settings Screen
  > Lets people change language,display preferences, and app notification settings.
  >

## **3. Navigation** 
### *Tab Navigation** (Tab to Screen) 
- Profile
- Search
- Setting
- Products

### Optional: 
- Product Alternatives
- Bar Code Scanner

## **Flow Navigation** (Screen to Screen) 
- Forced Log-in -> Account creation if no log in is available
- Allergy Selection -> Jumps to Search Bar
- Profile -> Jumps to ingredient search bar
- Settings -> Toggle settings

# Wireframes 

  
![processed-641D03D3-B332-4904-98C5-DE09B224BEC1](https://github.com/Team-Doitall/Alert-Allergy/assets/103546272/d0471cf4-dfb2-4b18-9628-cb2be24f95a2)

# Digital Wireframes and Mockups
[Alert-Allergy.pdf](https://github.com/Team-Doitall/Alert-Allergy/files/14204122/Alert-Allergy.pdf)

# Schema 
## Models 
### Post 
[Coote-Projects.pdf](https://github.com/Team-Doitall/Alert-Allergy/files/14215500/Coote-Projects.pdf)

### Networking
#### List of network requests by screen
User Screen 
Endpoints: 
- **GET /users/{userId}** - Get details of a specific user. 
- **POST /users** - Create a new user. 
- **PUT /users/{userId}** - Update user details, including allergy profile. 
- **DELETE /users/{userId}** - Delete a user. 

Authentication Screen 
Endpoints: 
- **POST /login** - User login. 
- **POST /register** - User registration. 
- **POST /logout** - User logout. 

Product Screen
Endpoints: 
- **GET /products** - Search for products based on query parameters (name, barcode). 
- **GET /products/{productId}** - Get details of a specific product, including allergen information. 

Product Search Screen 
Endpoints: 

- **POST /user-product-searches** - Log a new product search for a user, including allergen match results. 
- **GET /user-product-searches/{userId}** - Retrieve the history of product searches for a specific user. 
Settings Screen 
Endpoints: 
- **GET /settings/{userId}** - Get the settings for a specific user. 
- **PUT /settings/{userId}** - Update settings for a specific user, including language and notification preferences. 
AllergyProfile Screen 
Endpoints: 
- **GET /allergy-profiles/{userId}** - Get allergy profile for a specific user. 
- **POST /allergy-profiles/{userId}** - Create or update the allergy profile for a user.

#### [IF EXISTS:] Existing API Endpoints
##### An API of InstaCart
- Base URL [https://docs.instacart.com/connect/api/#:~:text=You%20can%20use%20the%20Instacart,%2C%20response%20codes%2C%20and%20authentication.]
HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | /orders/{order_id} | Retrieve details about a specific order identified by order id
    `POST`    | /batches/{batch_id}/acknowledge | Acknowledge a batch with the specified batch_id.
    `GET`    | /batches/{batch_id}  | Retireve details about a specific batch identified by batch_id
    `POST`    | /orders/{order_id}/acknowledge | Acknowledge an order with the specified order_id
    'GET'    |  /orders/ | Retrieve a list of orders along with their details 
    'POST'  | /deliveries/{delivery_id}/confirm  | Confirm the delivery associated with the given 'delivery_id'.
    'GET'   | /deliveries/{deliveries_id} | Retrieve details about a specific delivery identified by 'delivery_id'.

##### An API of HealthIntnet
- Base URL [https://docs.healtheintent.com/api/v1/allergy/#allergy-api-v1]
  
HTTP Verb | Endpoint | Description
----------|----------|------------
     'GET'   | /populations/{population_id} | Identifier of the population
    'GET'    | /patients/(patientId} | identifier of the patient
    'GET'   | /allergies/{allergyId} | Id that uniquely identfies the allergy for the patient  


##### GIFs 
- 
    ![Alert-Allergy](https://github.com/Team-Doitall/Alert-Allergy/assets/103546272/c3dad457-f31a-4cfd-8dfe-3e8fa7bec10c)


 




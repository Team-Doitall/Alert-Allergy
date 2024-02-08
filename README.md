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
Schema 

User 

Property 

Type 

Description 

objectId 

String 

Unique ID for the user (default field) 

username 

String 

User's chosen username 

password 

String 

User's password (stored securely) 

email 

String 

User's email address 

allergies 

Array of Strings 

List of allergens the user is allergic to 

createdAt 

DateTime 

Date when the user account is created (default field) 

updatedAt 

DateTime 

Date when the user account is last updated (default field) 

 

Product 

Property 

Type 

Description 

objectId 

String 

Unique ID for the product (default field) 

productName 

String 

Name of the product 

ingredients 

Array of Strings 

List of ingredients contained in the product 

allergenWarnings 

Array of Strings 

List of allergens present in the product, if any 

createdAt 

DateTime 

Date when product info is created (default field) 

updatedAt 

DateTime 

Date when product info is last updated (default field) 

 

UserProductSearch 

Property 

Type 

Description 

objectId 

String 

Unique ID for the search instance (default field) 

user 

Pointer to User 

Reference to the user who conducted the search 

product 

Pointer to Product 

Reference to the product searched 

searchDate 

DateTime 

Date when the search was conducted 

allergenMatch 

Array of Strings 

List of matched allergens found in the product 

canConsume 

Boolean 

Indicates whether the user can consume the product based on their allergy profile 

createdAt 

DateTime 

Date when the search instance is created (default field) 

updatedAt 

DateTime 

Date when the search instance is last updated (default field) 

 

UserSettings 

Property 

Type 

Description 

objectId 

String 

Unique ID for the user settings (default field) 

user 

Pointer to User 

Reference to the user these settings belong to 

language 

String 

Preferred language of the user 

notificationPreferences 

Object 

Preferences for different types of notifications (e.g., allergen alerts, app updates) 

displayPreferences 

Object 

User preferences for app display (e.g., dark mode, light mode) 

createdAt 

DateTime 

Date when the settings are created (default field) 

updatedAt 

DateTime 

Date when the settings are last updated (default field) 

 




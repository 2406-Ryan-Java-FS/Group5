# Calorie Tracker PRO
---
<img src="https://github.com/2406-Ryan-Java-FS/Group5/blob/main/documents/calorie-track-pro-front.png"  width="800" height="400"/>

## Developers
- Eugene Kirima
- Hayoung Cheon
- Uriel Cohavy

## Installation Guide
You need to run both camp-in-project(Backend) and campin-react-app(Frontend).
* Run SQL in your MySQL workbench.
  
* To run camp-in-project(Backend)
 *  Open IDE for Java
 *  Open Folder of `camp-in-project`
 *  Set environment Variables
    * DB_URL
    * DB_USERNAME
    * DB_PASSWROD
 *  Run `CalorieTrackApp.java`
   
*  To run campin-react-app
 * In your terminal, `cd` to front-end/calorie-tracker
 * `npm install` to install all the dependencies
 * `npm start` or `npm run start`

## Table of Contents
> 1. [ About the project ](#about-project)
> 2. [ Key Features ](#key-features)
> 3. [ User Stories ](#user-stories)
> 4. [ Task Stacks ](#tack-stacks)
> 5. [ Database Schema ](#database-schema)
> 6. [ Presentation Documents ](#presentation-documents)
> 7. [ Demonstration ](#demonstration)

---

<a name="about-project"></a>
### About the Project
  - An app to track your calorie intake to meet your specific calorie count goal. It helps users track their calorie intake to meet their calorie count goals and maintain their health.

<a name="key-features"></a>
### Key Features
  - User authentication and profile management.
  - BMI calculation.
  - Calorie calculation.
  - Daily and weekly calorie count monitoring
  - Admin Functionality for food database management.

<a name="user-stories"></a>
### User Stories
  - Users can register and create user accounts.
  - User can log in and log out from their account.
  - User can create a profile with the following information
    - gender, height, weight, activity levels, and calorie goals.
  - User can view their profile information.
  - User can edit and delete their profile
  - User can view their BMI calculation (calculated from their profile details)
  - Users can search for foods in the database and enter the food they have eaten to track the calories taken per meal per day.
  - User can view or monitor their daily calorie intake and goal in the calorie track view dashboard.
  - Users can monitor their intake progress weekly
  - The admin can add, update, and delete foods from the food data.
  - Admin can access and manage users.

<a name="tech-stacks"></a>
### Tech Stacks
<img src="documents/tech-stacks.png"  width="800" height="400"/>

* Front End
  * React JS
  * HTML
  * CSS
  * React BootStrap
  * JavaScript
    
* Backend
  * Java
  * Spring Web Framework
  * Spring boot project
  * Spring Modules: Spring MVC, Spring REST, Spring Data JPA
  * Maven
    
* DB
  * PostgreSQL
  * AWS RDS

<a name="database-schema"></a>
### Database Schema
<img src="documents/database-schema.png"  width="800" height="400"/>

<a name="presentation-documents"></a>
### Presentation Documents
* We recommend reading the pdf file.
   <br>
⌞ [Calorie Tracker PRO Presentation.pdf](documents/Calorie Tracker PRO Presentation.pdf "Presentation pdf file")
<br>
⌞ [Calorie Tracker PRO Presentation.pptx](documents/Calorie Tracker PRO Presentation.pptx "Presentation ppt file")

<a name="demonstration"></a>
### Demonstration
* Landing Page
<img src="documents/demo-gif/landing-page.gif"  width="800" height="400"/>
<br>
  
* Signup
<img src="documents/demo-gif/signup.gif"  width="800" height="400"/>
<br>
  
* Login
<img src="documents/demo-gif/login.gif"  width="800" height="400"/>
<br>
  
* User Profile registration/update
<img src="documents/demo-gif/profile.gif"  width="800" height="400"/>
<br>

* User BMI Calculation
<img src="documents/demo-gif/bmi-calculation.gif"  width="800" height="400"/>
<br>

* User Calorie track registration
<img src="documents/demo-gif/calorie-track-view1.gif"  width="800" height="400"/>
<img src="documents/demo-gif/calorie-track-view2.gif"  width="800" height="400"/>
<br>

* User Calorie track update
<img src="documents/demo-gif/calorie-track-update.gif"  width="800" height="400"/>
<br>

* User Calorie track deletion
<img src="documents/demo-gif/calorie-track-delete.gif"  width="800" height="400"/>
<br>

* User Logout
<img src="documents/demo-gif/logout.gif"  width="800" height="400"/>
<br>

* Admin Landing Page
<img src="documents/demo-gif/admin-login.gif"  width="800" height="400"/>
<br>

* Admin food add/view
<img src="documents/demo-gif/admin-food-add.gif"  width="800" height="400"/>
<br>

* Admin food update/delete
<img src="documents/demo-gif/admin-food-update.gif"  width="800" height="400"/>
<br>

* Admin user management.
<img src="documents/demo-gif/admin-food-user.gif"  width="800" height="400"/>
<br>





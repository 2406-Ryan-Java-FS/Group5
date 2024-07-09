# Project2 Proposal - Group 5

## Calorie Tracker Application

### About the project

- An app to track your calorie intake to meet a specific goal.

### User stories

- Login
    - As a user, I can create my account with information for my profile(such as weight, height, and activity level)
    - As a user, I can log into my account and view my profile.
    - As a user, I can update or delete my account and profile.
    - As an admin, I can login to my account. (creating an account for admin needed?)

- Calorie Goal
    - As a user, I can set my daily calorie to monitor my progress towards my health objectives.
    - As a user, I can view my daily calorie.
    - As a user, I can view my daily calorie recommendation.
        - How to Calculate Calories
            - For women, BMR = 655.1 + (9.563 x weight in kg) + (1.850 x height in cm) - (4.676 x age in years)
            - For men, BMR = 66.47 + (13.75 x weight in kg) + (5.003 x height in cm) - (6.755 x age in years)

- Log claories        
    - As a user, I can monitor my calorie intakes such as how much I have take and how much is left for a day.
    - AS a user, I can monitor total calorie count for a daily, weekly and all-time intake.
    - As a user, I can add my calorie intake.
    - As a user, I can manipulate(update/delete) my calorie intake tracker if I find that the calorie count is different from the input so that my records remain accurate.
    - As a user, I can manually create a food information and add it to my tracker if it is not found in the database so that I can log my calorie intake accurately.

- Request for adding new food
    - As a user, I can request to admin for adding new food information.
    - As an admin, I can confirm a user's request and add it to a database.

### Ideas we may include:

- As a user, I can recieve notifications if I am close to exceeding my daily calorie goal.
- As a user, I can view an average of the calorie intake.
- As a user, I can add a food journal.
- As a user, I can share my healthy receipe.
- Do we need more roles for admins?

### Models

- User/accounts table(username, password)
- role(User and Admin)
- calorietrack(a table for saving user's calorie log)
- Profile(user's height, weight, activity levels, calorie goal, etc...)
- Food(public data api? or manual database table?)


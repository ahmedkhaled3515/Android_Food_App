
# Food Planner App

A brief description of what this project does and who it's for
////////////
Food Planner App - A Culinary Journey Across Borders
In this project, I've delved into the fascinating world of global cuisine, bringing you a convenient and enriching way to explore meals from various countries. Here's a glimpse of what you can expect:
📱 Meal Details at Your Fingertips: Dive deep into the specifics of each meal, including ingredients and even instructional videos for a step-by-step guide on preparation.
❤️ Personalized Experience: Make the app your own by curating a list of your favorite meals. Never lose track of the dishes that tantalize your taste buds!
🗓️ Plan Your Culinary Adventures: With the ability to add meals to your future dining plans, you can organize your gastronomic journey with ease.
🔍 Effortless Search: Whether you're craving a specific dish, exploring cuisines by country, or seeking out recipes based on ingredients or categories, the search feature has got you covered.

## Screenshots
![Splash Screen]()
![Registeration Screen]()
![Login Screen]()
![Home Screen]()
![Schedule Screen]()
![Details Screen]()
![Meal List Screen]()
![Search]()
![Splash Screen]()
## API Reference



#### Get random meal
```https:www.themealdb.com/api/json/v1/1/
  GET /random.php
```
#### Get all meal categories
```https:www.themealdb.com/api/json/v1/1/
  GET /categories.php
```
#### List all Categories, Area, Ingredients
```https:www.themealdb.com/api/json/v1/1/
  GET /list.php?c=list
  GET /list.php?a=list
  GET /list.php?i=list
```
#### Get meal items
```https:www.themealdb.com/api/json/v1/1/
  GET /search.php?s={meal_name}
```
#### Ingredient Thumbnail Images
```https:www.themealdb.com/images/ingredients/Lime.png
  GET /categories.php
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `meal_name` | `string` | **Required**. the meal name or a first letters |

#### Filter by Category
```https:www.themealdb.com/api/json/v1/1/
  GET /filter.php?c={Category_name}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `category_name` | `string` | **Required**. the category name|

#### Filter by area
```https:www.themealdb.com/api/json/v1/1/
  GET /filter.php?c={area_name}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Area_name` | `string` | **Required**. the area name|

#### Filter by main Ingredients
```https:www.themealdb.com/api/json/v1/1/
  GET /filter.php?c={ingredient_name}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `ingrediants_name` | `string` | **Required**. the Ingredient name|




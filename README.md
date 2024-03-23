![Screenshot_20240323_095734](https://github.com/ahmedkhaled3515/Android_Food_App/assets/65148538/bb4dfba4-e675-4b15-a643-68e44fa23197)
# Food Planner App
Food Planner App - A Culinary Journey Across Borders
In this project, I've delved into the fascinating world of global cuisine, bringing you a convenient and enriching way to explore meals from various countries. Here's a glimpse of what you can expect:
📱 Meal Details at Your Fingertips: Dive deep into the specifics of each meal, including ingredients and even instructional videos for a step-by-step guide on preparation.
❤️ Personalized Experience: Make the app your own by curating a list of your favorite meals. Never lose track of the dishes that tantalize your taste buds!
🗓️ Plan Your Culinary Adventures: With the ability to add meals to your future dining plans, you can organize your gastronomic journey with ease.
🔍 Effortless Search: Whether you're craving a specific dish, exploring cuisines by country, or seeking out recipes based on ingredients or categories, the search feature has got you covered.

## Screenshots
![Splash Screen](https://github.com/ahmedkhaled3515/Android_Food_App/assets/65148538/454acc4e-0f9b-46e3-a697-2f2f48872ab7)
![Registeration Screen](https://github.com/ahmedkhaled3515/Android_Food_App/assets/65148538/dec5d3e2-fde2-4b44-83fc-b0fab08f9e2d)
![Login Screen](https://github.com/ahmedkhaled3515/Android_Food_App/assets/65148538/bdf1c327-477c-41d2-a46e-0e0526c3a89e)
![Home Screen](https://github.com/ahmedkhaled3515/Android_Food_App/assets/65148538/f4339b7c-1f7b-49a7-9df5-352af55bd60d)
(https://github.com/ahmedkhaled3515/Android_Food_App/assets/65148538/4d63585d-fcf4-4df5-9a52-a743dc15c4b4)
![Schedule Screen]
(https://github.com/ahmedkhaled3515/Android_Food_App/assets/65148538/0f8d03e2-5c28-41e6-aac4-2833262f3da7)

![Details Screen]
(https://github.com/ahmedkhaled3515/Android_Food_App/assets/65148538/a90a9407-ab99-4c6d-a42e-355ab2e5644b)
(https://github.com/ahmedkhaled3515/Android_Food_App/assets/65148538/7f7a01c6-050e-4d27-870d-72c609e9b0fb)

![Meal List Screen]
(https://github.com/ahmedkhaled3515/Android_Food_App/assets/65148538/39d29a9e-89a7-4127-b8bf-d596be7e7eda)

![Search]
(https://github.com/ahmedkhaled3515/Android_Food_App/assets/65148538/5cdb397f-c05d-4cad-be2e-2a961ce00ded)
(https://github.com/ahmedkhaled3515/Android_Food_App/assets/65148538/2baa68f8-ce43-4ab1-8f5e-c261d68acb71)
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




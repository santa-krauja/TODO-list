#ToDo list


##Endpoints

#####To create new list items:
POST localhost:8080/list
JSON example:
`
{        "id": 1,
         "task": "Ask for help",
         "progress": "In progress",
         "assignee": "Santa"}`

#####To see all elements:
GET http://localhost:5000/todo

#####To delete element:
DELETE localhost:8080/list/{id}

#####To update list item:
PUT localhost:8080/list/{id}
JSON example:
`{"id": 1,
         "task": "Ask for help",
         "progress": "In progress",
         "assignee": "Santa"}`


#####All is saved on embedded H2 database

##Frontend part
To install necessary dependencies for frontend in terminal go to src\main\resources\static and use 
`npm install`

 To add new dependencies `npm install dependency-name --save`
 
 ##Heroku
 https://stark-hollows-72801.herokuapp.com/
 
 gradle.bat build deployHeroku
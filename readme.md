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
GET localhost:8080/list

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
To install necessery dependencies for frontend in terminal go to src\main\resources\static and use 
`npm install`

 To add new dependencies `npm install dependency-name --save`
spring-boot-rest-api with docker
==========================
simple rest api

Docker Compose
--------------
    cd ../spring-boot-rest-api
    docker-compose up
    
The configuration is stored in `docker-compose.yml`

# REST API FOR STUDENTS

## Get list of Students

### Request

`GET /api/student/`

### Response Payload

```json
[
  {
    "albumId": "000000",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@mail.com"
  }
]
```

## Create Students

### Request

`POST /api/student/`

### Request Payload

```json
{
    "albumId": "000000",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@mail.com"
}
```

### Response Payload

```json
{
    "albumId": "000000",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@mail.com"
}
```

## Update Student

### Request

`PUT /api/student/{id}`

### Request Payload

```json
{
    "albumId": "000000",
    "firstName": "Updated name",
    "lastName": "Updated name",
    "email": "Updated mail"
}
```

### Response Payload

    "Student was successfully updated!"

## Delete Student

### Request

`DELETE /api/student/{id}`

### Response Payload

    "Student was successfully deleted!"

## Enroll Course

### Request

`POST /api/student/enroll/{courseId}`

### Request Payload

```json
{
    "albumId": "000000",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@doe.com"
}
```

### Response Payload

```json
{
    "albumId": "000000",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@doe.com"
}
```

## Find Students With Specific Course

### Request

`GET /api/student?courseId=id`

### Response Payload

```json
[
  {
    "albumId": "000000",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@doe.com"
  },
  {
    "albumId": "000001",
    "firstName": "Mark",
    "lastName": "Doe",
    "email": "mark@doe.com"
  }
]
```

## Find Students Without Courses

### Request

`GET /api/student/without-courses`

### Response Payload

```json
[
  {
    "albumId": "000000",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@doe.com"
  },
  {
    "albumId": "000001",
    "firstName": "Mark",
    "lastName": "Doe",
    "email": "mark@doe.com"
  }
]
```

# REST API FOR COURSES

## Get list of Courses

### Request

`GET /api/course/`

### Response Payload

```json
[
  {
    "name": "Course",
    "description": "Description"
  }
]
```

## Create Course

### Request

`POST /api/course/`

### Request Payload

```json
{
  "name": "Course",
  "description": "Description"
}
```

### Response Payload

```json
{
  "name": "Course",
  "description": "Description"
}
```

## Update Course

### Request

`PUT /api/course/{id}`

### Request Payload

```json
{
  "name": "Course",
  "description": "Description"
}
```

### Response Payload

    "Course was successfully updated!"

## Delete Course

### Request

`DELETE /api/course/{id}`

### Response Payload

    "Course was successfully deleted!"

## Find Courses With Specific Student In

### Request

`GET /api/course?studentId=id`

### Response Payload

```json
[
  {
    "name": "Course",
    "description": "Description"
  },
  {
    "name": "Course2",
    "description": "Description2"
  }
]
```

## Find Courses Without Students

### Request

`GET /api/course/without-students`

### Response Payload

```json
[
  {
    "name": "Course",
    "description": "Description"
  },
  {
    "name": "Course2",
    "description": "Description2"
  }
]
```




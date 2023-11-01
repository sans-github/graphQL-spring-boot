# Overview

A simple GraphQL App using Spring boot. Original tutorial [here](https://www.youtube.com/watch?v=atA2OovQBic)

## Endpoints

* [App URL](http://localhost:8080)
* [GraphiQL tests](http://localhost:8080/graphiql?path=/graphql)

## Sample GraphQL queries

### Queries

Basic query

``` graphQL
query {
  authors {
    id
    name
    books {
      id
      title
      publisher
      __typename
    }
    __typename
  }
  __typename
}
```

Multiple queries

``` graphQL
query {
  authors {
    name
  }, 
  authors {
    id
  }, 

  authors {
    books {
      title
    }
  } 
}
```

Get by author id

``` graphQL
query {
  authorById(id:1) {
    id
    name
    books {
      id
      title
      publisher
    }
  }
}
```

### Mutations

Add a book

``` graphQL
mutation {
  addBook(book: { title: "title-4", publisher: "publisher-4", authorId: 1 }) {
    id
  }
}
```

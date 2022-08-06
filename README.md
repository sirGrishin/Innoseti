Задание:
ОБЯЗАТЕЛЬНАЯ ЧАСТЬ:
==================== Необходимо реализовать сервис, принимающий и обрабатывающий GraphQL-запросы:
mutations:
saveBook(title, authors), saveAuthor(name, books)
queries:
getBooksByAuthor(author), getAllBooks() - получить список книг по автору, получить все книги соответственно. getAuthor(
name) - вывод автора (в т.ч. со списком книг автора)
Хранимые сущности:
Book (id, title, authors)
Author (id, name, books)

Приложение с использованием Spring boot, в качестве СУБД (postgresql)
Сборка и запуск с помощью maven (либо gradle), исходники выложить на github или любой другой подобный сервис.

ЖЕЛАТЕЛЬНАЯ ЧАСТЬ:
===================
Написать DAO Integration Test с использованием Testcontainers. Как совсем "жирный плюс" docker compose yaml - файл для
запуска готового приложения.

# {
#  getAllBooks {
#   id
#   title

#   }
# }


# {
#  getAuthor(name : "Иван"){
#   id
#   name
#   books{
#     id
#     title
#    }
# }
# }


# query{
#   getBooksByAuthor(author: {id:7, name:"Иван"}){
#     id
#     title
#   }
# }

# mutation{
#   saveBook(title: "Книга Александра и Игоря", authors:[{id:2, name: "Игорь"}, {id:3, name: "Александр"}]){
#     id
#     title
#   }
# 

# mutation{
#   saveAuthor(name: "Евгений", books:[ {title: "книга Евгения"}, {title: "книга Евгения 2 "}]){
#     id
#     name
#   }
# }
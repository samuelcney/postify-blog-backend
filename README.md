# postify-blog-backend
```
Directory structure:
└── samuelcney-postify-blog-backend/
    ├── README.md
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── spring/
    │   │   │           └── app/
    │   │   │               └── postify/
    │   │   │                   ├── PostifyApplication.java
    │   │   │                   ├── config/
    │   │   │                   │   ├── AppConfig.java
    │   │   │                   │   └── WebConfig.java
    │   │   │                   ├── controller/
    │   │   │                   │   ├── AuthController.java
    │   │   │                   │   ├── CategoryController.java
    │   │   │                   │   ├── CommentController.java
    │   │   │                   │   ├── FavoriteController.java
    │   │   │                   │   ├── PostController.java
    │   │   │                   │   └── UserController.java
    │   │   │                   ├── dto/
    │   │   │                   │   ├── CommentRequestDTO.java
    │   │   │                   │   ├── FavoriteRequestDTO.java
    │   │   │                   │   ├── PostRequestDTO.java
    │   │   │                   │   └── UserRequestDTO.java
    │   │   │                   ├── model/
    │   │   │                   │   ├── Category.java
    │   │   │                   │   ├── Comment.java
    │   │   │                   │   ├── Favorite.java
    │   │   │                   │   ├── Post.java
    │   │   │                   │   └── User.java
    │   │   │                   ├── repository/
    │   │   │                   │   ├── CategoryRepository.java
    │   │   │                   │   ├── CommentRepository.java
    │   │   │                   │   ├── FavoriteRepository.java
    │   │   │                   │   ├── PostRepository.java
    │   │   │                   │   └── UserRepository.java
    │   │   │                   ├── service/
    │   │   │                   │   ├── CategoryService.java
    │   │   │                   │   ├── CommentService.java
    │   │   │                   │   ├── FavoriteService.java
    │   │   │                   │   ├── LoginService.java
    │   │   │                   │   ├── PostService.java
    │   │   │                   │   └── UserService.java
    │   │   │                   └── utils/
    │   │   │                       ├── ApiResponse.java
    │   │   │                       └── EmailValidator.java
    │   │   └── resources/
    │   │       ├── application.properties
    │   │       └── db/
    │   │           └── migration/
    │   │               ├── V1__create_users_table.sql
    │   │               ├── V2__create_categories_table.sql
    │   │               ├── V3__create_posts_table.sql
    │   │               ├── V4__create_comments_table.sql
    │   │               ├── V5__create_table_favorites.sql
    │   │               └── V6__alter_posts_table.sql
    │   └── test/
    │       └── java/
    │           └── com/
    │               └── spring/
    │                   └── app/
    │                       └── postify/
    │                           └── PostifyApplicationTests.java
    └── .mvn/
        └── wrapper/
            └── maven-wrapper.properties

```

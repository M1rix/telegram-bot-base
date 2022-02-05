# <img src="https://user-images.githubusercontent.com/78680620/152656642-d8037ac9-80e9-4396-8da3-c833278bee92.png" width="30" height="30"> Telegram BaseBot

-
- **Don't forget change change configs in** `/resources/config/application-*.yml`
-

#### Database (PostgreSQL)

```
datasource:
    url: jdbc:postgresql://localhost:5432/db_name
    username: db_username
    password: db_password
    
```


#### Bot Credentials
In the end of the file `/resources/config/application-*.yml`
```
bot:
    username: put-username-of-your-bot
    token: put-token-of-your-bot
```

## Development

Run the following commands in two separate terminals to create a blissful development experience where your browser auto-refreshes when files change on your hard drive.
```
./mvnw
npm start
```

## Building for production

#### Packaging as jar

To build the final jar and optimize the BaseBot application for production, run:

```
./mvnw -Pprod clean verify
```

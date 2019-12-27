## Teste

**A hosted REST-API ready to respond** projeto de teste. 
https://reqres.in/
<br/>

#### Requirements

- gradle >= 4.7

```
gradle wrapper --gradle-version 4.7
```

#### Getting Started

Install the dependencies, go to the directory `./gatling` and execute;
```
gradle build
```

Run tests;

```
./gradlew gatlingRun
```
#### Subir para repositório
```
git init
git remote add origin https://github.com/diegomachadoti/gatling-gradle
git status
git add .
git commit -m "mensagem"
git push --set-upstream origin master
```

#### Report
The reports are available in `gatling/build/reports/gatling`

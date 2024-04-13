Getting Started
=================

First of all, this documentation is YOUR documentation :

* We use English for the sake of the technic
* 1 Sentence per line (for easiest merge)

Tools
----------

The tools that you'll need for developping on this project are :
* **Git** : https://git-scm.com/
* **Java 17**
* **Maven** : https://maven.apache.org/
* **Docker** : https://www.docker.com/products/docker-desktop/
* Your favorite IDE

Starting dev environment
-------------------------
To start your dev environment, you need to follow these steps:

* **Docker containers** :
    * gateway needs these containers to be running to function properly:
        - postgreSQL
        - pgadmin

once you have cloned the gateway project, go to the **root folder** and run the command **docker-compose up -d**

* **pgadmin**
    *  to access pgadmin and manage authentication, use (user@example.com/user) in [http://localhost:8082] and setup connection with the database.

Git
----------

#### Format of the commit message:
```bash
<type>: <subject>
```

#### Example commit message:

```bash
fix: fixing responsive behaviour
```

##### Allowed `<type>` values:

* **feat** (new feature for the user, not a new feature for build script)
* **fix** (bug fix for the user, not a fix to a build script)
* **style** (formatting, missing semi colons, etc; no production code change)
* **refactor** (refactoring production code, eg. renaming a variable)
* **test** (adding missing tests, refactoring tests; no production code change)


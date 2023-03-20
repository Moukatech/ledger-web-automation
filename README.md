# ledger-web-automation

Web test automation using [Selenide](https://selenide.org/), Maven and Testng

## Clone the project.
```bash
git clone  https://github.com/Moukatech/ledger-web-automation.git
```

## Installation

Use the package manager [mvn](https://maven.apache.org/) to run and install all dependencies in the pom.xml .

```bash
cd /ledger-web-automation
mvn clean install
```

## Run Tests

```bash
mvn test -Durl=https://react-redux.realworld.io/
```
The url is a paramater and can be changed depending on the enviroment to be tested.
Test report can be viewed in the index.html file at the root of the project.

## Github Actions

For the git action to execute a git push will trigger the job to run.

## Reporting after the git action
After the tests have executed ,the test report can be accessed [Here](https://moukatech.github.io/ledger-web-automation/)

## License

[MIT](https://choosealicense.com/licenses/mit/)

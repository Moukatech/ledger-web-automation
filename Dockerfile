FROM ubuntu
MAINTAINER "jagadish Manchala"
#Install git
RUN apt-get update \
     && apt-get install -y git

#install Chrome
RUN apt-get update && \
    apt-get install -y gnupg wget curl unzip --no-install-recommends && \
    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list && \
    apt-get update -y && \
    apt-get install -y google-chrome-stable && \
    CHROMEVER=$(google-chrome --product-version | grep -o "[^\.]*\.[^\.]*\.[^\.]*") && \
    DRIVERVER=$(curl -s "https://chromedriver.storage.googleapis.com/LATEST_RELEASE_$CHROMEVER") && \
    wget -q --continue -P /chromedriver "http://chromedriver.storage.googleapis.com/$DRIVERVER/chromedriver_linux64.zip" && \
    unzip /chromedriver/chromedriver* -d /chromedriver


RUN mkdir reactApp \
           && cd reactApp/ \
           && git clone https://github.com/gothinkster/react-redux-realworld-example-app.git
#Set working directory
WORKDIR reactApp/

# pull official base image
FROM node:alpine

# set working directory
WORKDIR /app

COPY --from=0 reactApp/react-redux-realworld-example-app ./

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies

RUN npm install

# start app
CMD ["npm", "start"]

# Running Tests
# install chrome

FROM maven:3.6.3-jdk-11

WORKDIR /ledger

COPY . /ledger/


RUN mvn test -Durl=http://localhost:4100/ -Dchromeoptions.args=headless
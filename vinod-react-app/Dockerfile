# base image
# FROM node:12.2.0-alpine
FROM python

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
# ENV PATH /app/node_modules/.bin:$PATH

# install and cache app dependencies
COPY build/. /app/


# start app
CMD ["python", "-m", "http.server", "4000"]

EXPOSE 4000
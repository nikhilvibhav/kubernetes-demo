# 1st stage of a 2 stage build
## pull official base image
FROM node:16.2.0-alpine3.11 AS builder

## set working directory
WORKDIR /app

## add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

## install app dependencies
COPY package.json ./
COPY package-lock.json ./
RUN npm install --silent

## add app
COPY . ./

## run the build
RUN npm run build

# 2nd stage of a 2 stage build
## Copy build directory in previous stage to nginx container
FROM nginx:stable-alpine
COPY --from=builder /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

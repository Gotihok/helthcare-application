FROM node:lts-slim AS build
WORKDIR /app
RUN npm install -g @angular/cli
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:stable
#COPY default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/frontend/browser/ /usr/share/nginx/html
EXPOSE 80

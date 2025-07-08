FROM node:16 as build

WORKDIR /app
COPY . .

ARG REACT_APP_MAPBOX_TOKEN
ENV REACT_APP_MAPBOX_TOKEN=$REACT_APP_MAPBOX_TOKEN

RUN npm install
RUN npm run build

# -----------

FROM node:16-alpine as production

WORKDIR /app

RUN npm install -g serve

COPY --from=build /app/build ./build

CMD ["serve", "-s", "build", "-l", "3000"]

EXPOSE 3000

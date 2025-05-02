LABEL authors="deniz"

ENTRYPOINT ["top", "-b"]

# Node.js imajı kullanarak temel alıyoruz
FROM node:16

# Çalışma dizinini /app olarak ayarlıyoruz
WORKDIR /app

# Frontend dosyalarınızı Docker konteynerine kopyalıyoruz
COPY . /app

# Burada build sırasında env değişkenlerini geçiriyoruz
ARG REACT_APP_MAPBOX_TOKEN
ENV REACT_APP_MAPBOX_TOKEN=$REACT_APP_MAPBOX_TOKEN

# Bağımlılıkları yükliyoruz
RUN npm install

# Uygulamanızı derliyoruz (build ediyoruz)
RUN npm run build

# Konteyneri başlattığınızda çalışacak komut
CMD ["npm", "start"]

# Port 3000'i expose ediyoruz
EXPOSE 3000

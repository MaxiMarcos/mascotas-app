# Usamos nginx ligero
FROM nginx:alpine

# Copiamos todos los archivos del frontend al directorio de nginx
COPY . /usr/share/nginx/html

# Exponemos el puerto que vamos a mapear en docker-compose
EXPOSE 80

# Nginx ya tiene un ENTRYPOINT por defecto, así que no hace falta ponerlo
# Usamos nginx ligero
FROM nginx:alpine

# Copiamos todos los archivos del frontend al directorio de nginx
COPY . /usr/share/nginx/html

# Copiamos configuración personalizada de nginx para usar PORT
COPY nginx.conf /etc/nginx/nginx.conf

# Copiamos el script de inicio
COPY start.sh /start.sh
RUN chmod +x /start.sh

# Exponemos el puerto por defecto
EXPOSE 80

# Usar el script de inicio
CMD ["/start.sh"]
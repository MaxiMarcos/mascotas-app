#!/bin/sh

# Reemplazar el placeholder con la URL real del backend
if [ -n "$API_URL" ]; then
    sed -i "s|API_URL_PLACEHOLDER|$API_URL|g" /usr/share/nginx/html/index.html
fi

# Configurar el puerto de nginx
envsubst '$$PORT' < /etc/nginx/nginx.conf > /tmp/nginx.conf && mv /tmp/nginx.conf /etc/nginx/nginx.conf

# Iniciar nginx
nginx -g 'daemon off;'
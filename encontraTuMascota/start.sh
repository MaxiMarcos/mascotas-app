#!/bin/sh
set -e

# Asegurar valor por defecto de PORT para nginx
export PORT="${PORT:-80}"

# Reemplazar el placeholder con la URL real del backend


# Sustituir PORT en nginx.conf
envsubst '${PORT}' < /etc/nginx/nginx.conf > /tmp/nginx.conf && mv /tmp/nginx.conf /etc/nginx/nginx.conf

# Iniciar nginx
nginx -g 'daemon off;'
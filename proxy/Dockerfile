# Build reverse proxy to add remote address headers
FROM nginx:alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
CMD ["nginx", "-g", "daemon off;"]

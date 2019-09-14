FROM python
WORKDIR /app
COPY build/. /app/
CMD ["python", "-m", "http.server", "4000"]
EXPOSE 4000
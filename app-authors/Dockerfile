FROM python:3.11-alpine3.17
ENV PYTHONUNBUFFERED 1
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN pip3 install pipenv
RUN pipenv install --system
EXPOSE 8080



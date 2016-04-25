FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD services.jar app.jar
RUN sh -c 'touch /app.jar'
COPY docker-entrypoint.sh /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]
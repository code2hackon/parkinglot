# parkinglot

BUILD
./gradlew clean build;

RUN
java -Xdebug -Xrunjdwp:transport=dt_socket,address=9009,suspend=n,server=y -jar ./build/libs/parkinglot-0.0.1-SNAPSHOT.war



APIS
===============

Create a parking lot
--------------------
  curl --location --request GET 'http://localhost:9091/parkinglot/v1/api/create?slot=23&level=1&row=10'



Park a vehicle
---------------------

curl --location --request POST 'http://localhost:9091/parkinglot/v1/api/park' \
--header 'Content-Type: application/json' \
--data-raw '{
    "registrationNumber":"KA-05-1019",
    "vehicleType": "MOTORCYCLE"
}'


UnPark a vehicle
---------------------------


curl --location --request POST 'http://localhost:9091/parkinglot/v1/api/leave?regdNo=KA-05-1004' \
--header 'Content-Type: application/json' \
--data-raw '{
    "registrationNumber":"KA-05-1022",
    "vehicleType": "BUS"
}'



Get Vehicle Details
------------------------------

curl --location --request GET 'http://localhost:9091/parkinglot/v1/api/details?regdNo=KA-05-1003' \
--header 'Content-Type: application/json' \
--data-raw '{
    "registrationNumber":"KA-05-1022",
    "vehicleType": "BUS"
}'








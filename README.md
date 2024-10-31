# Flight
A Java package that can be used to track flights one airport to another. Includes a data file of all the world's airports.

# Use
To use the package:
- Create a new file in your Java project's directory titled "Flight.java", or download the file and place it in your directory.
- Read the documentation section of this readme.

# Documentation
Variables:
- String: home, dest, homeName, destName.
- double: flightTime, elapsedTime, eta, distance.
- int: speed.
- ArrayList Double: homeCoords, destCoords.

Methods:
- getHomeName() & getDestName() (String)
- getHomeCoords() & getDestCoords() (ArrayList Double)
- getSpeed() (int)
- getDistance() (double, in km)
- getPlaces() (ArrayList String), returns a 2-item list, first item being home and second being dest.
- getTimes() (ArrayList Double), returns a 2-item list, first item being the total flight time, the second being the elapsed time, third being the eta.
  - Values for the eta and elapsedTime variables do not have methods associated with altering them.
- printFlightInfo() (String), returns a string in this format: {home} - {dest} : Distance - {distance} km, Flight Time: {flightTime} hours at {speed} km/hr.
- calcDistance() (Double), returns the distance between 2 airports using the Haversine Formula.
- findAirportCoords() (ArrayList Double), takes in an airport code and an integer, the integer value is unimportant to the user, it is for use in the constructor.
- readFile() (ArrayList String), returns lines in a file. Used within the package to parse through the file contaning all the airports.

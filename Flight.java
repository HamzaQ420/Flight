import java.util.ArrayList;
import java.text.DecimalFormat;
import java.io.BufferedReader; import java.io.IOException;
import java.io.FileReader;

public class Flight {
    private String home;
    private String dest;

    private static String homeName;
    private static String destName;

    private double flightTime; // distance (km) / speed (km/h)
    private double elapsedTime;
    private double eta; // flightTime - elapsedTime

    private static ArrayList<Double> homeCoords;
    private static ArrayList<Double> destCoords;

    private int speed; // (km/h)
    private double distance; // (km)

    // Getter Methods
    public String getHomeName() { return homeName; }
    public String getDestName() { return destName; }

    public static ArrayList<Double> getHomeCoords() { return homeCoords; }
    public static ArrayList<Double> getDestCoords() { return destCoords; }

    public int getSpeed() { return speed; }
    public double getDistance() { return distance; }
    
    public ArrayList<String> getPlaces() { ArrayList<String> ret = new ArrayList<>(); ret.add(home); ret.add(dest); return ret; }
    public ArrayList<Double> getTimes() { ArrayList<Double> ret = new ArrayList<>(); ret.add(flightTime); ret.add(elapsedTime); ret.add(eta); return ret; }

    public Flight(String h, String d, int s) {
        home = h; dest = d; speed = s;
        homeCoords = findAirportCoords(h, 0); destCoords = findAirportCoords(d, 1);
        distance = calcDistance();
        flightTime = (double) distance / speed;
        eta = flightTime;
        elapsedTime = 0;
    }

    public String printFlightInfo() {
        DecimalFormat formatter = new DecimalFormat("#.##");
        return getHomeName() + " - " + getDestName() + ": Distance - " + formatter.format(getDistance()) + " km, Flight Time: " + formatter.format(getTimes().get(0)) + " hours at " + Integer.toString(getSpeed()) + " km/hr.";
    }
    
    // Uses the Haversine Formula to calculate the distance between the 2 airports.
    public static double calcDistance() {
        double R = 6371.0088;
        homeCoords = getHomeCoords(); destCoords = getDestCoords();
        double lat1 = homeCoords.get(0); double lon1 = homeCoords.get(1);
        double lat2 = destCoords.get(0); double lon2 = destCoords.get(1);
        double dLat = Math.toRadians(lat2 - lat1); lat1 = Math.toRadians(lat1);
        double dLon = Math.toRadians(lon2 - lon1); lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));

        return R * c;
    }

    public static ArrayList<Double> findAirportCoords(String airportCode, int op) {
        ArrayList<Double> coords = new ArrayList<>();
        String subline = "";
        ArrayList<String> lines = readFile("airports.txt");
        ArrayList<String> sublineItems = new ArrayList<>();
        String line = "";

        // Looking at each line in a file.
        for (int x = 0; x < lines.size(); x++) {
            line = lines.get(x);
            // Separating the line by commas.
            for (int y = 0; y < line.length(); y++) {
                if (line.substring(y, y + 1).equals(",")) {
                    sublineItems.add(subline); subline = "";
                } else { subline = subline + line.substring(y, y + 1); }
            }

            if (sublineItems.get(2).replace("\"", "").equals(airportCode)) {
                coords.add(Double.parseDouble(sublineItems.get(5).replace("\"", "")));
                coords.add(Double.parseDouble(sublineItems.get(6).replace("\"", "")));
                if (op == 0) { homeName = sublineItems.get(4).replace("\"", ""); }
                else { destName = sublineItems.get(4).replace("\"", ""); }
                break;
            } subline = ""; sublineItems.clear();
        }
        return coords;
    }

    public static ArrayList<String> readFile(String filename) {
        ArrayList<String> lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Process each line
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}

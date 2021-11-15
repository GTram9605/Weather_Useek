package za.ac.nplinnovations.weatheruseek.pojos;

import java.io.Serializable;

public class LocationData implements Serializable {
    private String name;
    private double latitude;
    private double longitude;

    private LocationData() {
    }

    private LocationData(String name, double latitude, double longitude) {
    }

    public static LocationData getInstance(String name, double latitude, double longitude) {
        return new LocationData(name, latitude, longitude);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

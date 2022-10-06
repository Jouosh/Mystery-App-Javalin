package dev.martin.dtos;

public class Coordinate {

    double lattitude;
    double longitude;
    String nsHemisphere;
    String ewHemisphere;

    public Coordinate() {
    }

    public Coordinate(double lattitude, double longitude, String nsHemisphere, String ewHemisphere) {
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.nsHemisphere = nsHemisphere;
        this.ewHemisphere = ewHemisphere;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNsHemisphere() {
        return nsHemisphere;
    }

    public void setNsHemisphere(String nsHemisphere) {
        this.nsHemisphere = nsHemisphere;
    }

    public String getEwHemisphere() {
        return ewHemisphere;
    }

    public void setEwHemisphere(String ewHemisphere) {
        this.ewHemisphere = ewHemisphere;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "latitude=" + lattitude +
                ", longitude=" + longitude +
                ", nsHemi='" + nsHemisphere + '\'' +
                ", ewHemi='" + ewHemisphere + '\'' +
                '}';
    }
}

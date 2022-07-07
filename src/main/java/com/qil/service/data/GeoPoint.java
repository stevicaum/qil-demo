package com.qil.service.data;

public class GeoPoint {
  private final double lat;
  private final double lon;

  public GeoPoint(double lat, double lon){
    this.lat=lat;
    this.lon=lon;
  }
  public final double getLat() {
    return this.lat;
  }

  public final double getLon() {
    return this.lon;
  }

}

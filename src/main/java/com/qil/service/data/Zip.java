package com.qil.service.data;

import com.qil.service.data.GeoPoint;
import java.util.Date;

public class Zip {
  private String code;
  private String city;
  private String state;
  private String county;
  private String area_code;
  private GeoPoint location;
  private Date date = new Date();

  protected Zip(){

  }

  public Zip(String code, String city, String state, String county, String area_code, GeoPoint location) {
    this.code = code;
    this.city = city;
    this.state = state;
    this.county = county;
    this.area_code = area_code;
    this.location = location;
  }

  public String getCode() {
    return code;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCounty() {
    return county;
  }

  public String getArea_code() {
    return area_code;
  }

  public GeoPoint getLocation() {
    return location;
  }

  public Date getDate() {
    return date;
  }
}

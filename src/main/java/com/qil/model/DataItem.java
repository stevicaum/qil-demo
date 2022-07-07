package com.qil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataItem {

  public static String FIELD_DATE = "date";
  public static String FIELD_META = "meta";
  public static String FIELD_DATA = "data";
//  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;
  private Set<String> meta;
  private String data;

  public DataItem(){

  }
  public DataItem(LocalDateTime date, Set<String> meta, String data) {
    this.date = date;
    this.meta = meta;
    this.data = data;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public Set<String> getMeta() {
    return meta;
  }

  @JsonIgnore
  public String getStringMeta() {
    return meta.stream().collect(Collectors.joining(", ", "[", "]"));
  }

  public String getData() {
    return data;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public void setMeta(Set<String> meta) {
    this.meta = meta;
  }

  public void setData(String data) {
    this.data = data;
  }
}

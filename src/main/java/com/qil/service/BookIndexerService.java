package com.qil.service;

import com.qil.es.IndexService;
import com.qil.service.data.GeoPoint;
import com.qil.service.data.Zip;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class BookIndexerService {

  private final IndexService indexService;

  public BookIndexerService(IndexService indexService){
    this.indexService = indexService;
  }


  public boolean indexBook(){
    InputStream inputStream = this.getClass().getResourceAsStream("/book.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    try {
      String line = reader.readLine();
      String chapter = "";
      while (line != null) {
        System.out.println(line);
        if(!line.trim().isBlank()){
          chapter+=line;
        } else {
          indexService.index(Map.of("chapter", chapter));
          chapter = "";
        }
        line = reader.readLine();
      }
    }catch (Exception e){
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }

}

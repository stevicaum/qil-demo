package com.qil.service;

import com.qil.es.ClientService;
import com.qil.es.IndexService;
import com.qil.service.data.GeoPoint;
import com.qil.service.data.Zip;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZipsService {

  private final IndexService indexService;
  private List<Zip> zips = new ArrayList<>();
  private Random rand = new Random();

  public ZipsService(IndexService indexService) {
    this.indexService = indexService;
    init();
  }

  private void init() {
    InputStream inputStream = this.getClass().getResourceAsStream("/all_us_zipcodes.csv");
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    try {
      //Skip first line
      reader.readLine();
      String line = reader.readLine();
      while (line != null) {
        System.out.println(line);
        String[] tokens = line.split(",");
        if (tokens.length == 7) {
          Zip zip = new Zip(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
              new GeoPoint(Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6])));
          zips.add(zip);
        }
        line = reader.readLine();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    if(zips.size()<=0){
      throw new IllegalStateException("Array zips cant be empty");
    }
  }

  public Zip getRandomZip() {
    int item= Math.abs(rand.nextInt(zips.size()));
    return zips.get(item);
  }

  public void indexZips(){
    List<Zip> indexList = new ArrayList<>();
    for (int i = 0; i < 20000 ; i++) {
      indexList.add(getRandomZip());
      if(indexList.size()>=100) {
        System.out.println("Indexing "+((i+1))+" "+indexService.indexBulk(indexList));
        indexList = new ArrayList<>();
      }
    }
  }
}

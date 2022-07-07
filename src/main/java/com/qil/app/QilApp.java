package com.qil.app;

import com.qil.es.ClientService;
import com.qil.es.IndexService;
import com.qil.service.BookIndexerService;
import com.qil.service.ZipsService;
import java.io.IOException;

public class QilApp {
  public static void main(String[] args) throws IOException {
    IndexService indexService = new IndexService(new ClientService().getClient());

    ZipsService zipsService = new ZipsService(indexService);
    zipsService.indexZips();

    BookIndexerService bookIndexerService = new BookIndexerService(indexService);
    bookIndexerService.indexBook();
  }
}

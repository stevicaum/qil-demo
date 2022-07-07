package com.qil.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.UpdateRequest;

import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import com.qil.model.DataItem;
import com.qil.service.data.Zip;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IndexService {
  public static final String DATA_INDEX = "data_index";
  public static final String BOOK_INDEX = "book_index";
  private final ElasticsearchClient client;

  public IndexService(final ElasticsearchClient client) {
    this.client = client;
  }

  public boolean index(Set<String> metaSet, String body) {
    DataItem dataItem = new DataItem(LocalDateTime.now(), metaSet, body);
    try {
      client.index(IndexRequest.of(r -> r.index(DATA_INDEX).document(dataItem)));
      return Boolean.TRUE;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Boolean.FALSE;
  }

  public boolean indexBulk(List<Zip> dataItems) {
    BulkRequest.Builder br = new BulkRequest.Builder();
    dataItems.forEach(item -> br.operations(op -> op.create(idx -> idx.index(DATA_INDEX).document(item))));
    try {
      BulkResponse result = client.bulk(br.build());
      return !isFailed(result);
    } catch (IOException e) {
      System.out.println(e);
      return false;
    }
  }

  public boolean index(Map<String, String> chapter) {
    try {
      client.index(IndexRequest.of(r -> r.index(BOOK_INDEX).document(chapter)));
      return true;
    } catch (IOException e) {
      System.out.println(e);
      return false;
    }
  }

  private boolean isFailed(BulkResponse result) {
    if (result.errors()) {
      for (BulkResponseItem item : result.items()) {
        if (item.error() != null) {
          return true;
        }
      }
    }
    return false;
  }

}

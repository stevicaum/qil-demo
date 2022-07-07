package com.qil.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.cluster.HealthResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

public class ClientService {

  private final ElasticsearchClient client;

  public ClientService() throws IOException {
    //    RestClientBuilder builder = RestClient.builder(HttpHost.create("http://127.0.0.1:9200"))
//        .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
    //    RestClient restClient = builder.build();
    RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
    final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper(mapper));
    client = new ElasticsearchClient(transport);
    HealthResponse response = client.cluster().health();
    System.out.println("ES status:"+response.status().jsonValue());

  }

  public ElasticsearchClient getClient(){
    return client;
  }
}

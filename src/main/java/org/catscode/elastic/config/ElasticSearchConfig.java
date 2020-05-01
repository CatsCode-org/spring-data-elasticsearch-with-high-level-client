package org.catscode.elastic.config;

import lombok.Setter;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Setter
@Configuration
@ConfigurationProperties(value = "elastic-client", ignoreUnknownFields = false)
public class ElasticSearchConfig {

    private String host;
    private Integer port;
    private String schema;
    private Integer timeout;

    @Bean(destroyMethod = "close")
    RestHighLevelClient elasticsearchClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, schema))
                .setRequestConfigCallback(
                        requestConfigBuilder -> requestConfigBuilder
                                .setConnectTimeout(timeout)
                                .setConnectionRequestTimeout(timeout)
                                .setSocketTimeout(timeout)));
    }

    @Bean
    ElasticsearchRestTemplate elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }
}

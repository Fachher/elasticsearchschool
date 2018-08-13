package de.syed.elasticsearchschool;

import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShipmentRepository extends ClientObtainer{

    public void findByTrackingNumber(String trackingNumber) throws IOException {
        System.out.println("Find Shipment by tracking number");

        highLevelCall((client) -> {
//            BoolQueryBuilder query = QueryBuilders.boolQuery()
//                    .must(matchTrackingNumber(trackingNumber));
//                    .should(matchMarkedAsCurrent())
//                    .should(matchProjectPages())
//                    .should(matchPhrase(queryTerm).boost(3f));

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termQuery("trackingNumber", trackingNumber));
//            searchSourceBuilder.query(query);
//            searchSourceBuilder.postFilter(query);
            SearchRequest searchRequest = new SearchRequest("shipment");
//            searchRequest.types("doc");
//            searchRequest.routing("routing");
//            searchRequest.routing("shipment");
            searchRequest.source(searchSourceBuilder);
            try {
                // TODO: This is actually provided by request parameter
                Pageable pageable = PageRequest.of(0,5);
                SearchResponse search = client.search(searchRequest);
                SearchHits hits = search.getHits();

                PageImpl<ShipmentDto> shipmentDtos = convertHitsToJson(hits, pageable);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private PageImpl<ShipmentDto> convertHitsToJson(SearchHits h, Pageable pageRequest) {
        long totalHits = h.getTotalHits();

        final List<ShipmentDto> result = Optional.of(h)
                .map(SearchHits::getHits)
                .map(Arrays::stream)
                .orElse(Stream.empty())
                .map(this::convertToShipmentDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageRequest, totalHits);
    }

    private ShipmentDto convertToShipmentDto(SearchHit sh) {
        // TODO: to be implemented
        System.out.println(sh.getSourceAsString());
        return new ShipmentDto();
    }

}

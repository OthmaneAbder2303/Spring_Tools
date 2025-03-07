package ma.mycomp.demo.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
public class PageableResponse<T> extends PageImpl<T> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PageableResponse(
            @JsonProperty("content") List<T> content,
            @JsonProperty("number") int number,
            @JsonProperty("size") int size,
            @JsonProperty("totalElements") long totalElements,
            @JsonProperty("last") boolean last,
            @JsonProperty("first") boolean first,
            @JsonProperty("totalPages") int totalPages,
            @JsonProperty("numberOfElements") int numberOfElements,
            @JsonProperty("pageable") JsonNode pageable,
            @JsonProperty("sort") JsonNode sort) {

        super(content, PageRequest.of(number, size), totalElements);
    }
}

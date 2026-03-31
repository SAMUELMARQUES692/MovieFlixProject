package dev.com.movieflix.mapper;

import dev.com.movieflix.dto.request.StreamingRequest;
import dev.com.movieflix.dto.response.StreamingResponse;
import dev.com.movieflix.model.StreamingModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static StreamingModel toStreaming(StreamingRequest streamingRequest) {
        return StreamingModel
                .builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(StreamingModel streaming) {
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }

}

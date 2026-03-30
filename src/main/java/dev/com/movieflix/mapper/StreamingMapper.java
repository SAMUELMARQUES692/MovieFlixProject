package dev.com.movieflix.mapper;

import dev.com.movieflix.dto.request.StreamingRequest;
import dev.com.movieflix.dto.response.StreamingResponse;
import dev.com.movieflix.model.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest streamingRequest) {
        return Streaming
                .builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming) {
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }

}

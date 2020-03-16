package com.iot.video.app.flink.processor;
import com.iot.video.app.flink.util.VideoEventStringData;
import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonDeserializer extends AbstractDeserializationSchema<VideoEventStringData> {
    private static final long serialVersionUID = 1L;
    
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public VideoEventStringData deserialize(byte[] bytes) throws IOException {
        VideoEventStringData videoEvent = new VideoEventStringData();
        videoEvent = mapper.readValue(bytes, VideoEventStringData.class);
        return videoEvent;
    }
}

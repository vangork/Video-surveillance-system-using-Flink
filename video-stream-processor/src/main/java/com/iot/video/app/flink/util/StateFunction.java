package com.iot.video.app.flink.util;

import com.iot.video.app.flink.processor.VideoMotionDetector;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StateFunction extends RichMapFunction<VideoEventStringData, VideoEventStringData> {
    private static final long serialVersionUID = 1L;
    
    private transient ValueState<VideoEventStringData> state;

    @Override
    public VideoEventStringData map(VideoEventStringData in) throws Exception {
        VideoEventStringData existingState = null;
        Properties prop = PropertyFileReader.readPropertyFile();
        final String processedImageDir = prop.getProperty("processed.output.dir");

        if (state!=null){
            existingState = state.value();
        }

        List<VideoEventStringData> it = new ArrayList<VideoEventStringData>();
        it.add(in);

        VideoEventStringData processedState = VideoMotionDetector.detectMotion(in.getCameraId(),it.iterator(),processedImageDir,existingState);

        if (processedState!= null){
            state.update(processedState);
        }
        return processedState;
    }

    public void open(Configuration config) {
        ValueStateDescriptor<VideoEventStringData> descriptor =
                new ValueStateDescriptor<>(
                        "prev_state",
                        VideoEventStringData.class);
        state = getRuntimeContext().getState(descriptor);
    }
}
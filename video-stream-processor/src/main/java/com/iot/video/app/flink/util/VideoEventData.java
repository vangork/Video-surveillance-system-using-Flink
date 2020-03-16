package com.iot.video.app.flink.util;

import java.io.Serializable;


/**
 * Java Bean to hold JSON message
 */
public class VideoEventData implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cameraId;
	private String timestamp;
	private int rows;
	private int cols;
	private int type;
	private String data;
	
	public String getCameraId() {
		return cameraId;
	}
	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}

/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gcm.server;

import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * GCM message.
 *
 * <p>
 * Instances of this class are immutable and should be created using a
 * {@link Builder}. Examples:
 *
 * <strong>Simplest message:</strong>
 * <pre><code>
 * Message message = new Message.Builder().build();
 * </pre></code>
 *
 * <strong>Message with optional attributes:</strong>
 * <pre><code>
 * Message message = new Message.Builder()
 *    .collapseKey(collapseKey)
 *    .timeToLive(3)
 *    .delayWhileIdle(true)
 *    .dryRun(true)
 *    .highPriority(true);
 *    .build();
 * </pre></code>
 *
 * <strong>Message with optional attributes and payload data:</strong>
 * <pre><code>
 * Message message = new Message.Builder()
 *    .collapseKey(collapseKey)
 *    .timeToLive(3)
 *    .delayWhileIdle(true)
 *    .addData("key1", "value1")
 *    .addData("key2", "value2")
 *    .build();
 * </pre></code>
 */
public class Message implements Serializable {

  private final String collapseKey;
  private final Boolean delayWhileIdle;
  private final Boolean dryRun;
  private final Boolean highPriority;
  private final Integer timeToLive;
  private final JSONObject data;

  public static final class Builder {

    private JSONObject data;

    // optional parameters
    private String collapseKey;
    private Boolean delayWhileIdle;
    private Boolean dryRun;
    private Boolean highPriority;
    private Integer timeToLive;

    public Builder() {
      this.data = new JSONObject();
    }

    /**
     * Sets the collapseKey property.
     */
    public Builder collapseKey(String value) {
      collapseKey = value;
      return this;
    }

    /**
     * Sets the delayWhileIdle property (default value is {@literal false}).
     */
    public Builder delayWhileIdle(Boolean value) {
        delayWhileIdle = value;
        return this;
    }

    /**
     * Sets the dryRun property (default value is {@literal false}).
     */
    public Builder dryRun(Boolean value) {
        dryRun = value;
        return this;
    }

    /**
     * Sets the highPriority property (default value is {@literal false}).
     */
    public Builder highPriority(Boolean value) {
      highPriority = value;
      return this;
    }

    /**
     * Sets the time to live, in seconds.
     */
    public Builder timeToLive(Integer value) {
      timeToLive = value;
      return this;
    }

    /**
     * Adds a key/value pair to the payload data.
     */
    public Builder addData(String key, String value) {
      data.put(key, value);
      return this;
    }

    public Builder setData(JSONObject data) {
      this.data.clear();
      this.data.putAll(data);
      return this;
    }

    public Message build() {
      return new Message(this);
    }

  }

  private Message(Builder builder) {
    collapseKey = builder.collapseKey;
    delayWhileIdle = builder.delayWhileIdle;
    dryRun = builder.dryRun;
    highPriority = builder.highPriority;
    data = builder.data;
    timeToLive = builder.timeToLive;
  }

  /**
   * Gets the collapse key.
   */
  public String getCollapseKey() {
    return collapseKey;
  }

  /**
   * Gets the delayWhileIdle flag.
   */
  public Boolean isDelayWhileIdle() {
    return delayWhileIdle;
  }

  /**
   * Gets the dryRun flag.
   */
  public Boolean isDryRun() {
        return dryRun;
    }

  /**
   * Gets the highPriority flag.
   */
  public Boolean isHighPriority() {
    return highPriority;
  }

  /**
   * Gets the time to live (in seconds).
   */
  public Integer getTimeToLive() {
    return timeToLive;
  }

  /**
   * Gets the payload data, which is immutable.
   */
  public JSONObject getData() {
    return data;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("Message(");
    if (collapseKey != null) {
      builder.append("collapseKey=").append(collapseKey).append(", ");
    }
    if (timeToLive != null) {
      builder.append("timeToLive=").append(timeToLive).append(", ");
    }
    if (delayWhileIdle != null) {
      builder.append("delayWhileIdle=").append(delayWhileIdle).append(", ");
    }
    if (dryRun != null) {
        builder.append("dryRun=").append(dryRun).append(", ");
    }
    if (dryRun != null) {
      builder.append("highPriority=").append(highPriority).append(", ");
    }
    if (!data.isEmpty()) {
      builder.append(data.toJSONString());
    }
    if (builder.charAt(builder.length() - 1) == ' ') {
      builder.delete(builder.length() - 2, builder.length());
    }
    builder.append(")");
    return builder.toString();
  }

}

package com.apitest.callapi.callapi.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class AirApiResponseVo {

  AirResponse response;

  @Data
  public static class AirResponse {
    AirBody body;
  }

  @Data
  public static class AirBody{
    int totalCount;
    int pageNo;
    int numOfRows;
    List<AirInfo> items;
  }

  @Data
  public static class AirInfo{
    String pm25Grade1h;
    String pm10Value24;
    String pm10Grade1h;
    String pm10Value;
    String pm25Value;
    String mangName;
    String stationName;
    String sidoName;
    String pm25Value24;
    String pm25Grade;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date dataTime;
    String pm10Grade;
  }

}

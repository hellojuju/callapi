package com.apitest.callapi.callapi.vo;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class WeatherApiResponseVo {

  WeatherResponse response;

  @Data
  public static class WeatherResponse{
    WeatherBody body;
  }
  @Data
  public static class WeatherBody{
    int totalCount;
    int pageNo;
    int numOfRows;
    WeatherItem items;
  }

  @Data
  public static class WeatherItem {
    List<WeatherInfo> item;
  }

  @Data
  public static class WeatherInfo {
    Date announceTime;
    String numEf;
    String regId;
    String ta;
    String wf;
    String wfCd;
    String rnYn;
  }
}

package com.apitest.callapi.callapi.util;


import com.apitest.callapi.callapi.vo.AirApiRequestVo;
import com.apitest.callapi.callapi.vo.AirApiResponseVo;
import com.apitest.callapi.callapi.vo.WeatherApiRequestVo;
import com.apitest.callapi.callapi.vo.WeatherApiResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CallApiUtilTest {

  @Autowired
  CallApiUtil callApiUtil;

  @Test
  @DisplayName("Air OpenApi 호출 테스트")
  void testFnd() {
    String serviceKey = "key";
    int pageNo = 1;
    int numOfRows = 100;
    String sidoName = "전국";
    AirApiRequestVo data = AirApiRequestVo.builder()
        .serviceKey(serviceKey)
        .ver("1.3")
        .pageNo(pageNo)
        .returnType("json")
        .numOfRows(numOfRows)
        .sidoName(sidoName)
        .build();
    AirApiResponseVo tm = callApiUtil.callAir(data);
    System.out.println("");
    System.out.println("tm ::: " + tm.toString());
    System.out.println("");
    Assertions.assertThat(tm).isNotNull();
  }

  @Test
  @DisplayName("Weather OpenApi 호출 테스트")
  void testWeather() {
    String serviceKey = "key";
    int pageNo = 1;
    int numOfRows = 100;
    String regId = "11B10101";
    WeatherApiRequestVo data = WeatherApiRequestVo.builder().
        serviceKey(serviceKey)
        .dataType("JSON")
        .numOfRows(numOfRows)
        .pageNo(pageNo)
        .regId(regId)
        .build();
    WeatherApiResponseVo tm = callApiUtil.callWeather(data);
    System.out.println("");
    System.out.println("tm ::: " + tm.toString());
    System.out.println("");
    Assertions.assertThat(tm).isNotNull();
  }
}
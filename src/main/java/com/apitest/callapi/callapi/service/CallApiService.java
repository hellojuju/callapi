package com.apitest.callapi.callapi.service;

import com.apitest.callapi.callapi.util.CallApiUtil;
import com.apitest.callapi.callapi.vo.AirApiRequestVo;
import com.apitest.callapi.callapi.vo.AirApiResponseVo;
import com.apitest.callapi.callapi.vo.WeatherApiRequestVo;
import com.apitest.callapi.callapi.vo.WeatherApiResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CallApiService {

  private final CallApiUtil callApiUtil;

  public WeatherApiResponseVo getWeatherInfo(WeatherApiRequestVo item) {
    return callApiUtil.callWeather(item);
  }

  public AirApiResponseVo getFndsInfo(AirApiRequestVo item) {
    return callApiUtil.callAir(item);
  }
}

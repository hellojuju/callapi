package com.apitest.callapi.callapi.controller;

import com.apitest.callapi.callapi.service.CallApiService;
import com.apitest.callapi.callapi.vo.AirApiRequestVo;
import com.apitest.callapi.callapi.vo.AirApiResponseVo;
import com.apitest.callapi.callapi.vo.WeatherApiRequestVo;
import com.apitest.callapi.callapi.vo.WeatherApiResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(description = "API호출")
@RequestMapping(value = "/call", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class CallApiController {

  private final CallApiService callApiService;

  @ApiOperation(value = "날씨정보 호출")
  @PostMapping("/wthrInfo")
  public ResponseEntity<WeatherApiResponseVo> getWthrInfo(@RequestBody WeatherApiRequestVo item) throws IOException {
    return ResponseEntity.ok().body(callApiService.getWeatherInfo(item));
  }

  @ApiOperation(value = "미세먼지정보 호출")
  @PostMapping("/fndsInfo")
  public ResponseEntity<AirApiResponseVo> getFndsInfo(@RequestBody AirApiRequestVo item) throws IOException {
    return ResponseEntity.ok().body(callApiService.getFndsInfo(item));
  }

}

package com.apitest.callapi.callapi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "날씨 정보")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Builder
public class WeatherApiRequestVo {

  @ApiModelProperty(notes = "인증키")
  private String serviceKey;
  @ApiModelProperty(notes = "한 페이지 결과 수")
  private int numOfRows;
  @ApiModelProperty(notes = "페이지 번호")
  private int pageNo;
  @ApiModelProperty(notes = "응답자료형식")
  private String dataType;
  @ApiModelProperty(notes = "예보구역코드")
  private String regId;


}

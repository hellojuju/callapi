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

@ApiModel(description = "미세먼지 정보")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Builder
public class AirApiRequestVo {

  @ApiModelProperty(notes = "서비스키", required = true)
  String serviceKey;
  @ApiModelProperty(notes = "데이터표출방식")
  String returnType;
  @ApiModelProperty(notes = "한 페이지 결과 수", required = true, example = "100")
  int numOfRows;
  @ApiModelProperty(notes = "페이지 번호", required = true)
  int pageNo;
  @ApiModelProperty(notes = "주소", example = "서울")
  String sidoName;
  @ApiModelProperty(notes = "오퍼레이션 버전", example = "1.0")
  String ver;

}

package com.apitest.callapi.callapi.util;

import com.apitest.callapi.callapi.vo.AirApiRequestVo;
import com.apitest.callapi.callapi.vo.AirApiResponseVo;
import com.apitest.callapi.callapi.vo.WeatherApiRequestVo;
import com.apitest.callapi.callapi.vo.WeatherApiResponseVo;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Slf4j
@Component
public class CallApiUtil {

  public AirApiResponseVo callAir(AirApiRequestVo requestVo) {
    AirApiResponseVo items = new AirApiResponseVo();

    WebClient client = WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(HttpClient.from(getTcpClient())))
        .uriBuilderFactory(getUrlBuilderFactory())
        .build();

    try {
      String sido = URLEncoder.encode("전국", "UTF-8");

      Mono<AirApiResponseVo> result = client.get()
          .uri(URLBuilder -> URLBuilder.scheme("http").host("apis.data.go.kr").path(
                  "/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty")
              .queryParam("serviceKey", requestVo.getServiceKey())   // 인코딩되는거 조심
              .queryParam("sidoName", sido)           // 한글깨짐 조심
              .queryParam("numOfRows", requestVo.getNumOfRows())
              .queryParam("pageNo", requestVo.getPageNo())
              .queryParam("returnType", "json")
              .queryParam("ver", requestVo.getVer())
              .build())
          .acceptCharset(Charset.forName("UTF-8"))
          .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
          .retrieve()
//          .toEntity(AirApiResponseVo.class); // --> 이걸로 하려면 MoNo<ResponseEntity<AirApiResponseVo>> 이렇게 받아야한다.
          .onStatus(status -> status.is4xxClientError()
                  || status.is5xxServerError()
              , clientResponse ->
                  clientResponse.bodyToMono(String.class)
                      .map(body -> new RuntimeException(body)))
          .bodyToMono(AirApiResponseVo.class)
          .doOnError(throwable -> log.error("err : {}", throwable.getMessage()));

      log.debug("result  :::: {}", result.block().toString());
      AirApiResponseVo airItem = result.block();  // block을 사용하면 동기식으로 처리할수있다.
      // TODO https://bravenamme.github.io/2021/01/07/web_client/
      log.debug("airItem.getDateTime :::: {}", airItem.getResponse().getBody().getTotalCount());
      items = airItem;

    } catch (Exception e) {
      log.error(e.toString());
    }
    return items;
  }


  public WeatherApiResponseVo callWeather(WeatherApiRequestVo requestVo) {
    WeatherApiResponseVo items = new WeatherApiResponseVo();

    WebClient client = WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(HttpClient.from(getTcpClient())))
        .uriBuilderFactory(getUrlBuilderFactory())
        .build();

    try {
      Mono<WeatherApiResponseVo> result = client.get()
          .uri(URLBuilder -> URLBuilder.scheme("http").host("apis.data.go.kr").path(
                  "/1360000/VilageFcstMsgService/getLandFcst")
              .queryParam("serviceKey", requestVo.getServiceKey())   // 인코딩되는거 조심
              .queryParam("regId", requestVo.getRegId())
              .queryParam("numOfRows", requestVo.getNumOfRows())
              .queryParam("pageNo", requestVo.getPageNo())
              .queryParam("dataType", "JSON")
              .build())
          .acceptCharset(Charset.forName("UTF-8"))
          .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
          .retrieve()
          .onStatus(status -> status.is4xxClientError()
                  || status.is5xxServerError()
              , clientResponse ->
                  clientResponse.bodyToMono(String.class)
                      .map(body -> new RuntimeException(body)))
          .bodyToMono(WeatherApiResponseVo.class);

      log.debug("result  :::: {}", result);
      WeatherApiResponseVo weatherItem = result.block();
      log.debug("weatherItem.getDateTime :::: {}",
          weatherItem.getResponse().getBody().getTotalCount());
      items = weatherItem;

    } catch (Exception e) {
      log.error(e.toString());
    }
    return items;
  }


  private DefaultUriBuilderFactory getUrlBuilderFactory() {
    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
    factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
    return factory;
  }

  private TcpClient getTcpClient() {
    return TcpClient
        .create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
        .doOnConnected(connection -> {
          connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
          connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
        });
  }
}
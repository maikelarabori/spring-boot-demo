package com.app.demo.http.endpoint;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.app.demo.StubProvider;
import com.app.demo.dto.DataElementGroupRoot;
import com.app.demo.dto.DataElementRoot;
import com.app.demo.http.AuthWebClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

@ExtendWith({MockitoExtension.class})
@DisplayName("Testing DataElementsGetEndPoint")
class DataElementsGetEndPointTest {

  @Mock
  private AuthWebClient authWebClient;

  private GetEndPoint<EndpointParams, DataElementRoot> dataElementsGetEndPoint;

  //@Test
  @DisplayName("Testing consume method with success")
  void testConsume_withSuccess() {
    // Given
    final DataElementRoot stubResponse = StubProvider.stubDataElementRoot();
    final EndpointParams stubParams = new EndpointParams(false, "anyAttr1,anyAttr2");
    dataElementsGetEndPoint = new DataElementsGetEndPoint(authWebClient);

    // When
    when(authWebClient.get(Mockito.<Class<DataElementRoot>> any(), anyString(),
        anyBoolean(), anyString())).thenReturn(stubResponse);
    final DataElementRoot actualResponse = dataElementsGetEndPoint.consume(stubParams);

    // Then
    assertThat(actualResponse, is(notNullValue()));
    assertThat(actualResponse.getDataElements(), hasSize(1));
    assertThat(actualResponse.getDataElements().get(0).getDisplayName(),
        is(equalTo("memberName")));
    assertThat(actualResponse.getDataElements().get(0).getId(),
        is(equalTo("memberId")));
    assertThat(actualResponse.getDataElements().get(0).getDataElementGroups(), hasSize(1));
  }

  @Test
  @DisplayName("Testing consume method with error: null params")
  void testConsume_withNullParams() {
    // Given
    final EndpointParams nullParams = null;
    dataElementsGetEndPoint = new DataElementsGetEndPoint(authWebClient);

    // Then
    final Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      dataElementsGetEndPoint.consume(nullParams);
    });
    assertThat("params: cannot be null", is(equalTo(exception.getMessage())));
  }

  @Test
  @DisplayName("Testing consume method with error: params.fields is null/empty")
  void testConsume_withNullFieldsParams() {
    // Given
    final EndpointParams emptyField = new EndpointParams(false, null);
    dataElementsGetEndPoint = new DataElementsGetEndPoint(authWebClient);

    // Then
    final Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      dataElementsGetEndPoint.consume(emptyField);
    });
    assertThat("params.fields: cannot be null/empty", is(equalTo(exception.getMessage())));
  }
}

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
import com.app.demo.dto.VehicleMakeRoot;
import com.app.demo.http.AuthWebClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
@DisplayName("Testing VehicleMakesGetEndPoint")
class VehicleMakesGetEndPointTest {

  @Mock
  private AuthWebClient authWebClient;

  private GetEndPoint<EndpointParams, VehicleMakeRoot> vehicleMakesGetEndPoint;

  //@Test
  @DisplayName("Testing consume method with success")
  void testConsume_withSuccess() {
    // Given
    final VehicleMakeRoot stubResponse = StubProvider.stubVehicleMakeRoot();
    final EndpointParams stubParams = new EndpointParams(false, "anyAttr1, anyAttr2");
    vehicleMakesGetEndPoint = new VehicleMakesGetEndPoint(authWebClient);

    // When
    when(authWebClient.get(Mockito.<Class<VehicleMakeRoot>> any(), anyString(),
        anyBoolean(), anyString())).thenReturn(stubResponse);
    final VehicleMakeRoot actualResponse = vehicleMakesGetEndPoint.consume(stubParams);

    // Then
    assertThat(actualResponse, is(notNullValue()));
    assertThat(actualResponse.getVehicleMakes(), hasSize(1));
    assertThat(actualResponse.getVehicleMakes().get(0).getDisplayName(),
        is(equalTo("makeName")));
    assertThat(actualResponse.getVehicleMakes().get(0).getId(),
        is(equalTo("makeId")));
    assertThat(actualResponse.getVehicleMakes().get(0).getVehicleMakeGroups(), hasSize(1));
  }

  @Test
  @DisplayName("Testing consume method with error: null params")
  void testConsume_withNullParams() {
    // Given
    final EndpointParams nullParams = null;
    vehicleMakesGetEndPoint = new VehicleMakesGetEndPoint(authWebClient);

    // Then
    final Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      vehicleMakesGetEndPoint.consume(nullParams);
    });
    assertThat("params: cannot be null", is(equalTo(exception.getMessage())));
  }

  @Test
  @DisplayName("Testing consume method with error: params.fields is null/empty")
  void testConsume_withNullFieldsParams() {
    // Given
    final EndpointParams emptyField = new EndpointParams(false, null);
    vehicleMakesGetEndPoint = new VehicleMakesGetEndPoint(authWebClient);

    // Then
    final Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      vehicleMakesGetEndPoint.consume(emptyField);
    });
    assertThat("params.fields: cannot be null/empty", is(equalTo(exception.getMessage())));
  }
}

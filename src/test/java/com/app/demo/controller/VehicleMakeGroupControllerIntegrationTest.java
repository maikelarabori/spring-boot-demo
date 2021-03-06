package com.app.demo.controller;

import static com.app.demo.StubProvider.stubVehicleMakeGroupRoot;
import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.app.demo.dto.VehicleMakeGroupRoot;
import com.app.demo.http.endpoint.EndpointParams;
import com.app.demo.http.endpoint.GetEndPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@Execution(SAME_THREAD)
@DisplayName("VehicleMakeGroupController: integration test + doc")
public class VehicleMakeGroupControllerIntegrationTest {

  @MockBean
  private GetEndPoint<EndpointParams, VehicleMakeGroupRoot> vehicleMakeGroupsGetEndPoint;

  private MockMvc mockMvc;
  private RestDocumentationResultHandler documentationHandler;

  @BeforeEach
  void setUp(final WebApplicationContext webApplicationContext,
             final RestDocumentationContextProvider restDocumentation) {
    this.documentationHandler = document("{method-name}",
        preprocessResponse(prettyPrint()));

    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(documentationConfiguration(restDocumentation))
        .alwaysDo(this.documentationHandler)
        .build();
  }

  @Test
  @DisplayName("Testing /api/vehicles/makes/groups with success")
  public void getVehiclesMakesGroups() throws Exception {
    // Given
    final VehicleMakeGroupRoot stubResponse = stubVehicleMakeGroupRoot();

    // When
    when(vehicleMakeGroupsGetEndPoint.consume(any(EndpointParams.class))).thenReturn(stubResponse);

    // Then
    this.mockMvc.perform(get("/api/vehicles/makes/groups")).andExpect(status().isFound())
        .andDo(this.documentationHandler.document(
            responseFields(
                subsectionWithPath("[]").description("The collection of make groups").type(ARRAY),
                fieldWithPath("[].id").description("The group id").type(STRING),
                fieldWithPath("[].name").description("The group name").type(STRING),
                fieldWithPath("[].makes")
                    .description("The list of makes associated with this group").type(ARRAY)),
            responseHeaders(headerWithName("Content-Type")
                .description("The Content-Type of the payload"))));
  }

  @Test
  @DisplayName("Testing /api/vehicles/makes/groups with error: Not Found")
  public void getVehiclesMakesGroups_notFound() throws Exception {
    // Given
    final VehicleMakeGroupRoot nullResponse = null;

    // When
    when(vehicleMakeGroupsGetEndPoint.consume(any(EndpointParams.class))).thenReturn(nullResponse);

    // Then
    this.mockMvc.perform(get("/api/vehicles/makes/groups"))
        .andExpect(status().isNotFound());
  }
}

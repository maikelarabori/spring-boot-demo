package com.app.demo.controller;

import static com.app.demo.StubProvider.stubDataElementGroupRoot;
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

import com.app.demo.dto.DataElementGroupRoot;
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
@DisplayName("DataElementGroupController: integration test + doc")
public class DataElementGroupControllerIntegrationTest {

  @MockBean
  private GetEndPoint<EndpointParams, DataElementGroupRoot> dataElementGroupsGetEndPoint;

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
  @DisplayName("Testing getDataElementGroups with success")
  public void getDataElementGroups() throws Exception {
    // Given
    final DataElementGroupRoot stubResponse = stubDataElementGroupRoot();

    // When
    when(dataElementGroupsGetEndPoint.consume(any(EndpointParams.class))).thenReturn(stubResponse);

    // Then
    this.mockMvc.perform(get("/api/data/element/groups")).andExpect(status().isFound())
        .andDo(this.documentationHandler.document(
            responseFields(
                subsectionWithPath("[]").description("The collection of groups").type(ARRAY),
                fieldWithPath("[].id").description("The group id").type(STRING),
                fieldWithPath("[].name").description("The group name").type(STRING),
                fieldWithPath("[].members")
                    .description("The list of members associated with this group").type(ARRAY)),
            responseHeaders(headerWithName("Content-Type")
                .description("The Content-Type of the payload"))));
  }

  @Test
  @DisplayName("Testing getDataElementGroups with error: Not Found")
  public void getDataElementGroups_notFound() throws Exception {
    // Given
    final DataElementGroupRoot nullResponse = null;

    // When
    when(dataElementGroupsGetEndPoint.consume(any(EndpointParams.class))).thenReturn(nullResponse);

    // Then
    this.mockMvc.perform(get("/api/data/element-groups"))
        .andExpect(status().isNotFound());
  }
}

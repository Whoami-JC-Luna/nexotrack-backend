package com.nexologistics.nexotrack.shared.error;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void return404WhenResourceNotFound() throws Exception{
        mockMvc.perform(get("/test/not-found"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.title").value("Resource Not Found"))
            .andExpect(jsonPath("$.detail").value("Usuario no encontrado"));
    }




    @Test
    void return400WhenValidationFails() throws Exception {
        String json = """
            {
                "nombre":"",
                "password":"1234"
            }
            """;

        mockMvc.perform(
                post("/test/bad-request")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.title").value("Bad Request"))
            .andExpect(jsonPath("$.detail").value("Error de validación"))
            .andExpect(jsonPath("$.errors.nombre[0]")
                .value("El nombre no puede estar vacio"))
            .andExpect(jsonPath("$.errors.password[0]")
                .value("La constraseña debe tener al menos 6 caracteres"));

    }
    @Test
    void return400WhenJsonIsMalformed() throws Exception {
        String malformedJson = "{\"nombre\": ";

        mockMvc.perform(
                post("/test/bad-request")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(malformedJson)
            )
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.detail").value("Failed to read request"))
            .andExpect(result -> {
                String body = result.getResponse().getContentAsString();
                assertThat(body)
                    .doesNotContain("com.nexologistics")
                    .doesNotContain("JSON parse error")
                    .doesNotContain("Exception");
            });
        }

    @Test
    void shouldReturn500WithoutExposingInternalDetails() throws Exception {
        mockMvc.perform(get("/test/server-error"))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
            .andExpect(jsonPath("$.correlationId").isNotEmpty())
            .andExpect(jsonPath("$.status").value(500))
            .andExpect(jsonPath("$.title").value("Internal Server Error"))
            .andExpect(jsonPath("$.detail")
                .value("Error interno, inténtalo más tarde"))

            .andExpect(result -> {
                String body = result.getResponse().getContentAsString();
                assertThat(body)
                    .doesNotContain("INTERNAL_SECRET_MARKER")
                    .doesNotContain("IllegalStateException")
                    .doesNotContain("com.nexologistics")
                    .doesNotContain("java.")
                    .doesNotContain("\tat ")
                    .doesNotContain("trace")
                    .doesNotContainIgnoringCase("select ");

            });
    }
}

package ru.scarlet.company;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.scarlet.company.controllers.DeanController;
import ru.scarlet.company.dtos.DeanRequest;
import ru.scarlet.company.entities.Dean;
import ru.scarlet.company.entities.Faculty;
import ru.scarlet.company.excpetions.NotFound.DeanNotFoundException;
import ru.scarlet.company.services.DeanService;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DeanTests {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DeanService deanService;

    @InjectMocks
    private DeanController deanController;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Mock
    private HttpServletRequest httpServletRequest = new MockHttpServletRequest();

    @Test
    public void testCreateDean() throws Exception {
        DeanRequest deanRequest = new DeanRequest("Name", "Surname", "Patronymic");
        Dean dean = new Dean();
        dean.setFirstName(deanRequest.getFirstName());
        dean.setLastName(deanRequest.getLastName());
        dean.setPatronymic(deanRequest.getPatronymic());
        dean.setTeachingCourses(new ArrayList<>());
        Mockito.when(deanService.createDean(deanRequest)).thenReturn(dean);
        Mockito.when(httpServletRequest.getRequestURI()).thenReturn("/api/v1/university/deans/");
//        mockMvc
//                .perform(MockMvcRequestBuilders.post("/api/v1/university/deans/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(deanRequest))
//                ).andExpect(MockMvcResultMatchers.status().isCreated());
        Assertions.assertEquals(deanController.createDean(deanRequest, httpServletRequest).getBody().getTeachingCourses(), new ArrayList<>());
//        Assertions.assertEquals();
    }

    @Test
    public void getDeanTest() {
        Mockito.when(deanService.getDeanDtoById(-1)).thenThrow(DeanNotFoundException.class);
        Assertions.assertThrows(DeanNotFoundException.class, () -> deanController.getDeanById(-1));
    }

    @Test
    public void getDeanTestValid() {
        Faculty faculty = Faculty.builder().name("fac1").build();
        Dean dean = Dean.builder()
                .oid(1)
                .firstName("firstName")
                .lastName("lastName")
                .teachingCourses(new ArrayList<>())
                .patronymic("patronymic")
                .faculty(faculty).build();
        faculty.setDean(dean);

        Mockito.when(deanService.getDeanById(Mockito.anyInt())).thenReturn(dean);
        Assertions.assertEquals(deanService.getDeanById(1), dean);
    }
}

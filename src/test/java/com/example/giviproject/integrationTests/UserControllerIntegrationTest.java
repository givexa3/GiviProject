package com.example.giviproject.integrationTests;

import com.example.giviproject.dto.UserDTO;
import com.example.giviproject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService; // Mocking UserService

    //I think this is the part when we need testContainers because we are not sure if user was deleted or not
    //so lets learn testContainers also and write test based on that
    @Test
    void testDeleteUser() throws Exception {
        // Mocking UserService to return null (no exception) when deleteUser method is called
        Mockito.doNothing().when(userService).deleteUser(anyLong());

        // Perform a DELETE request to "/deleteUser" endpoint with userId as request parameter
        //it is very important to match url otherwise we might face errors
        //in our case url is /user
        mockMvc.perform(MockMvcRequestBuilders.delete("/user")
                        .param("userId", "123") // Example userId
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Expect status 200 OK
                .andExpect(MockMvcResultMatchers.content().string("User Deleted Successfully")); // Expect response body

        // Verify that userService.deleteUser method is called with correct userId
        Mockito.verify(userService, Mockito.times(1)).deleteUser(123L);

    }

    @Test
    void getUser() throws Exception {
        UserDTO userDTO = UserDTO
                .builder()
                .age(24)
                .name("Givi")
                .build();

        when(userService.getUserById(anyLong())).thenReturn(userDTO);

        //.andExpect(jsonPath("$.name").value(userDTO.getName())):
        // This line asserts that there is a field named "name" in the JSON response,
        // and its value matches the name of the mockUser object.
        // It ensures that the name field in the response matches the expected user name.

        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .param("userId", "123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(userDTO.getName()))
                .andExpect(jsonPath("$.age").value(userDTO.getAge()));;

        Mockito.verify(userService, Mockito.times(1)).getUserById(123L);
    }

    @Test
    void createUser() throws Exception {

        UserDTO userDTO = UserDTO
                .builder()
                .age(24)
                .name("Givi")
                .build();

        //instead of writing .content("{\"name\": \"Givi\", \"age\": 24}"))
        //in mockMvc we will follow this approach which will create string json for us more readable and maintanable
        //rather than introducing lots of strings
        //we can refactor this code later on
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(userDTO);

        Mockito.doNothing().when(userService).createUser(any());

        // Perform a POST request to "/createUser" endpoint with mockUserDTO as request body
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)) // Example JSON body
                .andExpect(status().isCreated());

        // Verify that userService.createUser method is called with the correct UserDTO object
        Mockito.verify(userService).createUser(any(UserDTO.class));
    }

    @Test
    void updateUser() throws Exception {
        // Prepare a mock UserDTO object representing the updated user data
        UserDTO userDTO = UserDTO
                .builder()
                .age(35)
                .name("Updated Name")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(userDTO);

        Mockito.doNothing().when(userService).createUser(any());

        // Perform a PUT request to "/updateUser" endpoint with mockUserDTO and userId as request parameters
        mockMvc.perform(MockMvcRequestBuilders.put("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent) // Example JSON body
                        .param("userId", "123")) // Example userId
                .andExpect(status().isOk()); // Expect status 200 OK

        // Verify that userService.updateUser method is called with the correct UserDTO object and userId
        Mockito.verify(userService, Mockito.times(1)).updateUser(any(UserDTO.class), anyLong());
    }
}

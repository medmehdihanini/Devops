package tn.esprit.tp1yassinejallouli4twin7;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.tp1yassinejallouli4twin7.controllers.FoyerRestController;
import tn.esprit.tp1yassinejallouli4twin7.entities.Foyer;
import tn.esprit.tp1yassinejallouli4twin7.services.IMPL.FoyerServicesImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(MockitoJUnitRunner.class)
public class FoyerControllerTest {

    @Mock
    private FoyerServicesImpl foyerServices;

    @InjectMocks
    private FoyerRestController foyerController;

    private MockMvc mockMvc;

    @Test
    public void testGetAllFoyers() throws Exception {
        // Given
        List<Foyer> expectedFoyers = new ArrayList<>();
        // Add some sample Foyer objects to the list (you may need to adjust this based on your actual data)
        expectedFoyers.add(new Foyer());
        expectedFoyers.add(new Foyer());
        expectedFoyers.add(new Foyer());

        // Mock the behavior of the service method to return the list of expected Foyer objects
        when(foyerServices.getAllFoyer()).thenReturn(expectedFoyers);

        // Set up MockMvc for the controller
        mockMvc = MockMvcBuilders.standaloneSetup(foyerController).build();

        // Perform an HTTP GET request to "/foyer/all" endpoint and verify the response
        mockMvc.perform(get("/foyer/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(expectedFoyers.size()));

        // Verify that the service method is called once
        verify(foyerServices, times(1)).getAllFoyer();
    }
    @Test
    public void testGetFoyer() throws Exception {
        // Given
        long id = 1L;
        Foyer expectedFoyer = new Foyer();
        // Mock the behavior of the service method to return the expected Foyer object
        when(foyerServices.getFoyer(id)).thenReturn(expectedFoyer);

        // Set up MockMvc for the controller
        mockMvc = MockMvcBuilders.standaloneSetup(foyerController).build();

        // Perform an HTTP GET request to "/foyer/getOne/{id}" endpoint and verify the response
        mockMvc.perform(get("/foyer/getOne/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Verify that the service method is called once with the correct parameter
        verify(foyerServices, times(1)).getFoyer(id);
    }

    @Test
    public void testAddFoyer() throws Exception {
        // Given
        Foyer foyerToAdd = new Foyer();
        // Mock the behavior of the service method to return the added Foyer object
        when(foyerServices.ajouterFoyer(any(Foyer.class))).thenReturn(foyerToAdd);

        // Set up MockMvc for the controller
        mockMvc = MockMvcBuilders.standaloneSetup(foyerController).build();

        // Perform an HTTP POST request to "/foyer/add" endpoint with the Foyer object in the request body and verify the response
        mockMvc.perform(post("/foyer/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // Example JSON content
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Verify that the service method is called once with the correct parameter
        verify(foyerServices, times(1)).ajouterFoyer(any(Foyer.class));
    }

    @Test
    public void testUpdateFoyer() throws Exception {
        // Given
        Foyer foyerToUpdate = new Foyer();
        // Mock the behavior of the service method to return the updated Foyer object
        when(foyerServices.updateFoyer(any(Foyer.class))).thenReturn(foyerToUpdate);

        // Set up MockMvc for the controller
        mockMvc = MockMvcBuilders.standaloneSetup(foyerController).build();

        // Perform an HTTP POST request to "/foyer/update" endpoint with the Foyer object in the request body and verify the response
        mockMvc.perform(post("/foyer/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // Example JSON content
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Verify that the service method is called once with the correct parameter
        verify(foyerServices, times(1)).updateFoyer(any(Foyer.class));
    }

    @Test
    public void testDeleteFoyer() throws Exception {
        // Given
        long id = 1L;

        // Set up MockMvc for the controller
        mockMvc = MockMvcBuilders.standaloneSetup(foyerController).build();

        // Perform an HTTP DELETE request to "/foyer/delete/{id}" endpoint and verify the response
        mockMvc.perform(delete("/foyer/delete/{id}", id))
                .andExpect(status().isOk());

        // Verify that the service method is called once with the correct parameter
        verify(foyerServices, times(1)).supprimerFoyer(id);
    }


}
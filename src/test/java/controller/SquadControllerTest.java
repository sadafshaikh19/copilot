package controller;

import com.copilot.controller.SquadController;
import com.copilot.model.Squad;
import com.copilot.repository.SquadRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = SquadController.class)
public class SquadControllerTest {
    @Autowired
    private SquadController squadController;

    @MockBean
    private SquadRepository squadRepository;

    @Test
    public void testGetAllSquads() {
        // Arrange
        // Mock data
        Squad squad1 = new Squad("test", "This is a test field", "abc");
        Squad squad2 = new Squad("test2", "This is a test field", "def");
        List<Squad> squads = List.of(squad1, squad2);

        // Mock repository behavior
        when(squadRepository.findAll()).thenReturn(squads);

        // Act
        ResponseEntity<List<Squad>> responseEntity = squadController.getAllSquads(null);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(squads, responseEntity.getBody());
    }

    @Test
    public void testGetSquadById() {
        // Arrange
        long squadId = 1L;
        // Mock data
        Squad squad = new Squad("test", "This is a test field", "abc");
        Optional<Squad> squadData = Optional.of(squad);

        // Mock repository behavior
        when(squadRepository.findById(squadId)).thenReturn(squadData);

        // Act
        ResponseEntity<Squad> responseEntity = squadController.getSquadById(squadId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(squad, responseEntity.getBody());
    }

    @Test
    public void testGetSquadByIdNotFound() {
        // Arrange
        long squadId = 1L;
        when(squadRepository.findById(squadId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Squad> responseEntity = squadController.getSquadById(squadId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testUpdateSquad() {
        // Arrange
        long squadId = 1L;

        // Mock data
        Squad existingSquad = new Squad("test", "This is a test field", "abc");
        Squad updatedSquad = new Squad("test2", "This is a test field", "def");

        // Mock repository behavior
        when(squadRepository.findById(squadId)).thenReturn(Optional.of(existingSquad));
        when(squadRepository.save(any(Squad.class))).thenReturn(updatedSquad);

        // Act
        ResponseEntity<Squad> responseEntity = squadController.updateSquad(squadId, updatedSquad);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedSquad, responseEntity.getBody());
    }

    @Test
    public void testUpdateSquadNotFound() {
        // Arrange
        long squadId = 1L;
        // Mock data
        Squad updatedSquad = new Squad("test2", "This is a test field", "def");

        // Mock repository behavior for a non-existing squad
        when(squadRepository.findById(squadId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Squad> responseEntity = squadController.updateSquad(squadId, updatedSquad);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testDeleteSquad() {
        // Arrange
        long squadId = 1L;

        // Mock repository behavior
        doNothing().when(squadRepository).deleteById(squadId);

        // Act
        ResponseEntity<HttpStatus> responseEntity = squadController.deleteSquad(squadId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteSquadError() {
        // Arrange
        long squadId = 1L;

        // Mock repository behavior for a deletion error
        doThrow(new RuntimeException("Simulated internal server error")).when(squadRepository).deleteById(squadId);

        // Act
        ResponseEntity<HttpStatus> responseEntity = squadController.deleteSquad(squadId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteAllSquads() {

        // Mock repository behavior
        doNothing().when(squadRepository).deleteAll();

        // Act
        ResponseEntity<HttpStatus> responseEntity = squadController.deleteAllSquads();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteAllSquadsError() {

        // Mock repository behavior for a deletion error
        doThrow(new RuntimeException("Simulated internal server error")).when(squadRepository).deleteAll();

        // Act
        ResponseEntity<HttpStatus> responseEntity = squadController.deleteAllSquads();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}




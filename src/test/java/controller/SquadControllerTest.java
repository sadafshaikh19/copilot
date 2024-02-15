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
}




package edu.craptocraft.medimanage.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import edu.craptocraft.medimanage.entity.Patients;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientsControllerTest {

    @Autowired
    private PatientsController controller;
    
    private static Patients entity = null;

    @Test
    public void test_create() {

        entity = new Patients("test@gmail.com", "test");
        ResponseEntity<?> response = controller.create(entity);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Patients createdEntity = (Patients) response.getBody();

        assertEquals(entity.getId(), createdEntity.getId());
        assertEquals(entity.getEmail(), createdEntity.getEmail());
        assertEquals(entity.getName(), createdEntity.getName());

        ResponseEntity<?> responseDelete = controller.delete(createdEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_getAll() {
        ResponseEntity<?> response = controller.getAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<?> entities = (List<?>) response.getBody();
        assertNotNull(entities);

    }

    @Test
    public void test_getOne() {

        entity = new Patients("test@gmail.com", "test");

        ResponseEntity<?> createdResponse = controller.create(entity);

        assertEquals(HttpStatus.CREATED, createdResponse.getStatusCode());

        ResponseEntity<?> response = controller.getOne(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Patients retrievedEntity = (Patients) response.getBody();

        assertNotNull(retrievedEntity);
        assertEquals(entity.getId(), retrievedEntity.getId());
        assertEquals(entity.getEmail(), retrievedEntity.getEmail());
        assertEquals(entity.getName(), retrievedEntity.getName());


        ResponseEntity<?> responseDelete = controller.delete(retrievedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_update() {
        entity = new Patients("test@gmail.com", "test");

        ResponseEntity<?> createdResponse = controller.create(entity);

        Patients createdEntity = (Patients) createdResponse.getBody();

        int entityId = createdEntity.getId();

        assertEquals(entity.getId(), entityId, 0);

        entity.setEmail("test2@gmail.com");
        entity.setName("test2");

        ResponseEntity<?> updatedResponse = controller.update(entityId, entity);

        assertNotNull(updatedResponse);

        assertEquals(HttpStatus.CREATED, updatedResponse.getStatusCode());

        Patients updatedEntity = (Patients) updatedResponse.getBody();

        assertEquals(createdEntity.getId(), updatedEntity.getId());
        assertEquals("test2@gmail.com", updatedEntity.getEmail());
        assertEquals("test2", updatedEntity.getName());

        ResponseEntity<?> responseDelete = controller.delete(updatedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());

    }

    @Test
    public void test_delete() {

        ResponseEntity<?> beforeDeleteResponse = controller.getAll();

        List<?> beforeDeleteEntities = (List<?>) beforeDeleteResponse.getBody();

        assertNotNull(beforeDeleteEntities);

        entity = new Patients("test@gmail.com", "test");

        ResponseEntity<?> response = controller.delete(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<?> afterDeleteResponse = controller.getAll();

        List<?> afterDeleteEntities = (List<?>) afterDeleteResponse.getBody();

        assertNotNull(afterDeleteEntities);
        assertEquals(afterDeleteEntities.size(), beforeDeleteEntities.size());

    }
}

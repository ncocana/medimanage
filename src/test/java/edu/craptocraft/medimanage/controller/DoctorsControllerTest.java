package edu.craptocraft.medimanage.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import edu.craptocraft.medimanage.entity.Doctors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorsControllerTest {

    @Autowired
    private DoctorsController controller;
    
    private static Doctors entity = null;

    @Test
    public void test_create() {

        entity = new Doctors("test@gmail.com", "password", "test");
        ResponseEntity<?> response = controller.create(entity);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Doctors createdEntity = (Doctors) response.getBody();

        assertEquals(entity.getId(), createdEntity.getId());
        assertEquals(entity.getEmail(), createdEntity.getEmail());
        assertEquals(entity.getPassword(), createdEntity.getPassword());
        assertEquals(entity.getName(), createdEntity.getName());
        assertEquals(entity.getLastLog(), createdEntity.getLastLog());
        assertEquals(entity.getSession(), createdEntity.getSession());

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

        entity = new Doctors("test@gmail.com", "password", "test");

        ResponseEntity<?> createdResponse = controller.create(entity);

        assertEquals(HttpStatus.CREATED, createdResponse.getStatusCode());

        ResponseEntity<?> response = controller.getOne(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Doctors retrievedEntity = (Doctors) response.getBody();

        assertNotNull(retrievedEntity);
        assertEquals(entity.getId(), retrievedEntity.getId());
        assertEquals(entity.getEmail(), retrievedEntity.getEmail());
        assertEquals(entity.getPassword(), retrievedEntity.getPassword());
        assertEquals(entity.getName(), retrievedEntity.getName());
        assertEquals(entity.getLastLog(), retrievedEntity.getLastLog());
        assertEquals(entity.getSession(), retrievedEntity.getSession());

        ResponseEntity<?> responseDelete = controller.delete(retrievedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_update() {
        entity = new Doctors("test@gmail.com", "password", "test");

        ResponseEntity<?> createdResponse = controller.create(entity);

        Doctors createdEntity = (Doctors) createdResponse.getBody();

        int entityId = createdEntity.getId();

        assertEquals(entity.getId(), entityId, 0);

        entity.setEmail("test2@gmail.com");
        entity.setPassword("password2");
        entity.setName("test2");
        entity.setLastLog(LocalDate.of(2023, 05, 15));
        
        Random random = new Random();
        Integer session = random.nextInt(1000000000);  // Generates a random number between 0 and 1000000000
        entity.setSession(session);
        assertNotEquals(0, entity.getSession(), 0);

        ResponseEntity<?> updatedResponse = controller.update(entityId, entity);

        assertNotNull(updatedResponse);

        assertEquals(HttpStatus.CREATED, updatedResponse.getStatusCode());

        Doctors updatedEntity = (Doctors) updatedResponse.getBody();

        assertEquals(createdEntity.getId(), updatedEntity.getId());
        assertEquals("test2@gmail.com", updatedEntity.getEmail());
        assertEquals("password2", updatedEntity.getPassword());
        assertEquals("test2", updatedEntity.getName());
        assertEquals(LocalDate.of(2023, 05, 15), updatedEntity.getLastLog());
        assertEquals(entity.getSession(), updatedEntity.getSession());

        ResponseEntity<?> responseDelete = controller.delete(updatedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());

    }

    @Test
    public void test_delete() {

        ResponseEntity<?> beforeDeleteResponse = controller.getAll();

        List<?> beforeDeleteEntities = (List<?>) beforeDeleteResponse.getBody();

        assertNotNull(beforeDeleteEntities);

        entity = new Doctors("test@gmail.com", "password", "test");

        ResponseEntity<?> response = controller.delete(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<?> afterDeleteResponse = controller.getAll();

        List<?> afterDeleteEntities = (List<?>) afterDeleteResponse.getBody();

        assertNotNull(afterDeleteEntities);
        assertEquals(afterDeleteEntities.size(), beforeDeleteEntities.size());

    }

}

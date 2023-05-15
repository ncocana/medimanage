package edu.craptocraft.medimanage.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import edu.craptocraft.medimanage.entity.Medicines;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicinesControllerTest {

    @Autowired
    private MedicinesController controller;
    
    private static Medicines entity = null;

    @Test
    public void test_create() {

        entity = new Medicines("test", 10.0f, 5.0f);
        ResponseEntity<?> response = controller.create(entity);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Medicines createdEntity = (Medicines) response.getBody();

        assertEquals(entity.getId(), createdEntity.getId());
        assertEquals(entity.getName(), createdEntity.getName());
        assertEquals(entity.getTmax(), createdEntity.getTmax());
        assertEquals(entity.getTmin(), createdEntity.getTmin());

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

        entity = new Medicines("test", 10.0f, 5.0f);

        ResponseEntity<?> createdResponse = controller.create(entity);

        assertEquals(HttpStatus.CREATED, createdResponse.getStatusCode());

        ResponseEntity<?> response = controller.getOne(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Medicines retrievedEntity = (Medicines) response.getBody();

        assertNotNull(retrievedEntity);
        assertEquals(entity.getId(), retrievedEntity.getId());
        assertEquals(entity.getName(), retrievedEntity.getName());
        assertEquals(entity.getTmax(), retrievedEntity.getTmax());
        assertEquals(entity.getTmin(), retrievedEntity.getTmin());


        ResponseEntity<?> responseDelete = controller.delete(retrievedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_update() {
        entity = new Medicines("test", 10.0f, 5.0f);

        ResponseEntity<?> createdResponse = controller.create(entity);

        Medicines createdEntity = (Medicines) createdResponse.getBody();

        int entityId = createdEntity.getId();

        assertEquals(entity.getId(), entityId, 0);

        entity.setName("test2");
        entity.setTmax(15.0f);
        entity.setTmin(10.0f);

        ResponseEntity<?> updatedResponse = controller.update(entityId, entity);

        assertNotNull(updatedResponse);

        assertEquals(HttpStatus.CREATED, updatedResponse.getStatusCode());

        Medicines updatedEntity = (Medicines) updatedResponse.getBody();

        assertEquals(createdEntity.getId(), updatedEntity.getId());
        assertEquals("test2", updatedEntity.getName());
        assertEquals(15.0f, updatedEntity.getTmax(), 0f);
        assertEquals(10.0f, updatedEntity.getTmin(), 0f);

        ResponseEntity<?> responseDelete = controller.delete(updatedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());

    }

    @Test
    public void test_delete() {

        ResponseEntity<?> beforeDeleteResponse = controller.getAll();

        List<?> beforeDeleteEntities = (List<?>) beforeDeleteResponse.getBody();

        assertNotNull(beforeDeleteEntities);

        entity = new Medicines("test", 10.0f, 5.0f);

        ResponseEntity<?> response = controller.delete(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<?> afterDeleteResponse = controller.getAll();

        List<?> afterDeleteEntities = (List<?>) afterDeleteResponse.getBody();

        assertNotNull(afterDeleteEntities);
        assertEquals(afterDeleteEntities.size(), beforeDeleteEntities.size());

    }
}

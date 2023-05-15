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

import edu.craptocraft.medimanage.entity.Doctors;
import edu.craptocraft.medimanage.entity.Medicines;
import edu.craptocraft.medimanage.entity.Patients;
import edu.craptocraft.medimanage.entity.Prescriptions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrescriptionsControllerTest {

    @Autowired
    private PrescriptionsController controller;
    
    private static Prescriptions entity = null;

    @Test
    public void test_create() {

        entity = new Prescriptions(new Doctors(1), new Medicines(1), new Patients(1), LocalDate.of(2023, 05, 15));
        ResponseEntity<?> response = controller.create(entity);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Prescriptions createdEntity = (Prescriptions) response.getBody();

        assertEquals(entity.getId(), createdEntity.getId());
        assertEquals(entity.getIdDoctor(), createdEntity.getIdDoctor());
        assertEquals(entity.getIdMedicine(), createdEntity.getIdMedicine());
        assertEquals(entity.getIdPatient(), createdEntity.getIdPatient());

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

        entity = new Prescriptions(new Doctors(1), new Medicines(1), new Patients(1), LocalDate.of(2023, 05, 15));

        ResponseEntity<?> createdResponse = controller.create(entity);

        assertEquals(HttpStatus.CREATED, createdResponse.getStatusCode());

        ResponseEntity<?> response = controller.getOne(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Prescriptions retrievedEntity = (Prescriptions) response.getBody();

        assertNotNull(retrievedEntity);
        assertEquals(entity.getId(), retrievedEntity.getId());
        assertEquals(entity.getIdDoctor(), retrievedEntity.getIdDoctor());
        assertEquals(entity.getIdMedicine(), retrievedEntity.getIdMedicine());
        assertEquals(entity.getIdPatient(), retrievedEntity.getIdPatient());


        ResponseEntity<?> responseDelete = controller.delete(retrievedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_update() {

        entity = new Prescriptions(new Doctors(1), new Medicines(1), new Patients(1), LocalDate.of(2023, 05, 15));

        ResponseEntity<?> createdResponse = controller.create(entity);

        Prescriptions createdEntity = (Prescriptions) createdResponse.getBody();

        int entityId = createdEntity.getId();

        assertEquals(entity.getId(), entityId, 0);

        entity.setDate(LocalDate.of(2023, 06, 17));

        ResponseEntity<?> updatedResponse = controller.update(entityId, entity);

        assertNotNull(updatedResponse);

        assertEquals(HttpStatus.CREATED, updatedResponse.getStatusCode());

        Prescriptions updatedEntity = (Prescriptions) updatedResponse.getBody();

        assertEquals(createdEntity.getId(), updatedEntity.getId());
        assertEquals(createdEntity.getIdDoctor(), updatedEntity.getIdDoctor());
        assertEquals(createdEntity.getIdMedicine(), updatedEntity.getIdMedicine());
        assertEquals(createdEntity.getIdPatient(), updatedEntity.getIdPatient());
        assertEquals(LocalDate.of(2023, 06, 17), updatedEntity.getDate());

        ResponseEntity<?> responseDelete = controller.delete(updatedEntity.getId());

        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());

    }

    @Test
    public void test_delete() {

        ResponseEntity<?> beforeDeleteResponse = controller.getAll();

        List<?> beforeDeleteEntities = (List<?>) beforeDeleteResponse.getBody();

        assertNotNull(beforeDeleteEntities);

        entity = new Prescriptions(new Doctors(1), new Medicines(1), new Patients(1), LocalDate.of(2023, 05, 15));

        ResponseEntity<?> response = controller.delete(entity.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<?> afterDeleteResponse = controller.getAll();

        List<?> afterDeleteEntities = (List<?>) afterDeleteResponse.getBody();

        assertNotNull(afterDeleteEntities);
        assertEquals(afterDeleteEntities.size(), beforeDeleteEntities.size());

    }
}

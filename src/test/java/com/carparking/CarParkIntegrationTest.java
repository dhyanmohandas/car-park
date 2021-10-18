package com.carparking;

import com.carparking.model.ParkingSlot;
import com.carparking.model.ParkingSlotRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=RANDOM_PORT)
public class CarParkIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testInsertParkingSlot() throws Exception{
        final HttpEntity<ParkingSlotRequest> request = new HttpEntity<>(new ParkingSlotRequest("KA-01",2));
        final ResponseEntity<ParkingSlot> response = restTemplate.postForEntity("/parkingslots", request, ParkingSlot.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getVehicleNumber()).isEqualTo("KA-01");
        assertThat(response.getBody().getDurationInHours()).isEqualTo(2);

    }
    @Test
    public void putTest(){
        HttpEntity<ParkingSlotRequest> request = new HttpEntity<>(new ParkingSlotRequest("KA", 2));
        ResponseEntity<ParkingSlot> res = restTemplate.exchange("/parkingslots/2", HttpMethod.PUT, request, ParkingSlot.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK,res.getStatusCode());
    }

    @Test
    public void delete(){
        String entityUrl = "/parkingslots" + "/" + 2;
        HttpEntity<ParkingSlotRequest> request = new HttpEntity<>(new ParkingSlotRequest("KA", 2));
        ResponseEntity<ParkingSlot> res = restTemplate
                .exchange(entityUrl, HttpMethod.DELETE, request, ParkingSlot.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK,res.getStatusCode());
    }

}

package com.workshop22.workshop22.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workshop22.workshop22.models.RSVP;
import com.workshop22.workshop22.repo.RsvpRepository;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@RestController
@RequestMapping("/api")
public class RSVPRestController {
    @Autowired
    RsvpRepository rsvpRepository;

    @GetMapping(path="/rsvps", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllRsvp(){

        List<RSVP> rsvps = rsvpRepository.getAllRsvp();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for (RSVP r : rsvps) {
            arrBuilder.add(r.toJsonObject());
        }
        
        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Json.createObjectBuilder()
                    .add("rsvps",arrBuilder)
                    .build().toString());
    }

    @GetMapping(path="/rsvp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRsvpByName(@RequestParam(name="q")String name){

        List<RSVP> rsvps = rsvpRepository.getRsvpByName(name);

        if(rsvps.isEmpty()) {
            return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Json.createObjectBuilder()
                    .add("message", "rsvp with name like %s not found".formatted(name))
                    .build().toString());
        }

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for (RSVP r : rsvps) {
            arrBuilder.add(r.toJsonObject());
        }
        
        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Json.createObjectBuilder()
                    .add("rsvps",arrBuilder)
                    .build().toString());
    }

    @PostMapping(path="/rsvp", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<String> insertRSVP(RSVP rsvp){
        //try update 1st >> if updated = 0 >> either no id input or new id stated >> insert new record.
        Integer updated = rsvpRepository.updateRSVP(rsvp);
        Integer inserted = 0;

        if (updated == 0) {
            inserted = rsvpRepository.insertRSVP(rsvp);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Json.createObjectBuilder()
                            .add("update_count", updated)
                            .add("insert_count", inserted)
                            .build().toString());
    }

    @PutMapping(path="/rsvp/{email}", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> UpdateRsvpByName(RSVP rsvp,@PathVariable String email){
        Integer updated = rsvpRepository.updateRsvpByEmail(rsvp,email);

        if(updated <= 0) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Json.createObjectBuilder()
                             .add("message", "email %s not found".formatted(email))
                             .build().toString());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Json.createObjectBuilder()
                        .add("update_count", updated)
                        .build().toString());
    
    }

    @GetMapping(path="/rsvps/count")
    public ResponseEntity<String> getRsvpCount() {

        Integer count = rsvpRepository.getRsvpCount();

        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Json.createObjectBuilder()
                            .add("noOfRSVP", count)
                            .build().toString());
    }

    @DeleteMapping(path="/rsvp/{id}")
    public ResponseEntity<String> deleteRsvpById(@PathVariable("id")Integer id){
        Integer deleted = rsvpRepository.deleteRsvp(id);
        if(deleted <= 0) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Json.createObjectBuilder()
                             .add("message", "id %s not found".formatted(id))
                             .build().toString());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Json.createObjectBuilder()
                        .add("delete_count", deleted)
                        .build().toString());
    }
}

package com.ragul.assignment.dataserver.controller;

import com.ragul.assignment.dataserver.model.State;
import com.ragul.assignment.dataserver.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class StateController {
    @Autowired
    StateService stateService;

    @GetMapping(path = "codeToState/{code}")
    public ResponseEntity<State> codeToState(@PathVariable String code) {
        State state = stateService.findFirstByAbbreviation(code);

        if (isEmpty(state)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(state);
    }

    @GetMapping(path = "stateToCode/{state}")
    public ResponseEntity<State> stateToCode(@PathVariable String state) {
        State stateDb = stateService.findFirstByName(state);

        if (isEmpty(stateDb)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(stateDb);
    }

    private boolean isEmpty(State state) {
        return state == null;
    }
}

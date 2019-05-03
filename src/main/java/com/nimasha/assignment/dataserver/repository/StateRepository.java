package com.ragul.assignment.dataserver.repository;

import com.ragul.assignment.dataserver.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    State findFirstByName(String state);

    State findFirstByAbbreviation(String code);
}
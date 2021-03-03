package com.leverx.pets.transaction;

import com.leverx.pets.dto.response.ResponseEntity;

public interface Transactional {

    ResponseEntity save();

    void delete();

}

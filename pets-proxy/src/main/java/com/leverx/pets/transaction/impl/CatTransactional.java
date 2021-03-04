package com.leverx.pets.transaction.impl;

import com.leverx.pets.dto.request.CatRequest;
import com.leverx.pets.dto.response.CatResponse;
import com.leverx.pets.service.CatService;
import com.leverx.pets.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.leverx.pets.transaction.ConstantValues.CAT_ID;
import static com.leverx.pets.transaction.ConstantValues.USER_ID;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Data
@AllArgsConstructor
@Builder
public class CatTransactional implements Transactional {

    private final CatRequest catRequest;
    private final CatService catService;

    @Override
    public CatResponse executeSave() {
        long userId = (long) currentRequestAttributes().getAttribute(USER_ID, SCOPE_REQUEST);
        catRequest.setUserId(userId);
        CatResponse catResponse = catService.save(catRequest);
        currentRequestAttributes()
                .setAttribute(CAT_ID, catResponse.getId(), SCOPE_REQUEST);
        return catResponse;
    }

    @Override
    public void rollback() {
        long id = (long) currentRequestAttributes().getAttribute(CAT_ID, SCOPE_REQUEST);
        catService.deleteById(id);
    }

}

package com.revature.services;

import com.revature.models.Airplane;
import com.revature.repos.AirplaneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AirplaneService {

    private AirplaneRepo airplaneRepo;

    @Autowired
    public AirplaneService(AirplaneRepo AirplaneRepo) {
        airplaneRepo = AirplaneRepo;
    }

    public AirplaneService() {
        super();
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public List<Airplane> getAll() {
        return airplaneRepo.getAll();
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Airplane getById(int airplane_id) {
        return airplaneRepo.getById(airplane_id);
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Airplane getByType(String type) {
        return airplaneRepo.getByType(type);
    }
}

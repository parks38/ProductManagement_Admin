package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component // @autowired 받기 위해서
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {

    //repository folder 에성 상속받은 - ex. AdminRepo extends JpaRepository <Entity, Long>
    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;

}

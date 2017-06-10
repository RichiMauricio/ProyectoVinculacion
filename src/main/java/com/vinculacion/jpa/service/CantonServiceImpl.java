package com.vinculacion.jpa.service;

import com.google.common.collect.Lists;
import com.vinculacion.jpa.model.Canton;
import com.vinculacion.jpa.model.Establecimiento;
import com.vinculacion.jpa.repository.CantonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mauricio on 08/05/2017.
 */
@Service("cantonService")
@Transactional
public class CantonServiceImpl implements CantonService{

    @Autowired
    CantonRepository cantonRepository;

    //Buscar todos los cantones
    @Transactional(readOnly = true)
    public List<Canton> findAll() {
        return cantonRepository.findAll();
    }

    @Override
    public Canton findOne(Long cntId) {
        return cantonRepository.findOne(cntId);
    }
}

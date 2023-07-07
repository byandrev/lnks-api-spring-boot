package com.byandrev.lnks.services;

import com.byandrev.lnks.entities.LinkEntity;

import java.util.List;
import java.util.Optional;

public interface LinkService {

    public void create(LinkEntity link);

    public Optional<LinkEntity> getById(Long id);

    public List<LinkEntity> getAll();

    public void delete(Long id);

}

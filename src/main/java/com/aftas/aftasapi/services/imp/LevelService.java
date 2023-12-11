package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqLevel;
import com.aftas.aftasapi.dtos.ResLevel;
import com.aftas.aftasapi.exceptions.LevelNotFoundException;
import com.aftas.aftasapi.models.Level;
import com.aftas.aftasapi.repositories.LevelRepository;
import com.aftas.aftasapi.services.ILevelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelService implements ILevelService {
    private final LevelRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public ResLevel read(Integer id) {
        Optional<Level> level = repository.findById(id);
        if(level.isPresent())
            return modelMapper.map(level, ResLevel.class);
        throw new LevelNotFoundException("level not found with id " + id);
    }

    @Override
    public List<ResLevel> readAll() {
        List<ResLevel> levels = repository.findAll().stream()
                .map(level -> modelMapper.map(level, ResLevel.class))
                .toList();
        if(levels.isEmpty()) {
            throw new LevelNotFoundException("No Levels were found");
        }
        return levels;
    }

    @Override
    public Page<ResLevel> readAllPaginated(Pageable pageable) {
        Page<Level> paginatedLevels = repository.findAll(pageable);
        if(paginatedLevels.isEmpty()) {
            throw new LevelNotFoundException("No Levels were found");
        }
        return paginatedLevels.map(level -> modelMapper.map(level, ResLevel.class));
    }

    @Override
    public ResLevel create(ReqLevel reqLevel) {
        return null;
    }

    @Override
    public ResLevel update(ReqLevel reqLevel) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}

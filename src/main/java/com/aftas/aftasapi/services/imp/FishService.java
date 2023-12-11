package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqFish;
import com.aftas.aftasapi.dtos.ResFish;
import com.aftas.aftasapi.models.Fish;
import com.aftas.aftasapi.repositories.FishRepository;
import com.aftas.aftasapi.services.IFishService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FishService implements IFishService {
    private final FishRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public ResFish read(String s) {
        return null;
    }

    @Override
    public List<ResFish> readAll() {
        return null;
    }

    @Override
    public Page<ResFish> readAllPaginated(Pageable pageable) {
        return null;
    }

    @Override
    public ResFish create(ReqFish reqFish) {
        System.out.println("service called");
        Fish fish = modelMapper.map(reqFish, Fish.class);
        Fish savedFish = repository.save(fish);
        System.out.println("created fish + " + savedFish.getName());
        return modelMapper.map(savedFish, ResFish.class);
    }

    @Override
    public ResFish update(ReqFish reqFish, Integer id) {
        return null;
    }

    @Override
    public void delete(String s) {

    }
}

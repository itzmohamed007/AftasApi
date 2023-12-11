package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqMember;
import com.aftas.aftasapi.dtos.ResLevel;
import com.aftas.aftasapi.dtos.ResMember;
import com.aftas.aftasapi.exceptions.LevelNotFoundException;
import com.aftas.aftasapi.models.Level;
import com.aftas.aftasapi.models.Member;
import com.aftas.aftasapi.repositories.MemberRepository;
import com.aftas.aftasapi.services.IMemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements IMemberService {
    private final MemberRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public ResMember read(Integer id) {
        Optional<Member> member = repository.findById(id);
        if(member.isPresent())
            return modelMapper.map(member.get(), ResMember.class);
        throw new LevelNotFoundException("member not found with id " + id);
    }

    @Override
    public List<ResMember> readAll() {
        List<ResMember> members = repository.findAll().stream()
                .map(member -> modelMapper.map(member, ResMember.class))
                .toList();
        if(members.isEmpty()) {
            throw new LevelNotFoundException("No members were found");
        }
        return members;
    }

    @Override
    public Page<ResMember> readAllPaginated(Pageable pageable) {
        Page<Member> paginatedMembers = repository.findAll(pageable);
        if(paginatedMembers.isEmpty()) {
            throw new LevelNotFoundException("No Members were found");
        }
        return paginatedMembers.map(member -> modelMapper.map(member, ResMember.class));
    }

    @Override
    public ResMember create(ReqMember reqMember) {
        Member member = modelMapper.map(reqMember, Member.class);
        Member savedMember = repository.save(member);
        return modelMapper.map(savedMember, ResMember.class);
    }

    @Override
    public ResMember update(ReqMember reqMember, Integer id) {
        Optional<Member> level = repository.findById(id);
        if(level.isPresent()) {
            reqMember.setId(id);
            Member insertMember = repository.save(modelMapper.map(reqMember, Member.class));
            return modelMapper.map(insertMember, ResMember.class);
        }
        throw new LevelNotFoundException("No member was found with id " + id);
    }

    @Override
    public void delete(Integer id) {
        Optional<Member> member = repository.findById(id);
        if(member.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new LevelNotFoundException("Member not found with id " + id);
        }
    }
}

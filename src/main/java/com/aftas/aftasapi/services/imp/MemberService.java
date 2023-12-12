package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqMember;
import com.aftas.aftasapi.dtos.ResLevel;
import com.aftas.aftasapi.dtos.ResMember;
import com.aftas.aftasapi.exceptions.LevelNotFoundException;
import com.aftas.aftasapi.exceptions.MemberNotFoundException;
import com.aftas.aftasapi.exceptions.UniqueConstraintViolationException;
import com.aftas.aftasapi.models.Level;
import com.aftas.aftasapi.models.Member;
import com.aftas.aftasapi.repositories.MemberRepository;
import com.aftas.aftasapi.services.IMemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ResMember read(Integer num) {
        Optional<Member> member = repository.findById(num);
        if(member.isPresent())
            return modelMapper.map(member.get(), ResMember.class);
        throw new MemberNotFoundException("member not found with num " + num);
    }

    @Override
    public List<ResMember> readAll() {
        List<ResMember> members = repository.findAll().stream()
                .map(member -> modelMapper.map(member, ResMember.class))
                .toList();
        if(members.isEmpty()) {
            throw new MemberNotFoundException("No members were found");
        }
        return members;
    }

    @Override
    public Page<ResMember> readAllPaginated(Pageable pageable) {
        Page<Member> paginatedMembers = repository.findAll(pageable);
        if(paginatedMembers.isEmpty()) {
            throw new MemberNotFoundException("No Members were found");
        }
        return paginatedMembers.map(member -> modelMapper.map(member, ResMember.class));
    }

    @Override
    public ResMember create(ReqMember reqMember) {
        try {
            Member member = modelMapper.map(reqMember, Member.class);
            Member savedMember = repository.save(member);
            return modelMapper.map(savedMember, ResMember.class);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueConstraintViolationException("Violated unique constraint (document number)");
        }
    }

    @Override
    public ResMember update(ReqMember reqMember, Integer num) {
        Optional<Member> level = repository.findById(num);
        if(level.isPresent()) {
            try {
                reqMember.setNum(num);
                Member insertMember = repository.save(modelMapper.map(reqMember, Member.class));
                return modelMapper.map(insertMember, ResMember.class);
            } catch (DataIntegrityViolationException e) {
                throw new UniqueConstraintViolationException("Violated unique constraint (document number)");
            }
        } else throw new MemberNotFoundException("No member was found with num " + num);
    }

    @Override
    public void delete(Integer num) {
        Optional<Member> member = repository.findById(num);
        if (member.isPresent()) repository.deleteById(num);
        else throw new MemberNotFoundException("Member not found with num " + num);
    }
}

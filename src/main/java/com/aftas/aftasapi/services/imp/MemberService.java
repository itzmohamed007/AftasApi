package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqMember;
import com.aftas.aftasapi.dtos.ResMember;
import com.aftas.aftasapi.enums.IdentityDocumentType;
import com.aftas.aftasapi.exceptions.DocumentTypeViolationException;
import com.aftas.aftasapi.exceptions.MemberNotFoundException;
import com.aftas.aftasapi.exceptions.UniqueConstraintViolationException;
import com.aftas.aftasapi.models.User;
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
        Optional<User> member = repository.findById(num);
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
        Page<User> paginatedMembers = repository.findAll(pageable);
        if(paginatedMembers.isEmpty()) {
            throw new MemberNotFoundException("No Members were found");
        }
        return paginatedMembers.map(member -> modelMapper.map(member, ResMember.class));
    }

    @Override
    public ResMember create(ReqMember reqMember) {
        try {
            User member = modelMapper.map(reqMember, User.class);
            member.setIdentityDocument(IdentityDocumentType.valueOf(reqMember.getIdentityDocument()));
            User savedMember = repository.save(member);
            return modelMapper.map(savedMember, ResMember.class);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueConstraintViolationException("Violated unique constraint (document number)");
        } catch ( IllegalArgumentException e) {
            throw new DocumentTypeViolationException("Violated available documents types [CIN, RESIDENCE_CARD, PASSPORT]");
        }
    }

    @Override
    public ResMember update(ReqMember reqMember, Integer num) {
        Optional<User> level = repository.findById(num);
        if(level.isPresent()) {
            try {
                reqMember.setNum(num);
                User insertMember = repository.save(modelMapper.map(reqMember, User.class));
                return modelMapper.map(insertMember, ResMember.class);
            } catch (DataIntegrityViolationException e) {
                throw new UniqueConstraintViolationException("Violated unique constraint (document number)");
            }
        } else throw new MemberNotFoundException("No member was found with num " + num);
    }

    @Override
    public void delete(Integer num) {
        Optional<User> member = repository.findById(num);
        if (member.isPresent()) repository.deleteById(num);
        else throw new MemberNotFoundException("Member not found with num " + num);
    }
}

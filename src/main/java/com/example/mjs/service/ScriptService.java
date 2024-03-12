package com.example.mjs.service;

import com.example.mjs.dto.ScriptDTO;
import com.example.mjs.model.ScriptEntity;
import com.example.mjs.repository.ScriptRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScriptService {
    private final ScriptRepository scriptRepository;

    public void save(ScriptDTO scriptDTO) {
        ScriptEntity scriptEntity = ScriptEntity.toSaveEntity(scriptDTO);
        scriptRepository.save(scriptEntity);
    }

    public List<ScriptDTO> findAll() {
        List<ScriptEntity> scriptEntityList = scriptRepository.findAll();
        List<ScriptDTO> scriptDTOList = new ArrayList<>();
        for (ScriptEntity scriptEntity: scriptEntityList) {
            scriptDTOList.add(ScriptDTO.toScriptDTO(scriptEntity));
        }
        return scriptDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        scriptRepository.updateHits(id);
    }

    public ScriptDTO findById(Long id) {
        Optional<ScriptEntity> optionalScriptEntity = scriptRepository.findById(id);
        if (optionalScriptEntity.isPresent()) {
            ScriptEntity scriptEntity = optionalScriptEntity.get();
            ScriptDTO scriptDTO = ScriptDTO.toScriptDTO(scriptEntity);
            return scriptDTO;
        }else {
            return null;
        }
    }

    public ScriptDTO update(ScriptDTO scriptDTO) {
        ScriptEntity scriptEntity = ScriptEntity.toUpdateEntity(scriptDTO);
        scriptRepository.save(scriptEntity);
        return findById(scriptDTO.getId());
    }

    public void delete(Long id) {
        scriptRepository.deleteById(id);
    }

    public Page<ScriptDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() -1;
        int pageLimit = 10;
        Page<ScriptEntity> scriptEntities =
                scriptRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
//        System.out.println("scriptEntities.getContent() =  "+ scriptEntities.getContent());
//        System.out.println("scriptEntities.getTotalElements() = " + scriptEntities.getTotalElements());
//        System.out.println("scriptEntities.getNumber() = " + scriptEntities.getNumber());
//        System.out.println("scriptEntities.getTotalPages() = " + scriptEntities.getTotalPages());
//        System.out.println("scriptEntities.getSize() = " +scriptEntities.getSize());
//        System.out.println("scriptEntities.hasPrevious() = "+scriptEntities.hasPrevious() );
//        System.out.println("scriptEntities.isFirst() = " + scriptEntities.isFirst());
//        System.out.println("scriptEntities.isLasT() = " + scriptEntities.isLast());

        Page<ScriptDTO> scriptDTOS = scriptEntities.map(script -> new ScriptDTO(script.getId(), script.getScriptWriter(), script.getScriptTitle(), script.getScriptHits(), script.getCreatedTime()));
        return scriptDTOS;
    }

    public Page<ScriptDTO> searchByTitle(String searchTerm, Pageable pageable) {
        Page<ScriptEntity> scriptEntities = scriptRepository.findByScriptTitleContaining(searchTerm, pageable);
        return scriptEntities.map(ScriptDTO::toScriptDTO);
    }
}
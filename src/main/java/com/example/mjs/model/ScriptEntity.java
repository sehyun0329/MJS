package com.example.mjs.model;

import com.example.mjs.dto.ScriptDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "script_table")
public class ScriptEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql auto_increment
    private Long id;

    @Column(length = 20, nullable = false)
    private String scriptWriter;

    @Column
    private String scriptPass;

    @Column
    private String scriptTitle;

    @Column(columnDefinition = "TEXT")
    private String scriptContents;

    @Column
    private int scriptHits;

    public static ScriptEntity toSaveEntity(ScriptDTO scriptDTO) {
        ScriptEntity scriptEntity = new ScriptEntity();
        scriptEntity.setScriptWriter(scriptDTO.getScriptWriter());
        scriptEntity.setScriptPass(scriptDTO.getScriptPass());
        scriptEntity.setScriptTitle(scriptDTO.getScriptTitle());
        scriptEntity.setScriptContents(scriptDTO.getScriptContents());
        scriptEntity.setScriptHits(0);
        return scriptEntity;
    }

    public static ScriptEntity toUpdateEntity(ScriptDTO scriptDTO) {
        ScriptEntity scriptEntity = new ScriptEntity();
        scriptEntity.setId(scriptDTO.getId());
        scriptEntity.setScriptWriter(scriptDTO.getScriptWriter());
        scriptEntity.setScriptPass(scriptDTO.getScriptPass());
        scriptEntity.setScriptTitle(scriptDTO.getScriptTitle());
        scriptEntity.setScriptContents(scriptDTO.getScriptContents());
        scriptEntity.setScriptHits(scriptDTO.getScriptHits());
        return scriptEntity;
    }
}
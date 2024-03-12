package com.example.mjs.dto;

import com.example.mjs.model.ScriptEntity;
import lombok.*;

import java.time.LocalDateTime;

//DTO data transfer object (데이터 전송 객체)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScriptDTO {
    private Long id;
    private String scriptWriter;
    private String scriptPass;
    private String scriptTitle;
    private String scriptContents;
    private int scriptHits;
    private LocalDateTime scriptCreatedTime;
    private LocalDateTime scriptUpdatedTime;

    public ScriptDTO(Long id, String scriptWriter, String scriptTitle, int scriptHits, LocalDateTime scriptCreatedTime) {
        this.id = id;
        this.scriptWriter = scriptWriter;
        this.scriptTitle = scriptTitle;
        this.scriptHits = scriptHits;
        this.scriptCreatedTime = scriptCreatedTime;
    }

    public static ScriptDTO toScriptDTO(ScriptEntity scriptEntity) {
        ScriptDTO scriptDTO = new ScriptDTO();
        scriptDTO.setId(scriptEntity.getId());
        scriptDTO.setScriptWriter(scriptEntity.getScriptWriter());
        scriptDTO.setScriptPass(scriptEntity.getScriptPass());
        scriptDTO.setScriptTitle(scriptEntity.getScriptTitle());
        scriptDTO.setScriptContents(scriptEntity.getScriptContents());
        scriptDTO.setScriptHits(scriptEntity.getScriptHits());
        scriptDTO.setScriptCreatedTime(scriptEntity.getCreatedTime());
        scriptDTO.setScriptUpdatedTime(scriptEntity.getUpdatedTime());
        return scriptDTO;
    }
}
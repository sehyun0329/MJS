package com.example.mjs.repository;

import com.example.mjs.model.ScriptEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScriptRepository extends JpaRepository<ScriptEntity, Long> {
   @Modifying
   @Query(value = "update ScriptEntity s set s.scriptHits=s.scriptHits+1 where s.id=:id")
   void updateHits(@Param("id") Long id);

   Page<ScriptEntity> findByScriptTitleContaining(String searchTerm, Pageable pageable);
}
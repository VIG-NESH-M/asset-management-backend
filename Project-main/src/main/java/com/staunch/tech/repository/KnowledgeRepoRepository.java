package com.staunch.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.staunch.tech.entity.KnowledgeRepo;

@Repository
public interface KnowledgeRepoRepository extends JpaRepository<KnowledgeRepo, Integer> {

}

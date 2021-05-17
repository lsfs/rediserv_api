package me.lsfs.rediserv.repositories;

import me.lsfs.rediserv.models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
package com.gian.tgestiona.repository;

import com.gian.tgestiona.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,String> {
}

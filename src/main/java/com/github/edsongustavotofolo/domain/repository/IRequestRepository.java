package com.github.edsongustavotofolo.domain.repository;

import com.github.edsongustavotofolo.domain.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRequestRepository extends JpaRepository<Request, Long> {
}

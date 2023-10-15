package ru.scarlet.company.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.scarlet.company.entities.Dean

@Repository
interface DeanRepository : JpaRepository<Dean?, Int?> 
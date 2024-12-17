package com.example.demo1.repository

import com.example.demo1.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface EmployeeRepository : JpaRepository<Employee?, Long?>
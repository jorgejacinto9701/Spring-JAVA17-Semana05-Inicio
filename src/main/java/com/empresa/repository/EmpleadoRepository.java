package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empresa.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
	
	//Consulta
	@Query("select e from Empleado e where e.nombres like ?1 or e.apellidos like ?1")
	public abstract List<Empleado> listaNombreApellidoLike(String filtro);
	
	//Validaciones
	@Query("select e from Empleado e where e.nombres = ?1 and e.apellidos = ?2")
	public abstract List<Empleado> listaNombreApellidoRegistra(String nombres, String apellidos);
	
	@Query("select e from Empleado e where e.nombres = ?1 and e.apellidos = ?2 and e.idEmpleado != ?3")
	public abstract List<Empleado> listaNombreApellidoActualiza(String nombres, String apellidos, int idEmpleado);

	
}

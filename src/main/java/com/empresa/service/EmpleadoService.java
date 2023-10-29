package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Empleado;

public interface EmpleadoService {

	//CRUD
	public abstract List<Empleado> listaPorNombreApellidoLike(String filtro);
	public abstract Empleado insertaEmpleado(Empleado obj);
	public abstract Empleado actualizaEmpleado(Empleado obj);
	public abstract Optional<Empleado> buscaEmpleado(int idEmpleado);
	
	//Validacion de Registrar
	public abstract List<Empleado> listaNombreApellidoRegistra(String nombres, String apellidos);
	
	//Validacion de Actualizar
	public abstract List<Empleado> listaNombreApellidoActualiza(String nombres, String apellidos, int idEmpleado);

	
}

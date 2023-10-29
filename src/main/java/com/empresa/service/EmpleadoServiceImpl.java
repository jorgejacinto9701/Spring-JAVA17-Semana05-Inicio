package com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Empleado;
import com.empresa.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	@Autowired
	private EmpleadoRepository repository;
	
	@Override
	public List<Empleado> listaPorNombreApellidoLike(String filtro) {
		return repository.listaNombreApellidoLike(filtro);
	}

	@Override
	public Empleado insertaEmpleado(Empleado obj) {
		return repository.save(obj);
	}

	@Override
	public Empleado actualizaEmpleado(Empleado obj) {
		return repository.save(obj);
	}

	@Override
	public Optional<Empleado> buscaEmpleado(int idEmpleado) {
		return repository.findById(idEmpleado);
	}

	@Override
	public List<Empleado> listaNombreApellidoRegistra(String nombres, String apellidos) {
		return repository.listaNombreApellidoRegistra(nombres, apellidos);
	}

	@Override
	public List<Empleado> listaNombreApellidoActualiza(String nombres, String apellidos, int idEmpleado) {
		return repository.listaNombreApellidoActualiza(nombres, apellidos, idEmpleado);
	}

}

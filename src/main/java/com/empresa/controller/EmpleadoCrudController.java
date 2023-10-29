package com.empresa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entity.Empleado;
import com.empresa.service.EmpleadoService;
import com.empresa.util.FunctionUtil;

@Controller
public class EmpleadoCrudController {

	@Autowired
	private EmpleadoService empleadoService; 
	
	@RequestMapping("/verCrudEmpleado")
	public String verInicio() {
		return "crudEmpleado";
	}
	
	@GetMapping("/consultaCrudEmpleado")
	@ResponseBody
	public List<Empleado> consulta(String filtro) {
		return empleadoService.listaPorNombreApellidoLike("%"+filtro+"%");
	}
	
	@PostMapping("/registraCrudEmpleado")
	@ResponseBody
	public Map<?, ?> registra(Empleado obj) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		obj.setEstado(1);
		obj.setFechaRegistro(new Date());
		obj.setFechaActualizacion(new Date());  
		Empleado objSalida = empleadoService.insertaEmpleado(obj);
		if (objSalida == null) {
			map.put("mensaje", "Error en el registro");
		} else {
			List<Empleado> lista = empleadoService.listaPorNombreApellidoLike("%");
			map.put("lista", lista);
			map.put("mensaje", "Registro exitoso");
		}
		return map;
	}
	
	@ResponseBody
	@PostMapping("/eliminaCrudEmpleado")
	public Map<?, ?> elimina(int id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Empleado objEmpleadoBD = empleadoService.buscaEmpleado(id).get();
		objEmpleadoBD.setFechaActualizacion(new Date());  
		objEmpleadoBD.setEstado( objEmpleadoBD.getEstado() == 1 ? 0 : 1);
		
		Empleado objSalida = empleadoService.actualizaEmpleado(objEmpleadoBD);
		if (objSalida == null) {
			map.put("mensaje", "Error en actualizar");
		} else {
			List<Empleado> lista = empleadoService.listaPorNombreApellidoLike("%");
			map.put("lista", lista);
		}
		return map;
	}
	
	@PostMapping("/actualizaCrudEmpleado")
	@ResponseBody
	public Map<?, ?> actualiza(Empleado obj) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		  
		Optional<Empleado> optEmpleadoBD= empleadoService.buscaEmpleado(obj.getIdEmpleado());
		
		obj.setFechaRegistro(optEmpleadoBD.get().getFechaRegistro());
		obj.setEstado(optEmpleadoBD.get().getEstado());
		obj.setFechaActualizacion(new Date());
		
		Empleado objSalida = empleadoService.actualizaEmpleado(obj);
		if (objSalida == null) {
			map.put("mensaje", "Error en actualizar");
		} else {
			List<Empleado> lista = empleadoService.listaPorNombreApellidoLike("%");
			map.put("lista", lista);
			map.put("mensaje", "Actualización exitosa");
		}
		return map;
	}
	
	
	
	@GetMapping("/validaCrudEmpleadoFecNac")
	@ResponseBody
	public String validaFecNac(String fechaNacimiento) {
		if(FunctionUtil.isMayorEdad(fechaNacimiento)) {
			return "{\"valid\":true}";
		}else {
			return "{\"valid\":false}";
		} 
	}
	
	@GetMapping("/validaCrudEmpleadoNomApeRegistra")
	@ResponseBody
	public String validaNomApeRegistra(String nombres, String apellidos) {
		List<Empleado> lstSalida = empleadoService.listaNombreApellidoRegistra(nombres, apellidos);
		if(lstSalida.isEmpty()) {
			return "{\"valid\":true}";
		}else {
			return "{\"valid\":false}";
		} 
	}
	
	@GetMapping("/validaCrudEmpleadoNomApeActualiza")
	@ResponseBody
	public String validaNomApeActualiza(String nombres, String apellidos,int idEmpleado) {
		List<Empleado> lstSalida = empleadoService.listaNombreApellidoActualiza(nombres, apellidos, idEmpleado);
		if(lstSalida.isEmpty()) {
			return "{\"valid\":true}";
		}else {
			return "{\"valid\":false}";
		} 
	}
	
	
	
}




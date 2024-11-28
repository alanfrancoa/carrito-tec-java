package repositories;

import java.util.ArrayList;
import java.util.List;

import modelos.usuarios.Empleado;
import repositories.interfaces.EmpleadoRepo;


public class EmpleadoRepoSingleton implements EmpleadoRepo{	
	
	private List<Empleado> listaEmpleados;
	//Lista de usuarios empleados
    public List<Empleado> getAllEmpleados() {
    	return new ArrayList<Empleado>(this.listaEmpleados);
    	
	}
    
    //obtener empleado por nombre de usuario
    public Empleado getEmpleado(String usuarioEmpleado) {
		return null;
	}
    
    //agregar un empleado
    public void addEmpleado(Empleado empleado) {
    	
	}
}

package repositories.interfaces;

import java.util.List;

import modelos.usuarios.Empleado;

public interface EmpleadoRepo {
	//Lista de usuarios empleados
    List<Empleado> getAllEmpleados();
    
    //obtener empleado por nombre de usuario
    public Empleado getEmpleado(String nombreUsuario);
  
}


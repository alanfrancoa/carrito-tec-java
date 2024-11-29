package repositories;

import java.util.List;
import java.util.stream.Collectors;

import modelos.usuarios.Cliente;
import modelos.usuarios.UsuarioBase;
import repositories.interfaces.ClienteRepo;

public class ClienteRepoSingleton implements ClienteRepo {
    private static ClienteRepoSingleton instance;
    private UsuarioRepoSingleton usuarioRepo; // Referencia al repositorio de usuarios.
    private List<String> mensajes; // Lista de mensajes para operaciones específicas de clientes.

    private ClienteRepoSingleton() {
        this.usuarioRepo = UsuarioRepoSingleton.getInstance(); // Usa el Singleton de usuarios.
        this.mensajes = usuarioRepo.getMensajes(); // Sincroniza mensajes con el repositorio padre.
    }

    public static ClienteRepoSingleton getInstance() {
        if (instance == null) {
            instance = new ClienteRepoSingleton();
        }
        return instance;
    }

    @Override
    public List<Cliente> getAllClientes() {
        // Filtra la lista de usuarios por el tipo de usuario "CLIENTE".
        return usuarioRepo.getAllUsuarios().stream()
                .filter(usuario -> "CLIENTE".equals(usuario.getTipoUsuario()))
                .map(usuario -> (Cliente) usuario) // Cast seguro porque ya filtramos por tipo.
                .collect(Collectors.toList());
    }

    @Override
    public Cliente getCliente(String nombreUsuario) {
        UsuarioBase usuario = usuarioRepo.getUsuario(nombreUsuario);
        if (usuario != null && "CLIENTE".equals(usuario.getTipoUsuario())) {
            return (Cliente) usuario; // Cast seguro porque validamos el tipo.
        }
        mensajes.add("El usuario no es un cliente o no existe: " + nombreUsuario);
        return null;
    }

    @Override
    public void addCliente(Cliente cliente) {
        // Reutiliza el método `addUsuario` del repositorio padre.
        usuarioRepo.addUsuario(cliente);
        // Los mensajes ya se gestionan desde `addUsuario`.
    }

    @Override
    public void transferirSaldo(String usuarioOrigen, String usuarioDestino, double monto) {
        Cliente origen = getCliente(usuarioOrigen);
        Cliente destino = getCliente(usuarioDestino);

        if (origen == null || destino == null) {
            mensajes.add("Uno o ambos usuarios no existen o no son clientes.");
            return;
        }

        if (origen.getSaldo() < monto) {
            mensajes.add("Saldo insuficiente en la cuenta de " + usuarioOrigen + " para transferir.");
            return;
        }

        origen.setSaldo(origen.getSaldo() - monto);
        destino.setSaldo(destino.getSaldo() + monto);
        mensajes.add("Transferencia exitosa de " + monto + " de " + usuarioOrigen + " a " + usuarioDestino + ".");
    }

    @Override
    public void ingresarSaldo(String nombreUsuario, double monto) {
        Cliente cliente = getCliente(nombreUsuario);

        if (cliente == null) {
            mensajes.add("El usuario no existe o no es un cliente: " + nombreUsuario);
            return;
        }

        if (monto <= 0) {
            mensajes.add("El monto debe ser mayor a cero.");
            return;
        }

        cliente.setSaldo(cliente.getSaldo() + monto);
        mensajes.add("Saldo ingresado exitosamente: " + monto + " a la cuenta de " + nombreUsuario + ".");
    }

    @Override
    public void retirarSaldo(String nombreUsuario, double monto) {
        Cliente cliente = getCliente(nombreUsuario);

        if (cliente == null) {
            mensajes.add("El usuario no existe o no es un cliente: " + nombreUsuario);
            return;
        }

        if (monto <= 0) {
            mensajes.add("El monto debe ser mayor a cero.");
            return;
        }

        if (cliente.getSaldo() < monto) {
            mensajes.add("Saldo insuficiente para realizar el retiro.");
            return;
        }

        cliente.setSaldo(cliente.getSaldo() - monto);
        mensajes.add("Retiro exitoso de " + monto + " de la cuenta de " + nombreUsuario + ".");
    }

    // Métodos auxiliares para mensajes
    public List<String> getMensajes() {
        return usuarioRepo.getMensajes(); // Reutiliza los mensajes del repositorio de usuarios.
    }

    public void limpiarMensajes() {
        usuarioRepo.limpiarMensajes(); // Limpia los mensajes en el repositorio de usuarios.
    }
}

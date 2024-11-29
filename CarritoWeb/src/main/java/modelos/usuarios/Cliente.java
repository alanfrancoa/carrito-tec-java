package modelos.usuarios;

public class Cliente extends UsuarioBase {
    
    // Atributos
    private double saldo;

    // Constructor de la clase (hereda a través de super los atributos del padre)
    public Cliente(String nombreUsuario, String claveUsuario, double saldo) {
        super(nombreUsuario, claveUsuario);
        this.saldo = saldo;
    }

    // Getters y setters propios de la clase Cliente
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Implementamos el método getTipoUsuario para el cliente
    @Override
    public String getTipoUsuario() {
        return "CLIENTE";
    }

    // Método para retirar saldo del cliente
    public boolean retirarSaldo(double saldoRetirar) {
        if (saldoRetirar > 0 && saldoRetirar <= saldo) {
            saldo -= saldoRetirar; // Resta el saldo
            return true; // Retiro exitoso
        }
        return false; // No tiene suficiente saldo
    }

    // Método para transferir saldo a otro cliente
    public boolean transferirSaldo(Cliente clienteDestino, double monto) {
        if (monto > 0 && monto <= saldo) {
            // Si el cliente tiene suficiente saldo
            saldo -= monto; // Resta el monto al saldo del cliente actual
            clienteDestino.setSaldo(clienteDestino.getSaldo() + monto); // Suma el monto al saldo del cliente destino
            return true; // Transferencia exitosa
        }
        return false; // No tiene suficiente saldo
    }

    // Método para ingresar saldo en la cuenta del cliente
    public void ingresarSaldo(double saldoIngresar) {
        if (saldoIngresar > 0) {
            saldo += saldoIngresar; // Se agrega el saldo al saldo actual
        }
    }
}

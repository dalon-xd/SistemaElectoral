package sistemaelectoral;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


// === Clase Operador ===
class Operador {
    private String usuario;
    private String contrasena;

    public Operador(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public boolean autenticar(String u, String c) {
        return this.usuario.equals(u) && this.contrasena.equals(c);
    }
}

// === Clase Partido Político ===
class PartidoPolitico {
    private String nombre;
    private String sigla;
    private String representante;
    private String simbolo;

    public PartidoPolitico(String nombre, String sigla, String representante, String simbolo) {
        this.nombre = nombre;
        this.sigla = sigla;
        this.representante = representante;
        this.simbolo = simbolo;
    }

    public String toString() {
        return sigla + " - " + nombre + " (" + representante + ") | Símbolo: " + simbolo;
    }
}

// === Clase Candidato ===
class Candidato {
    private String nombres;
    private String apellidos;
    private String dni;
    private String partido;

    public Candidato(String nombres, String apellidos, String dni, String partido) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.partido = partido;
    }

    public String toString() {
        return nombres + " " + apellidos + " | DNI: " + dni + " | " + partido;
    }
}

// === Clase Elección ===
class Eleccion {
    private String fecha;
    private String tipo;
    private ArrayList<String> candidatos;

    public Eleccion(String fecha, String tipo) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.candidatos = new ArrayList<>();
    }

    public void agregarCandidato(String nombre) {
        candidatos.add(nombre);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(tipo).append(" - ").append(fecha).append("\nCandidatos:");
        for (String c : candidatos) {
            sb.append("\n - ").append(c);
        }
        return sb.toString();
    }
}

// === Clase Acta Electoral ===
class ActaElectoral {
    private static int contador = 1;
    private final int numeroActa;
    private String titulo;
    private String fecha;
    private String hora;
    private String lugar;
    private String mesa;
    private String miembrosMesa;
    private int votantesRegistrados;
    private int votantesEfectivos;
    private Map<String, Integer> resultados;
    private int blancos;
    private int nulos;
    private String observaciones;
    private String firmas;
    private String sello;

    public ActaElectoral(String titulo, String fecha, String hora, String lugar, String mesa, String miembrosMesa, int votantesRegistrados, int votantesEfectivos, Map<String, Integer> resultados, int blancos, int nulos, String observaciones, String firmas, String sello) {
        this.numeroActa = contador++;
        this.titulo = titulo;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.mesa = mesa;
        this.miembrosMesa = miembrosMesa;
        this.votantesRegistrados = votantesRegistrados;
        this.votantesEfectivos = votantesEfectivos;
        this.resultados = resultados;
        this.blancos = blancos;
        this.nulos = nulos;
        this.observaciones = observaciones;
        this.firmas = firmas;
        this.sello = sello;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Acta N°: ").append(numeroActa).append("\n")
          .append("Título: ").append(titulo).append("\n")
          .append("Fecha: ").append(fecha).append(" Hora: ").append(hora).append("\n")
          .append("Lugar: ").append(lugar).append("\n")
          .append("Mesa: ").append(mesa).append("\n")
          .append("Miembros de mesa: ").append(miembrosMesa).append("\n")
          .append("Registrados: ").append(votantesRegistrados).append(" | Efectivos: ").append(votantesEfectivos).append("\n")
          .append("Resultados:\n");
        for (String candidato : resultados.keySet()) {
            sb.append("  ").append(candidato).append(": ").append(resultados.get(candidato)).append(" votos\n");
        }
        sb.append("Blancos: ").append(blancos).append(" | Nulos: ").append(nulos).append("\n")
          .append("Observaciones: ").append(observaciones).append("\n")
          .append("Firmas: ").append(firmas).append("\n")
          .append("Sello: ").append(sello);
        return sb.toString();
    }

    public Map<String, Integer> getResultados() { return resultados; }
    public int getBlancos() { return blancos; }
    public int getNulos() { return nulos; }
    public String getMesa() { return mesa; }
    public String getTitulo() { return titulo; }
}

// === Ventana Principal del Sistema Electoral ===
class VentanaPrincipal extends JFrame {
    private List<ActaElectoral> actas = new ArrayList<>();

    public VentanaPrincipal() {
        setTitle("Sistema Electoral - Menú Principal");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 1, 10, 10));

        JButton btnElecciones = new JButton("Gestionar Elecciones");
        JButton btnCandidatos = new JButton("Gestionar Candidatos");
        JButton btnActas = new JButton("Registrar Acta Electoral");
        JButton btnPartidos = new JButton("Gestionar Partidos");
        JButton btnMesas = new JButton("Gestionar Mesas Electorales");
        JButton btnMiembros = new JButton("Gestionar Miembros de Mesa");
        JButton btnResultados = new JButton("Ver Resultados de Votación");
        JButton btnSalir = new JButton("Salir");

        btnElecciones.addActionListener(e -> mostrarEleccionesSimuladas());
        btnCandidatos.addActionListener(e -> JOptionPane.showMessageDialog(this, "Candidato: Renzo Gutiérrez | DNI: 77665544 | Partido Azul"));
        btnPartidos.addActionListener(e -> JOptionPane.showMessageDialog(this, "Partido: PA - Partido Azul (Lucía Ramos) | Símbolo: Sol"));
        btnActas.addActionListener(e -> registrarActaEjemplo());
        btnMesas.addActionListener(e -> JOptionPane.showMessageDialog(this, "Mesa 001: Presidente - Ana Soto, Secretario - Julio Campos, Vocal - Teresa León"));
        btnMiembros.addActionListener(e -> JOptionPane.showMessageDialog(this, "Miembro: Ana Soto | Cargo: Presidente"));
        btnResultados.addActionListener(e -> mostrarResultadosDetallados());
        btnSalir.addActionListener(e -> System.exit(0));

        add(btnElecciones);
        add(btnCandidatos);
        add(btnActas);
        add(btnPartidos);
        add(btnMesas);
        add(btnMiembros);
        add(btnResultados);
        add(btnSalir);
    }

    private void mostrarEleccionesSimuladas() {
        Eleccion eleccion = new Eleccion("2025-10-15", "MUNICIPAL");
        eleccion.agregarCandidato("Renzo Gutiérrez");
        eleccion.agregarCandidato("Mario Ayala");
        eleccion.agregarCandidato("Diana López");
        JOptionPane.showMessageDialog(this, eleccion.toString(), "Elección", JOptionPane.INFORMATION_MESSAGE);
    }

    private void registrarActaEjemplo() {
        Map<String, Integer> resultados = new HashMap<>();
        resultados.put("Renzo Gutiérrez", 110);
        resultados.put("Mario Ayala", 97);
        resultados.put("Diana López", 82);
        ActaElectoral acta = new ActaElectoral(
            "Acta Oficial de Resultados", "2025-10-15", "14:45", "Arequipa",
            "Mesa 005", "Luis Torres, Carmen Salas, Hugo Bravo",
            230, 210, resultados, 12, 8,
            "Proceso regular sin observaciones mayores.", "FirmaX, FirmaY", "SelloRegional"
        );
        actas.add(acta);
        JOptionPane.showMessageDialog(this, acta.toString(), "Acta Electoral", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarResultadosDetallados() {
        StringBuilder sb = new StringBuilder();
        for (ActaElectoral acta : actas) {
            sb.append("Mesa: ").append(acta.getMesa()).append("\n")
              .append("Acta: ").append(acta.getTitulo()).append("\n")
              .append("Desglose por candidato:\n");
            for (Map.Entry<String, Integer> entry : acta.getResultados().entrySet()) {
                sb.append(" - ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" votos\n");
            }
            sb.append("Votos en blanco: ").append(acta.getBlancos()).append(" | Votos nulos: ").append(acta.getNulos()).append("\n\n");
        }
        if (sb.length() == 0) {
            sb.append("No hay actas registradas aún.");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Informe Detallado de Resultados", JOptionPane.INFORMATION_MESSAGE);
    }
}

// === Ventana Login ===
class VentanaLogin extends JDialog {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private boolean autenticado;

    public VentanaLogin(JFrame parent) {
        super(parent, "Login Operador", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2));

        txtUsuario = new JTextField();
        txtContrasena = new JPasswordField();
        btnIngresar = new JButton("Ingresar");

        add(new JLabel("Usuario:")); add(txtUsuario);
        add(new JLabel("Contraseña:")); add(txtContrasena);
        add(new JLabel()); add(btnIngresar);

        Operador operador = new Operador("admin", "1234");

        btnIngresar.addActionListener(e -> {
            String u = txtUsuario.getText();
            String c = new String(txtContrasena.getPassword());
            if (operador.autenticar(u, c)) {
                autenticado = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
            }
        });
    }

    public boolean mostrarLogin() {
        setVisible(true);
        return autenticado;
    }
}

// === MAIN ===
public class SistemaElectoral {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame dummy = new JFrame();
            VentanaLogin login = new VentanaLogin(dummy);
            if (login.mostrarLogin()) {
                JOptionPane.showMessageDialog(null, "✅ Bienvenido operador autorizado.");
                new VentanaPrincipal().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "❌ Acceso denegado.");
                System.exit(0);
            }
        });
    }
}

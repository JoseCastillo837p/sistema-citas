package mx.uacam.fi.sistemacitas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {

    @FXML private TextField txtCorreoLogin;
    @FXML private PasswordField txtPassLogin;
    @FXML private Label lblError;
    @FXML private CheckBox chkAcepto;
    @FXML private Button btnAceptarPrivacidad;

    @FXML private ComboBox<String> cbGenero;
    @FXML private TextField txtEdadMax;
    @FXML private TextField txtIntereses;
    @FXML private ListView<String> listaResultados;

    @FXML private ListView<String> listaMensajes;
    @FXML private Label lblRemitente;
    @FXML private TextArea txtMensaje;
    @FXML private Label lblDatosContacto;

    private void cambiarPantalla(ActionEvent event, String archivoFxml, String titulo) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(archivoFxml));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(titulo);
    }

    @FXML
    protected void onIniciarSesionClick(ActionEvent event) throws IOException {
        String correo = txtCorreoLogin.getText();
        String password = txtPassLogin.getText();
        if ("admin@uacam.mx".equals(correo) && "12345".equals(password)) {
            cambiarPantalla(event, "perfil-view.fxml", "Mi Perfil");
        } else {
            lblError.setText("Correo o contraseña incorrectos.");
        }
    }

    @FXML
    protected void onLoginSocialClick(ActionEvent event) throws IOException {
        cambiarPantalla(event, "perfil-view.fxml", "Mi Perfil");
    }

    @FXML
    protected void onIrARegistroClick(ActionEvent event) throws IOException {
        cambiarPantalla(event, "registro-view.fxml", "Registro de Usuario");
    }

    @FXML
    protected void onRegresarALoginClick(ActionEvent event) throws IOException {
        cambiarPantalla(event, "login-view.fxml", "Iniciar Sesión");
    }

    @FXML
    protected void onSiguienteClick(ActionEvent event) throws IOException {
        cambiarPantalla(event, "privacidad-view.fxml", "Aviso de Privacidad");
    }

    @FXML
    protected void onCasillaClick(ActionEvent event) {
        btnAceptarPrivacidad.setDisable(!chkAcepto.isSelected());
    }

    @FXML
    protected void onAceptarPrivacidadClick(ActionEvent event) throws IOException {
        cambiarPantalla(event, "login-view.fxml", "Iniciar Sesión");
    }

    @FXML
    protected void onRegresarAPerfilClick(ActionEvent event) throws IOException {
        cambiarPantalla(event, "perfil-view.fxml", "Mi Perfil");
    }

    @FXML
    protected void onGuardarPerfilClick(ActionEvent event) throws IOException {
        cambiarPantalla(event, "busqueda-view.fxml", "Búsqueda de Personas");
    }

    @FXML
    protected void onBuscarClick(ActionEvent event) {
        listaResultados.getItems().clear();

        String genero = cbGenero.getValue();
        String edadTexto = txtEdadMax.getText();
        String interesFiltro = txtIntereses.getText().toLowerCase();

        int edadMax = 100;
        if (edadTexto != null && !edadTexto.isEmpty()) {
            try { edadMax = Integer.parseInt(edadTexto); } catch (Exception e) {}
        }

        boolean pasaAna = true;
        if (genero != null && genero.equals("Hombre")) pasaAna = false;
        if (22 > edadMax) pasaAna = false;
        if (!interesFiltro.isEmpty() && !"programacion".contains(interesFiltro) && !"programación".contains(interesFiltro)) pasaAna = false;
        if (pasaAna) listaResultados.getItems().add("Ana - 22 años - Le gusta: Programación");

        boolean pasaCarlos = true;
        if (genero != null && genero.equals("Mujer")) pasaCarlos = false;
        if (24 > edadMax) pasaCarlos = false;
        if (!interesFiltro.isEmpty() && !"musica".contains(interesFiltro) && !"música".contains(interesFiltro)) pasaCarlos = false;
        if (pasaCarlos) listaResultados.getItems().add("Carlos - 24 años - Le gusta: Música");

        boolean pasaValeria = true;
        if (genero != null && genero.equals("Hombre")) pasaValeria = false;
        if (21 > edadMax) pasaValeria = false;
        if (!interesFiltro.isEmpty() && !"fotografia".contains(interesFiltro) && !"fotografía".contains(interesFiltro)) pasaValeria = false;
        if (pasaValeria) listaResultados.getItems().add("Valeria - 21 años - Le gusta: Fotografía");
    }

    @FXML
    protected void onContactarClick(ActionEvent event) throws IOException {
        cambiarPantalla(event, "contacto-view.fxml", "Formulario de Contacto");
    }

    @FXML
    protected void onCancelarContactoClick(ActionEvent event) throws IOException {
        cambiarPantalla(event, "busqueda-view.fxml", "Búsqueda de Personas");
    }

    @FXML
    protected void onEnviarSolicitudClick(ActionEvent event) throws IOException {
        cambiarPantalla(event, "mensajes-view.fxml", "Bandeja de Mensajes");
    }

    @FXML
    protected void onMensajeSeleccionado(MouseEvent event) {
        String seleccionado = listaMensajes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            lblRemitente.setText("Mensaje de: " + seleccionado);
            lblDatosContacto.setText("Datos de contacto: [Ocultos hasta aceptar solicitud]");
            lblDatosContacto.setStyle("-fx-text-fill: black; -fx-font-weight: normal;");

            if (seleccionado.equals("Ana")) {
                txtMensaje.setText("Hola, vi tu perfil y me parecieron muy interesantes tus hobbies. ¿Te gustaría platicar?");
            } else if (seleccionado.equals("Carlos")) {
                txtMensaje.setText("¡Qué tal! Tenemos gustos musicales muy similares. ¿Te interesaría ir a un concierto pronto?");
            } else if (seleccionado.equals("Valeria")) {
                txtMensaje.setText("Hola, me llamó la atención tu perfil. Me encantaría conocerte, ¡Saludos!");
            }
        }
    }

    @FXML
    protected void onAceptarSolicitudClick(ActionEvent event) {
        String seleccionado = listaMensajes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) seleccionado = "usuario";
        lblDatosContacto.setText("Datos de contacto:\nTeléfono: 981-123-4567\nCorreo: " + seleccionado.toLowerCase() + "@uacam.mx");
        lblDatosContacto.setStyle("-fx-text-fill: #4CAF50; -fx-font-weight: bold;");
    }

    @FXML
    protected void onRechazarSolicitudClick(ActionEvent event) {
        lblDatosContacto.setText("Solicitud rechazada. Los datos permanecen ocultos.");
        lblDatosContacto.setStyle("-fx-text-fill: #F44336; -fx-font-weight: bold;");
    }

    @FXML
    protected void onVolverABusquedaClick(ActionEvent event) throws IOException {
        cambiarPantalla(event, "busqueda-view.fxml", "Búsqueda de Personas");
    }
    @FXML
    protected void onVerEquipoClick(ActionEvent event) throws IOException {
        cambiarPantalla(event, "lista_integrantes-view.fxml", "Integrantes del Equipo");
    }
    @FXML
    protected void onRegresarClick(ActionEvent event) throws IOException {
        
        cambiarPantalla(event, "login-view.fxml", "Iniciar Sesión");
    }
}
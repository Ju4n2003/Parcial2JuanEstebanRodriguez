package co.edu.uniquindio.SOLID.Controlador;

import co.edu.uniquindio.SOLID.Model.DTO.EmpleadoDTO;
import co.edu.uniquindio.SOLID.Service.Fachadas.EmpresaAdminFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class EmpleadosController implements Initializable {

    @FXML private TextField txtEmpId;
    @FXML private TextField txtEmpNombre;
    @FXML private ComboBox<String> cmbEmpRol;
    @FXML private TableView<EmpleadoDTO> tblEmpleados;
    @FXML private TableColumn<EmpleadoDTO, String> colEmpId;
    @FXML private TableColumn<EmpleadoDTO, String> colEmpNombre;
    @FXML private TableColumn<EmpleadoDTO, String> colEmpRol;
    @FXML private TableColumn<EmpleadoDTO, String> colEmpEstado;

    private ObservableList<EmpleadoDTO> empleados;
    private final EmpresaAdminFacade facade = new EmpresaAdminFacade();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        empleados = FXCollections.observableArrayList(facade.listarEmpleados());
        
        if (cmbEmpRol != null) {
            cmbEmpRol.setItems(FXCollections.observableArrayList("CAJERO", "BODEGUERO"));
            cmbEmpRol.setValue("CAJERO");
        }
        if (tblEmpleados != null) {
            colEmpId.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getId()));
            colEmpNombre.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getNombre()));
            colEmpRol.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getRol()));
            colEmpEstado.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().isEstado() ? "Activo" : "Inactivo"));
            tblEmpleados.setItems(empleados);
        }
    }

    @FXML
    void crearEmpleado() {
        String id = txtEmpId != null ? txtEmpId.getText() : null;
        String nombre = txtEmpNombre != null ? txtEmpNombre.getText() : null;
        String rol = cmbEmpRol != null ? cmbEmpRol.getValue() : null;
        
        // Validaciones de campos
        if (id == null || id.trim().isEmpty()) {
            mostrarError("El ID es obligatorio");
            return;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            mostrarError("El nombre es obligatorio");
            return;
        }
        if (rol == null) {
            mostrarError("El rol es obligatorio");
            return;
        }
        
        try {
            EmpleadoDTO dto = new EmpleadoDTO(id, nombre, rol, true);
            EmpleadoDTO creado = facade.crearEmpleado(dto);
            empleados.add(creado);
            if (tblEmpleados != null) tblEmpleados.refresh();
            if (txtEmpId != null) txtEmpId.clear();
            if (txtEmpNombre != null) txtEmpNombre.clear();
            if (cmbEmpRol != null) cmbEmpRol.setValue("CAJERO");
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    void actualizarEmpleado() {
        String id = txtEmpId != null ? txtEmpId.getText() : null;
        String nombre = txtEmpNombre != null ? txtEmpNombre.getText() : null;
        String rol = cmbEmpRol != null ? cmbEmpRol.getValue() : null;
        if (id == null || id.trim().isEmpty()) { mostrarError("El ID es obligatorio"); return; }
        try {
            EmpleadoDTO dto = new EmpleadoDTO(id, nombre, rol, true);
            EmpleadoDTO actualizado = facade.actualizarEmpleado(dto);
            for (int i = 0; i < empleados.size(); i++) {
                if (empleados.get(i).getId().equals(id)) { empleados.set(i, actualizado); break; }
            }
            if (tblEmpleados != null) tblEmpleados.refresh();
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    void eliminarEmpleado() {
        String id = txtEmpId != null ? txtEmpId.getText() : null;
        if (id == null || id.trim().isEmpty()) { mostrarError("El ID es obligatorio"); return; }
        try {
            facade.eliminarEmpleado(id);
            empleados.removeIf(e -> e.getId().equals(id));
            if (tblEmpleados != null) tblEmpleados.refresh();
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    void activarEmpleado() {
        String id = txtEmpId != null ? txtEmpId.getText() : null;
        if (id == null || id.trim().isEmpty()) { mostrarError("El ID es obligatorio"); return; }
        try {
            EmpleadoDTO actualizado = facade.activarEmpleado(id);
            for (int i = 0; i < empleados.size(); i++) { if (empleados.get(i).getId().equals(id)) { empleados.set(i, actualizado); break; } }
            if (tblEmpleados != null) tblEmpleados.refresh();
        } catch (IllegalArgumentException e) { mostrarError(e.getMessage()); }
    }

    @FXML
    void inactivarEmpleado() {
        String id = txtEmpId != null ? txtEmpId.getText() : null;
        if (id == null || id.trim().isEmpty()) { mostrarError("El ID es obligatorio"); return; }
        try {
            EmpleadoDTO actualizado = facade.inactivarEmpleado(id);
            for (int i = 0; i < empleados.size(); i++) { if (empleados.get(i).getId().equals(id)) { empleados.set(i, actualizado); break; } }
            if (tblEmpleados != null) tblEmpleados.refresh();
        } catch (IllegalArgumentException e) { mostrarError(e.getMessage()); }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}



package Controllers;

/**
 * Sample Skeleton for 'page.fxml' Controller Class
 */

import Entity.KableEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import procedures.FindInDB;

import java.util.List;

public class FXController {

    @FXML // fx:id="findText"
    private Label findText; // Value injected by FXMLLoader

    @FXML // fx:id="table_stock"
    private TableColumn<?, ?> table_stock; // Value injected by FXMLLoader

    @FXML // fx:id="findButton"
    private Button findButton; // Value injected by FXMLLoader

    @FXML // fx:id="table_copy"
    private TableColumn<?, ?> table_copy; // Value injected by FXMLLoader

    @FXML // fx:id="photo2"
    private ImageView photo2; // Value injected by FXMLLoader

    @FXML // fx:id="onlyWithPrice"
    private CheckBox onlyWithPrice; // Value injected by FXMLLoader

    @FXML // fx:id="table_price"
    private TableColumn<?, ?> table_price; // Value injected by FXMLLoader

    @FXML // fx:id="photo1"
    private ImageView photo1; // Value injected by FXMLLoader

    @FXML // fx:id="clearButton"
    private Button clearButton; // Value injected by FXMLLoader

    @FXML // fx:id="table_copy1"
    private TableColumn<?, ?> table_copy1; // Value injected by FXMLLoader

    @FXML // fx:id="onlyWithValue"
    private CheckBox onlyWithValue; // Value injected by FXMLLoader

    @FXML // fx:id="table_fullName"
    private TableColumn<?, ?> table_fullName; // Value injected by FXMLLoader

    @FXML // fx:id="findInput"
    private TextField findInput; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<?> table; // Value injected by FXMLLoader

    @FXML // fx:id="table_actuality"
    private TableColumn<?, ?> table_actuality; // Value injected by FXMLLoader

    @FXML // fx:id="table_volume"
    private TableColumn<?, ?> table_volume; // Value injected by FXMLLoader

    @FXML
    void enter(ActionEvent event) {
        FindInDB findInDB = new FindInDB();
        String requset = findInput.getText();
        List<KableEntity> kableEntityList = findInDB.findByLIST(requset);
        for(KableEntity kableEntity: kableEntityList){

        }
    }

    @FXML
    void click(ActionEvent event) {

    }

    @FXML
    void clear(ActionEvent event) {

    }

}

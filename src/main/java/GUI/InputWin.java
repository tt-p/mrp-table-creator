package GUI;

import Algorithm.MrpAlg;
import ItemOp.Item;
import ItemOp.ItemManager;
import Utilities.ResourceOp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class InputWin extends Stage {

    private ItemManager iM;

    private TextField[][] tfTop = new TextField[2][11];
    private TableView<Item> table;

    public InputWin() {
        this.iM = new ItemManager();
        paint();
    }

    private void paint() {
        initialize();
    }

    private void initialize() {
        this.getIcons().setAll(ResourceOp.setImage("stageIcon.png"));

        GridPane top = prepareTop();
        StackPane sPane = prepareCenter();

        Button btCreateTables = new Button("Create Tables");
        btCreateTables.setContentDisplay(ContentDisplay.TOP);
        btCreateTables.setGraphic(ResourceOp.setImageView("createTablesIcon.png", 100, 100));
        btCreateTables.setOnAction(e -> createTables());

        VBox vBox = new VBox(50);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(50));
        vBox.getChildren().addAll(top, sPane, btCreateTables);

        System.out.println(iM.toString());

        Scene scene = new Scene(vBox);
        this.setTitle("MRP Table Creator");
        this.setScene(scene);
    }

    private GridPane prepareTop() {
        GridPane top = new GridPane();
        top.setAlignment(Pos.CENTER);
        GridPane bottom = new GridPane();
        bottom.setAlignment(Pos.CENTER);

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 11; j++) {
                tfTop[i][j] = new TextField();
                tfTop[i][j].setPrefWidth(92);
                tfTop[i][j].setPrefHeight(25);
                if (i == 1 && j != 0) tfTop[i][j].setOnKeyTyped(this::tfTopKeyTyped);
                top.add(tfTop[i][j], j,i);
            }
        }

        tfTop[0][0].setText("Period");
        tfTop[0][0].setEditable(false);
        tfTop[0][0].setStyle("-fx-font-family: 'Times New Roman';-fx-font-weight: Bold");
        tfTop[1][0].setText("Demand\n(1605)");
        tfTop[1][0].setEditable(false);
        tfTop[1][0].setStyle("-fx-font-family: 'Times New Roman';-fx-font-weight: Bold");

        for(int i = 1; i < 11; i++) {
            tfTop[0][i].setText(String.valueOf(i));
            tfTop[0][i].setEditable(false);
            tfTop[0][i].setStyle("-fx-font-family: 'Times New Roman';-fx-font-weight: Bold");
        }

        for(int i = 1; i < 11; i++) {
            tfTop[1][i].setText(String.valueOf(0));
        }

        tfTop[1][4].setText("60");
        tfTop[1][5].setText("100");
        tfTop[1][7].setText("50");
        tfTop[1][9].setText("30");

        return top;
    }

    private StackPane prepareCenter() {
        StackPane sPane = new StackPane();

        ObservableList<Item> items = FXCollections.observableArrayList();
        items.addAll(iM.getItems());

        table = new TableView<>();
        table.setPrefHeight(345);
        table.setEditable(true);

        TableColumn<Item, String> idColumn = new TableColumn<>("Item ID");
        idColumn.setMinWidth(168.5);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Item, Integer> amountColumn = new TableColumn<>("Amount On Hand");
        amountColumn.setMinWidth(168.5);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        amountColumn.setOnEditCommit(e ->
        {
            (e.getTableView().getItems().get(e.getTablePosition().getRow())).setAmount(e.getNewValue());
            System.out.println(iM.toString());
        });

        TableColumn<Item, Integer> scheduledColumn = new TableColumn<>("Scheduled Receipt");
        scheduledColumn.setMinWidth(168.5);
        scheduledColumn.setCellValueFactory(new PropertyValueFactory<>("scheduled"));
        scheduledColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        scheduledColumn.setOnEditCommit(e ->
        {
            (e.getTableView().getItems().get(e.getTablePosition().getRow())).setScheduled(e.getNewValue());
            System.out.println(iM.toString());
        });

        TableColumn<Item, Integer> arrivalColumn = new TableColumn<>("Arrival on week");
        arrivalColumn.setMinWidth(168.5);
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        arrivalColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        arrivalColumn.setOnEditCommit(e ->
        {
            (e.getTableView().getItems().get(e.getTablePosition().getRow())).setArrival(e.getNewValue());
            System.out.println(iM.toString());
        });

        TableColumn<Item, Integer> leadTimeColumn = new TableColumn<>("Lead Time");
        leadTimeColumn.setMinWidth(168.5);
        leadTimeColumn.setCellValueFactory(new PropertyValueFactory<>("leadTime"));
        leadTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        leadTimeColumn.setOnEditCommit(e ->
        {
            (e.getTableView().getItems().get(e.getTablePosition().getRow())).setLeadTime(e.getNewValue());
            System.out.println(iM.toString());
        });

        TableColumn<Item, Integer> lotSizingRule = new TableColumn<>("Lot Sizing Rule");
        lotSizingRule.setMinWidth(168.5);
        lotSizingRule.setCellValueFactory(new PropertyValueFactory<>("lotSizingRule"));
        lotSizingRule.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        lotSizingRule.setOnEditCommit(e ->
        {
            (e.getTableView().getItems().get(e.getTablePosition().getRow())).setLotSizingRule(e.getNewValue());
            System.out.println(iM.toString());
        });

        table.setItems(items);
        table.getColumns().addAll(
                idColumn, amountColumn, scheduledColumn, arrivalColumn, leadTimeColumn, lotSizingRule);

        sPane.getChildren().add(table);

        return sPane;
    }

    private void tfTopKeyTyped(KeyEvent event) {
        if(event.getCharacter().charAt(0)<48 || event.getCharacter().charAt(0)>57){
            event.consume();
        }
    }

    private void createTables() {
        Item item = iM.getItem("1605");

        Integer[] demand = new Integer[10];

        for(int i = 0; i < 10; i++) {
            demand[i] = Integer.valueOf(tfTop[1][i+1].getText());
        }

        item.getTable().setRow(0, demand);

        try {
            MrpAlg mrpA = new MrpAlg(iM);

            OutputWin outputWin = new OutputWin(iM.getItems());
            outputWin.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
            iM.clearItemTables();
        }

        iM.clearItemTables();
    }

}

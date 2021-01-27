package GUI;

import ItemOp.Item;
import Utilities.ResourceOp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class OutputWin extends Stage {

    private double width = 1200;
    private double height = 675;

    Scene[] scenes = new Scene[13];
    int sceneNum = 0;

    List<Item> items;

    public OutputWin(List<Item> items) {
        this.items = items;
        paint();
    }

    private void paint() {

        this.initModality(Modality.APPLICATION_MODAL);
        this.getIcons().setAll(ResourceOp.setImage("createTablesIcon.png"));

        this.setWidth(width);
        this.setHeight(height);

        this.setMinWidth(800);
        this.setMinHeight(450);

        for(int i = 0; i < scenes.length; i++) {

            Item item = items.get(i);

            OutputTable outputTable = new OutputTable(width, height);

            outputTable.setItemId(item.getId());
            outputTable.setLeadTime(String.valueOf(item.getLeadTime()));
            outputTable.setLotSizingRule(String.valueOf(item.getLotSizingRule()));

            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 10; k++) {
                    if (item == null) System.out.println("hi");
                    outputTable.setCellValue(j, k, item.getTable().getCellValue(j , k));
                }
            }

            Button btPrevious = new Button("<");
            btPrevious.setOnAction(e -> toPrevious());

            Button btNext = new Button(">");
            btNext.setOnAction(e -> toNext());

            HBox hBox = new HBox(2);
            hBox.setAlignment(Pos.CENTER);

            if (i == 0) {
                hBox.getChildren().addAll(btPrevious, btNext);
                btPrevious.setVisible(false);
            }else if (i == scenes.length -1) {
                hBox.getChildren().addAll(btPrevious, btNext);
                btNext.setVisible(false);
            }else {
                hBox.getChildren().addAll(btPrevious, btNext);
            }

            VBox vBox = new VBox(20);
            vBox.setPadding(new Insets(20));
            vBox.setAlignment(Pos.CENTER);

            vBox.getChildren().addAll(outputTable, hBox);

            scenes[i] = new Scene(vBox, width, height);

            vBox.widthProperty().addListener(e -> {
                outputTable.setW(Math.max(vBox.getWidth(), vBox.getHeight()));
                outputTable.setH(Math.min(vBox.getWidth(), vBox.getHeight()));
                width = vBox.getWidth();
            });
            vBox.heightProperty().addListener(e -> {
                outputTable.setW(Math.max(vBox.getWidth(), vBox.getHeight()));
                outputTable.setH(Math.min(vBox.getWidth(), vBox.getHeight()));
                height = vBox.getHeight();
            });
        }

        this.setScene(scenes[0]);
        this.setTitle("MRP Tables");
    }

    private void toPrevious() {
        this.setScene(scenes[--sceneNum]);
    }

    private void toNext() {
        this.setScene(scenes[++sceneNum]);
    }

}
package GUI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class OutputTable extends Pane {

    private double w , h, used_w, used_h, x_point1, y_point1, x_point2, y_point2, x_point3, y_point3,
            x_point4, y_point4, x_point5, y_point5, x_point6, y_point6, x_point7, y_point7,
            x_point8, y_point8, x_point9, y_point9, x_point10, y_point10, x_point11, y_point11,
            x_point12, y_point12, x_point13, y_point13, x_point14, y_point14, txtSize;

    private String[] defText = {"Gross Requirement", "Scheduled Receipt", "On Hand From Prior Period",
            "Net Requirement", "Time-Phased Net Requirement", "Planned Order Releases", "Planned Order Delivery"};

    private Text itemId;
    private Text leadTime;
    private Text lotSizingRule;

    private String str_itemId = "unk";
    private String str_leadTime = "unk";
    private String str_lotSizingRule = "unk";

    private Text[][] cell = new Text[7][10];
    private String[][] str_Cell = new String[7][10];

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
        paint();
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
        paint();
    }

    public OutputTable(double w, double h) {
        setW(w);
        setH(h);
        paint();
    }

    public void setItemId(String itemId) {
        this.str_itemId = "Item \n" + itemId;
        paint();
    }

    public void setLeadTime(String leadTime) {
        this.str_leadTime = "Lead Time \n" + leadTime;
        paint();
    }

    public void setLotSizingRule(String lotSizingRule) {
        this.str_lotSizingRule = "Q \n" + lotSizingRule;
        paint();
    }

    public void setCellValue(int row, int column, int number) {

        if (row != 2 && number == 0) {
            str_Cell[row][column] = " ";
            paint();
        }else {
            str_Cell[row][column] = String.valueOf(number);
            paint();
        }
    }

    private void setPoints() {

        used_w = (7 * w / 8);
        used_h = (7 * h / 9);

        x_point1 = (w / 16);
        y_point1 = (h / 9);

        x_point2 = (7 * w / 8);
        y_point2 = (h / 9);

        x_point3 = (w / 16);
        y_point3 = (7 * h / 9);

        x_point4 = (w / 16) + (3 * used_w / 8);
        y_point4 = (h / 9);

        x_point5 = (w / 16);
        y_point5 = (h / 9) + (used_h / 9);

        x_point6 = (w / 16) + (used_w / 8);
        y_point6 = (h / 9) + (used_h / 9);

        x_point7 = (w / 16) + (3 * used_w / 8);
        y_point7 = (h / 9) + (used_h / 9);

        x_point8 = (w / 16) + (used_w / 16);
        y_point8 = (h / 9) + (2 * used_h / 27);

        x_point9 = (w / 16) + (25 * used_w / 64);
        y_point9 = (h / 9) + (2 * used_h / 27);

        x_point10 = (w / 16) + (used_w / 8) + (2 *used_w / 96);
        y_point10 = (h / 9) + (used_h / 9) + (5 * used_h / 63);

        x_point11 = (w / 16) + (used_w / 64);
        y_point11 = (h / 9) + (used_h / 9) + (8 * used_h / 63);

        x_point12 = (w / 16) + (used_w / 64);
        y_point12 = (h / 9) + (used_h / 9) + (24 * used_h / 63);

        x_point13 = (w / 16) + (used_w / 64);
        y_point13 = (h / 9) + (used_h / 9) + (40 * used_h / 63);

        x_point14 = (w / 16) + (3 * used_w / 8) + (used_w / 64);
        y_point14 = (h / 9) + (used_h / 9) + (4 * used_h / 63);

        txtSize = Math.max(w, h) / 90;
    }

    private void setRectangles() {
        Rectangle outLine = new Rectangle(x_point1, y_point1, used_w, used_h);
        outLine.setStroke(Color.BLACK);
        outLine.setStrokeWidth(2);
        outLine.setFill(Color.TRANSPARENT);
        this.getChildren().add(outLine);

        Rectangle rec1 = new Rectangle(x_point1, y_point1,3 * used_w / 8,used_h / 9);
        rec1.setStroke(Color.BLACK);
        rec1.setFill(Color.rgb(90,130,180));
        this.getChildren().add(rec1);

        for(int i = 0; i < 10; i++) {
            double width = used_w / 16;
            double height = used_h / 9;
            double x = x_point4 + i * width;
            double y = y_point4;

            Rectangle rec = new Rectangle(x, y, width, height);
            rec.setStroke(Color.BLACK);
            rec.setFill(Color.rgb(90,130,180));
            this.getChildren().add(rec);
        }

        Rectangle rec2 = new Rectangle(x_point5, y_point5,used_w / 8,8 * used_h / 9);
        rec2.setStroke(Color.BLACK);
        rec2.setFill(Color.rgb(90,130,180));
        this.getChildren().add(rec2);

        for(int j = 0; j < 7; j++) {
            double width = used_w / 4;
            double height = 8 * used_h / 63;
            double x = x_point6;
            double y = y_point6 + j * height;

            Rectangle rec = new Rectangle(x, y, width, height);
            rec.setStroke(Color.BLACK);
            rec.setFill((j % 2 == 0) ?Color.rgb(130,170,210): Color.WHITE);
            this.getChildren().add(rec);
        }

        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 10; j++){
                double width = used_w / 16;
                double height = 8 * used_h / 63;
                double x = x_point7 + j * width;
                double y = y_point7 + i * height;

                Rectangle rec = new Rectangle(x, y, width, height);
                rec.setStroke(Color.BLACK);
                rec.setFill((i % 2 == 0) ?Color.rgb(120,160,200): Color.WHITE);
                this.getChildren().add(rec);
            }
        }
    }

    private void setText() {
        Text txt1 = new Text(x_point8, y_point8, "Period");
        txt1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, txtSize));
        txt1.setFill(Color.WHITE);
        this.getChildren().add(txt1);

        for(int i = 0; i < 10; i++) {
            double space = used_w / 16;
            double x = x_point9 + i * space;
            double y = y_point9;

            Text txt = new Text(x, y, String.valueOf(i + 1));
            txt.setFill(Color.WHITE);
            txt.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, txtSize));
            this.getChildren().add(txt);
        }

        for(int j = 0; j < 7; j++) {
            double space = 8 * used_h / 63;
            double x = x_point10;
            double y = y_point10 + j * space;

            Text txt = new Text(x, y, defText[j]);
            txt.setFont(Font.font("Times New Roman", FontWeight.BOLD, txtSize));
            this.getChildren().add(txt);
        }

        itemId = new Text(x_point11, y_point11, str_itemId);
        itemId.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, txtSize));
        itemId.setFill(Color.WHITE);
        leadTime = new Text(x_point12, y_point12, str_leadTime);
        leadTime.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, txtSize));
        leadTime.setFill(Color.WHITE);
        lotSizingRule = new Text(x_point13, y_point13, str_lotSizingRule);
        lotSizingRule.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, txtSize));
        lotSizingRule.setFill(Color.WHITE);

        this.getChildren().addAll(itemId, leadTime, lotSizingRule);

        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 10; j++) {
                double x_space = used_w / 16;
                double y_space = 8 * used_h / 63;
                double x = x_point14 + j * x_space;
                double y = y_point14 + i * y_space;

                Text txt = new Text(x, y, str_Cell[i][j]);
                txt.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, txtSize));
                cell[i][j] = txt;
                this.getChildren().addAll(cell[i][j]);
            }
        }
    }

    private void paint() {
        this.getChildren().clear();
        setPoints();
        setRectangles();
        setText();
    }

}
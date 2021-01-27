package Algorithm;

import ItemOp.Item;
import ItemOp.ItemManager;
import ItemOp.ItemSpec;
import ItemOp.ItemTable;

public class MrpAlg {

    private ItemManager iM;
    private ItemTree tree;

    public MrpAlg(ItemManager iM) throws Exception{
        this.iM = iM;
        initialize();
        traverse();
    }

    public ItemManager getItemManager() {
        return iM;
    }

    private void throwException(String message) throws Exception{
        throw new Exception(message);
    }

    private void initialize() throws Exception{
        setAmountOnHand();
        setScheduledReceipt();
    }

    private void setAmountOnHand() {
        for (Item item: iM.getItems()) {
            try {
                item.getTable().setCellValue(2, 0, item.getAmount());
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("Item : " + item);
            }
        }
    }

    private void setScheduledReceipt() throws Exception {
        for (Item item: iM.getItems()) {
            if (item.getScheduled() == 0) {
                continue;
            }else {
                try {
                    item.getTable().setCellValue(1, item.getArrival() - 1, item.getScheduled());
                } catch (Exception e) {
                    throwException("Arrival Time must be an integer between [1,10]" +
                            "\nItem : " + item.getId() + "\nArrival Time : " + item.getArrival());
                }
            }
        }
    }

    private void setGrossReq(TreeNode node) throws Exception {
        if (node.parent == null) {
            setNetReq(node.item);
        }else {
            ItemTable currentTable = node.item.getTable();
            ItemTable parentTable = node.parent.getTable();

            Integer[] plOrderRls =  parentTable.getRow(5).clone();

            for (int i = 0; i < 10; i++) {
                plOrderRls[i] *= node.required;
            }

            currentTable.addToRow(0, plOrderRls);

            if ( ( node.item.getItemSpec().equals(new ItemSpec("062")) && node.level <= 1 )
                    || ( node.item.getItemSpec().equals(new ItemSpec("14127")) && node.level <= 1 )) {
                // Pass
            } else {
                setNetReq(node.item);
            }
        }
    }

    private void setNetReq(Item item) throws Exception {

        for (int p = 0; p < 10; p++) {

            if (item.getTable().getCellValue(0, p) == 0) {

                if (p == 9) continue;
                int total = item.getTable().getCellValue(1, p) + item.getTable().getCellValue(2, p);
                item.getTable().setCellValue(2, p + 1, total);

            }else {

                int total = item.getTable().getCellValue(1, p) + item.getTable().getCellValue(2, p);
                int netReq = total - item.getTable().getCellValue(0, p);

                if (netReq >= 0 && p != 9) {
                    item.getTable().setCellValue(2, p + 1, Math.abs(netReq));
                    continue;
                }

                try {
                    item.getTable().setCellValue(3, p, Math.abs(netReq));
                    item.getTable().setCellValue(4,p - item.getLeadTime(), Math.abs(netReq));
                } catch (Exception e) {
                    throwException("This request cannot be fulfilled." +
                            "\nLead Time exceeds period." + "\nItem : " + item.getId() +
                            "\nOrder given at period : " + p +
                            "\nLead Time : " + item.getLeadTime() +
                            "\nOrder will placed in " + (p - item.getLeadTime())
                    );
                }

                clcPlDelivery(item, Math.abs(netReq), p);

                if (p == 9) continue;
                item.getTable().setCellValue(2, p + 1,
                        item.getTable().getCellValue(6, p) - Math.abs(netReq));

            }
        }
    }

    private void clcPlDelivery(Item item, int netReq, int p) throws Exception {

        for (int i = 1; true; i++) {

            int plOrder = i * item.getLotSizingRule();

            if (plOrder >= netReq) {
                try {
                    item.getTable().setCellValue(5, p - item.getLeadTime(), plOrder);
                    item.getTable().setCellValue(6, p, plOrder);
                } catch (Exception e) {
                    throwException("This request cannot be fulfilled." +
                            "\nLead Time exceeds period." + "\nItem : " + item.getId() +
                            "\nOrder given at period : " + p +
                            "\nLead Time : " + item.getLeadTime() +
                            "\nOrder will placed in " + (p - item.getLeadTime())
                    );
                }
                break;
            }
        }

    }

    private void traverse() throws Exception{
        tree = new ItemTree(iM);

        for (TreeNode treeNode : tree) {
            setGrossReq(treeNode);
        }
    }

}
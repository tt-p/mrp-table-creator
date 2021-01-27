package Algorithm;

import ItemOp.ItemManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemTree implements Iterable<TreeNode> {

    private ItemManager iM;
    TreeNode root;

    public ItemTree(ItemManager iM) {
        this.iM = iM;
        initializeTree();
    }

    private void initializeTree() {

        TreeNode tN1 = new TreeNode(iM.getItem("14127"), null, 6,2);
        TreeNode tN2 = new TreeNode(iM.getItem("019"),null, 1,2);
        TreeNode tN3 = new TreeNode(iM.getItem("2142"),null, 1,2);
        TreeNode[] childes1 = new TreeNode[6];
        childes1[0] = tN1;
        childes1[1] = tN2;
        childes1[2] = tN3;

        TreeNode tN4 = new TreeNode(iM.getItem("314"), childes1,1,1);

        tN1.parent = tN4.item;
        tN2.parent = tN4.item;
        tN3.parent = tN4.item;

        TreeNode tN5 = new TreeNode(iM.getItem("14127"),null,4,1);
        TreeNode tN6 = new TreeNode(iM.getItem("062"),null,4,1);
        TreeNode tN7 = new TreeNode(iM.getItem("118"),null,1,1);
        TreeNode tN8 = new TreeNode(iM.getItem("048"),null,1,1);

        TreeNode tN9 = new TreeNode(iM.getItem("1118"),null,1,3);
        TreeNode tN10 = new TreeNode(iM.getItem("129"),null,1,3);
        TreeNode[] childes2 = new TreeNode[6];
        childes2[0] = tN9;
        childes2[1] = tN10;

        TreeNode tN11 = new TreeNode(iM.getItem("11495"), childes2,1,2);

        tN9.parent = tN11.item;
        tN10.parent = tN11.item;

        TreeNode tN12 = new TreeNode(iM.getItem("062"),null,2,2);
        TreeNode tN13 = new TreeNode(iM.getItem("457"),null,1,2);
        TreeNode[] childes3 = new TreeNode[6];
        childes3[0] = tN11;
        childes3[1] = tN12;
        childes3[2] = tN13;

        TreeNode tN14 = new TreeNode(iM.getItem("13122"), childes3,1,1);

        tN11.parent = tN14.item;
        tN12.parent = tN14.item;
        tN13.parent = tN14.item;

        TreeNode[] childes4 = new TreeNode[6];
        childes4[0] = tN4;
        childes4[1] = tN5;
        childes4[2] = tN6;
        childes4[3] = tN7;
        childes4[4] = tN8;
        childes4[5] = tN14;

        TreeNode tN15 = new TreeNode(iM.getItem("1605"), childes4, 1,0);

        tN4.parent = tN15.item;
        tN5.parent = tN15.item;
        tN6.parent = tN15.item;
        tN7.parent = tN15.item;
        tN8.parent = tN15.item;
        tN14.parent = tN15.item;

        root = tN15;
    }

    private TreeNode getNode(String id, int level) {
        return getNode(root, id, level);
    }

    private TreeNode getNode(TreeNode tN, String id, int level) {
        TreeNode[] results = new TreeNode[6];

        if (tN == null) {
            return null;
        } else if (tN.item.getId().equals(id) && level == tN.level) {
            return tN;
        }else {
            if (tN.children != null) {
                for (int i = 0; i < 6; i++) {
                    results[i] = getNode(tN.children[i], id, level);
                }
            }
            for (int i = 0; i < 6; i++) {
                if(results[i] != null) {
                    return results[i];
                }
            }
        }
        return null;
    }

    @Override
    public Iterator<TreeNode> iterator() {
        return new LevelIterator();
    }

    private class LevelIterator implements Iterator<TreeNode> {

        int current = 0;

        private List<TreeNode> iterate() {
            List<TreeNode> list = new ArrayList();
            list.add(getNode("1605", 0));
            list.add(getNode("13122", 1));
            list.add(getNode("048", 1));
            list.add(getNode("118", 1));
            list.add(getNode("062", 1));
            list.add(getNode("14127", 1));
            list.add(getNode("314", 1));
            list.add(getNode("457", 2));
            list.add(getNode("062", 2));
            list.add(getNode("11495", 2));
            list.add(getNode("2142", 2));
            list.add(getNode("019", 2));
            list.add(getNode("14127", 2));
            list.add(getNode("129", 3));
            list.add(getNode("1118", 3));
            return list;
        }

        @Override
        public boolean hasNext() {
            return (current < 15);
        }

        @Override
        public TreeNode next() {
            return iterate().get(current++);
        }
    }

    public void check() {
        System.out.println();

        System.out.println(root.item);

        System.out.println();

        System.out.println(root.children[0].item);
        System.out.println(root.children[1].item);
        System.out.println(root.children[2].item);
        System.out.println(root.children[3].item);
        System.out.println(root.children[4].item);
        System.out.println(root.children[5].item);

        System.out.println();

        System.out.println(root.children[0].children[0].item);
        System.out.println(root.children[0].children[1].item);
        System.out.println(root.children[0].children[2].item);

        System.out.println();

        System.out.println(root.children[5].children[0].item);
        System.out.println(root.children[5].children[1].item);
        System.out.println(root.children[5].children[2].item);

        System.out.println();

        System.out.println(root.children[5].children[0].children[0].item);
        System.out.println(root.children[5].children[0].children[1].item);
    }
}
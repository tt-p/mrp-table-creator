package Algorithm;

import ItemOp.Item;

public class TreeNode {

    Item item;
    Item parent;
    int required;
    int level;

    TreeNode[] children;

    public TreeNode(Item item, TreeNode[] children, int required, int level) {
        this.item = item;
        this.children = children;
        this.required = required;
        this.level = level;
    }

}

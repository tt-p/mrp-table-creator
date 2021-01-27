package ItemOp;

import java.util.LinkedList;
import java.util.List;

public class ItemManager {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public ItemManager() {
        items = new LinkedList<>();

        Item item0 = new Item("1605", 30, 0, 0, 1, 1);
        Item item1 = new Item("13122", 0, 70, 3, 1, 40);
        Item item2 = new Item("048", 30, 0, 0, 3, 30);
        Item item3 = new Item("118", 0, 50, 2, 2, 1);
        Item item4 = new Item("062", 50, 100, 6, 2, 1);
        Item item5 = new Item("14127", 60, 0, 0, 1, 100);
        Item item6 = new Item("314", 0, 50, 5, 1, 50);
        Item item7 = new Item("457", 0, 20, 2, 2, 1);
        Item item8 = new Item("11495", 120, 0, 0, 1, 50);
        Item item9 = new Item("129", 0, 100, 8, 4, 40);
        Item item10 = new Item("1118", 30, 0, 0, 3, 1);
        Item item11 = new Item("2142", 80, 0, 0, 2, 100);
        Item item12 = new Item("019", 50, 40, 5, 2, 50);

        items.add(item0);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);
        items.add(item9);
        items.add(item10);
        items.add(item11);
        items.add(item12);
    }

    public Item getItem(String id) {
        ItemSpec itemSpec = new ItemSpec(id);
        for(Item item: items) {
            if (item.getItemSpec().equals(itemSpec)) {
                return item;
            }
        }
        return null;
    }

    public void clearItemTables() {
        for (Item item: items) {
            item.getTable().clearTable();
        }
    }

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        sB.append("[ ");
        for (Item item: items) {
            sB.append(item.toString() + " ");
        }
        sB.append("]");
        return sB.toString();
    }
}

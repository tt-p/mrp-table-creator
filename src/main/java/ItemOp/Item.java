package ItemOp;

public class Item {

    private ItemSpec itemSpec;
    private Integer amount;
    private Integer scheduled;
    private Integer arrival;
    private Integer leadTime;
    private Integer lotSizingRule;
    private ItemTable table;

    public ItemSpec getItemSpec() {
        return this.itemSpec;
    }

    public String getId() {
        return itemSpec.getId();
    }

    public void setId(String id) {
        this.itemSpec.setId(id);
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getScheduled() {
        return scheduled;
    }

    public void setScheduled(Integer scheduled) {
        this.scheduled = scheduled;
    }

    public Integer getArrival() {
        return arrival;
    }

    public void setArrival(Integer arrival) {
        this.arrival = arrival;
    }

    public Integer getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }

    public Integer getLotSizingRule() {
        return lotSizingRule;
    }

    public void setLotSizingRule(Integer lotSizingRule) {
        this.lotSizingRule = lotSizingRule;
    }

    public ItemTable getTable() { return table; }

    public Item() {
        this("",0,0,0,0,0);
    }

    public Item(String id, Integer amount, Integer scheduled, Integer arrival, Integer leadTime, Integer lotSizingRule) {
        ItemSpec itemSpec = new ItemSpec(id);
        this.itemSpec = itemSpec;
        this.amount = amount;
        this.scheduled = scheduled;
        this.arrival = arrival;
        this.leadTime = leadTime;
        this.lotSizingRule = lotSizingRule;
        this.table = new ItemTable();
    }

    @Override
    public String toString() {
        return getId() + "/" + getAmount() + "/" + getScheduled() + "/" + getArrival()
                + "/" + getLeadTime() + "/" + getLotSizingRule();
    }
}